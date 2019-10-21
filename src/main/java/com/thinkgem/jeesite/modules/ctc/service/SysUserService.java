/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.util.List;

import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.dao.RoleDao;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.SysUser;
import com.thinkgem.jeesite.modules.ctc.dao.SysUserDao;

/**
 * 用户管理Service
 * @author 成茂涛
 * @version 2019-10-19
 */
@Service
@Transactional(readOnly = true)
public class SysUserService extends CrudService<SysUserDao, SysUser> {

	@Autowired
	private SysUserDao sysUserDao;

	public SysUser get(String id) {
		return super.get(id);
	}
	
	public List<SysUser> findList(SysUser sysUser) {
		return super.findList(sysUser);
	}
	
	public Page<SysUser> findPage(Page<SysUser> page, SysUser sysUser) {
		return super.findPage(page, sysUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SysUser sysUser) {

		if (sysUser.getIsNewRecord()){
			sysUser.setPassword(SystemService.entryptPassword(StringUtils.isEmpty(sysUser.getPassword()) ? "123456" : sysUser.getPassword()));

			sysUser.preInsert();
			dao.insert(sysUser);
			sysUserDao.insertUserRole(sysUser);

		}else{

			sysUser.preUpdate();
			dao.update(sysUser);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SysUser sysUser) {
		super.delete(sysUser);
	}
	
}