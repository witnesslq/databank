/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.service.StatUserManager;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>Title: 统计分析控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-27 下午22:51
 */
@Controller
public class StatAction extends ActionSupport {

    @Autowired
    private StatUserManager statUserManager;

    /**
     * 列表
     *
     * @param dataType  显示数据(user显示指定用户的数据，total显示整体数据)
     * @param showType  显示类型
     * @param loginName 登录名
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 结果
     */
    public String list(@Param("dataType") String dataType,
                       @Param("showType") String showType,
                       @Param("loginName") String loginName,
                       @Param("startDate") String startDate,
                       @Param("endDate") String endDate) {
        try {
            if (null != showType && null != dataType && "json".equals(showType) && !"".equals(dataType)) {
                if ("user".equals(dataType) && null != loginName && !"".equals(loginName)) {
                    setJsonModel(statUserManager.getByLoginName(loginName, startDate, endDate));
                } else if ("user".equals(dataType)) {
                    HttpSession session = getSession();
                    UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
                    setJsonModel(statUserManager.getByLoginName(userDTO.getLoginName(), startDate, endDate));
                } else if ("total".equals(dataType)) {
                    setJsonModel(statUserManager.getTotal(startDate, endDate));
                }
                return JSON;
            } else {
                addModelObject("loginName", loginName);
                return SUCCESS;
            }
        } catch (Exception e) {
            setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return SUCCESS;
    }

    /**
     * 按日更新
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 结果
     */
    public String update(@Param("day") String start,
                         @Param("end") String end) {
        try {
            if (null == end || "".equals(end)) {
                statUserManager.generateByDay(start);
            } else {
                statUserManager.generateBetween(start, end);
            }
            setJsonModel("执行成功！");
        } catch (Exception e) {
            setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return JSON;
    }

}
