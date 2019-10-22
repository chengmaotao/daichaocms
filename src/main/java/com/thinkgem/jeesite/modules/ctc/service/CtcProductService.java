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
import com.thinkgem.jeesite.modules.ctc.entity.CtcProduct;
import com.thinkgem.jeesite.modules.ctc.dao.CtcProductDao;
import org.springframework.web.multipart.MultipartFile;

/**
 * 贷超产品管理Service
 * @author 成茂涛
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class CtcProductService extends CrudService<CtcProductDao, CtcProduct> {

	public CtcProduct get(String id) {
		return super.get(id);
	}
	
	public List<CtcProduct> findList(CtcProduct ctcProduct) {
		return super.findList(ctcProduct);
	}
	
	public Page<CtcProduct> findPage(Page<CtcProduct> page, CtcProduct ctcProduct) {
		return super.findPage(page, ctcProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(CtcProduct ctcProduct, MultipartFile image) {
        if (image != null) {
            try {
                File f = CtcUtils.getFile(image);
                image.transferTo(f);//将上传的文件存储到指定位置
                ctcProduct.setLogo(f.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		if (ctcProduct.getIsNewRecord()) {
			ctcProduct.preInsert();
			dao.insert(ctcProduct);
		} else {
			ctcProduct.preUpdate();
			dao.update(ctcProduct);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(CtcProduct ctcProduct) {
		super.delete(ctcProduct);
	}
	
}