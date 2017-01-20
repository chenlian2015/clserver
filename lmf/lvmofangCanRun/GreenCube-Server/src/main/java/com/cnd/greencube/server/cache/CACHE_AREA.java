package com.cnd.greencube.server.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.cnd.greencube.server.dao.AreaDao;
import com.cnd.greencube.server.dao.redis.JedisTemplate;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.util.StringUtils;

/**
 * 地区Redis缓存类
 * 
 * @author huxg
 * 
 */
@Service("CACHE_AREA")
public class CACHE_AREA {
	private static final String CACHE_KEY_AREA_JSON_STR = "objpool/Area/listjson";

	@Resource(name = "jedisTemplate")
	protected JedisTemplate jedisTemplate;

	@Resource(name = "AreaDaoImpl")
	private AreaDao areaDao;

	/**
	 * 取得地区在缓存中得字符串数据，直接返回给前端，如果不存在，则生成
	 * 
	 * @return
	 */
	public String getAreaCache() {
		return jedisTemplate.execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				String str = jedis.get(CACHE_KEY_AREA_JSON_STR);
				if (str == null) {
					List<Area> areas = getAreasTree();
					jedis.set(CACHE_KEY_AREA_JSON_STR, JSON.toJSONString(areas));
				}
				return str;
			}
		});
	}

	public List<Area> getAreasTree() {
		List<Area> areas = areaDao.getAreaList();
		List<Area> result = new ArrayList<Area>();

		Map<String, Area> areaMap = new HashMap<String, Area>();

		for (Area area : areas) {
			String fjdm = area.getClassCode();
			if (StringUtils.isEmpty(fjdm)) {
				result.add(area);
			} else {
				// 取得父亲的id
				String parentId;
				if (!fjdm.contains("."))
					parentId = fjdm;
				else
					parentId = fjdm.substring(fjdm.lastIndexOf(".") + 1);

				Area parentArea = areaMap.get(parentId);
				if (null != parentArea) {
					parentArea.addSubArea(area);
				}
			}

			areaMap.put(area.getId(), area);
		}
		return result;
	}
}
