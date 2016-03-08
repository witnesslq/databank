package com.yeepay.g3.app.databank.dao.impl;

import com.yeepay.g3.app.databank.dao.ApplyDao;
import com.yeepay.g3.app.databank.dao.GenericDaoDefault;
import com.yeepay.g3.app.databank.entity.ApplyEntity;
import com.yeepay.g3.app.databank.enumtype.ApplyStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hgs on 15/12/16.
 */
@Repository
public class ApplyDaoImpl extends GenericDaoDefault<ApplyEntity> implements ApplyDao {
    @Override
    public void createApply(ApplyEntity applyEntity) {
        this.getSqlSession().insert(getStatementId("addApplyMessage"), applyEntity);
    }

    @Override
    public void addUserDataSource(Long userId, Long dsId, Date endDataTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("dsId", dsId);
        map.put("endDateTime", endDataTime);
        this.getSqlSession().insert("addUserDataSource", map);
    }

    @Override
    public ApplyEntity getByUserIdAndDsId(Long userId, Long dsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("dsId", dsId);
        map.put("applyStatus", ApplyStatusEnum.APPLY_REVIEW.toString());
        return (ApplyEntity) this.queryOne("getByUserIdAndDsId", map);
    }

    @Override
    public void scheduleDelete() {
        this.getSqlSession().delete("scheduleDeleteAllow");
        this.getSqlSession().delete("scheduleDeleteAllowColumn");
        this.getSqlSession().delete("scheduleDeleteNotAllow");
        this.getSqlSession().delete("scheduleDeleteNotAllowColumn");
    }
}
