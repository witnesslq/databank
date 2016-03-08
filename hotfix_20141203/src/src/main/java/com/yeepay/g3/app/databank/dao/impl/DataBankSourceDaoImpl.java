package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.dao.DataBankSourceDao;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserEntity: dreambt
 * Date: 13-5-4
 * Time: 下午3:19
 */
@Repository
public class DataBankSourceDaoImpl extends GenericDaoDefault<DataBankSource> implements DataBankSourceDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<DataBankSource> getByIds(List<Long> ids) {
        if (null == ids || ids.size() == 0) {
            return Lists.newArrayList();
        }
        return this.getSqlSession().selectList("getByIds", ids);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
