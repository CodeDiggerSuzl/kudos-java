package com.suzl.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/5/29 6:02 下午
 */
public class DriverInviteDO extends BaseDO {
    /**
     * 礼包名称
     */
    private String name;
    /**
     * 礼包类型（1.邀请司机。2.邀请乘客）
     */
    private Integer type;
    /**
     * 获得金额
     */
    private BigDecimal inviteMoney;
    /**
     * 总数量
     */
    private Integer totalNumber;
    /**
     * 剩余量
     */
    private Integer remainNumber;
    /**
     * 发送城市Code
     */
    private String cityCodes;
    /**
     * 发送城市名称
     */
    private String cityNames;
    /**
     * 被邀请乘客礼包ID
     */
    private Long invitedGiftId;
    /**
     * 被邀请者礼包名称
     */
    private String invitedGiftName;
    /**
     * 被邀请司机所得金额
     */
    private BigDecimal invitedMoney;
    /**
     * 礼包获得方式：礼包获得方式：1.司机入驻，2.完成首单
     */
    private Integer getMode;
    /**
     * 礼包状态：0.初始化，1.进行中，2.关闭，3.过期
     */
    private Integer status;
    /**
     * 创建者
     */
    private String createdUser;
    /**
     * 修改者
     */
    private String modifiedUser;

    //20190219新增字段
    /**
     * 活动启用日期
     */
    private Date effectiveDate;
    /**
     * 有效期，日期
     */
    private Date expiryDate;

    //20190814增加字段
    /**
     * 1.单日 2.自然周累计 3.活动期间累计
     */
    private Integer awardCycle;
    /**
     * 1.人人奖励 2.阶梯奖励
     */
    private Integer awardType;
    /**
     * 司推司：奖励层级
     */
    private String awardHierarchy;

    /**
     * 消息推送 1开启 0关闭
     */
    private Integer pushType;

    /**
     * 完单开关 1开启 0关闭
     */
    private Integer rewardStatus;

    /**
     * 完单激活天数
     */
    private Integer activationDate;

    /**
     * 完单奖励阶层配置
     */
    private String orderAwardHierarchy;

    /**
     * 完单奖励阶层配置
     */
    private Integer version;

    /**
     * 活动奖励统计：0未统计  1成功  2失败
     */
    private Integer grantStatus;
    /**
     * 邀请金额的 string 形式 (schedule BigDecimal 格式无法转换)
     */
    private String inviteMoneyStr;

    public String getInviteMoneyStr() {
        return inviteMoneyStr;
    }

    public void setInviteMoneyStr(String inviteMoneyStr) {
        this.inviteMoneyStr = inviteMoneyStr;
    }

    public Integer getGrantStatus() {
        return grantStatus;
    }

    public void setGrantStatus(Integer grantStatus) {
        this.grantStatus = grantStatus;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public Integer getRewardStatus() {
        return rewardStatus;
    }

    public void setRewardStatus(Integer rewardStatus) {
        this.rewardStatus = rewardStatus;
    }

    public Integer getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Integer activationDate) {
        this.activationDate = activationDate;
    }

    public String getOrderAwardHierarchy() {
        return orderAwardHierarchy;
    }

    public void setOrderAwardHierarchy(String orderAwardHierarchy) {
        this.orderAwardHierarchy = orderAwardHierarchy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getInviteMoney() {
        return inviteMoney;
    }

    public void setInviteMoney(BigDecimal inviteMoney) {
        this.inviteMoney = inviteMoney;
    }

    public Integer getTotalNumber() {
        return this.totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getRemainNumber() {
        return this.remainNumber;
    }

    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
    }

    public String getCityCodes() {
        return this.cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getCityNames() {
        return this.cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public Long getInvitedGiftId() {
        return this.invitedGiftId;
    }

    public void setInvitedGiftId(Long invitedGiftId) {
        this.invitedGiftId = invitedGiftId;
    }

    public BigDecimal getInvitedMoney() {
        return this.invitedMoney;
    }

    public void setInvitedMoney(BigDecimal invitedMoney) {
        this.invitedMoney = invitedMoney;
    }

    public Integer getGetMode() {
        return this.getMode;
    }

    public void setGetMode(Integer getMode) {
        this.getMode = getMode;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedUser() {
        return this.createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return this.modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    public String getInvitedGiftName() {
        return invitedGiftName;
    }

    public void setInvitedGiftName(String invitedGiftName) {
        this.invitedGiftName = invitedGiftName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getAwardCycle() {
        return awardCycle;
    }

    public void setAwardCycle(Integer awardCycle) {
        this.awardCycle = awardCycle;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardHierarchy() {
        return awardHierarchy;
    }

    public void setAwardHierarchy(String awardHierarchy) {
        this.awardHierarchy = awardHierarchy;
    }
}
