/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.data.stat;

import com.yeepay.g3.app.databank.entity.StatUserEntity;

import java.util.Random;

/**
 * <p>Title: 快速构建 StatUser 测试数据</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午14:41
 */
public class StatUserEntityData {

    private static Random random = new Random();

    public static StatUserEntity getRandom() {
        StatUserEntity statUser = new StatUserEntity();
        Long id = (long) random.nextInt(5);
        statUser.setId(id);
        statUser.setLoginName("baitao.ji");
        statUser.setDate("2013-09-27");
        statUser.setNum(5L);
        statUser.setAvgScore(4L);
        statUser.setSumTime(50L);
        statUser.setAvgTime(10L);
        return statUser;
    }

}
