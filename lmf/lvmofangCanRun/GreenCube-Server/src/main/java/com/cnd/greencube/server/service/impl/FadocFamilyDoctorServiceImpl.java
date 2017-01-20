package com.cnd.greencube.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;

import com.cnd.greencube.server.business.FadocDoctorBusiness;
import com.cnd.greencube.server.business.FadocHospitalBusiness;
import com.cnd.greencube.server.business.FadocHospitalDeparementBusiness;
import com.cnd.greencube.server.entity.FadocDoctor;
import com.cnd.greencube.server.entity.FadocHospital;
import com.cnd.greencube.server.entity.FadocHospitalDeparement;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.service.FadocFamilyDoctorService;
import com.cnd.greencube.server.util.JsonUtils;
import com.cnd.greencube.server.util.PageInfo;

public class FadocFamilyDoctorServiceImpl extends AbstractServiceImpl implements FadocFamilyDoctorService.Iface {
	private static final Logger logger = Logger.getLogger(FadocFamilyDoctorServiceImpl.class);
	@Resource(name = "FadocHospitalBusinessImpl")
	protected FadocHospitalBusiness fadocHospitalBusiness;

	@Resource(name = "FadocDoctorBusinessImpl")
	protected FadocDoctorBusiness fadocDoctorBusiness;

	@Resource(name = "FadocHospitalDeparementBusinessImpl")
	protected FadocHospitalDeparementBusiness fadocHospitalDeparementBusiness;

	/**
	 * 将Excel数据导入到数据库
	 */
	@Override
	public String addExcelDataToFadocDoctor(String path) throws TException {
		try {
			boolean flag = fadocDoctorBusiness.addExcelDataToFadocDoctor(path);
			if (flag == true) {
				return Message.okMessage();
			}
			return Message.error();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除医生数据
	 */
	@Override
	public String deleteFadocDoctorById(String id) throws TException {
		try {
			fadocDoctorBusiness.deleteFadocDoctorById(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除医院信息
	 */
	@Override
	public String deleteFadocHospitalById(String id) throws TException {
		try {

			fadocHospitalBusiness.deleteFadocHospitalById(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 删除科室信息
	 */
	@Override
	public String deleteFadocHospitalDeparementById(String id) throws TException {
		try {
			fadocHospitalDeparementBusiness.deleteFadocHospitalDeparementById(id);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 依据id得到医院信息
	 */
	@Override
	public String getFadocHospitalById(String id) throws TException {
		try {
			FadocHospital fadocHospital = fadocHospitalBusiness.getFadocHospitalById(id);
			return Message.okMessage(fadocHospital);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 依据name得到医院信息
	 */
	@Override
	public String getFadocHospitalByName(String name) throws TException {
		try {
			FadocHospital fadocHospital = fadocHospitalBusiness.getFadocHospitalByName(name);
			return Message.okMessage(fadocHospital);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 依据id得到科室信息
	 */
	@Override
	public String getFadocHospitalDeparementById(String id) throws TException {
		try {
			FadocHospitalDeparement fadocHospitalDeparement = fadocHospitalDeparementBusiness
					.getFadocHospitalDeparementById(id);
			return Message.okMessage(fadocHospitalDeparement);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 依据name得到科室信息
	 */
	@Override
	public String getFadocHospitalDeparementByName(String name) throws TException {
		try {
			FadocHospitalDeparement fadocHospitalDeparement = fadocHospitalDeparementBusiness
					.getFadocHospitalDeparementByName(name);
			return Message.okMessage(fadocHospitalDeparement);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 得到所有医生信息
	 */
	@Override
	public String loadFadocDoctors(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<FadocDoctor> fadocDoctor = fadocDoctorBusiness.loadFadocDoctors(pageInfo);
			return Message.okMessage(fadocDoctor);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 得到所有科室信息
	 */
	@Override
	public String loadFadocHospitalDeparements(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<FadocHospitalDeparement> fadocHospitalDeparement = fadocHospitalDeparementBusiness
					.loadFadocHospitalDeparements(pageInfo);
			return Message.okMessage(fadocHospitalDeparement);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 得到所有医院信息
	 */
	@Override
	public String loadFadocHospitals(int pageNum) throws TException {
		try {
			PageInfo pageInfo = initPageInfo(pageNum);
			List<FadocHospital> fadocHospital = fadocHospitalBusiness.loadFadocHospitals(pageInfo);
			return Message.okMessage(fadocHospital);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存医生信息
	 */
	@Override
	public String saveFadocDoctor(String fadocDoctorJson) throws TException {
		try {
			FadocDoctor fadocDoctor = JsonUtils.String2Bean(fadocDoctorJson, FadocDoctor.class);
			fadocDoctorBusiness.saveFadocDoctor(fadocDoctor);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存医院信息
	 */
	@Override
	public String saveFadocHospital(String fadocHospitalJson) throws TException {
		try {
			FadocHospital fadocHospital = JsonUtils.String2Bean(fadocHospitalJson, FadocHospital.class);
			fadocHospitalBusiness.saveFadocHospital(fadocHospital);
			return Message.okMessage(fadocHospital);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 保存科室信息
	 */
	@Override
	public String saveFadocHospitalDeparement(String FadocHospitalDeparementJson) throws TException {
		try {
			FadocHospitalDeparement fadocHospitalDeparement = JsonUtils.String2Bean(FadocHospitalDeparementJson,
					FadocHospitalDeparement.class);
			fadocHospitalDeparementBusiness.saveFadocHospitalDeparement(fadocHospitalDeparement);
			return Message.okMessage(fadocHospitalDeparement);
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改医生信息
	 */
	@Override
	public String updateFadocDoctor(String fadocDoctorJson) throws TException {
		try {
			FadocDoctor fadocDoctor = JsonUtils.String2Bean(fadocDoctorJson, FadocDoctor.class);
			fadocDoctorBusiness.updateFadocDoctor(fadocDoctor);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改医院信息
	 */
	@Override
	public String updateFadocHospital(String fadocHospitalJson) throws TException {
		try {
			FadocHospital fadocHospital = JsonUtils.String2Bean(fadocHospitalJson, FadocHospital.class);
			fadocHospitalBusiness.updateFadocHospital(fadocHospital);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

	/**
	 * 修改科室信息
	 */
	@Override
	public String updateFadocHospitalDeparement(String FadocHospitalDeparementJson) throws TException {
		try {
			FadocHospitalDeparement fadocHospitalDeparement = JsonUtils.String2Bean(FadocHospitalDeparementJson,
					FadocHospitalDeparement.class);
			fadocHospitalDeparementBusiness.updateFadocHospitalDeparement(fadocHospitalDeparement);
			return Message.okMessage();
		} catch (Exception e) {
			logger.error(e);
			return Message.error();
		}
	}

}
