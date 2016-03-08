/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.StatUserDao;
import com.yeepay.g3.app.databank.entity.StatUserEntity;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: 按用户统计 Dao 实现类</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-9-27 下午6:55
 */
@Component
public class StatUserDaoImpl extends GenericDaoDefault<StatUserEntity> implements StatUserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatUserDao.class);

    @Override
    public void generateByDay(String day) {
        int num;
        try {
            num = super.getSqlSession().insert(getStatementId("generateByDay"), day);
        } catch (DuplicateKeyException e) {
            deleteByDay(day);
            num = super.getSqlSession().insert(getStatementId("generateByDay"), day);
        }
        LOGGER.info("生成统计数据 {} 条，{}.", num, day);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<StatUserEntity> getByLoginName(String loginName, String start, String end) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("loginName", loginName);
        map.put("start", start);
        map.put("end", end);
        return super.getSqlSession().selectList(getStatementId("getByLoginName"), map);
    }

    @Override
    public int deleteByDay(String day) {
        return super.getSqlSession().delete(getStatementId("deleteByDay"), day);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
