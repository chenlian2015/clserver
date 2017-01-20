package com.cnd.greencube.server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.api.JdbcAPI;
import com.cnd.greencube.server.dao.ShopGoodsDao;
import com.cnd.greencube.server.dao.jdbc.JdbcDAO;
import com.cnd.greencube.server.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.ShopGoods;
import com.thoughtworks.xstream.XStream;

@Repository("ShopGoodsDaoImpl")
public class ShopGoodsDaoImpl extends RedisDaoSupportImpl<ShopGoods, String> implements ShopGoodsDao {
	@SuppressWarnings("unchecked")
	public List<ShopGoods> getHotGoods(int count) {
		String sql = "select g.C_ID from T_SHOP_GOODS where N_STATUS_SHELF = 1 and N_STATUS_AUDIT = 1 and N_STATUS_DELETE = 0 and t.N_IS_RECOMMEND = 1 limit 0, "
				+ count;

		JdbcDAO jdbc = JdbcAPI.getJdbc();
		try {
			final List<Map> ids = (List<Map>) jdbc.queryExt(sql);

			List<ShopGoods> goods = (List<ShopGoods>) jedisTemplate.execute(new JedisAction() {
				@Override
				public Object action(Jedis jedis) {
					List<ShopGoods> goods = new ArrayList<ShopGoods>();
					XStream x = new XStream();
					for (Map idMap : ids) {
						String id = (String) idMap.get("C_ID");
						String goodsXml = jedis.hget(RedisDaoSupportImpl.POOL + "ShopGoodsHot", id);
						if (goodsXml != null) {
							ShopGoods g = (ShopGoods) x.fromXML(goodsXml);
							if (g != null) {
								goods.add(g);
							}
						} else {
							// 写入缓存
							ShopGoods g = get(id);
							if (g != null) {
								String xml = x.toXML(g);
								jedis.hset(RedisDaoSupportImpl.POOL + "ShopGoodsHot", id, xml);
							}
							goods.add(g);
						}
					}
					return goods;
				}

			});
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
