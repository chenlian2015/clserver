package com.cnd.greencube.web.admin.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnd.greencube.server.entity.*;
import com.cnd.greencube.server.service.*;
import com.cnd.greencube.web.base.filter.parameter.ParameterWrapper;
import com.cnd.greencube.web.base.security.UserAuthentication;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.JsonUtils;
import com.cnd.greencube.web.base.util.Pinyin;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 会员管理
 * 
 * @author dongyali
 */
@Controller("MemberManagerController")
@RequestMapping("/admin/member")
public class MemberManagerController extends BaseController {
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	/**
	 * 供产会会员申请列表处理
	 */
	@RequestMapping(value = "/provider_applied_list")
	public String provider_applied_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("MemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				return ((MemberService.Client) ps).loadMembers(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);

		return "/admin/member/provider_applied_list";
	}

	/**
	 * 供产会执行审核操作
	 */
	@RequestMapping(value = "/provider_applied_audit")
	public @ResponseBody String provider_applied_audit() throws Exception {
		// // 取得当前登录用户
		// final JSONObject currentLoginUser =
		// UserAuthentication.getLoginUserInfo(ParameterWrapper.getWrapper().getRequest());
		// if (currentLoginUser != null) {
		// final String memberId = (String) getParameter("memberId");
		// final int auditStatus = getParameter("auditStatus") == null ? 0 :
		// getParameterInt("auditStatus");
		//
		// if (auditStatus == 1) {
		//
		// return thriftTemplate.execute("MemberService", new ThriftAction() {
		// @Override
		// public String action(TServiceClient ps) throws Exception {
		// return ((MemberService.Client) ps).approveShop(memberId,
		// currentLoginUser.getString("id"),
		// currentLoginUser.getString("name"));
		// }
		// });
		// } else {
		// return thriftTemplate.execute("MemberService", new ThriftAction() {
		// @Override
		// public String action(TServiceClient ps) throws Exception {
		// return ((MemberService.Client) ps).rejectShop(memberId,
		// currentLoginUser.getString("id"),
		// currentLoginUser.getString("name"));
		// }
		// });
		// }
		// } else {
		return Message.errorMessage("用户审核功能已关闭！");
		// }

	}

