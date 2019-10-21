/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import com.thinkgem.jeesite.common.config.Global;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品类别管理Entity
 * @author 成茂涛
 * @version 2019-10-19
 */
public class CtcPrroductCategory extends DataEntity<CtcPrroductCategory> {
	
	private static final long serialVersionUID = 1L;
	private String productCategoryId;		// product_category_id
	private String productCategoryName;		// 产品类别名称
	private String productCategoryUrl;		// 产品类别对应的图标

	private String voProductCategoryUrl;

	public String getVoProductCategoryUrl() {
		return new StringBuffer("").append(Global.getConfig("ctc.http.pref")).append(Global.getConfig("ctc.upload.file.path")).append(productCategoryUrl).toString();
	}

	public void setVoProductCategoryUrl(String voProductCategoryUrl) {
		this.voProductCategoryUrl = voProductCategoryUrl;
	}

	public CtcPrroductCategory() {
		super();
	}

	public CtcPrroductCategory(String id){
		super(id);
	}

	@Length(min=1, max=11, message="product_category_id长度必须介于 1 和 11 之间")
	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	
	@Length(min=1, max=50, message="产品类别名称长度必须介于 1 和 50 之间")
	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	
	@Length(min=0, max=255, message="产品类别对应的图标长度必须介于 0 和 255 之间")
	public String getProductCategoryUrl() {
		return productCategoryUrl;
	}

	public void setProductCategoryUrl(String productCategoryUrl) {
		this.productCategoryUrl = productCategoryUrl;
	}
	
}