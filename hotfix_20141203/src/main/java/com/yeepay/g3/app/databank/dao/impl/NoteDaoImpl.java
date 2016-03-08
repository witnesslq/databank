/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao.impl;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.dao.NoteDao;
import com.yeepay.g3.app.databank.entity.NoteEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: Note Dao 实现类</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 上午11:47
 */
@Repository
public class NoteDaoImpl extends GenericDaoDefault<NoteEntity> implements NoteDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<NoteEntity> getByUserId(@Param("userId") Long userId) {
        return this.getSqlSession().selectList(getStatementId("getByUserId"), userId);
    }

    @Override
    public void addRelation(Long noteId, Long userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("noteId", noteId);
        map.put("userId", userId);
        this.getSqlSession().insert(getStatementId("addRelation"), map);
    }

    @Override
    public void updateUsedTimes(@Param("id") Long id) {
        this.getSqlSession().update("updateUsedTimes", id);
    }

    @Override
    public void migrate(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("fromUserId", fromUserId);
        map.put("toUserId", toUserId);
        this.getSqlSession().insert(getStatementId("migrate"), map);
    }

    @Autowired
//    @Override
    public void setSqlSessionFactory2(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}
