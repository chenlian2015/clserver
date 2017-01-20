package com.yuhuayuan.core.persistence;

import java.util.List;

import com.yuhuayuan.core.domain.BalanceLog;

public interface BalanceLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BalanceLog record);

    int insertSelective(BalanceLog record);

    BalanceLog selectByPrimaryKey(Integer id);

    List<BalanceLog> selectByYuHuaYuanID(Integer id);
    
    int updateByPrimaryKeySelective(BalanceLog record);

    int updateByPrimaryKey(BalanceLog record);
}