/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.dao.log;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.LogDao;
import com.yeepay.g3.app.databank.data.log.LogEntityData;
import com.yeepay.g3.app.databank.entity.LogEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * <p>Title: 日志 Mapper 测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午5:48
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class LogMapperTest extends SpringTransactionalTests {

    @Autowired
    private LogDao mapper;

    @Test
    public void testGet() throws Exception {
        LogEntity log = mapper.get(1L);
        assertNotNull(log);
        assertEquals("baitao.ji", log.getExecutor());
    }

    @Test
    public void testGetAll() throws Exception {
        List<LogEntity> logs = mapper.getAll();
        assertTrue(logs.size() > 0);
        assertNotNull(logs.get(0));
        assertEquals("baitao.ji", mapper.getAll().get(0).getExecutor());
    }

    @Test
    public void testSave() throws Exception {
        LogEntity log = LogEntityData.getRandom();
        log.setId(null);
        mapper.add(log);
        assertTrue(log.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(mapper.get(2L));
        mapper.delete(2L);
        assertNull(mapper.get(2L));
    }

}
