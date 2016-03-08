/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>Title: SQL 出错码实体</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-10-24 下午5:54
 */
public class SQLStatusEntity extends Entity {

    private static final long serialVersionUID = -4082457458619235442L;

    /**
     * 出错码
     * <p/>
     * 占位符 sqlstate 仅供开发使用。在交付代码之前必须对其进行更改。
     */
    private String sqlStatus = "ZZZZZ";

    /**
     * 解释
     */
    private String explanation = "未知异常";

    public String getSqlStatus() {
        return sqlStatus;
    }

    public void setSqlStatus(String sqlStatus) {
        this.sqlStatus = sqlStatus;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
