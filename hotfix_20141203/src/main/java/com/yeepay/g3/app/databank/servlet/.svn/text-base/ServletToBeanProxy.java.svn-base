/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-1 下午2:35
 */
public class ServletToBeanProxy extends GenericServlet {

    private String targetBean;

    private Servlet proxy;

    public void init() throws ServletException {
        this.targetBean = getInitParameter("targetBean");
        getServletBean();
        proxy.init(getServletConfig());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        proxy.service(req, res);
    }

    public void getServletBean() {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        this.proxy = (Servlet) context.getBean(targetBean);
    }

}
