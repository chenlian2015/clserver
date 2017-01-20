package com.cnd.greencube.server.business;

import java.util.List;

import com.cnd.greencube.server.entity.FadocDoctor;
import com.cnd.greencube.server.util.PageInfo;

public interface FadocDoctorBusiness {
	/**
	 * 得到所有医生资源
	 * 
	 * @return
	 */
	List<FadocDoctor> loadFadocDoctors(PageInfo pageInfo);

	/**
	 * 将Excel表格数据导入百万医生数据表
	 * 
	 * @param path
	 * @return
	 */
	boolean addExcelDataToFadocDoctor(String path);

	/**
	 * 保存医生信息
	 */
	boolean saveFadocDoctor(FadocDoctor fadocDoctor);

	/**
	 * 修改医生信息
	 */
	boolean updateFadocDoctor(FadocDoctor fadocDoctor);

	/**
	 * 删除医生信息
	 */
	boolean deleteFadocDoctorById(String id);
}
