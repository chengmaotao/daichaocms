/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.ctc.utils.CtcUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ctc.entity.CtcAdvet;
import com.thinkgem.jeesite.modules.ctc.dao.CtcAdvetDao;
import org.springframework.web.multipart.MultipartFile;

/**
 * 广告轮播图管理Service
 *
 * @author 成茂涛
 * @version 2019-10-19
 */
@Service
@Transactional(readOnly = true)
public class CtcAdvetService extends CrudService<CtcAdvetDao, CtcAdvet> {

    public CtcAdvet get(String id) {
        return super.get(id);
    }

    public List<CtcAdvet> findList(CtcAdvet ctcAdvet) {
        return super.findList(ctcAdvet);
    }

    public Page<CtcAdvet> findPage(Page<CtcAdvet> page, CtcAdvet ctcAdvet) {
        return super.findPage(page, ctcAdvet);
    }

    @Transactional(readOnly = false)
    public void save(CtcAdvet ctcAdvet, MultipartFile image) {

        if (ctcAdvet.getIsNewRecord()) {

            try {
                File f = CtcUtils.getFile(image);
                image.transferTo(f);//将上传的文件存储到指定位置
                ctcAdvet.setAdvetUrl(f.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ctcAdvet.preInsert();
            dao.insert(ctcAdvet);
        } else {
            if (image != null) {
                try {
                    File f = CtcUtils.getFile(image);
                    image.transferTo(f);//将上传的文件存储到指定位置
                    ctcAdvet.setAdvetUrl(f.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ctcAdvet.preUpdate();
            dao.update(ctcAdvet);
        }
    }

    @Transactional(readOnly = false)
    public void delete(CtcAdvet ctcAdvet) {
        super.delete(ctcAdvet);
    }

}