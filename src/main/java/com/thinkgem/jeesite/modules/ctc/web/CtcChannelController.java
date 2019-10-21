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
import com.thinkgem.jeesite.modules.ctc.entity.CtcChannel;
import com.thinkgem.jeesite.modules.ctc.service.CtcChannelService;

/**
 * 渠道管理Controller
 * @author 成茂涛
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcChannel")
public class CtcChannelController extends BaseController {

	@Autowired
	private CtcChannelService ctcChannelService;
	
	@ModelAttribute
	public CtcChannel get(@RequestParam(required=false) String id) {
		CtcChannel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ctcChannelService.get(id);
		}
		if (entity == null){
			entity = new CtcChannel();
		}
		return entity;
	}
	
	@RequiresPermissions("ctc:ctcChannel:view")
	@RequestMapping(value = {"list", ""})
	public String list(CtcChannel ctcChannel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CtcChannel> page = ctcChannelService.findPage(new Page<CtcChannel>(request, response), ctcChannel); 
		model.addAttribute("page", page);
		return "modules/ctc/ctcChannelList";
	}

	@RequiresPermissions("ctc:ctcChannel:view")
	@RequestMapping(value = "form")
	public String form(CtcChannel ctcChannel, Model model) {
		model.addAttribute("ctcChannel", ctcChannel);
		return "modules/ctc/ctcChannelForm";
	}

	@RequiresPermissions("ctc:ctcChannel:edit")
	@RequestMapping(value = "save")
	public String save(CtcChannel ctcChannel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ctcChannel)){
			return form(ctcChannel, model);
		}
		ctcChannelService.save(ctcChannel);
		addMessage(redirectAttributes, "保存渠道成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcChannel/?repage";
	}
	
	@RequiresPermissions("ctc:ctcChannel:edit")
	@RequestMapping(value = "delete")
	public String delete(CtcChannel ctcChannel, RedirectAttributes redirectAttributes) {
		ctcChannelService.delete(ctcChannel);
		addMessage(redirectAttributes, "删除渠道成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/ctcChannel/?repage";
	}

}