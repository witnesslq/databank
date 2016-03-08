/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank;

import com.yeepay.g3.unittest.ScriptRunnerWrapper;
import org.jmock.Mockery;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * biz层测试基类
 *
 * @author xingwei.bi
 * @version 1.0
 * @Copyright: Copyright (c)2011
 * @company 易宝支付(YeePay)
 * @since 2011-6-8
 */
@ContextConfiguration(locations = {"classpath:/databank-spring/databank-*.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class BaseBizTest extends AbstractJUnit4SpringContextTests {

	static {
		// 禁用远程调用组件
		// System.setProperty("disablermi","true");
		System.setProperty("mockrmi", "true");
		System.setProperty("mockconfig", "true");
		System.setProperty("disablemsg", "true");
		System.setProperty("disablecache", "true");
	}

	private ScriptRunnerWrapper scriptRunnerWrapper;

	protected Mockery mockery = new Mockery();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void save(String sql) {
		jdbcTemplate.execute(sql);
	}

	public void update(String sql) {
		jdbcTemplate.execute(sql);
	}

	public void delete(String sql) {
		jdbcTemplate.execute(sql);
	}

	/**
	 * List 里的每个对应是一个Map
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	protected Object getObjectByIoc(String oname) {
		return this.applicationContext.getBean(oname);
	}
}