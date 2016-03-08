/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.data.log;

import com.yeepay.g3.app.databank.entity.LogEntity;
import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;

import java.util.Date;
import java.util.Random;

/**
 * <p>Title: 快速构建 Tag 测试数据</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午14:41
 */
public class LogEntityData {

    private static Random random = new Random();

    public static LogEntity getRandom() {
        LogEntity log = new LogEntity();
        Long id = (long) random.nextInt(5);
        log.setId(id);
        log.setExecutor("baitao.ji");
        log.setDsId(id);
        log.setQuery("select * from user");
        log.setLevel(LogLevelEnum.SELECT);
        log.setScore(10);
        log.setStartDateTime(new Date());
        log.setUsedMilliSecond(897);
        return log;
    }

}
