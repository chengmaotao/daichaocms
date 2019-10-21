/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import com.thinkgem.jeesite.common.config.Global;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 广告轮播图管理Entity
 * @author 成茂涛
 * @version 2019-10-19
 */
public class CtcAdvet extends DataEntity<CtcAdvet> {
	
	private static final long serialVersionUID = 1L;
	private String advetUrl;		// 广告图片地址
	private String advetJumpUrl;		// 广告图片跳转地址

	private String voAdvetUrl;

	public String getVoAdvetUrl() {
		return new StringBuffer("").append(Global.getConfig("ctc.http.pref")).append(Global.getConfig("ctc.upload.file.path")).append(advetUrl).toString();
	}

	public void setVoAdvetUrl(String voAdvetUrl) {
		this.voAdvetUrl = voAdvetUrl;
	}

	public CtcAdvet() {
		super();
	}

	public CtcAdvet(String id){
		super(id);
	}

	@Length(min=1, max=255, message="广告图片地址长度必须介于 1 和 255 之间")
	public String getAdvetUrl() {
		return advetUrl;
	}

	public void setAdvetUrl(String advetUrl) {
		this.advetUrl = advetUrl;
	}
	
	@Length(min=1, max=255, message="广告图片跳转地址长度必须介于 1 和 255 之间")
	public String getAdvetJumpUrl() {
		return advetJumpUrl;
	}

	public void setAdvetJumpUrl(String advetJumpUrl) {
		this.advetJumpUrl = advetJumpUrl;
	}
	
}