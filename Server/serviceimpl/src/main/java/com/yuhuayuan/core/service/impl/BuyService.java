package com.yuhuayuan.core.service.impl;

import com.yuhuayuan.core.dto.BalanceLog;
import com.yuhuayuan.core.persistence.BalanceLogMapper;
import com.yuhuayuan.core.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyService {
	@Autowired
	BalanceLogMapper balanceLogMapper;
	
	@Autowired
	UserMapper userMapper;
	
	
	public List<BalanceLog> GetBalanceLog(int yuhuayuanid)
	{
		return balanceLogMapper.selectByYuHuaYuanID(yuhuayuanid);
	}
}
