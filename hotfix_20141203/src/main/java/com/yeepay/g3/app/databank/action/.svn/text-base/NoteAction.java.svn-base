/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.database.MetaInfoProviderFactory;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.NoteEntity;
import com.yeepay.g3.app.databank.helper.DBHelper;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.app.databank.service.NoteManager;
import com.yeepay.g3.app.databank.utils.JsonMapper;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.config.impl.DefaultConfigParam;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-7 下午2:29
 */
@Controller
public class NoteAction extends ActionSupport {

    private static final String DATABANK_HELP = "DATABANK_HELP";

    private static final String NOTE = "note";

    @Autowired
    private NoteManager noteManager;

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    @Autowired
    private DBHelper dbHelper;

    private JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

    /**
     * 新建处理方法
     *
     * @param note 笔记实体
     * @return 结果
     */
    public String create(@Param(NOTE) NoteEntity note) {
        // 非法提交
        if (null == note) {
            this.setJsonModel(new JsonMessageResult(ERROR, "非法提交路径！"));
        } else {
            try {
                HttpSession session = getSession();
                UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
                note.setUserId(userDTO.getUserId());
                note = noteManager.create(note);
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "保存笔记失败！Detail: " + e.getMessage()));
                return JSON;
            }
            this.setJsonModel(new JsonMessageResult(note.getId().toString()));
        }
        return JSON;
    }

    /**
     * 更新处理方法
     *
     * @param noteId 编号
     * @param type   直接执行还是编辑
     * @param note   笔记实体
     * @return 结果
     */
    public String update(@Param("noteId") Long noteId,
                         @Param("type") String type,
                         @Param(NOTE) NoteEntity note) {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        if (null == note) {
            addModelObject("method", "update");

            note = noteManager.get(noteId);
            if (null == note) {
                return INPUT;
            }
            addModelObject(NOTE, note);

            if (null != type) {
                addModelObject("type", type);
            }

            try {
                TreeSet<DataBankSource> dsList = dataBankSourceManager.getAllByUserId(userDTO.getUserId());
                addModelObject("dsList", dsList);

                Map<String, String> schemaList = MetaInfoProviderFactory.loadSchema(dataBankSourceManager.get(note.getDsId()));
                addModelObject("schemaList", schemaList);

                List<NoteEntity> noteList = noteManager.getByUserId(userDTO.getUserId());
                for (NoteEntity note2 : noteList) {
                    note2.setQuery(note2.getQuery().replace("'", "&acute;"));
                }
                addModelObject("noteList", jsonMapper.toJson(noteList));
            } catch (Exception e) {
                return ERROR;
            }
            return SUCCESS;
        } else {
            try {
                note.setUserId(userDTO.getUserId());
                noteManager.update(note);
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "更新笔记失败！Detail: " + e.getMessage()));
                return JSON;
            }
            this.setJsonModel(new JsonMessageResult("更新笔记成功！"));
        }
        return JSON;
    }

    /**
     * 我的笔记列表
     *
     * @return 结果
     */
    public String list() {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        addModelObject("userId", userDTO.getUserId());
        addModelObject("dsList", dataBankSourceManager.getAllByUserId(userDTO.getUserId()));
        return SUCCESS;
    }

    /**
     * 删除指定 id 的笔记
     *
     * @param id 笔记编号
     * @return 结果
     */
    public String delete(@Param("id") Long id) {
        try {
            if (null == id) {
                DefaultConfigParam value = (DefaultConfigParam) ConfigurationUtils.getAppConfigParam(DATABANK_HELP);
                if (null != value && value.getValue() instanceof String) {
                    this.setJsonModel(value);
                }
            } else {
                noteManager.delete(id);
                this.setJsonModel(new JsonMessageResult("删除成功！"));
            }
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            logger.warn("删除笔记 #" + id + " 时出错", e);
        }
        return JSON;
    }

    /**
     * 迁移笔记
     *
     * @param fromUserId 被迁移用户
     * @param toUserId   迁移到用户
     * @return
     */
    public String migrate(@Param("from") Long fromUserId, @Param("to") Long toUserId) {
        try {
            noteManager.migrate(fromUserId, toUserId);
            this.setJsonModel(new JsonMessageResult("笔记迁移成功！"));
        } catch (Exception e) {
            logger.warn("迁移笔记时出错", e);
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return JSON;
    }

}
