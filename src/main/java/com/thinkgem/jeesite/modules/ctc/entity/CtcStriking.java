/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 醒目文案管理Entity
 * @author 成茂涛
 * @version 2019-10-21
 */
public class CtcStriking extends DataEntity<CtcStriking> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title
	
	public CtcStriking() {
		super();
	}

	public CtcStriking(String id){
		super(id);
	}

	@Length(min=1, max=100, message="title长度必须介于 1 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}