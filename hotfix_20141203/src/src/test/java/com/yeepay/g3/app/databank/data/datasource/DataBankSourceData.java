/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.data.datasource;

import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;

import java.util.Date;
import java.util.Random;

/**
 * <p>Title: 快速构建 DataBankSource 测试数据</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-3 下午2:59
 */
public class DataBankSourceData {

    private static Random random = new Random();

    public static DataBankSource getRandom() {
        DataBankSource dataBankSource = new DataBankSource();
        Long id = (long) random.nextInt(5);
        dataBankSource.setId(id);
        dataBankSource.setName("DataSource" + id);
        dataBankSource.setDbType(DatabaseTypeEnum.DB2);
        dataBankSource.setConnStr("jdbc:db2://172.17.102.8:8004/boss");
        dataBankSource.setUsername("config_1");
        dataBankSource.setPassword("123");
        dataBankSource.setDescription("456");
        Date now = new Date();
        dataBankSource.setLastModifiedDate(now);
        dataBankSource.setCreatedDate(now);
        return dataBankSource;
    }

}
