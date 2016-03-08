/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.facade.notifier.NotifyFacade;
import com.yeepay.g3.facade.notifier.exception.*;
import com.yeepay.g3.utils.common.encrypt.Digest;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 邮件 Helper</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-22 下午4:06
 */
public class EmailHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailHelper.class);

	// 通知系统配置
	private static final String TOKEN = "5YbXePzms0egCPdW";
	private static final String ADMIN = "baitao.ji";
	private static final String NOTUFY_RULE_NAME = "databank-error";
	private static final String MAIL_SUFFIX = "@yeepay.com";

	private static NotifyFacade notifyFacade = RemoteServiceFactory.getService(NotifyFacade.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void reportError(HttpServletRequest request, Throwable excp, String subTitle) {
		boolean isLocalhost = (null != request) && "127.0.0.1".equals(request.getRemoteHost());
		Throwable t = excp;
		if (null == t) {
			t = getException(request);
		}
		if (null == t) {
			return;
		}

		if (!isLocalhost) {
			try {
				// 异步发送邮件
//				sendEmail(subTitle, getErrorHtml(request, t));
				BaseEventUtils.sendEvent(EventNameEnum.SEND_EMAIL.toString(), subTitle, getErrorHtml(request, t));
			} catch (Exception e) {
				LOGGER.warn("程序貌似存在 BUG 啊，可惜发送邮件时报错了！");
			}
		}
	}

	public static void sendEmail(String subTitle, String message) throws IOException {
		List<String> recipients = Lists.newArrayList();
		recipients.add(ADMIN + MAIL_SUFFIX);

		Map<String, Object> messages = Maps.newHashMap();
		messages.put("subTitle", subTitle);
		messages.put("content", message);

		String sign = Digest.md5Digest(ADMIN + NOTUFY_RULE_NAME + StringUtils.join(recipients, ",") + TOKEN);

		try {
			notifyFacade.notify(ADMIN, sign, NOTUFY_RULE_NAME, recipients, messages);
		} catch (AuthorizeException e) {
			LOGGER.warn("调用通知系统发送邮件报错", e);
		} catch (InvalidNotifyRuleException e) {
			LOGGER.warn("调用通知系统发送邮件报错", e);
		} catch (InvalidRecipientException e) {
			LOGGER.warn("调用通知系统发送邮件报错", e);
		} catch (MessageGenerateFailureException e) {
			LOGGER.warn("调用通知系统发送邮件报错", e);
		} catch (FrequencyLimitedException e) {
			LOGGER.warn("调用通知系统发送邮件报错", e);
		}
	}

	/**
	 * 格式化错误信息
	 *
	 * @param req
	 * @param t   错误信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getErrorHtml(HttpServletRequest req, Throwable t) {
		StringBuilder html = new StringBuilder();
		if (req != null) {
			html.append("<h2>Request Headers</h2><table><tr><th>Request URL</th><td>");
			html.append(req.getRequestURL().toString());
			if (req.getQueryString() != null) {
				html.append('?');
				html.append(req.getQueryString());
			}
			html.append("</td></tr><tr><th>Remote Addr</th><td>");
			html.append(req.getRemoteHost());
			html.append("</td></tr><tr><th>Request Method</th><td>");
			html.append(req.getMethod());
			html.append("</td></tr><tr><th>CharacterEncoding</th><td>");
			html.append(req.getCharacterEncoding());
			html.append("</td></tr><tr><th>Request Locale</th><td>");
			html.append(req.getLocale());
			html.append("</td></tr><tr><th>Content Type</th><td>");
			html.append(req.getContentType());
			html.append("</td></tr>");
			Enumeration headers = req.getHeaderNames();
			while (headers.hasMoreElements()) {
				String key = (String) headers.nextElement();
				html.append("<tr><th>");
				html.append(key);
				html.append("</th><td>");
				html.append(req.getHeader(key));
				html.append("</td></tr>");
			}
			html.append("</table><h2>Request Parameters</h2><table><tr><th>LoginName</th><td>");
			UserDTO user = (UserDTO) req.getSession().getAttribute("yeepay_session_user");
			html.append(user.getUserName());
			html.append(" ");
			html.append(user.getEmail());
			html.append("</td></tr>");
			Enumeration params = req.getParameterNames();
			while (params.hasMoreElements()) {
				String key = (String) params.nextElement();
				html.append("<tr><th>");
				html.append(key);
				html.append("</th><td>");
				html.append(req.getParameter(key));
				html.append("</td></tr>");
			}
			html.append("</table>");
		}
		html.append("<h2>");
		html.append(t.getClass().getName());
		html.append(" (");
		html.append(sdf.format(new Date()));
		html.append(")</h2>");
		html.append(t.getMessage());
		html.append("<pre>");
		try {
			html.append(getException(t));
		} catch (IOException ex) {
			// Do nothing
		}
		html.append("</pre>");

//		html.append("<h2>System Properties</h2><table>");
//		Set props = System.getProperties().keySet();
//		for (Object prop : props) {
//			html.append("<tr><th>");
//			html.append(prop);
//			html.append("</th><td>");
//			html.append(System.getProperty((String) prop));
//			html.append("</td></tr>");
//		}
//		html.append("</table>");
		return html.toString();
	}

	/**
	 * 将当前上下文发生的异常转为字符串
	 *
	 * @param request 请求
	 * @return
	 */
	private static Throwable getException(HttpServletRequest request) {
		if (null == request) {
			return null;
		}

		Throwable t = (Throwable) request.getAttribute("javax.servlet.jsp.jspException");
		if (null == t) {
			t = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return t;
	}

	/**
	 * 将异常信息转化成字符串
	 *
	 * @param t 异常
	 * @return
	 * @throws IOException
	 */
	private static String getException(Throwable t) throws IOException {
		if (null == t) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		StackTraceElement[] stackTrace = t.getStackTrace();
		for (StackTraceElement item : stackTrace) {
			String className = item.getClassName();

			// 正式环境跳过一些无用堆栈信息，减少日志数量
			if (!className.startsWith("com.yeepay.g3")
					|| className.startsWith("com.yeepay.g3.utils")
					|| className.startsWith("com.yeepay.g3.app.frame")) {
				continue;
			}
			sb.append(item.toString()).append("\n");
		}
		return sb.toString();
	}

}
