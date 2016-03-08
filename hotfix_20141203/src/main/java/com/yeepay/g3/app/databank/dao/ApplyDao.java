package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.ApplyEntity;

import java.util.Date;

/**
 * Created by hgs on 15/12/16.
 */
public interface ApplyDao extends GenericDao<ApplyEntity> {

    /**
     * 保存用户申请数据源信息
     */
    void createApply(ApplyEntity applyEntity);

    /**
     * 新增用户拥有的数据源
     *
     * @param userId
     * @param dsId
     * @param endDataTime
     */
    void addUserDataSource(Long userId, Long dsId, Date endDataTime);


    /**
     * 获取apply对象
     *
     * @param userId
     * @param dsId
     * @return
     */
    ApplyEntity getByUserIdAndDsId(Long userId, Long dsId);
}
