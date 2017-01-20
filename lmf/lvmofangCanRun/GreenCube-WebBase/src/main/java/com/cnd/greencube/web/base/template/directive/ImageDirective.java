package com.cnd.greencube.web.base.template.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cnd.greencube.web.base.config.SysConfiguration;
import com.cnd.greencube.web.base.util.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("imageDirective")
public class ImageDirective extends BaseDirective {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			String url = getString("url", params);

			Writer out = env.getOut();
			out.write(getImageUrl(url));
		} catch (Exception e) {
			Writer out = env.getOut();
			out.write(SysConfiguration.getProperty("system.static_server") + "/themes/greencube/images/no_pic.jpg");
		}
	}

	public static String getImageUrl(String url) {
		String imgUrl = SysConfiguration.getProperty("system.static_server") + "/themes/greencube/images/no_pic.jpg";
		if (!StringUtils.isEmpty(url)) {
			return imgUrl = url;
		}
		return imgUrl;
	}
}