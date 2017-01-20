package com.cnd.greencube.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.CmsArticle;
import com.cnd.greencube.server.entity.CmsChannel;
import com.cnd.greencube.server.service.CmsService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 信息发布管理
 * 
 * @author TaoJijia
 */
@Controller("CMSManagerController")
@RequestMapping("/admin/cms")
public class CMSManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 得栏目信息列表
	 */
	@RequestMapping(value = "/channel_list")
	public String channel_list() throws Exception {
		// final int start = getParameter("start") == null ? 1 :
		// getParameterInt("start");
		String result = thriftTemplate.execute("CmsService", new ThriftAction() {
			@Override
			public String action(TServiceClient cms) throws Exception {
				return ((CmsService.Client) cms).loadChannels();
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(result);
		JSONArray ja = jo.getJSONArray("data");
		// JSONArray ja = JsonUtils.String2JSONArray(result);

		// JSONArray convertedJsonArray = makeChannelStructure(ja);

		// 迭代遍历
		StringBuffer sb = new StringBuffer();
		makeChannelTree(sb, ja);

		setParameter("treeHtml", sb.toString());

		setParameter("channels", ja);
		return "/admin/cms/channel_list";
	}

	JSONArray makeChannelStructure(JSONArray ja) {
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

				JSONArray sub = null;
				if (parent != null) {
					if (parent.containsKey("sub")) {
						sub = parent.getJSONArray("sub");
					} else {
						sub = new JSONArray();
						parent.put("sub", sub);
					}

					if (sub != null) {
						// 将当前节点添加至根上
						sub.add(channel);
					}
				}

			}
			maps.put(channel.getString("id"), channel);
		}
		return convertedChannels;
	}

	void makeChannelTree(StringBuffer sb, JSONArray ja) {
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

				if (jo.containsKey("sub")) {
					JSONArray subchannelChannels = jo.getJSONArray("sub");
					makeChannelTree(sb, subchannelChannels);
				}
				sb.append("</li>");
				sb.append("</ul>");
			}

		}
	}

	/**
	 * save/update栏目信息
	 */
	@RequestMapping(value = "/channel_update")
	public @ResponseBody String channel_update() {
		// 读取提交的json字符串
		final String outline = (String) getParameter("outline");
		try {
			return thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient cms) throws Exception {
					return ((CmsService.Client) cms).updateChannel(outline);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}

	/**
	 * 删除栏目
	 * 
	 * @return
	 */
	@RequestMapping(value = "/channel_delete")
	public @ResponseBody String channel_delete() {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");
		final String channelId = (String) getParameter("id");
		final String classCode = (String) getParameter("classCode");
		String result = null;
		// 如果当前classcode不为空，则删除当前节点和所有子节点
		if (!StringUtils.isEmpty(classCode)) {
			String jsonchannel = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((CmsService.Client) ps).loadChannelsForPagelit(start);
				}
			});

			JSONObject jo = JsonUtils.String2JSONObject(jsonchannel);
			JSONArray ja = jo.getJSONArray("data");

			for (int i = 0; i < ja.size(); i++) {
				JSONObject channelObject = ja.getJSONObject(i);
				String classCodeCurrent = channelObject.getString("classCode");
				final String idCurrent = channelObject.getString("id");
				if (classCodeCurrent.contains(classCode)) {
					result = thriftTemplate.execute("CmsService", new ThriftAction() {
						@Override
						public String action(TServiceClient channel) throws Exception {
							return ((CmsService.Client) channel).deleteChannel(idCurrent);
						}
					});
				}
				JSONArray subchannel = channelObject.getJSONArray("sub");
				if (subchannel != null && subchannel.size() != 0) {
					deleteSon(classCode, subchannel);
				}
			}
		} else {
			result = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient channel) throws Exception {
					return ((CmsService.Client) channel).deleteChannel(channelId);
				}
			});
		}

		return result;
	}

	// 删除子菜单
	String deleteSon(String classCode, JSONArray subchannel) {
		String result = null;
		for (int i = 0; i < subchannel.size(); i++) {
			JSONObject channelObject = subchannel.getJSONObject(i);
			String classCodeCurrent = channelObject.getString("classCode");
			final String idCurrent = channelObject.getString("id");
			if (classCodeCurrent.contains(classCode)) {
				result = thriftTemplate.execute("CmsService", new ThriftAction() {
					@Override
					public String action(TServiceClient channel) throws Exception {
						return ((CmsService.Client) channel).deleteChannel(idCurrent);
					}
				});
			}
			JSONArray subchannelElement = channelObject.getJSONArray("sub");
			if (subchannelElement != null && subchannelElement.size() != 0) {
				deleteSon(classCode, subchannelElement);
			}
		} 
		return result;
	}

	//
	/**
	 * 咨询列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/article_list")
	public String article_list() throws Exception {
		// 读取全部栏目信息
		String strResult = thriftTemplate.execute("CmsService", new ThriftAction() {
			@Override
			public String action(TServiceClient cms) throws Exception {
				return ((CmsService.Client) cms).loadChannelsForPagelit(-1);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(strResult);
		JSONArray ja = jo.getJSONArray("data");
		JSONArray convertedJsonArray = makeChannelStructure(ja);
		StringBuffer sb = new StringBuffer();

		// sb.append("<ul class=\"tree\" id=\"tree_1\">");
		sb.append("<li>");
		sb.append(
				"<a href=\"#\" data-role=\"branch\" class=\"tree-toggle\" data-toggle=\"branch\" data-value=\"Bootstrap_Tree\">所有栏目</a>");
		makeStaticChannelTree(sb, convertedJsonArray);
		sb.append("</li>");
		// sb.append("</ul>");
		setParameter("treeHtml", sb.toString());

		// 读取信息
		final String columnId = (String) getParameter("columnId");
		initPageInfo(30);
		List<CmsArticle> articles;
		if (!StringUtils.isEmpty(columnId)) {
			String result = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					CmsService.Client cms = (CmsService.Client) client;
					String jsonChannel = cms.getChannelById(columnId);
					CmsChannel column = Message.unpackObject(jsonChannel, CmsChannel.class);
					setParameter("column", column);

					String jsonArticles = cms.loadArticlesByChannelIdForPaglit(columnId, false, getPageStart());
					return jsonArticles;
				}
			});
			articles = Message.unpackList(result, CmsArticle.class);
		} else {

			articles = new ArrayList<CmsArticle>();
		}
		setParameter("articles", articles);
		return "/admin/cms/article_list";
	}

	void makeStaticChannelTree(StringBuffer sb, JSONArray ja) {
		if (null != ja && ja.size() > 0) {
			int size = ja.size();
			if (size > 0) {
				sb.append("<ul class=\"branch in\">");

				for (int i = 0; i < size; i++) {
					JSONObject jo = ja.getJSONObject(i);
					sb.append("<li>");

					// 判断是否有儿子
					if (jo.containsKey("sub")) {
						JSONArray subChannels = jo.getJSONArray("sub");
						sb.append("<a href=\"#\" class=\"tree-toggle\" data-toggle=\"branch\" data-value=\""
								+ jo.getString("id") + "\" id=\"" + jo.getString("id") + "\"> " + jo.getString("name")
								+ "</a>");
						makeStaticChannelTree(sb, subChannels);
					} else {
						sb.append("<a href=\"/admin/cms/article_list?columnId=" + jo.getString("id")
								+ "\" data-role=\"leaf\" id=\"" + jo.getString("id")
								+ "\"><i class=\"icon-suitcase\"></i> " + jo.getString("name") + "</a>");
					}
					sb.append("</li>");
				}

				sb.append("</ul>");
			}

		}
	}

	/**
	 * 添加文章
	 * 
	 * @return
	 */
	@RequestMapping(value = "/article_edit")
	public String article_edit() throws Exception {
		final String id = (String) getParameter("id");
		final String columnId = (String) getParameter("columnId");

		if (!StringUtils.isEmpty(id)) {

			String articleString = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).getArticleById(id);
				}
			});

			CmsArticle article = Message.unpackObject(articleString, CmsArticle.class);
			setParameter("article", article);
			setParameter("columnName", article.getChannelName());
		} else {
			String strChannel = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).getChannelById(columnId);
				}
			});

			CmsChannel channel = Message.unpackObject(strChannel, CmsChannel.class);
			setParameter("columnName", channel.getName());
		}

		setParameter("columnId", columnId);

		return "/admin/cms/article_edit";
	}

	/**
	 * 更新文章信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/article_update")
	public @ResponseBody String article_update() throws Exception {
		final String id = (String) getParameter("id");
		String columnId = (String) getParameter("columnId");

		if (!StringUtils.isEmpty(id)) {
			columnId = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					CmsService.Client cms = (CmsService.Client) client;
					String strArticle = cms.getArticleById(id);
					CmsArticle a = Message.unpackObject(strArticle, CmsArticle.class);
					registerProperty("article", a);
					cms.updateArticle(JsonUtils.bean2String(a), null);
					return a.getChannelId();
				}
			});
		} else {
			registerProperty("article", CmsArticle.class);
			final CmsArticle a = (CmsArticle) getParameter("article");

			final String c = columnId;
			thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					CmsService.Client cms = (CmsService.Client) client;
					String strChannel = cms.getChannelById(c);
					CmsChannel column = Message.unpackObject(strChannel, CmsChannel.class);
					cms.updateArticle(JsonUtils.bean2String(a), column.getId());
					return column.getId();
				}
			});
		}
		return Message.okMessage(columnId);
	}

	/**
	 * 发布资讯
	 * 
	 * @return
	 */
	@RequestMapping(value = "/article_publish")
	public @ResponseBody String article_publish() throws Exception {
		final String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {

			return thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).publishArticle(id);
				}
			});
		}
		return Message.okMessage();
	}

	/**
	 * 删除文章
	 * 
	 * @return
	 */
	@RequestMapping(value = "/article_delete")
	public @ResponseBody String article_delete() throws Exception {
		final String id = (String) getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			return thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).deleteArticle(id);
				}
			});
		}
		return Message.okMessage();
	}

	// 文章置顶
	@RequestMapping(value = "/article_top")
	public @ResponseBody String article_top() {
		final String id = (String) getParameter("id");

		try {
			String result = thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).getArticleById(id);
				}
			});

			final CmsArticle a = Message.unpackObject(result, CmsArticle.class);
			if (a.getIsTop() == null) {
				a.setIsTop(1);
			} else {
				if (a.getIsTop() == 1) {
					a.setIsTop(0);
				} else {
					a.setIsTop(1);
				}
			}

			return thriftTemplate.execute("CmsService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					return ((CmsService.Client) client).updateArticle(JsonUtils.bean2String(a), null);
				}
			});
		} catch (Exception e) {
			return Message.error();
		}
	}
}
