/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.entity;

import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * <p>Title: 数据源实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-3 上午9:16
 */
public class DataBankSource extends Entity {

    private static final long serialVersionUID = -4082457458619235442L;

    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 数据库连接字符串
     */
    private String connStr;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String passwordCipher;

    private String signture;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 最后修改时间
     */
    private Date lastModifiedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数据库类型枚举
     *
     * @return
     */
    public DatabaseTypeEnum getDbType() {
        return DatabaseTypeEnum.parse(this.dbType);
    }

    /**
     * 设置数据库环境类型
     *
     * @param dbType 数据库类型
     */
    public void setDbType(DatabaseTypeEnum dbType) {
        this.dbType = dbType.getValue();
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getConnStr() {
        return connStr;
    }

    public void setConnStr(String connStr) {
        this.connStr = connStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCipher() {
        return passwordCipher;
    }

    public void setPasswordCipher(String passwordCipher) {
        this.passwordCipher = passwordCipher;
    }

    public String getSignture() {
        return signture;
    }

    public void setSignture(String signture) {
        this.signture = signture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataBankSource) {
            return super.getId().equals(((DataBankSource) obj).getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
