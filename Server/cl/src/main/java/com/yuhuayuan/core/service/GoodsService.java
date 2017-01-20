package com.yuhuayuan.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhuayuan.core.domain.goods;
import com.yuhuayuan.core.persistence.UserMapper;
import com.yuhuayuan.core.persistence.goodsMapper;

@Service
public class GoodsService {

	@Autowired
    protected goodsMapper goodsMapperObj;
	
	 public boolean insert(goods record)
	 {
		 goodsMapperObj.insert(record);
		 return true;
	 }
	 
	 public List<goods> getAllGoods()
	 {
		 return goodsMapperObj.getAllGoods();
	 }
	 
	 public boolean deleteByPrimaryKey(Integer record)
	 {
		 goodsMapperObj.deleteByPrimaryKey(record);
		 return true;
	 }
}
