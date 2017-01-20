package com.cnd.greencube.server.business.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cnd.greencube.server.business.FadocHospitalBusiness;
import com.cnd.greencube.server.dao.FadocHospitalDao;
import com.cnd.greencube.server.entity.FadocHospital;
import com.cnd.greencube.server.entity.User;
import com.cnd.greencube.server.util.PageInfo;

/**
 * 百万医生-医院服务类
 * 
 * @author dongyali
 *
 */
@SuppressWarnings("rawtypes")
@Service("FadocHospitalBusinessImpl")
public class FadocHospitalBusinessImpl extends BaseBusinessImpl implements FadocHospitalBusiness {
	@Resource(name = "FadocHospitalDaoImpl")
	protected FadocHospitalDao fadocHospitalDao;

	@Override
	public List<FadocHospital> loadFadocHospitals(PageInfo pageInfo) {
		String sql = "select t.id from FadocHospital t";
		String ctql = "select count(t) from FadocHospital t";
		return fadocHospitalDao.findList(sql, ctql, null, pageInfo);
	}

	@Override
	public boolean saveFadocHospital(FadocHospital fadocHospital) {
		fadocHospitalDao.save(fadocHospital);
		return true;
	}

	@Override
	public boolean updateFadocHospital(FadocHospital fadocHospital) {
		fadocHospitalDao.update(fadocHospital);
		return true;
	}

	@Override
	public boolean deleteFadocHospitalById(String id) {
		fadocHospitalDao.delete(id);
		return true;
	}

	@Override
	public FadocHospital getFadocHospitalById(String id) {
		FadocHospital fadocHospital = fadocHospitalDao.get(id);
		return fadocHospital;
	}

	@Override
	public FadocHospital getFadocHospitalByName(String name) {
		String sql = "select t.id from FadocHospital t where name =  ? ";
		List<FadocHospital> fadocHospitals = fadocHospitalDao.findList(sql, new Object[] { name });
		FadocHospital fadocHospital = null;
		if (fadocHospitals != null && fadocHospitals.size() > 0) {
			fadocHospital = fadocHospitals.get(0);
		}
		return fadocHospital;
	}

}
