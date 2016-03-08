package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.ApplyEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by hgs on 15/12/16.
 */
public interface ApplyManager extends GenericManager<ApplyEntity, Long> {

    /**
     * 新增用户数据源
     */
    void addUserDataSource(Long userId, Long dsId, Date endDataTime);

    /**
     * 获取apply对象
     * @param userId
     * @param dsId
     * @return
     */
    ApplyEntity getByUserIdAndDsId(Long userId, Long dsId);
}
