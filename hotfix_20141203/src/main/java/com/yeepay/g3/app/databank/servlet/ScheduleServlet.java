package com.yeepay.g3.app.databank.servlet;

import com.yeepay.g3.app.databank.dao.StatUserDao;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class ScheduleServlet extends HttpServlet {

    private static final long serialVersionUID = -1855617048198368534L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServlet.class);

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private StatUserDao statUserDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
        Date yesterday = cal.getTime();

        try {
            statUserDao.generateByDay(sdf.format(yesterday));
        } catch (Exception e) {
            LOGGER.warn("生成用户统计信息时报错，可能集群中其他服务器已经生成。{}", e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

}
