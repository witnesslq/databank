/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3;

import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigurationUtils;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-3 下午1:10
 */
public class test {
	public static void main(String[] args) {
//		System.out.println(AES.decryptFromBase64("61RUqxUsXRXEP5H/hc0YDg==", "I am a fool, OK?"));
//
//		List<String> myList = Lists.newArrayList();
//		myList.add("1");
//		myList.add("2");
//		System.out.println(myList.contains(new Long("1").toString()));
//
//		List<String> myList2 = ImmutableList.copyOf(StringUtils.split("3,4", ","));
//		System.out.println(myList2.contains(new Long("4").toString()));


		ConfigurationUtils.init();
		ConfigParam param = ConfigurationUtils.getAppConfigParam("employee-boss-ssoValid");
		System.out.println(param == null ? "" : param.getValue());

	}
}
