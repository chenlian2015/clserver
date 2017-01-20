package com.cnd.greencube.server.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cnd.greencube.server.dao.UserAddressBookDao;
import com.cnd.greencube.server.dao.redis.RedisDaoSupportImpl;
import com.cnd.greencube.server.entity.UserAddressBook;

@Repository("UserAddressBookDaoImpl")
public class UserAddressBookDaoImpl extends RedisDaoSupportImpl<UserAddressBook, String> implements UserAddressBookDao {
	@Resource(name = "UserAddressBookDaoImpl")
	private UserAddressBookDao userAddressBookDao;

	/**
	 * 取得用户的邮件列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<UserAddressBook> getUserMailInfoList(String userid) {
		String sql = "select t.id from UserAddressBook t where t.CUserId = ? order by NIsDefault desc";
		return (List<UserAddressBook>) userAddressBookDao.findList(sql, new Object[] { userid });
	}

	/**
	 * 根据主键编号取得一个快递信息
	 * 
	 * @param id
	 * @return
	 */
	public UserAddressBook getUserMailInfoById(String id) {
		return (UserAddressBook) userAddressBookDao.get(id);
	}

	/**
	 * 更新用户快递逆袭
	 * 
	 * @param info
	 */

	public void updateUserMailingInfo(UserAddressBook info) {
		userAddressBookDao.update(info);
	}

	/**
	 * 保存用户快递信息
	 * 
	 * @param info
	 */

	public void saveUserMailingInfo(UserAddressBook info) {
		userAddressBookDao.save(info);
	}

	/**
	 * 根据主键编号删除快递信息
	 * 
	 * @param id
	 */

	public void deleteMailingInfoById(String id) {
		userAddressBookDao.delete(id);
	}

	/**
	 * 删除一个快递信息
	 * 
	 * @param id
	 */

	public void deleteDevliveryById(String id) {
		userAddressBookDao.delete(id);
	}

	/**
	 * 设置一个快递信息为默认
	 */
	public void setDefaultDevlivery(String userid, String id) {
		List<UserAddressBook> addressbs = userAddressBookDao.getUserMailInfoList(userid);
		for (UserAddressBook addr : addressbs) {
			addr.setIsDefault(0);
			userAddressBookDao.update(addr);
		}

		UserAddressBook info = getUserMailInfoById(id);
		info.setIsDefault(1);
		userAddressBookDao.update(info);
	}
}
