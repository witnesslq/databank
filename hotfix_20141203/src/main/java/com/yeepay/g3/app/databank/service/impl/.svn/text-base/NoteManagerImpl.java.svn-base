/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.dao.NoteDao;
import com.yeepay.g3.app.databank.entity.NoteEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.NoteManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
@Component
@Transactional(readOnly = false)
public class NoteManagerImpl implements NoteManager {

    private NoteDao noteMapper;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Transactional(readOnly = false)
    @Override
    public NoteEntity get(Long id) {
        return noteMapper.get(id);
    }

    @Override
    public NoteEntity create(NoteEntity object) {
        // 强制缓存过期
        String key = CacheKeyEnum.NOTE_BY_USERID.toString() + ":" + object.getUserId();
        ehcacheManager.put(key, null);

        noteMapper.add(object);
        noteMapper.addRelation(object.getId(), object.getUserId());
        return object;
    }

    @Override
    public void update(NoteEntity object) {
        // 强制缓存过期
        String key = CacheKeyEnum.NOTE_BY_USERID.toString() + ":" + object.getUserId();
        ehcacheManager.put(key, null);

        noteMapper.update(object);
    }

    @Override
    public void delete(Long id) {
        noteMapper.delete(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NoteEntity> getByUserId(Long userId) {
        // 已缓存
        String key = CacheKeyEnum.NOTE_BY_USERID.toString() + ":" + userId;
        List<NoteEntity> noteList = (List<NoteEntity>) ehcacheManager.get(key);
        if (null == noteList) {
            noteList = noteMapper.getByUserId(userId);
            ehcacheManager.put(key, noteList);
        }
        return noteList;
    }

    @Override
    public void updateUsedTimes(@Param("id") Long id) {
        noteMapper.updateUsedTimes(id);
    }

    @Override
    public void migrate(Long fromUserId, Long toUserId) {
        noteMapper.migrate(fromUserId, toUserId);
    }

    @Autowired
    public void setNoteMapper(NoteDao noteMapper) {
        this.noteMapper = noteMapper;
    }

}
