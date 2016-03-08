/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.dao.account;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.UserDao;
import com.yeepay.g3.app.databank.data.account.UserData;
import com.yeepay.g3.app.databank.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * <p>Title: 用户 Dao 测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 下午4:06
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class UserMapperTest extends SpringTransactionalTests {

    @Autowired
    private UserDao mapper;

    @Test
    public void testGet() throws Exception {
        UserEntity userEntity = mapper.get(1L);
        assertNotNull(userEntity);
        assertEquals("baitao.ji", userEntity.getLoginName());
        //assertEquals("纪柏涛", userEntity.getUserId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<UserEntity> userEntities = mapper.getAll();
        assertTrue(userEntities.size() > 0);
        assertNotNull(userEntities.get(0));
        assertEquals("baitao.ji", mapper.getAll().get(0).getLoginName());
    }

    @Test
    public void testSave() throws Exception {
        UserEntity userEntity = UserData.getRandom();
        userEntity.setId(null);
        mapper.add(userEntity);
        assertTrue(userEntity.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(mapper.get(2L));
        mapper.delete(2L);
        assertNull(mapper.get(2L));
    }

    @Test
    public void testDeleteRelationBatch() throws Exception {
        List<Long> roleIdList = Lists.newLinkedList();
        roleIdList.add(1L);
        mapper.deleteRelationBatch(1L, roleIdList);
        assertEquals(mapper.get(1L).getRoleList().get(0).getId().longValue(), 2);
    }

    @Test
    public void testUpdate() throws Exception {
        UserEntity expected = UserData.getRandom();
        expected.setId(3L);
        mapper.update(expected);
        UserEntity actual = mapper.get(3L);

        assertNotNull(actual);
        assertEquals(expected.getLoginName(), actual.getLoginName());
    }

    @Test
    public void testUpdateByUserId() throws Exception {
        UserEntity userEntity = mapper.get(1L);
        userEntity.getId();
    }

}
