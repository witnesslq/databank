/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.database.MetaInfoProviderFactory;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * <p>Title: 数据库操作 Helper 测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-9 下午7:53
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class DBHelperTest extends SpringTransactionalTests {

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    @Test
    public void testLoadTableIndex() throws Exception {
        List<String> indexes = MetaInfoProviderFactory.loadTableIndex(dataBankSourceManager.get(3L), "WECHAT", null);
        Assert.assertNotNull(indexes);
        Assert.assertTrue(indexes.size() > 20);
    }

}
