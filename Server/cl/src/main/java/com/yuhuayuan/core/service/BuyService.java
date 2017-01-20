package com.yuhuayuan.core.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuhuayuan.core.domain.BalanceLog;
import com.yuhuayuan.core.domain.User;
import com.yuhuayuan.core.persistence.BalanceLogMapper;
import com.yuhuayuan.core.persistence.UserMapper;

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
