package com.cnd.greencube.server.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.OrderMailDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.OrderMail;

/**
 * 订单快递数据访问类
 * 
 * @author huxg
 * 
 */
@Repository("OrderMailDaoImpl")
public class OrderMailDaoImpl extends RedisDaoSupportImpl<OrderMail, String> implements OrderMailDao {

	/**
	 * 取得一个订单的快递信息
	 */
	@Override
	public OrderMail getOrderMailing(String orderid) {
		String sql = "select t.id from OrderMail t where t.orderId = ?";
		List<OrderMail> mails = super.findList(sql);
		if (mails.size() > 0) {
			return mails.get(0);
		}
		return null;
	}

}
