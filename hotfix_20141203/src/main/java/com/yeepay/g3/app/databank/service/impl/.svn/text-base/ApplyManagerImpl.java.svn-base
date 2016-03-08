package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.dao.ApplyDao;
import com.yeepay.g3.app.databank.entity.ApplyEntity;
import com.yeepay.g3.app.databank.service.ApplyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hgs on 15/12/16.
 */
@Component
public class ApplyManagerImpl implements ApplyManager {

    @Autowired
    private ApplyDao applyDao;

    @Override
    public ApplyEntity get(Long id) {
        return applyDao.get(id);
    }

    @Override
    public ApplyEntity create(ApplyEntity applyEntity) {
        applyDao.createApply(applyEntity);
        return applyEntity;
    }

    @Override
    public void update(ApplyEntity applyEntity) {
        applyDao.update(applyEntity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void addUserDataSource(Long userId, Long dsId, Date endDataTime) {
        applyDao.addUserDataSource(userId, dsId, endDataTime);
    }

    @Override
    public ApplyEntity getByUserIdAndDsId(Long userId, Long dsId) {
        return applyDao.getByUserIdAndDsId(userId, dsId);
    }
}
