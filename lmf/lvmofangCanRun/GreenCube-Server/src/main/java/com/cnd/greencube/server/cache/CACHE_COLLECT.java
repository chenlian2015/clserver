package com.cnd.greencube.server.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.dao.UserCollectionDao;
import com.cnd.greencube.server.dao.jdbc.JdbcDAO;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisActionNoResult;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.protocal.Message;
import com.cnd.greencube.server.util.SpringUtils;
import com.cnd.greencube.server.util.StringUtils;

@Service("CACHE_COLLECT")
public class CACHE_COLLECT {
	@Resource(name = "UserCollectionDaoImpl")
	private UserCollectionDao userStoreDao;

	@Resource(name = "jedisTemplate")
	private JedisTemplate jedisTemplate;

	public static final String CACHE_USER_COLLECTION = RedisDaoSupportImpl.POOL + "UserCollectionCache";

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String read(final String userid) {
		Object o = jedisTemplate.execute(new JedisAction() {
			@Override
			public Object action(Jedis jedis) {
				String str = jedis.get(CACHE_USER_COLLECTION);

				if (StringUtils.isEmpty(str)) {
					List<Object[]> ids = userStoreDao.getStoreIds(userid);

					if (null != ids && ids.size() > 0) {
						StringBuffer sb = new StringBuffer();

						for (int i = 0; i < ids.size(); i++) {
							Object[] o = ids.get(i);
							if (i == 0)
								sb.append(o[0] + "_" + o[1]);
							else
								sb.append("," + o[0] + "_" + o[1]);
						}
						str = sb.toString();
					}

					// 保存到缓存服务器中
					if (!StringUtils.isEmpty(str)) {
						jedis.set(CACHE_USER_COLLECTION, str);
					}
				}
				return str;
			}
		});
		
		return o.toString();
	}
	
	/**
	 * 取得一个用户的全部收藏id
	 * 
	 * @param userId
	 * @return 返回全部收藏的id数组
	 */
	public String getMyCollectionIds(String userId) {
		try {
			String sql = "select C_FOREIGN_ID from T_USER_STORE t where t.C_USER_ID = '" + userId + "'";
			JdbcDAO jdbc = (JdbcDAO) SpringUtils.getBean("jdbcDao");
			List<Map<String, String>> rows = jdbc.queryExt(sql);
			List<String> stores = new ArrayList<String>();
			for (Map<String, String> m : rows) {
				stores.add(m.get("C_FOREGIN_ID"));
			}
			return Message.okMessage(stores);
		} catch (Exception e) {
			return Message.error();
		}
	}
	
	public void refresh(final String userid) {
		jedisTemplate.execute(new JedisActionNoResult() {
			@Override
			public void action(Jedis jedis) {
				List<Object[]> ids = userStoreDao.getStoreIds(userid);

				String str = "";
				if (null != ids && ids.size() > 0) {
					StringBuffer sb = new StringBuffer();

					for (int i = 0; i < ids.size(); i++) {
						Object[] o = ids.get(i);
						if (i == 0)
							sb.append(o[0] + "_" + o[1]);
						else
							sb.append("," + o[0] + "_" + o[1]);
					}
					str = sb.toString();
				}
				// 保存到缓存服务器中
				jedis.set(CACHE_USER_COLLECTION, str);
			}
		});
	}
}
