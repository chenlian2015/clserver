package com.cnd.greencube.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.CmsArticleDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.CmsArticle;

@Repository("CmsArticleDaoImpl")
public class CmsArticleDaoImpl extends RedisDaoSupportImpl<CmsArticle, String> implements CmsArticleDao {
}
