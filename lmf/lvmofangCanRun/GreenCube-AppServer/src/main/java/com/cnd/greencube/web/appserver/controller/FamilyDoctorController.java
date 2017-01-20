package com.cnd.greencube.web.appserver.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.thrift.TServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.cnd.greencube.server.entity.Area;
import com.cnd.greencube.server.service.AreaService;
import com.cnd.greencube.web.base.dao.redis.JedisTemplate;
import com.cnd.greencube.web.base.dao.redis.JedisTemplate.JedisAction;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate;
import com.cnd.greencube.web.base.thrift.ThriftClientTemplate.ThriftAction;
import com.cnd.greencube.web.base.util.Pinyin;
import com.cnd.greencube.web.base.vo.Message;
import com.cnd.greencube.web.base.web.controller.BaseController;

/**
 * 百万家庭医生控制器
 * 
 * @author 胡晓光
 * 
 */
@Controller("FamilyDoctorController")
@RequestMapping("/familydoctor")
public class FamilyDoctorController extends BaseController {
	public static final String CACHE_KEY_AREA_FAMILY_SELECT_AREA = "family_area_select";
	@Resource(name = "ThriftClientTemplate")
	protected ThriftClientTemplate thriftTemplate;

	@Resource(name = "jedisTemplate")
	private JedisTemplate jedisTemplate;

	/**
	 * 选择地区
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page_choose_area")
	public String page_choose_area() {
		return "/front/familydoctor/page_choose_area";
	}

	/**
	 * 选择地区
	 * 
	 * @return
	 */
	@RequestMapping(value = "/data_choose_area", produces = "plain/text;charset=UTF-8")
	public @ResponseBody
	String data_choose_area() {
		String str = jedisTemplate.execute(new JedisAction<String>() {
			@Override
			public String action(Jedis jedis) {
				String str = jedis.get(CACHE_KEY_AREA_FAMILY_SELECT_AREA);
				if (str == null) {
					String strAreaList = thriftTemplate.execute("AreaService", new ThriftAction() {
						@Override
						public String action(TServiceClient areaService) throws Exception {
							return ((AreaService.Client) areaService).getAllCities();
						}
					});

					List<Area> areas = Message.unpackList(strAreaList, Area.class);
					// 生成HTML字符串并放入jedis缓存中
					str = generateAreaSelectHtml(areas);
					jedis.set(CACHE_KEY_AREA_FAMILY_SELECT_AREA, str);
				}
				return str;
			}
		});

		return str;
	}

	String generateAreaSelectHtml(List<Area> areas) {
		StringBuffer sb = new StringBuffer();
		for (char c = 'a'; c < 'z'; c++) {
			String up = Character.toString(c).toUpperCase();
			sb.append("<h5 style=\"display: block;clear: both;\" data-value=\"" + up + "\" id=\"h_" + up + "\">" + up + "</h5>");
			sb.append("<ul>");

			for (Area a : areas) {
				String firstChar = Pinyin.getFirstPinLowerCase(a.getName());

				if (firstChar.equals(Character.toString(c))) {
					sb.append("<li class=\"area_item\" data-id=\"" + a.getId() + "\" data-name=\"" + a.getName() + "\">" + a.getName() + "</li>");
				}
			}
			sb.append("</ul>");
		}
		return sb.toString();
	}
}
