package com.yeepay.g3.app.wechat.functional;

import com.yeepay.g3.core.selenium.Selenium2;
import com.yeepay.g3.core.selenium.SeleniumSnapshotRule;
import com.yeepay.g3.core.selenium.WebDriverFactory;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 使用Selenium的功能测试基类.
 * 
 * 在BaseFunctionalTestCase的基础上，在整个测试期间仅启动一次Selenium.
 */
public class BaseSeleniumTestCase extends BaseFunctionalTestCase {

	protected static Selenium2 s;

	// 出错时截屏的规则
	@Rule
	public TestRule snapshotRule = new SeleniumSnapshotRule(s);

	@BeforeClass
	public static void initSelenium() throws Exception {
		createSeleniumOnce();
		loginAsUserIfNecessary();
	}

	/**
	 * 创建Selenium，仅创建一次.
	 */
	protected static void createSeleniumOnce() throws Exception {
		if (s == null) {
			// 根据配置创建Selenium driver.
			String driverName = propertiesLoader.getProperty("selenium.driver");

			WebDriver driver = WebDriverFactory.createDriver(driverName);

			s = new Selenium2(driver, baseUrl);
			s.setStopAtShutdown();
		}
	}

	/**
	 * 登录管理员, 如果用户还没有登录.
	 */
	protected static void loginAsUserIfNecessary() {
		s.open("http://employee.yeepay.com:8001/employee-boss/loginout/showLogin");

		if ("用户登录".equals(s.getTitle())) {
			s.type(By.name("loginName"), "baitao.ji2");
			s.type(By.name("password"), "baitao.ji2");
//			s.check(By.name("rememberMe"));
			s.click(By.id("loginButton"));
			s.open("http://employee.yeepay.com:8001/employee-boss/user/showModifyPwd");
			s.waitForTitleContains("修改密码");
		}
	}
}
