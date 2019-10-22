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
import com.thinkgem.jeesite.modules.ctc.entity.CtcAdvet;
import com.thinkgem.jeesite.modules.ctc.service.CtcAdvetService;

/**
 * 广告轮播图管理Controller
 * @author 成茂涛
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcAdvet")
public class CtcAdvetController extends BaseController {

	@Autowired
	private CtcAdvetService ctcAdvetService;

	@ModelAttribute
	public CtcAdvet get(@RequestParam(required=false) String id) {
		CtcAdvet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctcAdvetService.get(id);
		}
		if (entity == null){
			entity = new CtcAdvet();
		}
		return entity;
	}

	@RequiresPermissions("ctc:ctcAdvet:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtcAdvet ctcAdvet, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CtcAdvet> page = ctcAdvetService.findPage(new Page<CtcAdvet>(request, response), ctcAdvet);
		model.addAttribute("page", page);
		return "modules/ctc/ctcAdvetList";
	}

	@RequiresPermissions("ctc:ctcAdvet:view")
	@RequestMapping(value = "form")
	public String form(CtcAdvet ctcAdvet, Model model) {
		model.addAttribute("ctcAdvet", ctcAdvet);
		return "modules/ctc/ctcAdvetForm";
	}

	@RequiresPermissions("ctc:ctcAdvet:edit")
	@RequestMapping(value = "save")
	public String save(CtcAdvet ctcAdvet, Model model, RedirectAttributes redirectAttributes,MultipartFile image) {
		if (!beanValidator(model, ctcAdvet)){
			return form(ctcAdvet, model);
		}
		ctcAdvetService.save(ctcAdvet,image);
		addMessage(redirectAttributes, "保存广告轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcAdvet/?repage";
	}

	@RequiresPermissions("ctc:ctcAdvet:edit")
	@RequestMapping(value = "delete")
	public String delete(CtcAdvet ctcAdvet, RedirectAttributes redirectAttributes) {
		ctcAdvetService.delete(ctcAdvet);
		addMessage(redirectAttributes, "删除广告轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcAdvet/?repage";
	}

}