package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.StatUserEntity;

import java.util.List;

/**
 * <p>Title: 按用户统计 Dao 接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-4 下午03:32
 */
public interface StatUserDao extends GenericDao<StatUserEntity> {

    /**
     * 生成指定时间范围内的用户统计数据
     *
     * @param day 时间
     * @return
     */
    void generateByDay(String day);

    /**
     * 获取指定用户的统计数据
     *
     * @param loginName 用户登录名
     * @return
     */
    List<StatUserEntity> getByLoginName(String loginName, String start, String end);

    /**
     * 删除旧数据
     *
     * @param day 时间
     * @return
     */
    int deleteByDay(String day);

}
