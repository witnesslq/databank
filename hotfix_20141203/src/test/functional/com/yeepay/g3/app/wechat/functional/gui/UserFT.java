package com.yeepay.g3.app.wechat.functional.gui;

import com.yeepay.g3.app.wechat.functional.BaseSeleniumTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class UserFT extends BaseSeleniumTestCase {

	@Test
	public void list() {
		String userName = "baitao.ji2";
		s.open("/user/list.action");
		s.type(By.name("loginName"), userName);
		s.click(By.id("submit"));

		assertTrue("用户查询失败", s.isElementPresent(By.xpath("//table[@class='list']//tr[2]//td[2]//span[@contains(text(), '" + userName + "')]")));
	}

	@Test
	public void update() {
		String userName = "baitao.ji2";
		s.open("/user/update.action?id=1577");
		s.click(By.xpath("//table[@class='list']//tr[2]//td[2]//span[@contains(text(), '')]"));

		assertTrue("编辑用户失败", s.isElementPresent(By.xpath("//table[@class='list']//tr[2]//td[2]//span[@contains(text(), '')]")));
	}
}
