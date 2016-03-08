/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.dao.datasource;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.DataBankSourceDao;
import com.yeepay.g3.app.databank.data.datasource.DataBankSourceData;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static junit.framework.Assert.*;


/**
 * <p>Title: 数据源 Dao 测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-3 下午2:53
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class DataBankSourceMapperTest extends SpringTransactionalTests {

    @Autowired
    private DataBankSourceDao mapper;

    @Test
    public void testGet() throws Exception {
        DataBankSource dataBankSource = mapper.get(1L);
        assertNotNull(dataBankSource);
        assertEquals("CONFIG_1", dataBankSource.getName());
    }

    @Test
    public void testGetAll() throws Exception {
        List<DataBankSource> dataBankSources = mapper.getAll();
        assertTrue(dataBankSources.size() > 0);
        assertNotNull(dataBankSources.get(0));
        assertEquals("CONFIG_1", mapper.getAll().get(0).getName());
    }

    @Test
    public void testGetByIds() throws Exception {
        List<Long> ids = Lists.newLinkedList();
        ids.add(1L);
        ids.add(3L);
        List<DataBankSource> dataBankSources = mapper.getByIds(ids);
        assertNotNull(dataBankSources);
        assertEquals(2, dataBankSources.size());
    }

    @Test
    public void testSave() throws Exception {
        DataBankSource dataBankSource = DataBankSourceData.getRandom(DatabaseTypeEnum.DB2);
        dataBankSource.setId(null);
        mapper.add(dataBankSource);
        assertTrue(dataBankSource.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(mapper.get(2L));
        mapper.delete(2L);
        assertNull(mapper.get(2L));
    }

    @Test
    public void testUpdate() throws Exception {
        DataBankSource expected = DataBankSourceData.getRandom(DatabaseTypeEnum.DB2);
        expected.setId(3L);
        mapper.update(expected);
        DataBankSource actual = mapper.get(3L);

        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDbType(), actual.getDbType());
        assertEquals(expected.getConnStr(), actual.getConnStr());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

}
