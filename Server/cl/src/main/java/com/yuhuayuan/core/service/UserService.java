package com.yuhuayuan.core.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhuayuan.core.domain.User;
import com.yuhuayuan.core.persistence.UserMapper;
import com.yuhuayuan.core.utils.aliyun.ObjectUpLoad;



@Service
public class UserService {
	
    @Autowired
    protected UserMapper userMapper;
    
    @Autowired
    protected RedisCacheService cacheService;
    
    public boolean insert(User u)
    {
    	Map m = new HashMap<String,Object>();
		
    	User uGet = userMapper.selectByOpenid(u.getOpenid());
    	if(null == uGet)
    	{
    		userMapper.insert(u);
    	}
    	return true;
    }
    
    
}
