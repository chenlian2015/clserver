package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.LetterDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.Letter;

@Repository("LetterDaoImpl")
public class LetterDaoImpl extends RedisDaoSupportImpl<Letter, String> implements LetterDao {

}
