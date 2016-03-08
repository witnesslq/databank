/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.data.account;

import com.yeepay.g3.app.databank.entity.UserEntity;

import java.util.Date;
import java.util.Random;

/**
 * <p>Title: 快速构建 UserEntity 测试数据</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 下午4:08
 */
public class UserData {

    private static Random random = new Random();

    public static UserEntity getRandom() {
        UserEntity user = new UserEntity();
        Long id = (long) random.nextInt(5);
        user.setId(id);
        user.setLoginName("User" + id);
        Date now = new Date();
        user.setLastModifiedDate(now);
        user.setCreatedDate(now);
        return user;
    }

}
