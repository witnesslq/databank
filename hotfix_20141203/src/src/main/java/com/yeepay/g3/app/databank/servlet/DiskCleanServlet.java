package com.yeepay.g3.app.databank.servlet;

import com.yeepay.g3.app.databank.helper.EmailHelper;
import com.yeepay.g3.app.databank.schedule.DiskCleanScanner;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * title: 定时<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 15/5/28 10:54
 */
@Component
public class DiskCleanServlet extends HttpServlet {

    private static final long serialVersionUID = -1855617048198368534L;

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskCleanServlet.class);

    @Autowired
    private DiskCleanScanner diskCleanScanner;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LOGGER.info("定时删除上一小时生成的文件");
            Calendar cal = Calendar.getInstance();
            String diff = req.getParameter("diff");
            if (StringUtils.isNotBlank(diff)) {
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - Integer.parseInt(diff));
            } else {
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - 30);
            }
            Date date = cal.getTime();
            diskCleanScanner.execute(date);
        } catch (Exception e) {
            LOGGER.warn("删除磁盘文件时报错，可能集群中其他服务器已经生成。{}", e.getMessage());
            EmailHelper.reportError(null, e, " 统计失败");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}
