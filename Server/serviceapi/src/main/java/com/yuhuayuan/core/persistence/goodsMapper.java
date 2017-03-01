package com.yuhuayuan.core.persistence;

import com.yuhuayuan.core.dto.goods;

import java.util.List;


public interface goodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(goods record);

    int insertSelective(goods record);

    goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(goods record);

    int updateByPrimaryKey(goods record);
    
    List<goods> getAllGoods();
}