	/**
	 * 供产会执行详情操作
	 */
	@RequestMapping(value = "/provider_applied_detail")
	public String provider_applied_detail() throws Exception {
		// 取得当前共产会成员id
		final String memberId = (String) getParameter("memberId");

		thriftTemplate.execute("MemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				MemberService.Client client = (MemberService.Client) ps;

				// 基本信息
				String jsonBaseInfo = client.getMember(memberId);
				Member memberProvider = Message.unpackObject(jsonBaseInfo, Member.class);
				setParameter("baseinfo", memberProvider);
				return null;
			}
		});

		return "/admin/member/provider_applied_detail";
	}

	/**
	 * 供产会会员概览
	 */
	@RequestMapping(value = "/provider_list")
	public String provider_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("MemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				MemberService.Client client = (MemberService.Client) ps;

				return client.loadMembers(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/provider_list";
	}

	/**
	 * 店主会会员申请列表处理
	 */
	@RequestMapping(value = "/shop_applied_list")
	public String shop_applied_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("ShopService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				ShopService.Client client = (ShopService.Client) ps;

				return client.getAppliedShopsForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/shop_applied_list";
	}

	/**
	 * 店主会执行审核操作
	 */
	@RequestMapping(value = "/shop_applied_audit")
	public @ResponseBody String shop_applied_audit() throws Exception {
		// 取得当前登录用户
		final JSONObject currentLoginUser = UserAuthentication
				.getLoginUserInfo(ParameterWrapper.getWrapper().getRequest());
		if (currentLoginUser != null) {
			final String memberId = (String) getParameter("memberId");
			final int auditStatus = getParameter("auditStatus") == null ? 0 : getParameterInt("auditStatus");

			if (auditStatus == 1) {
				return thriftTemplate.execute("ShopService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						ShopService.Client client = (ShopService.Client) ps;

						return client.approveShop(memberId, currentLoginUser.getString("id"),
								currentLoginUser.getString("name"));
					}
				});
			} else {
				return thriftTemplate.execute("ShopService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						ShopService.Client client = (ShopService.Client) ps;

						return client.rejectShop(memberId, currentLoginUser.getString("id"),
								currentLoginUser.getString("name"));
					}
				});
			}
		} else {
			return Message.errorMessage("很抱歉，您还未登录，不能进行审核！");
		}
	}

	/**
	 * 店主会执行详情操作
	 */
	@RequestMapping(value = "/shop_applied_detail")
	public String shop_applied_detail() throws Exception {
		// 取得当前店主会会员id
		final String shopId = (String) getParameter("memberId");
		thriftTemplate.execute("ShopService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				ShopService.Client client = (ShopService.Client) ps;

				// 基本信息
				String jsonBaseInfo = client.getShopById(shopId);
				Shop shop = Message.unpackObject(jsonBaseInfo, Shop.class);
				setParameter("baseinfo", shop);
				return null;
			}
		});

		return "/admin/member/shop_applied_detail";
	}

	/**
	 * 店主会会员概览
	 */
	@RequestMapping(value = "/shop_list")
	public String shop_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("ShopService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				ShopService.Client client = (ShopService.Client) ps;
				return client.getApprovedShopsForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/shop_list";
	}

	/**
	 * 健康会会员申请列表处理
	 */
	@RequestMapping(value = "/healthy_applied_list")
	public String healthy_applied_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("HealthyMemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				HealthyMemberService.Client client = (HealthyMemberService.Client) ps;
				return client.loadAppliedHealthyMembersForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/healthy_applied_list";
	}

	/**
	 * 健康会执行审核操作
	 */
	@RequestMapping(value = "/healthy_applied_audit")
	public @ResponseBody String healthy_applied_audit() throws Exception {
		// 取得当前登录用户
		final JSONObject currentLoginUser = UserAuthentication
				.getLoginUserInfo(ParameterWrapper.getWrapper().getRequest());
		if (currentLoginUser != null) {
			final String memberId = (String) getParameter("memberId");
			final int auditStatus = getParameter("auditStatus") == null ? 0 : getParameterInt("auditStatus");
			if (auditStatus == 1) {
				return thriftTemplate.execute("HealthyMemberService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						HealthyMemberService.Client client = (HealthyMemberService.Client) ps;
						return client.auditHealthyMemberApply(memberId, currentLoginUser.getString("id"), auditStatus);
					}
				});
			} else {
				return thriftTemplate.execute("HealthyMemberService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						HealthyMemberService.Client client = (HealthyMemberService.Client) ps;
						return client.rejectHealthyMemberApply(memberId, currentLoginUser.getString("id"), auditStatus);
					}
				});
			}
		} else {
			return Message.errorMessage("很抱歉，您还未登录，不能进行审核！");
		}

	}

	/**
	 * 健康会执行详情操作
	 */
	@RequestMapping(value = "/healthy_applied_detail")
	public String healthy_applied_detail() throws Exception {
		// 取得当前健康会成员id
		final String healthyId = (String) getParameter("memberId");
		thriftTemplate.execute("HealthyMemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				HealthyMemberService.Client client = (HealthyMemberService.Client) ps;

				// 基本信息
				String jsonBaseInfo = client.getHealthyById(healthyId);
				MemberHealthy healthy = Message.unpackObject(jsonBaseInfo, MemberHealthy.class);
				setParameter("baseinfo", healthy);
				// 详细信息
				String jsonObject = client.getHealthyDetail(healthyId);
				JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
				JSONObject ja = jo.getJSONObject("data");
				setParameter("member", ja);
				return null;
			}
		});

		return "/admin/member/healthy_applied_detail";
	}

	/**
	 * 健康会会员概览
	 */
	@RequestMapping(value = "/healthy_list")
	public String healthy_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("HealthyMemberService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				HealthyMemberService.Client client = (HealthyMemberService.Client) ps;

				// 基本信息
				return client.loadApprovedHealthyMembersForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/healthy_list";
	}

	/**
	 * 注册用户会员概览
	 */
	@RequestMapping(value = "/user_list")
	public String user_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("UserService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				UserService.Client client = (UserService.Client) ps;
				// 基本信息
				return client.getUsersForPagelit(start);
			}
		});

		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("members", ja);
		return "/admin/member/user_list";
	}

	/**
	 * 注册用户会员详情
	 */
	@RequestMapping(value = "/user_detail")
	public String user_detail() throws Exception {
		// 取得当前注册用户id
		final String userId = (String) getParameter("memberId");

		String jsonBaseInfo = thriftTemplate.execute("UserService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				UserService.Client client = (UserService.Client) ps;
				// 基本信息
				return client.getUserById(userId);
			}
		});

		MemberHealthy user = Message.unpackObject(jsonBaseInfo, MemberHealthy.class);
		setParameter("baseinfo", user);
		return "/admin/member/user_detail";
	}

	/**
	 * 培训管理（试卷列表）
	 */
	@RequestMapping(value = "/train_list")
	public String train_list() throws Exception {

		return "/admin/member/train_list";
	}

	/**
	 * 培训管理（题目列表）
	 */
	@RequestMapping(value = "/train_question_list")
	public String train_question_list() throws Exception {

		return "/admin/member/train_question_list";
	}

	/**
	 * 培训管理（添加试卷）
	 */
	@RequestMapping(value = "/train_add")
	public String train_add() throws Exception {

		return "/admin/member/train_add";
	}

	/**
	 * 培训管理（添加试卷题目）
	 */
	@RequestMapping(value = "/train_question_add")
	public String train_question_add() throws Exception {

		return "/admin/member/train_question_add";
	}

	/**
	 * 百万医生-医生概览
	 */
	@RequestMapping(value = "/doctor_list")
	public String doctor_list() throws Exception {
		final int start = getParameter("start") == null ? 1 : getParameterInt("start");

		String jsonObject = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				FadocFamilyDoctorService.Client client = (FadocFamilyDoctorService.Client) ps;
				return client.loadFadocDoctors(start);
			}
		});
		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("fadocDoctor", ja);
		return "/admin/member/doctor_list";
	}

	/**
	 * 百万医生-医生概览
	 */
	@RequestMapping(value = "/import_excel_fadocdoctor")
	public String import_excel_fadocdoctor() throws Exception {
		MultipartFile uploadfile = (MultipartFile) getUploadFiles("excelFile");
		if (uploadfile == null || uploadfile.getSize() <= 0) {
			return Message.errorMessage("文件上传失败，不存在该文件");
		}

		DoctorExcelReader reader = new DoctorExcelReader();
		reader.parseExcel(uploadfile);

		final int start = getParameter("start") == null ? 1 : getParameterInt("start");
		String jsonObject = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
			@Override
			public String action(TServiceClient ps) throws Exception {
				FadocFamilyDoctorService.Client client = (FadocFamilyDoctorService.Client) ps;
				return client.loadFadocDoctors(start);
			}
		});
		// 转换为树
		JSONObject jo = JsonUtils.String2JSONObject(jsonObject);
		JSONArray ja = jo.getJSONArray("data");
		setParameter("fadocDoctor", ja);
		return "/admin/member/doctor_list";
	}

	/**
	 * 仅支持Excel2007以上版本：xlxs文件，不支持Excel2007一下版本的解析
	 * 
	 * @author root
	 *
	 */
	public class DoctorExcelReader {
		public boolean parseExcel(MultipartFile excelFile) {

			InputStream fin = null;
			try {
				fin = excelFile.getInputStream();
				// xls
				HSSFWorkbook workbook = new HSSFWorkbook(fin);
				HSSFSheet sheet = workbook.getSheetAt(0);
				// 2007以上版本 xlsx
				// XSSFWorkbook workbook = new XSSFWorkbook(fin);
				// XSSFSheet sheet = workbook.getSheetAt(0);
				// XSSFRow row = null;
				HSSFRow row = null;
				int totalRow = sheet.getLastRowNum() + 1;
				for (int i = 2; i < totalRow; i++) {
					String hospitalName = null;// 医院名称
					String hospitalDepartmentName = null;// 科室名称
					String name = null;// 医生姓名
					String sex = null;// 医生性别
					String jobTitle = null;// 职称
					String jobPost = null;// 职务
					String desc = null;// 医生描述（简介）
					String goodTreatmentDiseases = null;// 擅长治疗疾病
					Date visitsTime = null;// 出诊时间
					BigDecimal registrationFee = null;// 专家挂号费
					row = sheet.getRow(i);
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						String num = row.getCell(0).getStringCellValue().trim();
					}
					if (row.getCell(1) != null) {
						row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
						hospitalName = row.getCell(1).getStringCellValue().trim();
					}
					if (row.getCell(2) != null) {
						row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						hospitalDepartmentName = row.getCell(2).getStringCellValue().trim();
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						name = row.getCell(3).getStringCellValue().trim();
					}
					if (row.getCell(4) != null) {
						row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						sex = row.getCell(4).getStringCellValue().trim();
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						jobTitle = row.getCell(5).getStringCellValue().trim();
					}
					if (row.getCell(6) != null) {
						row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						jobPost = row.getCell(6).getStringCellValue().trim();
					}
					if (row.getCell(7) != null) {
						row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
						desc = row.getCell(7).getStringCellValue().trim();
					}
					if (row.getCell(8) != null) {
						row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
						goodTreatmentDiseases = row.getCell(8).getStringCellValue().trim();
					}
					if (row.getCell(9) != null) {
						row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
						String visitsTime2 = row.getCell(9).getStringCellValue().trim();
						if (!StringUtils.isEmpty(visitsTime2)) {
							visitsTime = java.sql.Date.valueOf(visitsTime2);
						}
					}
					if (row.getCell(10) != null) {
						row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
						String registrationFee2 = row.getCell(10).getStringCellValue().trim();
						if (!StringUtils.isEmpty(registrationFee2)) {
							registrationFee = new BigDecimal(registrationFee2);
						}
					}
					// 医院表
					String hospitalId = this.executeFadocHospital(hospitalName);

					// 科室表
					String hospitalDepartmentId = this.executeFadocHospitalDeparement(hospitalDepartmentName);

					// 用户表
					String userId = this.executeUser(name);

					// 会员表
					String memberId = this.executeMember(name, userId, sex);

					// 医生表
					FadocDoctor fadocDoctor = new FadocDoctor();
					fadocDoctor.setId(null);
					fadocDoctor.setHospitalName(hospitalName);
					fadocDoctor.setHospitalDepartmentName(hospitalDepartmentName);
					fadocDoctor.setName(name);
					fadocDoctor.setSex(sex);
					fadocDoctor.setJobTitle(jobTitle);
					fadocDoctor.setJobPost(jobPost);
					fadocDoctor.setDesc(desc);
					fadocDoctor.setGoodTreatmentDiseases(goodTreatmentDiseases);
					fadocDoctor.setVisitsTime(visitsTime);
					fadocDoctor.setRegistrationFee(registrationFee);
					fadocDoctor.setHospitalId(hospitalId);
					fadocDoctor.setHospitalDepartmentId(hospitalDepartmentId);
					fadocDoctor.setUserId(userId);
					fadocDoctor.setMemberId(memberId);
					final String fadocDoctorJson = JsonUtils.bean2String(fadocDoctor);
					String jsonObject = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
						@Override
						public String action(TServiceClient ps) throws Exception {
							FadocFamilyDoctorService.Client client = (FadocFamilyDoctorService.Client) ps;
							// 基本信息
							return client.saveFadocDoctor(fadocDoctorJson);
						}
					});
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fin != null) {
					try {
						fin.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			return true;
		}

		/**
		 * 处理医院表
		 * 
		 * @param hospitalName
		 * @return
		 */
		public String executeFadocHospital(String hospitalName) {
			final String hospName = hospitalName;
			String hospitalId = null;
			String hospitalJson = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((FadocFamilyDoctorService.Client) ps).getFadocHospitalByName(hospName);
				}
			});
			JSONObject hospitalJO = JsonUtils.String2JSONObject(hospitalJson);
			JSONObject hospitalJA = hospitalJO.getJSONObject("data");
			if (hospitalJA == null) {// 当前医院不存在，需要增加医院并得到医院id
				FadocHospital fadocHospital = new FadocHospital();
				fadocHospital.setId(null);
				fadocHospital.setName(hospName);
				final String fadocHospitalJson = JsonUtils.bean2String(fadocHospital);
				String hospiJson = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((FadocFamilyDoctorService.Client) ps).saveFadocHospital(fadocHospitalJson);
					}
				});
				JSONObject hospitalJO2 = JsonUtils.String2JSONObject(hospiJson);
				JSONObject hospitalJA2 = hospitalJO2.getJSONObject("data");
				if (hospitalJA2 != null) {
					hospitalId = hospitalJA2.getString("id");
				}
			} else {// 如果当前医院存在，直接获取医院id
				hospitalId = hospitalJA.getString("id");
			}
			return hospitalId;
		}

		/**
		 * 处理科室表
		 * 
		 * @param hospitalDepartmentName
		 * @return
		 */
		public String executeFadocHospitalDeparement(String hospitalDepartmentName) {
			final String hdName = hospitalDepartmentName;
			String hospitalDepartmentId = null;
			String hdJson = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((FadocFamilyDoctorService.Client) ps).getFadocHospitalDeparementByName(hdName);
				}
			});
			JSONObject hdJO = JsonUtils.String2JSONObject(hdJson);
			JSONObject hdJA = hdJO.getJSONObject("data");
			if (hdJA == null) {// 当前科室不存在，创建科室，并获取id
				FadocHospitalDeparement fd = new FadocHospitalDeparement();
				fd.setId(null);
				fd.setName(hospitalDepartmentName);
				final String fadocHospitalDeparementJson = JsonUtils.bean2String(fd);
				String hdJson2 = thriftTemplate.execute("FadocFamilyDoctorService", new ThriftAction() {
					@Override
					public String action(TServiceClient ps) throws Exception {
						return ((FadocFamilyDoctorService.Client) ps)
								.saveFadocHospitalDeparement(fadocHospitalDeparementJson);
					}
				});
				JSONObject hdJO2 = JsonUtils.String2JSONObject(hdJson2);
				JSONObject hdJA2 = hdJO2.getJSONObject("data");
				if (hdJA2 != null) {
					hospitalDepartmentId = hdJA2.getString("id");
				}
			} else {// 当前科室存在，直接获取id
				hospitalDepartmentId = hdJA.getString("id");
			}
			return hospitalDepartmentId;
		}

		/**
		 * 处理用户表
		 * 
		 * @param name
		 * @return
		 */
		public String executeUser(String name) {
			User user = new User();
			user.setId(null);
			user.setName(name);
			user.setNickname(name);
			Pinyin pinyin = new Pinyin();
			final String loginName = pinyin.getPinYin(name);
			String users = thriftTemplate.execute("UserService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((UserService.Client) ps).getUserByUserName(loginName);
				}
			});
			JSONObject userJO = JsonUtils.String2JSONObject(users);
			JSONObject userJA = userJO.getJSONObject("data");
			if (userJA == null) {
				user.setLoginName(loginName);
			} else {
				int randlogin = (int) ((Math.random() * 9 + 1) * 10);
				user.setLoginName(loginName + randlogin);
			}
			int randpass = (int) ((Math.random() * 9 + 1) * 10000);
			user.setPassword(randpass + "");
			final String userJson = JsonUtils.bean2String(user);
			String userId = thriftTemplate.execute("UserService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((UserService.Client) ps).addUser(userJson);
				}
			});
			return userId;
		}

		public String executeMember(String name, String userId, String sex) {
			Member member = new Member();
			member.setId(null);
			member.setUserId(userId);
			member.setRealName(name);
			int sexInt = sex == "男" ? 1 : 2;
			member.setSex(sexInt);
			member.setBeginTime(new Date());
			member.setRegistTime(new Date());
			final String memberJson = JsonUtils.bean2String(member);
			String memberId = thriftTemplate.execute("MemberService", new ThriftAction() {
				@Override
				public String action(TServiceClient ps) throws Exception {
					return ((MemberService.Client) ps).addMember(memberJson);
				}
			});
			return memberId;
		}
	}
}
