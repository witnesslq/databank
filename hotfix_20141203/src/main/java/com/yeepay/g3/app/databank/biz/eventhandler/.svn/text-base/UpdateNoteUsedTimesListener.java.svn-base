/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.biz.eventhandler;

import com.yeepay.g3.app.databank.service.NoteManager;
import com.yeepay.g3.utils.event.ext.BaseEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <p>Title: 异步更新笔记使用次数 事件监听器</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-11-6 上午11:54
 */
@Component
@Lazy(value = false)
public class UpdateNoteUsedTimesListener extends BaseEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateNoteUsedTimesListener.class);

    private NoteManager noteManager;

    @Override
    public String getListenedEventName() {
        return EventNameEnum.UPDATE_NOTE_USED_TIMES.toString();
    }

    @Override
    public void doAction(Object... objects) {
        try {
            Long noteId = (Long) objects[0];
            noteManager.updateUsedTimes(noteId);
        } catch (Exception e) {
            LOGGER.warn("异步更新笔记使用次数时出错", e);
        }
    }

    @Autowired
    public void setNoteManager(NoteManager noteManager) {
        this.noteManager = noteManager;
    }

}
