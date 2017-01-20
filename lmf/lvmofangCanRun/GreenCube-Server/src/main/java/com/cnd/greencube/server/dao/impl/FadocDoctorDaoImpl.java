package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.FadocDoctorDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FadocDoctor;
@Repository("FadocDoctorDaoImpl")
public class FadocDoctorDaoImpl extends RedisDaoSupportImpl<FadocDoctor, String> implements FadocDoctorDao{

}
