package com.yeepay.g3.app.databank.dao.impl;

import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.SQLStatusDao;
import com.yeepay.g3.app.databank.entity.SQLStatusEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: SQL 出错码 Dao 实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-10-24 下午06:32
 */
@Repository
public class SQLStatusDaoImpl extends GenericDaoDefault<SQLStatusEntity> implements SQLStatusDao {

    @Override
    public SQLStatusEntity getBySQLStatus(String sqlStatus) {
        return (SQLStatusEntity) super.getSqlSession().selectOne(getStatementId("getBySQLStatus"), sqlStatus);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
