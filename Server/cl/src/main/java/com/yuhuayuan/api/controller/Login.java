package com.yuhuayuan.api.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yuhuayuan.api.controller.tool.ControllTool;
import com.yuhuayuan.api.controller.tool.ImageGenerator;
import com.yuhuayuan.core.common.ServerErrorCode;
import com.yuhuayuan.core.domain.LoginRequest;
import com.yuhuayuan.core.domain.User;
import com.yuhuayuan.core.domain.test.City;
import com.yuhuayuan.core.domain.test.CityList;
import com.yuhuayuan.core.persistence.UserMapper;
import com.yuhuayuan.core.service.RedisCacheService;
import com.yuhuayuan.core.service.UserService;
import com.yuhuayuan.core.utils.returngson.GsonResult;

@Controller
public class Login {
	
	@Autowired
    private UserService userService;
	
    @Autowired
    private  HttpServletRequest request;
    
    private static final Logger logger = Logger.getLogger(Login.class);
    
	@Autowired
	protected UserMapper userMapper;
	
    @Autowired
    protected RedisCacheService cacheService;
    
    

    
    @RequestMapping(value = "test.do")
    private @ResponseBody void test(HttpServletRequest request, HttpServletResponse response)
	{
    	
    	String strResult = "";
    	try {

				int n=0;
				CityList cityList = new CityList();
				List<City> lstCity =  new ArrayList();
				
				while(n++<10)
				{
					City city = new City();
					city.setName("name"+n);
					city.setCountry("c:"+n);
					city.setCity("city:"+n);
					lstCity.add(city);
				}
				cityList.setRecords(lstCity);
				
				response.setContentType("text/json;charset=utf-8");
				PrintWriter pw = response.getWriter();
				strResult = JSON.toJSONString(cityList); 
				com.alibaba.fastjson.JSONObject jo = new JSONObject();
				
				pw.print(strResult);
				pw.flush();
				pw.close();
				pw = null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
    

	@RequestMapping(value = "noticeWeiXin.do", method=RequestMethod.POST)
	private @ResponseBody GsonResult login(HttpServletRequest request, HttpServletResponse response)
	{
		User u = null;
		ControllTool.LogRequest(request, logger);
		try
		{
			
		String strRequest = ControllTool.getRequestBody(request);
		logger.error(strRequest);

		
		User usr = JSON.parseObject(strRequest, User.class);
		
		boolean b = true;
		b = userService.insert(usr);
		  
		}catch(Exception e)
		{
			return new GsonResult(u, ServerErrorCode.FAILED.getCode(), "");
		}
		
		return new GsonResult(u, ServerErrorCode.SUCCESS.getCode(), "");  
	}

	

	
	/***
	 * 保存文件
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@RequestMapping(value="/uploadMultiFiles", method=RequestMethod.POST)
	public String filesUpload(@RequestParam("files") MultipartFile[] files) {
		//判断file数组不能为空并且长度大于0
		if(files!=null&&files.length>0){
			//循环获取file数组中得文件
			for(int i = 0;i<files.length;i++){
				MultipartFile file = files[i];
				//保存文件
				saveFile(file);
			}
		}
		// 重定向
		return "redirect:/index.jsp";
	}

}
