/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ctc.entity.CtcProduct;

/**
 * 贷超产品管理DAO接口
 * @author 成茂涛
 * @version 2019-10-21
 */
@MyBatisDao
public interface CtcProductDao extends CrudDao<CtcProduct> {
	
}