package com.yeepay.g3.app.wechat.functional.gui;

import com.yeepay.g3.app.wechat.functional.BaseSeleniumTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class QueryFT extends BaseSeleniumTestCase {

	@Test
	public void quickQuery() {
		s.open("/query/query.action");
		s.type(By.id("inputSQL"), "select * from EMP.TBL_USER");
		s.click(By.id("submit"));
		s.waitForValuePresent(By.id(""),"");

		assertTrue("快速查询失败", s.isTextPresent("ID"));
	}
}
