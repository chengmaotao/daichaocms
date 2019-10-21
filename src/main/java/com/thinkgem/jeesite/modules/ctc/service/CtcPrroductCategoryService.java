/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.thinkgem.jeesite.modules.ctc.utils.CtcUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.CtcPrroductCategory;
import com.thinkgem.jeesite.modules.ctc.dao.CtcPrroductCategoryDao;
import org.springframework.web.multipart.MultipartFile;

/**
 * 产品类别管理Service
 * @author 成茂涛
 * @version 2019-10-19
 */
@Service
@Transactional(readOnly = true)
public class CtcPrroductCategoryService extends CrudService<CtcPrroductCategoryDao, CtcPrroductCategory> {

	public CtcPrroductCategory get(String id) {
		return super.get(id);
	}
	
	public List<CtcPrroductCategory> findList(CtcPrroductCategory ctcPrroductCategory) {
		return super.findList(ctcPrroductCategory);
	}
	
	public Page<CtcPrroductCategory> findPage(Page<CtcPrroductCategory> page, CtcPrroductCategory ctcPrroductCategory) {
		return super.findPage(page, ctcPrroductCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(CtcPrroductCategory ctcPrroductCategory,MultipartFile image) {
		if (ctcPrroductCategory.getIsNewRecord()){

			try {
				File f = CtcUtils.getFile(image);
				image.transferTo(f);//将上传的文件存储到指定位置
				ctcPrroductCategory.setProductCategoryUrl(f.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}

			ctcPrroductCategory.preInsert();
			dao.insert(ctcPrroductCategory);
		}else{

			if (image != null) {
				try {
					File f = CtcUtils.getFile(image);
					image.transferTo(f);//将上传的文件存储到指定位置
					ctcPrroductCategory.setProductCategoryUrl(f.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			ctcPrroductCategory.preUpdate();
			dao.update(ctcPrroductCategory);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CtcPrroductCategory ctcPrroductCategory) {
		super.delete(ctcPrroductCategory);
	}
	
}