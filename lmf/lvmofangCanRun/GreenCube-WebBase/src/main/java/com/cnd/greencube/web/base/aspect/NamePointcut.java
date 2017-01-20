package com.cnd.greencube.web.base.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 切点统一在此管理
 * 
 * @author 
 *
 */
public class NamePointcut {
	@Pointcut("@within(com.cnd.greencube.web.base.annotation.Cachable)")
	public void cacheCut(){}
	
	@Pointcut("@within(com.cnd.greencube.web.base.annotation.OtherCachable)")
	public void otherCacheCut(){}
	
	@Pointcut("@within(com.cnd.greencube.web.base.annotation.Rwable)")
	public void rw(){};
	
	@Pointcut("@within(com.cnd.greencube.web.base.annotation.ClsRw)")
	public void clsRw(){};

}
