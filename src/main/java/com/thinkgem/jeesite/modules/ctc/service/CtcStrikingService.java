/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.CtcStriking;
import com.thinkgem.jeesite.modules.ctc.dao.CtcStrikingDao;

/**
 * 醒目文案管理Service
 * @author 成茂涛
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class CtcStrikingService extends CrudService<CtcStrikingDao, CtcStriking> {

	public CtcStriking get(String id) {
		return super.get(id);
	}
	
	public List<CtcStriking> findList(CtcStriking ctcStriking) {
		return super.findList(ctcStriking);
	}
	
	public Page<CtcStriking> findPage(Page<CtcStriking> page, CtcStriking ctcStriking) {
		return super.findPage(page, ctcStriking);
	}
	
	@Transactional(readOnly = false)
	public void save(CtcStriking ctcStriking) {
		super.save(ctcStriking);
	}
	
	@Transactional(readOnly = false)
	public void delete(CtcStriking ctcStriking) {
		super.delete(ctcStriking);
	}
	
}