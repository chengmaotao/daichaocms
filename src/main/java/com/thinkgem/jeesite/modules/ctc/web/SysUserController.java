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
import com.thinkgem.jeesite.modules.ctc.entity.SysUser;
import com.thinkgem.jeesite.modules.ctc.service.SysUserService;

/**
 * 用户管理Controller
 * @author 成茂涛
 * @version 2019-10-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/sysUser")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@ModelAttribute
	public SysUser get(@RequestParam(required=false) String id) {
		SysUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysUserService.get(id);
		}
		if (entity == null){
			entity = new SysUser();
		}
		return entity;
	}
	
	@RequiresPermissions("ctc:sysUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysUser sysUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysUser> page = sysUserService.findPage(new Page<SysUser>(request, response), sysUser); 
		model.addAttribute("page", page);
		return "modules/ctc/sysUserList";
	}

	@RequiresPermissions("ctc:sysUser:view")
	@RequestMapping(value = "form")
	public String form(SysUser sysUser, Model model) {
		model.addAttribute("sysUser", sysUser);
		return "modules/ctc/sysUserForm";
	}

	@RequiresPermissions("ctc:sysUser:edit")
	@RequestMapping(value = "save")
	public String save(SysUser sysUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysUser)){
			return form(sysUser, model);
		}
		sysUserService.save(sysUser);
		addMessage(redirectAttributes, "保存用户成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/sysUser/?repage";
	}
	
	@RequiresPermissions("ctc:sysUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SysUser sysUser, RedirectAttributes redirectAttributes) {
		sysUserService.delete(sysUser);
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:"+Global.getAdminPath()+"/ctc/sysUser/?repage";
	}

}