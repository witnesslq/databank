/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.dao.account;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.RoleDao;
import com.yeepay.g3.app.databank.data.account.RoleData;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * <p>Title: 角色 Dao 测试类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 下午5:29
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class RoleMapperTest extends SpringTransactionalTests {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testGet() throws Exception {
        RoleEntity role = roleDao.get(1L);
        assertNotNull(role);
        assertEquals("Admin", role.getName());
    }

    @Test
    public void testGetAll() throws Exception {
        List<RoleEntity> users = roleDao.getAll();
        assertTrue(users.size() > 0);
        assertNotNull(users.get(0));
        assertEquals("Admin", roleDao.getAll().get(0).getName());
    }

    @Test
    public void testGetAllGroup() throws Exception {
        List<RoleEntity> users = roleDao.getAll();
        assertNotNull(users);
        assertTrue(users.size() == 2);
    }

    @Test
    public void testGetSysRoleIdByUserId() throws Exception {
        List<Long> roleIdList = roleDao.getRoleIdsByUserId(1L);
        assertTrue(roleIdList.size() > 0);
    }

    @Test
    public void testSave() throws Exception {
        RoleEntity role = RoleData.getRandom();
        role.setId(null);
        roleDao.add(role);
        assertTrue(role.getId() > 0);
    }

    @Test
    public void testDelete() throws Exception {
        assertNotNull(roleDao.get(2L));
        roleDao.delete(2L);
        assertNull(roleDao.get(2L));
    }

    @Test
    public void testUpdate() throws Exception {
        RoleEntity expected = RoleData.getRandom();
        expected.setId(2L);
        roleDao.update(expected);
        RoleEntity actual = roleDao.get(2L);

        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPermissions(), actual.getPermissions());
    }

}
