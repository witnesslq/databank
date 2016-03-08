/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.NoteEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Title: Note Dao 接口</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 上午11:45
 */
public interface NoteDao extends GenericDao<NoteEntity> {

    /**
     * 根据 userId 获取 Note
     *
     * @param userId 用户编号
     * @return
     */
    List<NoteEntity> getByUserId(Long userId);

    /**
     * 保存 User-Note 关联
     *
     * @param noteId Note 编号
     * @param userId 用户编号
     */
    void addRelation(Long noteId, Long userId);

    /**
     * 更新指定 Note 的使用次数
     *
     * @param id Note 编号
     */
    void updateUsedTimes(@Param("id") Long id);

    /**
     * 迁移笔记
     *
     * @param fromUserId 被迁移用户
     * @param toUserId   迁移到用户
     */
    void migrate(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

}
