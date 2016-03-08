/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.LogEntity;
import com.yeepay.g3.app.databank.utils.result.QueryResult;

/**
 * <p>Title: 日志业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午10:48
 */
public interface LogManager extends GenericManager<LogEntity, Long> {

    /**
     * 创建
     *
     * @param dsId        数据源编号
     * @param queryResult 查询结果
     */
    public Long create(Long dsId, QueryResult queryResult);

    /**
     * 更新
     *
     * @param logId       日志编号
     * @param queryResult 查询结果
     */
    public void update(Long logId, QueryResult queryResult);

    /**
     * 验证用户是否 executor 查询频度受限
     *
     * @param executor 执行者
     * @return
     */
    public void frequencyLimit(String executor);

}
