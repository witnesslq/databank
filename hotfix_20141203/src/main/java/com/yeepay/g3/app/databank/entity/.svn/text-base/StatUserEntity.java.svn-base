/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>Title: 按开发统计实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-27 下午17:42
 */
public class StatUserEntity extends Entity {

    private static final long serialVersionUID = -4082457458619235442L;

    /**
     * 日期
     */
    private String date;

    /**
     * 执行者
     */
    private String loginName;

    /**
     * 使用次数
     */
    private long num;

    /**
     * 平均分数
     */
    private long avgScore;

    /**
     * 总计执行时间
     */
    private long sumTime;

    /**
     * 平均执行时间
     */
    private long avgTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public long getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(long avgScore) {
        this.avgScore = avgScore;
    }

    public long getSumTime() {
        return sumTime;
    }

    public void setSumTime(long sumTime) {
        this.sumTime = sumTime;
    }

    public long getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(long avgTime) {
        this.avgTime = avgTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
