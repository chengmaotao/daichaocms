/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ctc.entity.CtcProduct;
import com.thinkgem.jeesite.modules.ctc.service.CtcProductService;

/**
 * 贷超产品管理Controller
 * @author 成茂涛
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcProduct")
public class CtcProductController extends BaseController {

	@Autowired
	private CtcProductService ctcProductService;
	
	@ModelAttribute
	public CtcProduct get(@RequestParam(required=false) String id) {
		CtcProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctcProductService.get(id);
		}
		if (entity == null){
			entity = new CtcProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("ctc:ctcProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtcProduct ctcProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CtcProduct> page = ctcProductService.findPage(new Page<CtcProduct>(request, response), ctcProduct); 
		model.addAttribute("page", page);
		return "modules/ctc/ctcProductList";
	}

	@RequiresPermissions("ctc:ctcProduct:view")
	@RequestMapping(value = "form")
	public String form(CtcProduct ctcProduct, Model model) {
		model.addAttribute("ctcProduct", ctcProduct);
		return "modules/ctc/ctcProductForm";
	}

	@RequiresPermissions("ctc:ctcProduct:edit")
	@RequestMapping(value = "save")
	public String save(CtcProduct ctcProduct, Model model, RedirectAttributes redirectAttributes,MultipartFile image) {
		if (!beanValidator(model, ctcProduct)){
			return form(ctcProduct, model);
		}
		ctcProductService.save(ctcProduct,image);
		addMessage(redirectAttributes, "保存贷超产品成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcProduct/?repage";
	}
	
	@RequiresPermissions("ctc:ctcProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(CtcProduct ctcProduct, RedirectAttributes redirectAttributes) {
		ctcProductService.delete(ctcProduct);
		addMessage(redirectAttributes, "删除贷超产品成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcProduct/?repage";
	}

}