package com.thinkgem.jeesite.modules.ctc.config;

import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * @Auther: CTC
 * @Date: 2019/10/21 16:25
 * @Description:
 */
public enum UserStateEm {


    REGISTER0("0","仅注册"), REALNAME1("1","已实名");
    private String code;
    private String codeName;

    UserStateEm(String code,String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public static String getCodeNameByCode(String code){
        UserStateEm[] values = UserStateEm.values();
        for (UserStateEm value : values) {
            if(StringUtils.equals(value.code,code)){
                return value.codeName;
            }
        }
        return code;
    }
}
