package com.cnd.greencube.web.appserver.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.util.StringUtils;

/**
 * 显示用户头像
 */

@SuppressWarnings("serial")
public class ImageTag extends BodyTagSupport {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().print(getImageUrl(this.url));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release();
		}
		return EVAL_PAGE;
	}

	public static String getImageUrl(String url) {
		String imgUrl = SysConfiguration.getProperty("system.static_server") + "/themes/greencube/images/no_pic.jpg";
		if (!StringUtils.isEmpty(url)) {
			return imgUrl = url;
		}
		return imgUrl;
	}

	@Override
	public void release() {
		super.release();
		url = null;
	}
}