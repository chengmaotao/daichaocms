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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ctc.entity.CtcStriking;
import com.thinkgem.jeesite.modules.ctc.service.CtcStrikingService;

/**
 * 醒目文案管理Controller
 * @author 成茂涛
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcStriking")
public class CtcStrikingController extends BaseController {

	@Autowired
	private CtcStrikingService ctcStrikingService;
	
	@ModelAttribute
	public CtcStriking get(@RequestParam(required=false) String id) {
		CtcStriking entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctcStrikingService.get(id);
		}
		if (entity == null){
			entity = new CtcStriking();
		}
		return entity;
	}
	
	@RequiresPermissions("ctc:ctcStriking:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtcStriking ctcStriking, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CtcStriking> page = ctcStrikingService.findPage(new Page<CtcStriking>(request, response), ctcStriking); 
		model.addAttribute("page", page);
		return "modules/ctc/ctcStrikingList";
	}

	@RequiresPermissions("ctc:ctcStriking:view")
	@RequestMapping(value = "form")
	public String form(CtcStriking ctcStriking, Model model) {
		model.addAttribute("ctcStriking", ctcStriking);
		return "modules/ctc/ctcStrikingForm";
	}

	@RequiresPermissions("ctc:ctcStriking:edit")
	@RequestMapping(value = "save")
	public String save(CtcStriking ctcStriking, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctcStriking)){
			return form(ctcStriking, model);
		}
		ctcStrikingService.save(ctcStriking);
		addMessage(redirectAttributes, "保存醒目文案成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcStriking/?repage";
	}
	
	@RequiresPermissions("ctc:ctcStriking:edit")
	@RequestMapping(value = "delete")
	public String delete(CtcStriking ctcStriking, RedirectAttributes redirectAttributes) {
		ctcStrikingService.delete(ctcStriking);
		addMessage(redirectAttributes, "删除醒目文案成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcStriking/?repage";
	}

}