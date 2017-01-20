package com.cnd.greencube.server.business.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jxl.Sheet;
import jxl.Workbook;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cnd.greencube.server.business.FadocDoctorBusiness;
import com.cnd.greencube.server.dao.FadocDoctorDao;
import com.cnd.greencube.server.entity.FadocDoctor;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 百万医生-医生服务类
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("rawtypes")
@Service("FadocDoctorBusinessImpl")
public class FadocDoctorBusinessImpl extends BaseBusinessImpl implements FadocDoctorBusiness {
	@Resource(name = "FadocDoctorDaoImpl")
	protected FadocDoctorDao fadocDoctorDao;

	@Override
	public List<FadocDoctor> loadFadocDoctors(PageInfo pageInfo) {
		String sql = "select t.id from FadocDoctor t";
		String ctql = "select count(t) from FadocDoctor t";
		return fadocDoctorDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean addExcelDataToFadocDoctor(String path) {
		try {
			// 实例化一个工作簿对象
			Workbook workBook = Workbook.getWorkbook(new File("/root/workspace/aa.xls"));
			// 获取该工作表中的第一个工作表
			Sheet sheet = workBook.getSheet(0);
			// 获取该工作表的行数，以供下面循环使用
			int rowSize = sheet.getRows();
			for (int i = 2; i < rowSize; i++) {
				// 序号
				String num = sheet.getCell(0, i).getContents();
				// 医院名称
				String hospitalName = sheet.getCell(1, i).getContents();
				// 科室名称
				String hospitalDepartmentName = sheet.getCell(2, i).getContents();
				// 姓名
				String name = sheet.getCell(3, i).getContents();
				// 性别
				String sex = sheet.getCell(4, i).getContents();
				// 职称
				String jobTitle = sheet.getCell(5, i).getContents();
				// Date reg_time =
				// java.sql.Date.valueOf(sheet.getCell(5,i).getContents());
				// 职务
				String jobPost = sheet.getCell(6, i).getContents();
				// 个人简介
				String desc = sheet.getCell(7, i).getContents();
				// 擅长治疗疾病
				String goodTreatmentDiseases = sheet.getCell(8, i).getContents();
				// 出珍时间
				String visitsTime = sheet.getCell(9, i).getContents();
				Date visitsTime2 = null;
				if (!StringUtils.isEmpty(visitsTime)) {
					visitsTime2 = java.sql.Date.valueOf(visitsTime);
				}

				// 专家挂号费
				String RegistrationFee = sheet.getCell(10, i).getContents();
				BigDecimal registrationFee2 = null;
				if (!StringUtils.isEmpty(RegistrationFee)) {
					registrationFee2 = new BigDecimal(RegistrationFee);
				}

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
				fadocDoctor.setVisitsTime(visitsTime2);
				fadocDoctor.setRegistrationFee(registrationFee2);
				fadocDoctorDao.save(fadocDoctor);

			}
			// return mapping.findForward("import_success");
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			// return mapping.findForward("import_faile");
		}
		return false;
	}

	@Override
	public boolean saveFadocDoctor(FadocDoctor fadocDoctor) {
		fadocDoctorDao.save(fadocDoctor);
		return true;
	}

	@Override
	public boolean updateFadocDoctor(FadocDoctor fadocDoctor) {
		fadocDoctorDao.update(fadocDoctor);
		return true;
	}

	@Override
	public boolean deleteFadocDoctorById(String id) {
		fadocDoctorDao.delete(id);
		return true;
	}

}
