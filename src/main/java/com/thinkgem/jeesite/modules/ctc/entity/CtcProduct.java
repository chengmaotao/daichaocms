/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ctc.entity;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ctc.utils.CtcUtils;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.List;

/**
 * 贷超产品管理Entity
 *
 * @author 成茂涛
 * @version 2019-10-21
 */
public class CtcProduct extends DataEntity<CtcProduct> {

    private static final long serialVersionUID = 1L;
    private String productId;        // product_id
    private String productCategoryId;        // 产品分类ID多个以逗号隔开
    private String productName;        // 产品名称
    private String logo;        // logo
    private String voLogo;
    private String quota;        // 额度 3至5万（列表展示）
    private String rate;        // 最低日利率0.01%  （列表展示）
    private String ctcDescribe;        // 344万人申请（列表展示）
    private String detailsDescribe;        // 详情描述（门槛低 下款快 额度高）
    private String applyNums;        // 申请人数3449521（详情页展示）
    private String fastestLoanTime;        // 最快放款时间1分钟（详情页展示）
    private String successRate;        // 成功率98%（详情页展示）
    private String quotaRange;        // 额度范围30000-50000（详情页展示）
    private String termRange;        // 期限范围3个月-12个月（详情页展示）
    private String dateRate;        // 日利率0.01-0.02（详情页展示）
    private String applyProcess;        // 申请流程（多条件已英文加号+隔开）
    private String applyCondition;        // 申请条件（多条件已英文加号+隔开）
    private String applyMaterial;        // 所需材料（多条件已英文加号+隔开）
    private String newProduct;        // 0是新品推荐  其他不是

    public CtcProduct() {
        super();
    }

    public CtcProduct(String id) {
        super(id);
    }

    public String getVoLogo() {
        // return voLogo;
        return new StringBuffer("").append(Global.getConfig("ctc.http.pref")).append(Global.getConfig("ctc.upload.file.path")).append(logo).toString();
    }

    public void setVoLogo(String voLogo) {
        this.voLogo = voLogo;
    }

    @Length(min = 1, max = 32, message = "product_id长度必须介于 1 和 32 之间")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Length(min = 1, max = 32, message = "产品分类ID多个以逗号隔开长度必须介于 1 和 32 之间")
    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Length(min = 1, max = 50, message = "产品名称长度必须介于 1 和 50 之间")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Length(min = 0, max = 255, message = "logo长度必须介于 0 和 255 之间")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Length(min = 1, max = 50, message = "额度 3至5万（列表展示）长度必须介于 1 和 50 之间")
    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Length(min = 1, max = 50, message = "344万人申请（列表展示）长度必须介于 1 和 50 之间")
    public String getCtcDescribe() {
        return ctcDescribe;
    }

    public void setCtcDescribe(String ctcDescribe) {
        this.ctcDescribe = ctcDescribe;
    }

    @Length(min = 1, max = 50, message = "详情描述（门槛低 下款快 额度高）长度必须介于 1 和 50 之间")
    public String getDetailsDescribe() {
        return detailsDescribe;
    }

    public void setDetailsDescribe(String detailsDescribe) {
        this.detailsDescribe = detailsDescribe;
    }

    @Length(min = 1, max = 11, message = "申请人数3449521（详情页展示）长度必须介于 1 和 11 之间")
    public String getApplyNums() {
        return applyNums;
    }

    public void setApplyNums(String applyNums) {
        this.applyNums = applyNums;
    }

    @Length(min = 1, max = 50, message = "最快放款时间1分钟（详情页展示）长度必须介于 1 和 50 之间")
    public String getFastestLoanTime() {
        return fastestLoanTime;
    }

    public void setFastestLoanTime(String fastestLoanTime) {
        this.fastestLoanTime = fastestLoanTime;
    }

    @Length(min = 1, max = 50, message = "成功率98%（详情页展示）长度必须介于 1 和 50 之间")
    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    @Length(min = 1, max = 50, message = "额度范围30000-50000（详情页展示）长度必须介于 1 和 50 之间")
    public String getQuotaRange() {
        return quotaRange;
    }

    public void setQuotaRange(String quotaRange) {
        this.quotaRange = quotaRange;
    }

    @Length(min = 1, max = 50, message = "期限范围3个月-12个月（详情页展示）长度必须介于 1 和 50 之间")
    public String getTermRange() {
        return termRange;
    }

    public void setTermRange(String termRange) {
        this.termRange = termRange;
    }

    public String getDateRate() {
        return dateRate;
    }

    public void setDateRate(String dateRate) {
        this.dateRate = dateRate;
    }

    @Length(min = 1, max = 100, message = "申请流程（多条件已英文加号+隔开）长度必须介于 1 和 100 之间")
    public String getApplyProcess() {
        return applyProcess;
    }

    public void setApplyProcess(String applyProcess) {
        this.applyProcess = applyProcess;
    }

    @Length(min = 1, max = 100, message = "申请条件（多条件已英文加号+隔开）长度必须介于 1 和 100 之间")
    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition;
    }

    @Length(min = 1, max = 100, message = "所需材料（多条件已英文加号+隔开）长度必须介于 1 和 100 之间")
    public String getApplyMaterial() {
        return applyMaterial;
    }

    public void setApplyMaterial(String applyMaterial) {
        this.applyMaterial = applyMaterial;
    }

    @Length(min = 1, max = 1, message = "0是新品推荐  其他不是长度必须介于 1 和 1 之间")
    public String getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(String newProduct) {
        this.newProduct = newProduct;
    }


    private List<String> productCategoryIdList;

    private String productCategoryNames;

    public String getProductCategoryNames() {

        if (StringUtils.isNotBlank(getProductCategoryId())) {
            StringBuffer tempPcName = new StringBuffer("");
            String[] split = getProductCategoryId().split(",");
            for (String s : split) {
                String productCategoryName = CtcUtils.getProductCategoryById(s).getProductCategoryName();
                tempPcName.append(productCategoryName).append(",");
            }

            String stempPcName = tempPcName.toString();
            if (StringUtils.isNotBlank(stempPcName)) {
                return stempPcName.substring(0, stempPcName.length() - 1);
            }

        }
        return productCategoryNames;
    }

    public void setProductCategoryNames(String productCategoryNames) {
        this.productCategoryNames = productCategoryNames;
    }

    public List<String> getProductCategoryIdList() {
        if (StringUtils.isNotBlank(getProductCategoryId())) {
            List<String> tempList = Lists.newArrayList();
            String[] split = getProductCategoryId().split(",");
            for (String s : split) {
                tempList.add(s);
            }
            return tempList;
        }

        return productCategoryIdList;
    }

    public void setProductCategoryIdList(List<String> productCategoryIdList) {

        StringBuffer tempPcid = new StringBuffer("");
        /* StringBuffer tempPcName = new StringBuffer("");*/
        for (String s : productCategoryIdList) {
            tempPcid.append(s).append(",");

            //  String productCategoryName = CtcUtils.getProductCategoryById(s).getProductCategoryName();
            /* tempPcName.append(productCategoryName).append(",");*/
        }

        String stempPcid = tempPcid.toString();
        if (StringUtils.isNotBlank(stempPcid)) {
            this.productCategoryId = stempPcid.substring(0, stempPcid.length() - 1);
        }

/*        String stempPcName = tempPcName.toString();
        if (StringUtils.isNotBlank(stempPcName)) {
            this.productCategoryNames = stempPcName.substring(0,stempPcName.length()-1);
        }*/
    }
}