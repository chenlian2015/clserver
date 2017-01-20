package com.cnd.greencube.server.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cnd.greencube.server.entity.UserAddressBook;

public interface UserAddressBookDao extends BaseDao<UserAddressBook, String> {
	/**
	 * 取得用户的邮件列表
	 * 
	 * @param userid
	 * @return
	 */
	public List<UserAddressBook> getUserMailInfoList(String userid);

	/**
	 * 根据主键编号取得一个快递信息
	 * 
	 * @param id
	 * @return
	 */
	public UserAddressBook getUserMailInfoById(String id);

	/**
	 * 更新用户快递逆袭
	 * 
	 * @param info
	 */
	@Transactional
	public void updateUserMailingInfo(UserAddressBook info);

	/**
	 * 保存用户快递信息
	 * 
	 * @param info
	 */
	@Transactional
	public void saveUserMailingInfo(UserAddressBook info);

	/**
	 * 根据主键编号删除快递信息
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteMailingInfoById(String id);

	/**
	 * 删除一个快递信息
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteDevliveryById(String id);

	/**
	 * 设置一个快递信息为默认
	 */
	@Transactional
	public void setDefaultDevlivery(String userid, String id);
}
