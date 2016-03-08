package com.yeepay.g3.app.databank.entity;

import com.google.common.collect.ImmutableList;
import com.yeepay.g3.app.databank.utils.Collections3;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * Created by hgs on 15/12/16.
 */
public class ApplyEntity extends Entity {

    private static final long serialVersionUID = -940070857783213033L;

    /**
     * 用户ID
     */
    private long userId;
    /**
     * 数据源ID
     */
    private long dsId;

    /**
     * 申请理由
     */
    private String applyReason;
    /**
     * 申请状态
     */
    private String applyStatus;

    /**
     * 审核人ID
     */
    private String reviewName;


    /**
     * 审核信息
     */
    private String reviewMessage;

    /**
     * 申请使用时间
     */

    private Date applyUseDateTime;

    /**
     * 允许使用时间
     */

    private Date allowUseDateTime;

    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 最后修改时间
     */
    private Date lastModifiedDate;


    /**
     * 允许的表权限
     */
    private String allowTable;

    /**
     * 禁止的表权限
     */
    private String notAllowTable;

    /**
     * 允许的列权限
     */
    private String allowColumn;

    /**
     * 禁止的列权限
     */
    private String notAllowColumn;

    public String getAllowTable() {
        return allowTable;
    }

    public void setAllowTable(String allowTable) {
        this.allowTable = allowTable;
    }

    public String getNotAllowTable() {
        return notAllowTable;
    }

    public void setNotAllowTable(String notAllowTable) {
        this.notAllowTable = notAllowTable;
    }

    public String getAllowColumn() {
        return allowColumn;
    }

    public void setAllowColumn(String allowColumn) {
        this.allowColumn = allowColumn;
    }

    public String getNotAllowColumn() {
        return notAllowColumn;
    }

    public void setNotAllowColumn(String notAllowColumn) {
        this.notAllowColumn = notAllowColumn;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDsId() {
        return dsId;
    }

    public void setDsId(long dsId) {
        this.dsId = dsId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    public Date getApplyUseDateTime() {
        return applyUseDateTime;
    }

    public void setApplyUseDateTime(Date applyUseDateTime) {
        this.applyUseDateTime = applyUseDateTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getAllowUseDateTime() {
        return allowUseDateTime;
    }

    public void setAllowUseDateTime(Date allowUseDateTime) {
        this.allowUseDateTime = allowUseDateTime;
    }


    /**
     * 获取权限列表
     *
     * @return 权限列表
     */
    public static List<String> getPermissionList(String permissions) {
        if (null == permissions) return null;
        return ImmutableList.copyOf(StringUtils.split(permissions, ","));
    }

    public String getPermissionList(List<String> permissionList) {
        return Collections3.convertToString(permissionList, ",");
    }
}
