package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.LogEntity;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: 日志 Dao 接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-4 下午03:32
 */
public interface LogDao extends GenericDao<LogEntity> {

    /**
     * 获取指定用户的请求次数
     *
     * @param executor 用户登录名
     * @param before   秒数
     * @return
     */
    int countByExecutor(String executor, String before);

    /**
     * 获取指定时间范围内使用过的用户名列表
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    List<String> getUsedUserNameByDay(Date start, Date end);

}
