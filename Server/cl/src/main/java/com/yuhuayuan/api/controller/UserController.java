package com.yuhuayuan.api.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yuhuayuan.core.domain.User;


@Controller
public class UserController {
	@RequestMapping(value="/registerBatch", method=RequestMethod.POST)
	@ResponseBody
	public User registerBatch(@RequestBody User users)
	{
		return users;
	}
	
	
	@RequestMapping(value="/registerBatchX", method=RequestMethod.POST)
	public ModelAndView registerBatch(String users)
	{
		
		return new ModelAndView("userList", "userList", users);
	}
}
