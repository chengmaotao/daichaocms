/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.CtcChannel;
import com.thinkgem.jeesite.modules.ctc.dao.CtcChannelDao;

/**
 * 渠道管理Service
 * @author 成茂涛
 * @version 2019-10-19
 */
@Service
@Transactional(readOnly = true)
public class CtcChannelService extends CrudService<CtcChannelDao, CtcChannel> {

	public CtcChannel get(String id) {
		return super.get(id);
	}
	
	public List<CtcChannel> findList(CtcChannel ctcChannel) {
		return super.findList(ctcChannel);
	}
	
	public Page<CtcChannel> findPage(Page<CtcChannel> page, CtcChannel ctcChannel) {
		return super.findPage(page, ctcChannel);
	}
	
	@Transactional(readOnly = false)
	public void save(CtcChannel ctcChannel) {
		super.save(ctcChannel);
	}
	
	@Transactional(readOnly = false)
	public void delete(CtcChannel ctcChannel) {
		super.delete(ctcChannel);
	}
	
}