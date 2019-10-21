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
import com.thinkgem.jeesite.modules.ctc.entity.CtcPrroductCategory;
import com.thinkgem.jeesite.modules.ctc.service.CtcPrroductCategoryService;

/**
 * 产品类别管理Controller
 * @author 成茂涛
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcPrroductCategory")
public class CtcPrroductCategoryController extends BaseController {

	@Autowired
	private CtcPrroductCategoryService ctcPrroductCategoryService;
	
	@ModelAttribute
	public CtcPrroductCategory get(@RequestParam(required=false) String id) {
		CtcPrroductCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctcPrroductCategoryService.get(id);
		}
		if (entity == null){
			entity = new CtcPrroductCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("ctc:ctcPrroductCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtcPrroductCategory ctcPrroductCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CtcPrroductCategory> page = ctcPrroductCategoryService.findPage(new Page<CtcPrroductCategory>(request, response), ctcPrroductCategory); 
		model.addAttribute("page", page);
		return "modules/ctc/ctcPrroductCategoryList";
	}

	@RequiresPermissions("ctc:ctcPrroductCategory:view")
	@RequestMapping(value = "form")
	public String form(CtcPrroductCategory ctcPrroductCategory, Model model) {
		model.addAttribute("ctcPrroductCategory", ctcPrroductCategory);
		return "modules/ctc/ctcPrroductCategoryForm";
	}

	@RequiresPermissions("ctc:ctcPrroductCategory:edit")
	@RequestMapping(value = "save")
	public String save(CtcPrroductCategory ctcPrroductCategory, Model model, RedirectAttributes redirectAttributes,MultipartFile image) {
		if (!beanValidator(model, ctcPrroductCategory)){
			return form(ctcPrroductCategory, model);
		}
		ctcPrroductCategoryService.save(ctcPrroductCategory,image);
		addMessage(redirectAttributes, "保存产品类别成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcPrroductCategory/?repage";
	}
	
	@RequiresPermissions("ctc:ctcPrroductCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(CtcPrroductCategory ctcPrroductCategory, RedirectAttributes redirectAttributes) {
		ctcPrroductCategoryService.delete(ctcPrroductCategory);
		addMessage(redirectAttributes, "删除产品类别成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcPrroductCategory/?repage";
	}

}