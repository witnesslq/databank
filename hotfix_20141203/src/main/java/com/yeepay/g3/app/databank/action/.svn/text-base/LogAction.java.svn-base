/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>Title: 查询日志控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午10:53
 */
@Controller
public class LogAction extends ActionSupport {

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    /**
     * 显示所有日志
     *
     * @return 结果
     */
    public String list() {
        addModelObject("allDataSource", dataBankSourceManager.getAll());
        addModelObject("logLevels", LogLevelEnum.getValueMap());
        return SUCCESS;
    }

}
