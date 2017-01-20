package com.cnd.greencube.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 支付方式
 * 
 * @author huxg
 * 
 */
@Entity
@Table(name = "T_FI_PAYMENT_METHOD")
public class FiPaymentMethod extends BaseEntity {
	private static final long serialVersionUID = 1339526039020879320L;

	// 标题
	@Column(name = "C_NAME")
	String name;

	// 渠道唯一标识
	@Column(name = "C_INDEFIER")
	String indefier;

	// 来源：app、wap、pc
	@Column(name = "C_CLIENT_TYPE")
	String clientType;

	// 内容
	@Column(name = "C_LOGO_APP1")
	String appLogo1;

	// 图片
	@Column(name = "C_LOGO_APP2")
	String appLogo2;

	// 图片
	@Column(name = "C_LOGO_APP3")
	String appLogo3;

	// 图片
	@Column(name = "C_LOGO_APP4")
	String appLogo4;

	// 图片
	@Column(name = "C_LOGO_WEB1")
	String webLogo1;

	// 图片
	@Column(name = "C_LOGO_WEB2")
	String webLogo2;

	// 图片
	@Column(name = "C_LOGO_WEB3")
	String webLogo3;

	// 图片
	@Column(name = "C_LOGO_WEB4")
	String webLogo4;

	// 描述
	@Column(name = "C_DESC")
	String desc;

	// 顺序
	@Column(name = "N_ORDER")
	Integer order;

	// 是否启用
	@Column(name = "N_ENABLE")
	Integer enable;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndefier() {
		return indefier;
	}

	public void setIndefier(String indefier) {
		this.indefier = indefier;
	}

	public String getAppLogo1() {
		return appLogo1;
	}

	public void setAppLogo1(String appLogo1) {
		this.appLogo1 = appLogo1;
	}

	public String getAppLogo2() {
		return appLogo2;
	}

	public void setAppLogo2(String appLogo2) {
		this.appLogo2 = appLogo2;
	}

	public String getAppLogo3() {
		return appLogo3;
	}

	public void setAppLogo3(String appLogo3) {
		this.appLogo3 = appLogo3;
	}

	public String getAppLogo4() {
		return appLogo4;
	}

	public void setAppLogo4(String appLogo4) {
		this.appLogo4 = appLogo4;
	}

	public String getWebLogo1() {
		return webLogo1;
	}

	public void setWebLogo1(String webLogo1) {
		this.webLogo1 = webLogo1;
	}

	public String getWebLogo2() {
		return webLogo2;
	}

	public void setWebLogo2(String webLogo2) {
		this.webLogo2 = webLogo2;
	}

	public String getWebLogo3() {
		return webLogo3;
	}

	public void setWebLogo3(String webLogo3) {
		this.webLogo3 = webLogo3;
	}

	public String getWebLogo4() {
		return webLogo4;
	}

	public void setWebLogo4(String webLogo4) {
		this.webLogo4 = webLogo4;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

}
