/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ctc.dao.SysUserDao;
import com.thinkgem.jeesite.modules.ctc.entity.CtcChannel;
import com.thinkgem.jeesite.modules.ctc.utils.CtcUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.CtcUser;
import com.thinkgem.jeesite.modules.ctc.dao.CtcUserDao;

/**
 * 导流用户Service
 *
 * @author 成茂涛
 * @version 2019-10-21
 */
@Service
@Transactional(readOnly = true)
public class CtcUserService extends CrudService<CtcUserDao, CtcUser> {

    @Autowired
    private CtcUserDao ctcUserDao;

    public CtcUser get(String id) {
        return super.get(id);
    }

    public List<CtcUser> findList(CtcUser ctcUser) {
        return super.findList(ctcUser);
    }

    public Page<CtcUser> findPage(Page<CtcUser> page, CtcUser ctcUser) {
        return super.findPage(page, ctcUser);
    }

    public int findklPage(CtcUser ctcUser) {


        // 导流的商务用户 查看
        if (StringUtils.isBlank(ctcUser.getChannelId())) {
            ctcUser.setChannelId(UserUtils.getUser().getNo());
        }

        CtcChannel ctcChannelById = CtcUtils.getCtcChannelById(ctcUser.getChannelId());

        // 结算比例 例如 60（100个结算60个）
        String deduProportion = ctcChannelById.getDeduProportion();
        int jsblInt = Integer.parseInt(deduProportion);

        // 数目低于多少个时 不扣量
        String deduQuanNum = ctcChannelById.getDeduQuanNum();
        int bklNum = Integer.parseInt(deduQuanNum);

        int countNum = ctcUserDao.findCountNum(ctcUser);

        // 需要扣量了
        if (bklNum < countNum) {
            ctcUser.setBklNum(bklNum);
            int klnum = countNum - bklNum;

            int jsnm = new BigDecimal(klnum).multiply(new BigDecimal(jsblInt)).divide(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_DOWN).intValue();

            if (jsnm == 0) {
                // 不需要扣量
                //dao.findList(ctcUser).size();
                return bklNum;
            } else {
                return bklNum + jsnm;
            }


        } else {
            // 不需要扣量
            return ctcUserDao.findCountNum(ctcUser);
        }
    }

    @Transactional(readOnly = false)
    public void save(CtcUser ctcUser) {
        super.save(ctcUser);
    }

    @Transactional(readOnly = false)
    public void delete(CtcUser ctcUser) {
        super.delete(ctcUser);
    }

}