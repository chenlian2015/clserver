package com.yuhuayuan.core.persistence;

import java.util.List;

import com.yuhuayuan.core.domain.User;
import com.yuhuayuan.core.domain.goods;

public interface goodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(goods record);

    int insertSelective(goods record);

    goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(goods record);

    int updateByPrimaryKey(goods record);
    
    List<goods> getAllGoods();
}