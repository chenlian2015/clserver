package com.cnd.greencube.web.admin.controller;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.service.OpportunitySpaceService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 工作空间管理
 * 
 * @author dongyali
 *
 */
@Controller("OpportunitySpaceController")
@RequestMapping("/admin/opportunity_space")
public class OpportunitySpaceController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 业务类别表（可编辑）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_list")
	public String business_category_list() {
		String jsonCategory = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((OpportunitySpaceService.Client) ps).loadOpportunitySpaceCategorys();
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

		setParameter("category", ja);

		return "/admin/opportunity_space/category_list";
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
				result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((OpportunitySpaceService.Client) category).createOpportunitySpaceCategory(null,
								catagoryString);
					}
				});
			}
			// 修改result
			else {
				result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((OpportunitySpaceService.Client) category)
								.updateOpportunitySpaceCategory(catagoryString);
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

				getSubCategoryArray(parentId, subCategory);
			}
		}

		return Message.okMessage();
	}

	// 子菜单的新增和修改
	void getSubCategoryArray(final String parentId, JSONArray subCategory) {
		for (int i = 0; i < subCategory.size(); i++) {
			JSONObject subCategorySon = subCategory.getJSONObject(i);
			final String catagoryString = subCategorySon.toJSONString();
			String id = subCategorySon.getString("id");
			String result = "";
			// 新增
			if (StringUtils.isEmpty(id)) {
				result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((OpportunitySpaceService.Client) category).createOpportunitySpaceCategory(parentId,
								catagoryString);
					}
				});
			}
			// 修改
			else {
				result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((OpportunitySpaceService.Client) category)
								.updateOpportunitySpaceCategory(catagoryString);
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
				getSubCategoryArray(id2, subCategoryElement);
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
			String jsonCategory = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((OpportunitySpaceService.Client) ps).loadOpportunitySpaceCategorys();
				}
			});
			JSONObject jo = JsonUtils.String2JSONObject(jsonCategory);
			JSONArray categoryArray = jo.getJSONArray("data");
			// JSONArray categoryArray =
			// JsonUtils.String2JSONArray(jsonCategory);
			for (int i = 0; i < categoryArray.size(); i++) {
				JSONObject catagoryObject = categoryArray.getJSONObject(i);
				String classCodeCurrent = catagoryObject.getString("classCode");
				final String idCurrent = catagoryObject.getString("id");
				if (classCodeCurrent.contains(classCode)) {
					result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
						@Override
						public String action(TServiceClient category) throws Exception {
							return ((OpportunitySpaceService.Client) category)
									.deleteOpportunitySpaceCategory(idCurrent);
						}
					});
				}
				JSONArray subCategory = catagoryObject.getJSONArray("subCategory");
				if (subCategory != null && subCategory.size() != 0) {
					deleteSon(classCode, subCategory);
				}
			}
		} else {
			result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
				@Override
				public String action(TServiceClient category) throws Exception {
					return ((OpportunitySpaceService.Client) category).deleteOpportunitySpaceCategory(categoryId);
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
				result = thriftTemplate.execute("OpportunitySpaceService", new ThriftAction() {
					@Override
					public String action(TServiceClient category) throws Exception {
						return ((OpportunitySpaceService.Client) category).deleteOpportunitySpaceCategory(idCurrent);
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
					JSONArray subCategory = jo.getJSONArray("subCategory");
					makeCategoryTree(sb, subCategory);
				}
				sb.append("</li>");
				sb.append("</ul>");
			}

		}

	}
}
