/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.biz.eventhandler;

import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <p>Title: 异步更新用户最后使用的数据源 事件监听器</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-11-6 上午11:54
 */
@Component
@Lazy(value = false)
public class UpdateLastDsListener extends BaseEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLastDsListener.class);

    private UserManager userManager;

    @Override
    public String getListenedEventName() {
        return EventNameEnum.UPDATE_LAST_DS.toString();
    }

    @Override
    public void doAction(Object... objects) {
        try {
            Long userId = (Long) objects[0];
            Long logId = (Long) objects[1];
            userManager.updateLastDs(userId, logId);
        } catch (Exception e) {
            LOGGER.warn("异步更新用户最后使用的数据源时出错", e);
        }
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

}
