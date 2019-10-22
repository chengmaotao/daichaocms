/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ctc.config.UserStateEm;
import com.thinkgem.jeesite.modules.sys.entity.User;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 导流用户Entity
 *
 * @author 成茂涛
 * @version 2019-10-21
 */
public class CtcUser extends DataEntity<CtcUser> {

    private static final long serialVersionUID = 1L;
    private String userName;        // 用户名
    private String userSfz;        // 身份证号
    private String userPhone;        // 手机号
    private String channelId;        // 来源渠道
    private String userState;        // 0仅注册1已实名

    private String voUserState;
    private Date userRealNameTime;        // 实名成功时间

    private String userId;


    private Date beginDate;
    private Date endDate;

    public String getVoUserState() {
        if (StringUtils.isNotBlank(getUserState())) {
            return UserStateEm.getCodeNameByCode(getUserState());
        }

        return voUserState;

    }

    public void setVoUserState(String voUserState) {
        this.voUserState = voUserState;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CtcUser() {
        super();
    }

    public CtcUser(String id) {
        super(id);
    }

    @Length(min = 0, max = 32, message = "用户名长度必须介于 0 和 32 之间")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Length(min = 0, max = 255, message = "身份证号长度必须介于 0 和 255 之间")
    public String getUserSfz() {
        return userSfz;
    }

    public void setUserSfz(String userSfz) {
        this.userSfz = userSfz;
    }

    @Length(min = 1, max = 20, message = "手机号长度必须介于 1 和 20 之间")
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Length(min = 1, max = 32, message = "来源渠道长度必须介于 1 和 32 之间")
    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Length(min = 1, max = 10, message = "0仅注册1已实名长度必须介于 1 和 10 之间")
    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUserRealNameTime() {
        return userRealNameTime;
    }

    public void setUserRealNameTime(Date userRealNameTime) {
        this.userRealNameTime = userRealNameTime;
    }


    // 不扣量
    private int bklNum;

    private int jsendLength;

    public int getJsendLength() {
        return jsendLength;
    }

    public void setJsendLength(int jsendLength) {
        this.jsendLength = jsendLength;
    }

    public int getBklNum() {
        return bklNum;
    }

    public void setBklNum(int bklNum) {
        this.bklNum = bklNum;
    }


    private List<CtcUser> exUsers;

    public List<CtcUser> getExUsers() {
        return exUsers;
    }

    public void setExUsers(List<CtcUser> exUsers) {
        this.exUsers = exUsers;
    }
}