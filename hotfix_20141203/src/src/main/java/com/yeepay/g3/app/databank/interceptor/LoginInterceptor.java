/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.interceptor;

import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.web.emvc.EmvcActionInvocation;
import com.yeepay.g3.utils.web.emvc.EmvcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: 登录拦截器</p>
 * <p>Description: 登录拦截器</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 2013-3-1 下午2:59:32
 */
@Component
public class LoginInterceptor implements EmvcInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    private UserManager userManager;

    /**
     * 改为管理员后台添加新用户
     *
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept(EmvcActionInvocation invocation) throws Exception {
        HttpServletRequest request = invocation.getMVCFacade().getRequest();
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");

        // 尚未登录本系统
        UserEntity user = userManager.getByLoginname(userDTO.getLoginName());
        // 第一次登录本系统, 自动注册新用户
        if (null == user) {
            user = new UserEntity();
            user.setId(userDTO.getUserId());
            user.setLoginName(userDTO.getLoginName());
            user = userManager.create(user, null);
            LOGGER.info("# ^_^ New user: {}", userDTO.getLoginName());
            session.setAttribute("permissions", user.getPermissions());
        }

        return invocation.invoke();
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}
