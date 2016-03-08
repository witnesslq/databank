/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.data.account;

import com.yeepay.g3.app.databank.entity.RoleEntity;

import java.util.Random;

/**
 * <p>Title: 快速构建 Role 测试数据</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 下午17:30
 */
public class RoleData {

    private static Random random = new Random();

    public static RoleEntity getRandom() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("User Group");
        roleEntity.setPermissions("user:view,ds:view");
        return roleEntity;
    }

}
