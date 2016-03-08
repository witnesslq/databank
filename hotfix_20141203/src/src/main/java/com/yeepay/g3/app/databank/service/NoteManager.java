/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.NoteEntity;

import java.util.List;

/**
 * <p>Title: Note Service 层接口</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 上午11:53
 */
public interface NoteManager extends GenericManager<NoteEntity, Long> {

    /**
     * 根据 userId 获取 Note
     *
     * @param userId 用户编号
     * @return
     */
    public List<NoteEntity> getByUserId(Long userId);

    /**
     * 更新指定 Note 的使用次数
     *
     * @param id Note 编号
     */
    void updateUsedTimes(Long id);

    /**
     * 迁移笔记
     *
     * @param fromUserId 被迁移用户
     * @param toUserId   迁移到用户
     */
    void migrate(Long fromUserId, Long toUserId);

}
