namespace java com.cnd.greencube.server.service

/**
 * 地区接口
 */
service AreaService {
	/*
	 * 删除地区
	 * @param id -- 地区id
	 */
	string deleteArea(1:string id),
	
	/*
	 * 更新地区
	 */
	string updateArea(1:string areaInfo),
	
	/*
	 * 更新地区
	 */
	string getAllCities(),
	
	/*
	 * 通过id获取一个地区
	 */
	string getAreaById(1:string areaId),
	
	/*
	 * 获取所有地区
	 */
	string getAllAreas()
}