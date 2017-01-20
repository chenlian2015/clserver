package com.cnd.greencube.server.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.cnd.greencube.server.dao.CodeHealthyCategoryDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisActionNoResult;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.CodeHealthyCategory;
import com.cnd.greencube.server.util.StringUtils;

@Service("CACHE_HEALTHY_CATEGORY")
public class CACHE_HEALTHY_CATEGORY {

	@Resource(name = "CodeHealthyCategoryDaoImpl")
	private CodeHealthyCategoryDao codeHealthyCategoryDao;

	@Resource(name = "jedisTemplate")
	private JedisTemplate jedisTemplate;

	public static final String CACHE_HEALTHY_CATEGORY = RedisDaoSupportImpl.POOL + "HealthyCategory";

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getFromCache() {
		return jedisTemplate.execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				String str = jedis.get(CACHE_HEALTHY_CATEGORY);
				if (StringUtils.isEmpty(str)) {
					str = writeCache();
				}
				return str;
			}
		});
	}

	public String writeCache() {
		String sql = "select t.id from CodeHealthyCategory t order by t.classCode, t.order";
		List<CodeHealthyCategory> cs = codeHealthyCategoryDao.findList(sql);

		List<CodeHealthyCategory> result = new ArrayList<CodeHealthyCategory>();

		Map<String, CodeHealthyCategory> mapCategory = new HashMap<String, CodeHealthyCategory>();
		for (CodeHealthyCategory c : cs) {
			String fjdm = c.getClassCode();
			if (StringUtils.isEmpty(fjdm) || !fjdm.contains(".")) {
				result.add(c);
			} else {
				// 取得父亲的id
				String[] p = fjdm.split("\\.");

				String parentId = p[p.length - 2];
				CodeHealthyCategory parent = mapCategory.get(parentId);
				if (null != parent) {
					parent.addSubCategory(c);
				}
			}

			mapCategory.put(c.getId(), c);
		}

		final String json = JSON.toJSONString(result);
		jedisTemplate.execute(new JedisActionNoResult() {

			@Override
			public void action(Jedis jedis) {
				jedis.set(CACHE_HEALTHY_CATEGORY, json);

			}
		});
		return JSON.toJSONString(result);
	}
}
