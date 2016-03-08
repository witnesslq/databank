/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.entity;

import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * <p>Title: 日志实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午5:38
 */
public class LogEntity extends Entity {

    private static final long serialVersionUID = -4082457458619235442L;

    /**
     * 执行者
     */
    private String executor;

    /**
     * 数据源信息
     */
    private Long dsId;

    /**
     * Schema
     */
    private String schema;

    /**
     * 查询语句
     */
    private String query;

    /**
     * 日志类型
     */
    private String level;

    /**
     * 执行结果
     */
    private String status = "00000";

    /**
     * 打分机制
     */
    private long score;

    /**
     * 数据条数
     */
    private long num = 0;

    /**
     * 查询开始执行时间
     */
    private Date startDateTime;

    /**
     * 方便查询
     */
    private String startDate;

    /**
     * SQL 执行用时
     */
    private long usedMilliSecond;

    /**
     * 默认构造方法
     */
    public LogEntity() {
        // Nothing
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Long getDsId() {
        return dsId;
    }

    public void setDsId(Long dsId) {
        this.dsId = dsId;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public LogLevelEnum getLevel() {
        return LogLevelEnum.parse(this.level);
    }

    public void setLevel(LogLevelEnum level) {
        this.level = level.getValue();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getUsedMilliSecond() {
        return usedMilliSecond;
    }

    public void setUsedMilliSecond(long usedMilliSecond) {
        this.usedMilliSecond = usedMilliSecond;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
