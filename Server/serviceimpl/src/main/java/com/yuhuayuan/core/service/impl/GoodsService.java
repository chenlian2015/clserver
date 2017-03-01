package com.yuhuayuan.core.service.impl;

import com.yuhuayuan.core.dto.goods;
import com.yuhuayuan.core.persistence.goodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
