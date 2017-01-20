package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;
import com.cnd.greencube.server.dao.FadocHospitalDeparementDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FadocHospitalDeparement;

@Repository("FadocHospitalDeparementDaoImpl")
public class FadocHospitalDeparementDaoImpl extends RedisDaoSupportImpl< FadocHospitalDeparement, String> implements  FadocHospitalDeparementDao{

}
