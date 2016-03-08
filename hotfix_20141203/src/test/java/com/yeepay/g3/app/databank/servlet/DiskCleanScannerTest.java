package com.yeepay.g3.app.databank.servlet;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.schedule.DiskCleanScanner;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 15/5/28 11:45
 */
@Ignore
public class DiskCleanScannerTest extends SpringTransactionalTests {

    @Autowired
    private DiskCleanScanner diskCleanScanner;

    @Test
    public void testExecute() throws Exception {
        diskCleanScanner.executeDaily();
    }

}
