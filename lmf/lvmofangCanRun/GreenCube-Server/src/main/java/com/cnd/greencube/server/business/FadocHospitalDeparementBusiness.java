package com.cnd.greencube.server.business;

import java.util.List;
import com.cnd.greencube.server.entity.FadocHospitalDeparement;
import com.cnd.greencube.server.util.PageInfo;

public interface FadocHospitalDeparementBusiness {
	/**
	 * 得到所有科室信息
	 * 
	 * @return
	 */
	List<FadocHospitalDeparement> loadFadocHospitalDeparements(PageInfo pageInfo);

	/**
	 * 保存科室信息
	 */
	boolean saveFadocHospitalDeparement(FadocHospitalDeparement fadocHospitalDeparement);

	/**
	 * 修改科室信息
	 */
	boolean updateFadocHospitalDeparement(FadocHospitalDeparement fadocHospitalDeparement);

	/**
	 * 删除科室信息
	 */
	boolean deleteFadocHospitalDeparementById(String id);

	/**
	 * 依据id查询当前科室
	 * 
	 * @param id
	 * @return
	 */
	FadocHospitalDeparement getFadocHospitalDeparementById(String id);

	/**
	 * 依据name查询当前科室
	 * 
	 * @param name
	 * @return
	 */
	FadocHospitalDeparement getFadocHospitalDeparementByName(String name);
}
