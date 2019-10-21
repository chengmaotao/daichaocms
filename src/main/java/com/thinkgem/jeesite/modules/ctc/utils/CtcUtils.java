package com.thinkgem.jeesite.modules.ctc.utils;

import com.thinkgem.jeesite.common.config.Global;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Auther: CTC
 * @Date: 2019/10/19 16:12
 * @Description:
 */
public class CtcUtils {

    public static File getFile(MultipartFile image) {
        String filename = image.getOriginalFilename();//获取上传时的文件名称
        filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(filename);//创建一个新的文件名称    getExtension(name):获取文件后缀名
        File f = new File(Global.getConfig("ctc.upload.file.path"), filename);

        if (!f.exists()) {
            f.mkdirs();
        }

        return f;
    }
}
