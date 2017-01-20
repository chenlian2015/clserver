package com.yuhuayuan.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuhuayuan.core.common.Constant;
import com.yuhuayuan.core.service.RedisCacheService;

public class LoginInteceptor extends HandlerInterceptorAdapter {
	
    @Autowired
    protected RedisCacheService cacheService;
	
    
    private PathMatcher pathMatcher = new AntPathMatcher();

    private List<String> excludeUrlPatterns;
    
    public List<String> getExcludeUrlPatterns() {
        return excludeUrlPatterns;
    }

    public void setExcludeUrlPatterns(List<String> excludeUrlPatterns) {
        this.excludeUrlPatterns = excludeUrlPatterns;
    }
    
    
    /**
     * 判定path是否是放行URL
     *
     * @param path 用户请求路径
     * @return
     */
    protected boolean isExcludeUrl(String path) {
        if (excludeUrlPatterns == null || excludeUrlPatterns.isEmpty()) {
            return false;
        }
        for (String pattern : excludeUrlPatterns) {
            if (pathMatcher.match(pattern, path)) {
                return true;
            }
        }
        return false;
    }
    
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
//		 if (isExcludeUrl(request.getRequestURI())) {
//			 return true;
//		 }
//		 
//		String ticket = request.getHeader(Constant.REQUEST_PARAMETER_TICKET);
//		if(null == ticket)
//		{
//			ResponseWriter.writeErrResponse(response, Constant.ACK_ERR_INVALID_TICKET, Constant.ACK_ERR_INVALID_TICKET_MSG);
//			return false;
//		}
//		
//		String phone = cacheService.get(ticket, String.class);
//		if(null == phone || phone.length() ==0)
//		{
//			ResponseWriter.writeErrResponse(response, Constant.ACK_ERR_EMPTY_TICKET, Constant.ACK_ERR_EMPTY_TICKET_MSG);
//			return false;
//		}
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	}
}
