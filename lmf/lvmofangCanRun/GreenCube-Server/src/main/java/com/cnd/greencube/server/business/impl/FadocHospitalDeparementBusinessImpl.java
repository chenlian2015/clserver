package com.cnd.greencube.server.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnd.greencube.server.business.FadocHospitalDeparementBusiness;
import com.cnd.greencube.server.dao.FadocHospitalDeparementDao;
import com.cnd.greencube.server.entity.FadocHospital;
import com.cnd.greencube.server.entity.FadocHospitalDeparement;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 百万医生-科室服务类
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("rawtypes")
@Service("FadocHospitalDeparementBusinessImpl")
public class FadocHospitalDeparementBusinessImpl extends BaseBusinessImpl implements FadocHospitalDeparementBusiness {
	@Resource(name = "FadocHospitalDeparementDaoImpl")
	protected FadocHospitalDeparementDao fadocHospitalDeparementDao;

	@Override
	public List<FadocHospitalDeparement> loadFadocHospitalDeparements(PageInfo pageInfo) {
		String sql = "select t.id from FadocHospitalDeparement t";
		String ctql = "select count(t) from FadocHospitalDeparement t";
		return fadocHospitalDeparementDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean saveFadocHospitalDeparement(FadocHospitalDeparement fadocHospitalDeparement) {
		fadocHospitalDeparementDao.save(fadocHospitalDeparement);
		return true;
	}

	@Override
	public boolean updateFadocHospitalDeparement(FadocHospitalDeparement fadocHospitalDeparement) {
		fadocHospitalDeparementDao.update(fadocHospitalDeparement);
		return true;
	}

	@Override
	public boolean deleteFadocHospitalDeparementById(String id) {
		fadocHospitalDeparementDao.delete(id);
		return true;
	}

	@Override
	public FadocHospitalDeparement getFadocHospitalDeparementById(String id) {
		FadocHospitalDeparement fadocHospitalDeparement = fadocHospitalDeparementDao.get(id);
		return fadocHospitalDeparement;
	}

	@Override
	public FadocHospitalDeparement getFadocHospitalDeparementByName(String name) {
		String sql = "select t.id from FadocHospitalDeparement t where name =  ? ";
		List<FadocHospitalDeparement> fadocHospitalDeparements = fadocHospitalDeparementDao.findList(sql,
				new Object[] { name });
		FadocHospitalDeparement fadocHospitalDeparement = null;
		if (fadocHospitalDeparements != null && fadocHospitalDeparements.size() > 0) {
			fadocHospitalDeparement = fadocHospitalDeparements.get(0);
		}
		return fadocHospitalDeparement;
	}

}
