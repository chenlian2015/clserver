package com.yuhuayuan.core.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.yuhuayuan.core.aliyun.ObjectUpLoad;

@Service
public class AliyunService {

	
	public boolean uploadObjectToAliyun()
	{
		try {
			ObjectUpLoad.upLoadAliyunTest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		return true;
	}
}
