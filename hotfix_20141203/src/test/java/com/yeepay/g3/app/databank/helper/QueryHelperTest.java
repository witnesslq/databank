/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.helper;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * <p>Title: SQL 语句处理测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-1-9 下午15:16
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class QueryHelperTest extends SpringTransactionalTests {

  @Test
  public void testVerifyConn() throws Exception {

  }

  @Test
  public void testExecuteSelect() throws Exception {

  }

  @Test
  public void testSelectWithUR() throws Exception {

  }

  @Test
  public void testSelectOnlyOne() throws Exception {

  }

}
