package com.cnd.greencube.web.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnd.greencube.server.entity.Train;
import com.cnd.greencube.server.entity.TrainCategory;
import com.cnd.greencube.server.entity.TrainQuestion;
import com.cnd.greencube.server.entity.TrainQuestionOption;
import com.cnd.greencube.server.service.TrainService;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.StringUtils;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 培训管理
 * 
 * @author huxg
 * 
 */
@Controller("TrainController")
@RequestMapping("/admin/train")
public class TrainController extends BaseController {

	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 类别列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_list")
	public String category_list() throws Exception {
		initPageInfo();

		String result = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient cms) throws Exception {
				return ((TrainService.Client) cms).loadTrainCategories();
			}
		});

		List<TrainCategory> categories = Message.unpackList(result, TrainCategory.class);
		setParameter("clist", categories);
		return "/admin/train/category_list";
	}

	/**
	 * 进入类别编辑界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_edit")
	public String category_edit() throws Exception {
		final String categoryId = (String) getParameter("categoryId");
		if (!StringUtils.isEmpty(categoryId)) {
			String result = thriftTemplate.execute("TrainService", new ThriftAction() {
				@Override
				public String action(TServiceClient cms) throws Exception {
					return ((TrainService.Client) cms).getTrainCategoryById(categoryId);
				}
			});

			TrainCategory category = Message.unpackObject(result, TrainCategory.class);

			setParameter("c", category);
		}
		return "/admin/train/category_edit";
	}

	/**
	 * 类别保存更新操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_update")
	public @ResponseBody
	String category_update() throws Exception {
		final String categoryId = (String) getParameter("categoryId");
		try {

			if (StringUtils.isEmpty(categoryId)) {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;
						registerProperty("c", TrainCategory.class);
						TrainCategory category = (TrainCategory) getParameter("c");
						return trainService.saveTrainCategory(JsonUtils.bean2String(category));
					}
				});
			} else {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;
						String result = trainService.getTrainCategoryById(categoryId);

						TrainCategory category = Message.unpackObject(result, TrainCategory.class);
						registerProperty("c", category);
						return trainService.updateTrainCategory(JsonUtils.bean2String(category));
					}
				});
			}
		} catch (Exception e) {
			return Message.errorMessage(e.getMessage());
		}

	}

	/**
	 * 删除类别操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/category_delete")
	public @ResponseBody
	String category_delete() {
		final String categoryId = (String) getParameter("categoryId");
		try {
			if (!StringUtils.isEmpty(categoryId)) {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;
						return trainService.deleteTrainCategoryById(categoryId);
					}
				});

			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 试题列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/question_list")
	public String question_list() throws Exception {
		final String trainId = (String) getParameter("trainId");

		String result = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				TrainService.Client trainService = (TrainService.Client) client;
				return trainService.loadQuestions(trainId);
			}
		});

		List<TrainQuestion> questions = Message.unpackList(result, TrainQuestion.class);
		setParameter("tlist", questions);

		// 查询培训
		result = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				TrainService.Client trainService = (TrainService.Client) client;
				return trainService.loadTrains(null);
			}
		});
		List<Train> trains = Message.unpackList(result, Train.class);
		StringBuffer sb = new StringBuffer();

		sb.append("<li>");
		sb.append("<a href=\"#\" data-role=\"branch\" class=\"tree-toggle\" data-toggle=\"branch\" data-value=\"Bootstrap_Tree\">所有培训</a>");
		makeTrainTree(sb, trains);
		sb.append("</li>");

		setParameter("treeHtml", sb.toString());
		return "/admin/train/question_list";
	}

	void makeTrainTree(StringBuffer sb, List<Train> trains) {
		if (null != trains && trains.size() > 0) {
			int size = trains.size();
			if (size > 0) {
				sb.append("<ul class=\"branch in\">");

				for (Train c : trains) {
					sb.append("<li>");

					// 判断是否有儿子
					sb.append("<a href=\"/admin/train/question_list?trainId=" + c.getId() + "\" data-role=\"leaf\" id=\"" + c.getId()
							+ "\"> <i class=\"icon-file yellow\"></i>" + c.getName() + "</a>");
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
		}
	}

	/**
	 * 搜索题目
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search_question")
	public String search_question() throws Exception {
		final String keyword = (String) getParameter("keyword");

		// 查询培训
		String result = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				TrainService.Client trainService = (TrainService.Client) client;
				return trainService.searchQuestions(keyword);
			}
		});

		List<TrainQuestion> questions = Message.unpackList(result, TrainQuestion.class);
		setParameter("tlist", questions);
		return "/admin/train/question_list";
	}

	/**
	 * 进入试题编辑界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/question_edit")
	public String question_edit() throws Exception {
		final String questionId = (String) getParameter("questionId");
		String type = (String) getParameter("type");

		TrainQuestion question = null;
		if (!StringUtils.isEmpty(questionId)) {
			String result = thriftTemplate.execute("TrainService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					TrainService.Client trainService = (TrainService.Client) client;

					String result = trainService.getQuestionById(questionId);
					
					String resultOptions = trainService.loadQuestionOptions(questionId);
					List<TrainQuestionOption> items = Message.unpackList(resultOptions, TrainQuestionOption.class);
					setParameter("itemlist", items);
					return result;
				}
			});

			question = Message.unpackObject(result, TrainQuestion.class);
		} else {
			question = new TrainQuestion();
			String trainId = (String) getParameter("trainId");
			question.setTrainId(trainId);
		}

		setParameter("t", question);
		setParameter("type", type);
		if (type == "1" || "1".equals(type)) {
			return "/admin/train/question_edit";
		} else if (type == "2" || "2".equals(type)) {
			return "/admin/train/question_mulsel_edit";
		}
		return null;
	}

	/**
	 * 选择题更新操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/question_update")
	public @ResponseBody
	String question_update() {
		final String questionId = (String) getParameter("questionId");
		final String[] seqs = (String[]) getParaments("itemSeq[]");
		final String[] titles = (String[]) getParameter("name[]");

		try {
			if (StringUtils.isEmpty(questionId)) {
				registerProperty("t", TrainQuestion.class);
				final TrainQuestion question = (TrainQuestion) getParameter("t");

				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;
						return trainService.saveQuestion(JsonUtils.bean2String(question), org.apache.commons.lang.StringUtils.join(seqs, ","),
								org.apache.commons.lang.StringUtils.join(titles, ","));
					}
				});
			} else {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;

						String str = trainService.getQuestionById(questionId);
						TrainQuestion question = Message.unpackObject(str, TrainQuestion.class);

						str = trainService.loadQuestions(questionId);
						registerProperty("t", question);
						return trainService.updateQuestion(JsonUtils.bean2String(question), org.apache.commons.lang.StringUtils.join(seqs, ","),
								org.apache.commons.lang.StringUtils.join(titles, ","));
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error();
		}
	}

	/**
	 * 删除试题操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/question_delete")
	public @ResponseBody
	String topic_delete() {
		final String questionId = (String) getParameter("questionId");
		try {
			if (!StringUtils.isEmpty(questionId)) {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;

						return trainService.deleteQuestionById(questionId);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 培训管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train_list")
	public String train_list() throws Exception {
		final String categoryId = (String) getParameter("categoryId");

		String result = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				TrainService.Client trainService = (TrainService.Client) client;

				String result = trainService.loadTrains(categoryId);
				List<Train> trains = Message.unpackList(result, Train.class);
				setParameter("elist", trains);

				return trainService.loadTrainCategories();
			}
		});

		List<TrainCategory> categories = Message.unpackList(result, TrainCategory.class);
		StringBuffer sb = new StringBuffer();

		sb.append("<li>");
		sb.append("<a href=\"#\" data-role=\"branch\" class=\"tree-toggle\" data-toggle=\"branch\" data-value=\"Bootstrap_Tree\">所有类别</a>");
		makeStaticChannelTree(sb, categories);
		sb.append("</li>");

		setParameter("treeHtml", sb.toString());
		return "/admin/train/train_list";
	}

	void makeStaticChannelTree(StringBuffer sb, List<TrainCategory> categories) {
		if (null != categories && categories.size() > 0) {
			int size = categories.size();
			if (size > 0) {
				sb.append("<ul class=\"branch in\">");

				for (TrainCategory c : categories) {
					sb.append("<li>");

					// 判断是否有儿子
					sb.append("<a href=\"/admin/train/train_list?categoryId=" + c.getId() + "\" data-role=\"leaf\" id=\"" + c.getId() + "\"> " + c.getName()
							+ "</a>");
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
		}
	}

	/**
	 * 进入试卷编辑界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train_edit")
	public String train_edit() throws Exception {
		final String trainId = (String) getParameter("trainId");
		Train train;
		if (!StringUtils.isEmpty(trainId)) {

			String str = thriftTemplate.execute("TrainService", new ThriftAction() {
				@Override
				public String action(TServiceClient client) throws Exception {
					TrainService.Client trainService = (TrainService.Client) client;
					return trainService.getTrainById(trainId);
				}
			});

			train = Message.unpackObject(str, Train.class);
		} else {
			String categoryId = (String) getParameter("categoryId");
			train = new Train();
			train.setTrainCategoryId(categoryId);
		}

		setParameter("e", train);

		String str = thriftTemplate.execute("TrainService", new ThriftAction() {
			@Override
			public String action(TServiceClient client) throws Exception {
				TrainService.Client trainService = (TrainService.Client) client;
				return trainService.loadTrainCategories();
			}
		});

		List<TrainCategory> categories = Message.unpackList(str, TrainCategory.class);
		setParameter("categories", categories);
		return "/admin/train/train_edit";
	}

	/**
	 * 试卷更新操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train_update")
	public @ResponseBody
	String train_update() {
		final String trainId = (String) getParameter("trainId");
		try {
			if (StringUtils.isEmpty(trainId)) {
				registerProperty("e", Train.class);

				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						Train train = (Train) getParameter("e");
						TrainService.Client trainService = (TrainService.Client) client;
						return trainService.saveTrain(JsonUtils.bean2String(train));
					}
				});
			} else {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;

						String result = trainService.getTrainById(trainId);
						Train train = Message.unpackObject(result, Train.class);
						registerProperty("e", train);
						return trainService.updateTrain(JsonUtils.bean2String(train));
					}
				});
			}
		} catch (Exception e) {
			return Message.error();
		}
	}

	/**
	 * 试卷删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "/train_delete")
	public @ResponseBody
	String train_delete() throws Exception {
		final String trainId = (String) getParameter("trainId");
		try {
			if (!StringUtils.isEmpty(trainId)) {
				return thriftTemplate.execute("TrainService", new ThriftAction() {
					@Override
					public String action(TServiceClient client) throws Exception {
						TrainService.Client trainService = (TrainService.Client) client;

						return trainService.deleteTrainById(trainId);
					}
				});
			}
			return Message.okMessage();
		} catch (Exception e) {
			return Message.error();
		}
	}

}
