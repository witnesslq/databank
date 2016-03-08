/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.StatUserEntity;

/**
 * <p>Title: 按用户统计业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午10:48
 */
public interface StatUserManager {

    /**
     * 创建
     *
     * @param statUserEntity 实体
     */
    public void create(StatUserEntity statUserEntity);

    /**
     * 生成指定时间范围内的用户统计数据
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    void generateBetween(String start, String end);

    /**
     * 生成指定时间范围内的用户统计数据
     *
     * @param day
     * @return
     */
    void generateByDay(String day) throws Exception;

    /**
     * 获取指定用户的统计数据
     *
     * @param loginName 用户登录名
     * @param start     开始日期
     * @param end       结束日期
     * @return
     */
    public String getByLoginName(String loginName, String start, String end);

    /**
     * 获取系统整体的统计数据
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return
     */
    public String getTotal(String start, String end);

}
