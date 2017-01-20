package com.cnd.greencube.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.service.HealthyCategoryService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 经营类别管理
 * 
 * @author dongyali
 *
 */
@Controller("BusinessCategoryController")
@RequestMapping("/admin/business_category")
public class BusinessCategoryController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 业务类别表（可编辑）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/business_category_list")
	public String business_category_list() {
		String jsonCategory = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((HealthyCategoryService.Client) ps).getHealthyCategories();
			}
		});
		JSONObject jo = JsonUtils.String2JSONObject(jsonCategory);
		JSONArray ja = jo.getJSONArray("data");
		// JSONArray ja = JsonUtils.String2JSONArray(jsonCategory);
		// JSONArray convertedJsonArray = makeChannelStructure(ja);
		// 迭代遍历
		StringBuffer sb = new StringBuffer();
		makeCategoryTree(sb, ja);

		setParameter("treeHtml", sb.toString());

		setParameter("channels", ja);

		return "/admin/business_category/business_category_list";
	}

	@RequestMapping(value = "/category_list")
	public String category_list() {
		String jsonCategory = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((HealthyCategoryService.Client) ps).getHealthyCategories();
			}
		});
		List<Object> clist = JsonUtils.String2JSONArray(jsonCategory);
		setParameter("clist", clist);

		return "/admin/business_category/category_list";
	}

	@RequestMapping(value = "/category_edit")
	public @ResponseBody String category_edit() {
		final String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {// 修改
			thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					HealthyCategoryService.Client client = (HealthyCategoryService.Client) ps;
					String categoryJson = client.getHealthyCategory(id);
					CodeHealthyCategory category = Message.unpackObject(categoryJson, CodeHealthyCategory.class);
					registerProperty("category", category);
					client.updateHealthyCategory(JsonUtils.bean2String(category));
					return client.getHealthyCategory(id);
				}
			});
		} else {// 新增
			thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					HealthyCategoryService.Client client = (HealthyCategoryService.Client) ps;
					registerProperty("category", CodeHealthyCategory.class);
					CodeHealthyCategory category = (CodeHealthyCategory) getParameter("category");
					String parentId = (String) getParameter("parentId");
					client.createHealthyCategory(parentId, JsonUtils.bean2String(category));
					return client.getHealthyCategory(id);
				}
			});
		}
		return Message.okMessage();
	}

	/**
	 * 新增或修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_update")
	public @ResponseBody String category_update() {
		// 读取提交的json字符串
		final String outline = (String) getParameter("outline");
		JSONArray categoryArray = JsonUtils.String2JSONArray(outline);
		for (int i = 0; i < categoryArray.size(); i++) {
			JSONObject catagoryObject = categoryArray.getJSONObject(i);
			final String catagoryString = catagoryObject.toJSONString();
			String id = catagoryObject.getString("id");
			// 新增
			String result = "";
			if (StringUtils.isEmpty(id)) {
				result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((HealthyCategoryService.Client) category).createHealthyCategory(null, catagoryString);
					}
				});
			}
			// 修改result
			else {
				result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((HealthyCategoryService.Client) category).updateHealthyCategory(catagoryString);
					}
				});
			}
			JSONArray subCategory = catagoryObject.getJSONArray("subCategory");
			if (subCategory != null && subCategory.size() != 0) {
				JSONObject ja = JsonUtils.String2JSONObject(result);
				JSONObject data = ja.getJSONObject("data");
				String parentId = "";
				if (data != null) {
					parentId = data.getString("id");
				} else {
					parentId = id;
				}

				getsubCategoryArray(parentId, subCategory);
			}
		}

		return Message.okMessage();
	}

	// 子菜单的新增和修改
	void getsubCategoryArray(final String parentId, JSONArray subCategory) {
		for (int i = 0; i < subCategory.size(); i++) {
			JSONObject subCategorySon = subCategory.getJSONObject(i);
			final String catagoryString = subCategorySon.toJSONString();
			String id = subCategorySon.getString("id");
			String result = "";
			// 新增
			if (StringUtils.isEmpty(id)) {
				result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((HealthyCategoryService.Client) category).createHealthyCategory(parentId,
								catagoryString);
					}
				});
			}
			// 修改
			else {
				result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((HealthyCategoryService.Client) category).updateHealthyCategory(catagoryString);
					}
				});
			}
			// 展开多层数据
			JSONArray subCategoryElement = subCategorySon.getJSONArray("subCategory");
			if (subCategoryElement != null && subCategoryElement.size() != 0) {
				JSONObject ja = JsonUtils.String2JSONObject(result);
				JSONObject data = ja.getJSONObject("data");
				String id2 = "";
				if (data != null) {
					id2 = data.getString("id");
				} else {
					id2 = id;
				}
				getsubCategoryArray(id2, subCategoryElement);
			}
		}
	}

	// 删除类别
	@RequestMapping(value = "/category_delete")
	public @ResponseBody String category_delete() {
		final String categoryId = (String) getParameter("id");
		final String classCode = (String) getParameter("classCode");
		String result = null;
		// 如果当前classcode不为空，则删除当前节点和所有子节点
		if (!StringUtils.isEmpty(classCode)) {
			String jsonCategory = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((HealthyCategoryService.Client) ps).getHealthyCategories();
				}
			});
			JSONObject jo = JsonUtils.String2JSONObject(jsonCategory);
			JSONArray categoryArray = jo.getJSONArray("data");
			for (int i = 0; i < categoryArray.size(); i++) {
				JSONObject catagoryObject = categoryArray.getJSONObject(i);
				String classCodeCurrent = catagoryObject.getString("classCode");
				final String idCurrent = catagoryObject.getString("id");
				if (classCodeCurrent.contains(classCode)) {
					result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
						@Override
						public String action(TServiceClient category) throws Exception {
							return ((HealthyCategoryService.Client) category).deleteCategory(idCurrent);
						}
					});
				}
				JSONArray subCategory = catagoryObject.getJSONArray("subCategory");
				if (subCategory != null && subCategory.size() != 0) {
					deleteSon(classCode, subCategory);
				}
			}
		} else {
			result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
				@Override
				public String action(TServiceClient category) throws Exception {
					return ((HealthyCategoryService.Client) category).deleteCategory(categoryId);
				}
			});
		}

		return result;
	}

	// 删除子菜单
	String deleteSon(String classCode, JSONArray subCategory) {
		String result = null;
		for (int i = 0; i < subCategory.size(); i++) {
			JSONObject catagoryObject = subCategory.getJSONObject(i);
			final String idCurrent = catagoryObject.getString("id");
			String classCodeCurrent = catagoryObject.getString("classCode");
			if (classCodeCurrent.contains(classCode)) {
				result = thriftTemplate.execute("HealthyCategoryService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((HealthyCategoryService.Client) category).deleteCategory(idCurrent);
					}
				});
			}
			JSONArray subCategoryElement = catagoryObject.getJSONArray("subCategory");
			if (subCategoryElement != null && subCategoryElement.size() != 0) {
				deleteSon(classCode, subCategoryElement);
			}
		}
		return result;
	}

	JSONArray makeCategoryStructure(JSONArray ja) {
		// 遍历并做转换
		Map<String, JSONObject> maps = new HashMap<String, JSONObject>();
		JSONArray convertedChannels = new JSONArray();
		int size = ja.size();
		for (int i = 0; i < size; i++) {
			JSONObject channel = ja.getJSONObject(i);
			String classCode = channel.getString("classCode");
			if (StringUtils.isEmpty(classCode) || !classCode.contains(".")) {
				// 根节点
				convertedChannels.add(channel);
			} else {
				String[] p = classCode.split("\\.");
				String parentId = p[p.length - 2];
				JSONObject parent = maps.get(parentId);

				JSONArray subCategory = null;
				if (parent.containsKey("subCategory")) {
					subCategory = parent.getJSONArray("subCategory");
				} else {
					subCategory = new JSONArray();
					parent.put("subCategory", subCategory);
				}

				if (subCategory != null) {
					// 将当前节点添加至根上
					subCategory.add(channel);
				}
			}
			maps.put(channel.getString("id"), channel);
		}
		return convertedChannels;
	}

	void makeCategoryTree(StringBuffer sb, JSONArray ja) {
		if (null != ja && ja.size() > 0) {
			int size = ja.size();
			for (int i = 0; i < size; i++) {
				JSONObject jo = ja.getJSONObject(i);
				sb.append("<ul>");
				sb.append("<li class=\"branch\" id='node_" + jo.get("id") + "'>");
				sb.append("名称：<input type=\"text\" value=\"" + jo.getString("name")
						+ "\" data-role=\"name\"><input type='hidden' data-role='id' value='" + jo.getString("id")
						+ "'><input type='hidden' data-role='classCode' value='" + jo.getString("classCode")
						+ "'><span style='margin-left:10px;'>排序：<input type='text' style='width:40px;' data-role='order' value='"
						+ jo.getString("order")
						+ "' ></span> <div style=\"display: inline-block;margin-left:20px;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"addChildRoot('"
						+ jo.getString("id")
						+ "');\">增加</div> | <div style=\"display: inline-block;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"removeNode('"
						+ jo.getString("id") + "','" + jo.getString("classCode")
						+ "', this);\">删除</div>  <div style=\"display: inline-block;cursor:pointer;color:blue;\" class=\"add_chapter\" style='z-index:99999' onclick=\"moveLeft(this, '"
						+ jo.getString("id") + "');\"></div>");

				if (jo.containsKey("subCategory")) {
					JSONArray subCategoryChannels = jo.getJSONArray("subCategory");
					makeCategoryTree(sb, subCategoryChannels);
				}
				sb.append("</li>");
				sb.append("</ul>");
			}

		}

	}

}
