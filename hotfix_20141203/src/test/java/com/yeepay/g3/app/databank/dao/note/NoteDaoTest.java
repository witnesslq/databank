/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.dao.note;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.dao.NoteDao;
import com.yeepay.g3.app.databank.data.note.NoteEntityData;
import com.yeepay.g3.app.databank.entity.NoteEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * <p>Title: Note Dao 测试类</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 下午12:19
 */
@DirtiesContext
@ContextConfiguration(locations = {"/databank-spring/databank-application.xml"})
public class NoteDaoTest extends SpringTransactionalTests {

    @Autowired
    private NoteDao noteMapper;

    @Test
    public void testAdd() throws Exception {
        NoteEntity note = NoteEntityData.getRandom();
        noteMapper.add(note);
        NoteEntity actulNote = noteMapper.get(1L);
        assertEquals(note.getDsId().longValue(), actulNote.getDsId().longValue());
    }

    @Test
    public void testGetByUserId() throws Exception {
        List<NoteEntity> notes = noteMapper.getByUserId(204L);
        assertNotNull(notes);
        assertTrue(notes.size() > 0);
    }

    @Test
    public void testUpdateUsedTimes() throws Exception {
        NoteEntity note = noteMapper.get(1L);
        noteMapper.updateUsedTimes(1L);
        NoteEntity actulote = noteMapper.get(1L);
        assertNotNull(actulote);
        assertNotNull(note);
        assertEquals(actulote.getUsedTimes().longValue(), note.getUsedTimes().longValue() + 1);
    }

}
