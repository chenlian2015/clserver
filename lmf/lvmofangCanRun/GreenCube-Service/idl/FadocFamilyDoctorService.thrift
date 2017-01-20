namespace java com.cnd.greencube.server.service

/**
 * 百万医生接口<br/>
 * <b>用于前端访问提供的各种接口服务</b>
 * @author 董亚莉
 */
service FadocFamilyDoctorService {
/**
	 * 1 百万医生-得到所有医院列表
	 * @param pageNum 页数
	 * @return 统计情况的json串
	 */
	string loadFadocHospitals(1:i32 pageNum),
	/**
	 * 2 保存医院信息
	 * @param fadocHospitalJson 
	 * @return 成功与失败
	 */
	string saveFadocHospital(1:string fadocHospitalJson),
		/**
	 * 3 修改医院信息
	 * @param fadocHospitalJson 
	 * @return 成功与失败
	 */
	string updateFadocHospital(1:string fadocHospitalJson),
	/**
	 * 4 删除医院信息
	 * @param id 
	 * @return 成功与失败
	 */
	string deleteFadocHospitalById(1:string id),
	/**
	 * 5 依据id得到医院信息
	 * @param id 
	 * @return 医生实体
	 */
	string getFadocHospitalById(1:string id),
	/**
	 * 6 依据name得到医生信息
	 * @param id 
	 * @return 医生实体
	 */
	string getFadocHospitalByName(1:string name),
	/**
	 * 7 百万医生-得到所有医生列表
	 * @param pageNum 页数
	 * @return 统计情况的json串
	 */
	string loadFadocDoctors(1:i32 pageNum),
	/**
	 * 8 百万医生-导入医生列表（Excel表格）
	 * @param path 路径
	 * @return 成功与失败
	 */
	string addExcelDataToFadocDoctor(1:string path),
	/**
	 * 9 保存医生信息
	 * @param fadocDoctorJson 
	 * @return 成功与失败
	 */
	string saveFadocDoctor(1:string fadocDoctorJson),
	/**
	 * 10 修改医生信息
	 * @param fadocDoctorJson 
	 * @return 成功与失败
	 */
	string updateFadocDoctor(1:string fadocDoctorJson),
	/**
	 * 11 删除医生信息
	 * @param id 
	 * @return 成功与失败
	 */
	string deleteFadocDoctorById(1:string id),
	/**
	 * 12 百万医生-得到所有科室列表
	 * @param pageNum 页数
	 * @return 统计情况的json串
	 */
	string loadFadocHospitalDeparements(1:i32 pageNum),
	/**
	 * 13 保存科室信息
	 * @param fadocHospitalJson 
	 * @return 成功与失败
	 */
	string saveFadocHospitalDeparement(1:string fadocHospitalDeparementJson),
	/**
	 * 14 修改科室信息
	 * @param fadocHospitalJson 
	 * @return 成功与失败
	 */
	string updateFadocHospitalDeparement(1:string fadocHospitalDeparementJson),
	/**
	 * 15 删除科室信息
	 * @param id 
	 * @return 成功与失败
	 */
	string deleteFadocHospitalDeparementById(1:string id),
	/**
	 * 16 依据id得到科室信息
	 * @param id 
	 * @return 科室实体
	 */
	string getFadocHospitalDeparementById(1:string id),
	/**
	 * 17 依据name得到科室信息
	 * @param id 
	 * @return 科室实体
	 */
	string getFadocHospitalDeparementByName(1:string name)
	
	
	
}