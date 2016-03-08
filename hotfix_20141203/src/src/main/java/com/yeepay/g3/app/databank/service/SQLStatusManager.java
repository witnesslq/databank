/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.SQLStatusEntity;

/**
 * <p>Title: SQL 出错码业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-10-24 下午6:48
 */
public interface SQLStatusManager extends GenericManager<SQLStatusEntity, Long> {

    SQLStatusEntity getBySQLStatus(String sqlStatus);

}
