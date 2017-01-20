package com.yuhuayuan.api.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yuhuayuan.api.controller.tool.ControllTool;
import com.yuhuayuan.core.common.ServerErrorCode;
import com.yuhuayuan.core.domain.goods;
import com.yuhuayuan.core.domain.goodstr;
import com.yuhuayuan.core.service.GoodsService;

@Controller
public class Goods {

	@Autowired
	private GoodsService goodsService;
	
	private static final Logger logger = Logger.getLogger(Login.class);
	// 列表
	@RequestMapping(value = "listGoods.do")
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		
		List<goods> listGoods = goodsService.getAllGoods();
		mv.addObject("listGoods", listGoods);
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("listGoods");
		
		return mv;
	}
	
	@RequestMapping(value = "usergoodslist.do")
	public ModelAndView usergoodslist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		
		List<goods> listGoods = goodsService.getAllGoods();
		
		mv.addObject("listGoods", listGoods);
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("usergoodslist");
		
		return mv;
	}
	
	@RequestMapping(value = "addgoodsX.do", method=RequestMethod.POST)
	public String addGoodsX(goodstr goodsRecord) throws Exception{
		ControllTool.LogString(goodsRecord.toString());
		return null;
	}
	@RequestMapping(value = "addgoods.do", method=RequestMethod.POST)
	public ModelAndView addGoods(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ControllTool.LogRequest(request, logger);
		
		goods goodsRecord = new goods();
		goodsRecord.setGoodsdescribe(request.getParameter("goodsdescribe"));
		goodsRecord.setGoodsellprice(new BigDecimal(request.getParameter("goodsellprice")));
		goodsRecord.setGoodsimageurl(request.getParameter("goodsimageurl"));
		goodsRecord.setGoodsname(request.getParameter("goodsname"));
		goodsRecord.setGoodsorignprice(new BigDecimal(request.getParameter("goodsorignprice")));
		
		int status = 0;
		if("on".equalsIgnoreCase(request.getParameter("goodsstatus")))
		{
			status = 0;
		}
		else
		{
			status = 1;
		}
		goodsRecord.setGoodsstatus(status);
		goodsService.insert(goodsRecord);
		
	
		return new ModelAndView("redirect:/listGoods.do");
	}
	
	@RequestMapping(value = "delgoods.do", method=RequestMethod.POST)
	public ModelAndView delGoods(HttpServletRequest request, HttpServletResponse response) throws Exception{
		goodsService.deleteByPrimaryKey(Integer.parseInt(request.getParameter("goodsid")));
		return new ModelAndView("redirect:/listGoods.do");
	}
	
}
