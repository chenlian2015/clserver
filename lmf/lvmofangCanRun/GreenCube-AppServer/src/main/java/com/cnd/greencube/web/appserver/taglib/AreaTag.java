package com.cnd.greencube.web.appserver.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;


/**
 * 省市数据标签
 * 
 * @author huxg
 * 
 */
public class AreaTag extends BodyTagSupport {
	private static final long serialVersionUID = 8948361331374816782L;

	@Override
	public int doEndTag() throws JspException {
		try {
			// AreaService areaService = (AreaService)
			// SpringUtils.getBean("AreaServiceImpl");
			// pageContext.getOut().println(areaService.getAreaCache());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return EVAL_PAGE;
	}
}
