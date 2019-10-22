/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.ctc.entity.CtcChannel;
import com.thinkgem.jeesite.modules.ctc.utils.CtcUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.ctc.entity.CtcUser;
import com.thinkgem.jeesite.modules.ctc.service.CtcUserService;

/**
 * 导流用户Controller
 *
 * @author 成茂涛
 * @version 2019-10-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ctc/ctcUser")
public class CtcUserController extends BaseController {

    @Autowired
    private CtcUserService ctcUserService;

    @ModelAttribute
    public CtcUser get(@RequestParam(required = false) String id) {
        CtcUser entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = ctcUserService.get(id);
        }
        if (entity == null) {
            entity = new CtcUser();
        }
        return entity;
    }

    @RequiresPermissions("ctc:ctcUser:view")
    @RequestMapping(value = "klindex")
    public String klindex(CtcUser ctcUser, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<CtcUser> page = new Page<CtcUser>(request, response);
        ctcUser.setPage(page);
        page.setList(null);

        model.addAttribute("page", page);
        return "modules/ctc/ctcKouLiangUserList";
    }

    @RequiresPermissions("ctc:ctcUser:view")
    @RequestMapping(value = "kllist")
    public String kllist(CtcUser ctcUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        int regesitNum = ctcUserService.findklPage(ctcUser);
        model.addAttribute("regesitNum", regesitNum);
        return "modules/ctc/ctcKouLiangUserList";
    }


    @RequiresPermissions("ctc:ctcUser:view")
    @RequestMapping(value = "daoliulist")
    public String daoliulist(CtcUser ctcUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        int regesitNum = ctcUserService.findklPage(ctcUser);

        CtcChannel ctcChannel = CtcUtils.getCtcChannelById(UserUtils.getUser().getNo());


        model.addAttribute("ctcChannel",ctcChannel.getChannelName());
        model.addAttribute("regesitNum", regesitNum);
        return "modules/ctc/ctcDaoLiuShangWuUserList";
    }


    @RequiresPermissions("ctc:ctcUser:view")
    @RequestMapping(value = {"list", ""})
    public String list(CtcUser ctcUser, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<CtcUser> page = ctcUserService.findPage(new Page<CtcUser>(request, response), ctcUser);
        model.addAttribute("page", page);
        return "modules/ctc/ctcUserList";
    }

    @RequiresPermissions("ctc:ctcUser:view")
    @RequestMapping(value = "form")
    public String form(CtcUser ctcUser, Model model) {
        model.addAttribute("ctcUser", ctcUser);
        return "modules/ctc/ctcUserForm";
    }

    @RequiresPermissions("ctc:ctcUser:edit")
    @RequestMapping(value = "save")
    public String save(CtcUser ctcUser, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, ctcUser)) {
            return form(ctcUser, model);
        }
        ctcUserService.save(ctcUser);
        addMessage(redirectAttributes, "保存导流用户成功");
        return "redirect:" + Global.getAdminPath() + "/ctc/ctcUser/?repage";
    }

    @RequiresPermissions("ctc:ctcUser:edit")
    @RequestMapping(value = "delete")
    public String delete(CtcUser ctcUser, RedirectAttributes redirectAttributes) {
        ctcUserService.delete(ctcUser);
        addMessage(redirectAttributes, "删除导流用户成功");
        return "redirect:" + Global.getAdminPath() + "/ctc/ctcUser/?repage";
    }

}