/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ctc.entity.SysUser;

/**
 * 用户管理DAO接口
 * @author 成茂涛
 * @version 2019-10-19
 */
@MyBatisDao
public interface SysUserDao extends CrudDao<SysUser> {

    public int insertUserRole(SysUser user);
}