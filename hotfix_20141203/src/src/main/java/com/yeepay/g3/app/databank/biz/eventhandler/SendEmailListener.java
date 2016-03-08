/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.biz.eventhandler;

import com.yeepay.g3.app.databank.helper.EmailHelper;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <p>Title: 异步发送邮件 事件监听器</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-11-6 上午11:54
 */
@Component
@Lazy(value = false)
public class SendEmailListener extends BaseEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailListener.class);

	@Override
	public String getListenedEventName() {
		return EventNameEnum.SEND_EMAIL.toString();
	}

	@Override
	public void doAction(Object... objects) {
		try {
			String subTitle = (String) objects[0];
			String html = (String) objects[1];
			EmailHelper.sendEmail(subTitle, html);
		} catch (Exception e) {
			LOGGER.warn("异步发送邮件时出错", e);
		}
	}

}
