package com.cnd.greencube.server.business;

import java.util.List;
import com.cnd.greencube.server.entity.FadocHospital;
import com.cnd.greencube.server.util.PageInfo;

public interface FadocHospitalBusiness {
	/**
	 * 得到所有医院资源
	 * 
	 * @return
	 */
	List<FadocHospital> loadFadocHospitals(PageInfo pageInfo);

	/**
	 * 保存医院信息
	 */
	boolean saveFadocHospital(FadocHospital fadocHospital);

	/**
	 * 修改医院信息
	 */
	boolean updateFadocHospital(FadocHospital fadocHospital);

	/**
	 * 删除医院信息
	 */
	boolean deleteFadocHospitalById(String id);

	/**
	 * 依据id查询当前医院
	 * 
	 * @param id
	 * @return
	 */
	FadocHospital getFadocHospitalById(String id);

	/**
	 * 依据name查询当前医院
	 * 
	 * @param name
	 * @return
	 */
	FadocHospital getFadocHospitalByName(String name);
}
