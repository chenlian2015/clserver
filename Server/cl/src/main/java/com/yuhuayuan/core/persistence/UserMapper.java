package com.yuhuayuan.core.persistence;

import com.yuhuayuan.core.domain.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int insert(User user);
    
    User selectByOpenid(String openid);
    
    List<User> selectChildUsers(String openid);
}

