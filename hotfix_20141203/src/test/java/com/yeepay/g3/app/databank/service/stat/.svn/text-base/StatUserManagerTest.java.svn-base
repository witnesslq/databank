/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service.stat;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.data.stat.StatUserEntityData;
import com.yeepay.g3.app.databank.entity.StatUserEntity;
import com.yeepay.g3.app.databank.service.StatUserManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-27 下午7:17
 */
public class StatUserManagerTest extends SpringTransactionalTests {

    @Autowired
    private StatUserManager statUserManager;

    @Test
    public void testGenerateBetween() throws Exception {
        statUserManager.generateBetween("2013-09-09", "2013-10-09");
    }

    @Test
    public void testGetByLoginName() throws Exception {
        StatUserEntity statUser = StatUserEntityData.getRandom();
        statUserManager.create(statUser);
        String json = statUserManager.getByLoginName(statUser.getLoginName(), "2013-09-11", "2013-10-11");
        Assert.assertEquals("", json);
    }

}
