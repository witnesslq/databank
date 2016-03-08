/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank;

import org.jmock.Mockery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.sql.DataSource;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-3-12 下午1:17
 * @ActiveProfiles("test")
 */
@ContextConfiguration(locations = {"classpath:/databank-spring/databank-*.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class SpringTransactionalTests extends AbstractTransactionalJUnit4SpringContextTests {

	protected DataSource dataSource;

	protected Mockery mockery = new Mockery();

	static {
		// 禁用远程调用组件
		// System.setProperty("disablermi","true");
		System.setProperty("mockrmi", "true");
		System.setProperty("mockconfig", "true");
		System.setProperty("disablemsg", "true");
		System.setProperty("disablecache", "true");
	}

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

}
