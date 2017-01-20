package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FadocHospitalDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FadocHospital;
@Repository("FadocHospitalDaoImpl")
public class FadocHospitalDaoImpl extends RedisDaoSupportImpl<FadocHospital, String> implements FadocHospitalDao{

}
