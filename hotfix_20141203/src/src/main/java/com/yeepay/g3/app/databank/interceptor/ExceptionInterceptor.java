/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.interceptor;

import com.yeepay.g3.app.databank.helper.EmailHelper;
import com.yeepay.g3.utils.common.exception.YeepayRuntimeException;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author baitao.ji
 * @version 1.0.0
 * @since 2013-05-06
 */
@Component
public class ExceptionInterceptor implements EmvcInterceptor {

	@Override
	public String intercept(EmvcActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (Exception e) {
			if (e instanceof YeepayRuntimeException) {
				invocation.getMVCFacade().getRequest().setAttribute("exception", ((YeepayRuntimeException) e).getCoreStackTraceStr());
			} else {
				e = new YeepayRuntimeException(e.getMessage(), e);
				invocation.getMVCFacade().getRequest().setAttribute("exception", ((YeepayRuntimeException) e).getCoreStackTraceStr());
			}
			EmailHelper.reportError(invocation.getMVCFacade().getRequest(), e, " 500错误");
			return "error";
		}
	}

}
