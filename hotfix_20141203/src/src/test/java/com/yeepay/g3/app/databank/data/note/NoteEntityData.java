/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.data.note;

import com.yeepay.g3.app.databank.entity.NoteEntity;

import java.util.Date;
import java.util.Random;

/**
 * <p>Title: 快速构建 Note 测试数据</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 下午12:23
 */
public class NoteEntityData {

    private static Random random = new Random();

    public static NoteEntity getRandom() {
        NoteEntity note = new NoteEntity();
        note.setName("查询数据源列表");
        note.setDsId(1L);
        note.setQuery("select * from DATABANK.TBL_DB_DATASOURCE");
        Date now = new Date();
        note.setLastModifiedDate(now);
        note.setCreatedDate(now);
        return note;
    }

}
