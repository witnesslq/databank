/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service.account;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.service.RoleManager;
import org.jmock.Expectations;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 类功能描述</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-16 下午9:28
 */
public class RoleManagerImplTest extends SpringTransactionalTests {

    @Autowired
    private RoleManager roleManager;

    private RoleDao roleMapper = mockery.mock(RoleDao.class);

    @Test
    public void testTablePermissionByDsIdAndUserId() throws Exception {
        Long dsId = 2L;
        Long roleId = 93L;

        mockery.checking(new Expectations() {
            {
                oneOf(roleMapper).getAllowRoleIdAndDsId(2L, 82L);
                will(returnValue(new ArrayList<String>(Arrays.asList("FINAL.TBL_FINAL_PAYMENT_201306", "ACCCHECK.TBL_CHECK_RESULT"))));

                oneOf(roleMapper).getAllowRoleIdAndDsId(2L, 93L);
                will(returnValue(new ArrayList<String>(Arrays.asList("FINAL.TBL_FINAL_PAYMENT_201306", "ACCCHECK.TBL_ACC_HIS_SOURCE", "ACCCHECK.TBL_TASK_CONTROL"))));

                oneOf(roleMapper).getNotAllowRoleIdAndDsId(2L, 82L);
                will(returnValue(new ArrayList<String>(Arrays.asList("ACCCHECK.TBL_ACC_HIS_SOURCE", "ACCCHECK.TBL_CHECK_STAT", "ACCCHECK.TBL_TRX_DEF"))));

                oneOf(roleMapper).getNotAllowRoleIdAndDsId(2L, 93L);
                will(returnValue(new ArrayList<String>(Arrays.asList("ACCCHECK.TBL_CHECK_STAT", "ACCCHECK.TBL_CHECK_RESULT"))));
            }
        });


        Map<String, List<String>> permission = roleManager.getTablePermission(dsId, 1577L);
        List<String> allow = permission.get("allow");
        List<String> notAllow = permission.get("notAllow");
        Assert.notNull(allow);
        Assert.isTrue(allow.size() == 2);
        Assert.isTrue(allow.contains("FINAL.TBL_FINAL_PAYMENT_201306"));
        Assert.isTrue(allow.contains("ACCCHECK.TBL_TASK_CONTROL"));

        Assert.notNull(notAllow);
        Assert.isTrue(notAllow.size() == 4);
        Assert.isTrue(notAllow.contains("ACCCHECK.TBL_CHECK_STAT"));
        Assert.isTrue(notAllow.contains("ACCCHECK.TBL_TRX_DEF"));
        Assert.isTrue(notAllow.contains("ACCCHECK.TBL_ACC_HIS_SOURCE"));
        Assert.isTrue(notAllow.contains("ACCCHECK.TBL_CHECK_RESULT"));
    }
}
