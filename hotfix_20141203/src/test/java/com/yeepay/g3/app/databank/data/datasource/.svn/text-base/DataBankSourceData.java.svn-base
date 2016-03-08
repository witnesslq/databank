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

    public static DataBankSource getRandom(DatabaseTypeEnum databaseType) {
        DataBankSource dataBankSource = new DataBankSource();
        Long id = (long) random.nextInt(5);
        dataBankSource.setId(id);
        dataBankSource.setName("DataSource" + id);
        dataBankSource.setDbType(databaseType);
        switch (databaseType) {
            case DB2:
                getDB2(dataBankSource);
                break;
            case MYSQL:
                getMySQL(dataBankSource);
                break;
            default:

        }
        dataBankSource.setDescription("456");
        Date now = new Date();
        dataBankSource.setLastModifiedDate(now);
        dataBankSource.setCreatedDate(now);
        return dataBankSource;
    }

    private static void getDB2(final DataBankSource dataBankSource) {
        dataBankSource.setConnStr("jdbc:db2://172.17.106.194:50000/qa3new");
        dataBankSource.setUsername("db2inst");
        dataBankSource.setPassword("dev8132430");
        dataBankSource.setDescription("dev db2");
    }

    private static void getMySQL(final DataBankSource dataBankSource) {
        dataBankSource.setConnStr("jdbc:mysql://localhost:3306");
        dataBankSource.setUsername("root");
        dataBankSource.setPassword("");
        dataBankSource.setDescription("local mysql");
    }

}
