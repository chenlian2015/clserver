package com.cnd.greencube.server.dao.impl;

import com.cnd.greencube.server.dao.FadocFamilyDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.FadocFamily;

public class FadocFamilyDaoImpl extends RedisDaoSupportImpl<FadocFamily, String> implements FadocFamilyDao{

}
