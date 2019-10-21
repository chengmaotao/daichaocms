/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 渠道管理Entity
 * @author 成茂涛
 * @version 2019-10-19
 */
public class CtcChannel extends DataEntity<CtcChannel> {
	
	private static final long serialVersionUID = 1L;
	private String channelId;		// channel_id
	private String channelName;		// 渠道名称
	private String deduProportion;		// 结算比例 除以 100，（例如 写60的话，就是 100个人给结算60个）
	
	public CtcChannel() {
		super();
	}

	public CtcChannel(String id){
		super(id);
	}

	@Length(min=1, max=32, message="channel_id长度必须介于 1 和 32 之间")
	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	@Length(min=0, max=50, message="渠道名称长度必须介于 0 和 50 之间")
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	@Length(min=1, max=11, message="结算比例 除以 100，（例如 写60的话，就是 100个人给结算60个）长度必须介于 1 和 11 之间")
	public String getDeduProportion() {
		return deduProportion;
	}

	public void setDeduProportion(String deduProportion) {
		this.deduProportion = deduProportion;
	}
	
}