/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.action;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.app.databank.database.MetaInfoProviderFactory;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.NoteEntity;
import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;
import com.yeepay.g3.app.databank.exception.DatabankException;
import com.yeepay.g3.app.databank.helper.DBHelper;
import com.yeepay.g3.app.databank.helper.QueryHelper;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.service.NoteManager;
import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.app.databank.utils.JsonMapper;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.app.databank.utils.result.QueryResult;
import com.yeepay.g3.app.databank.utils.sql.dialect.AbstractDialect;
import com.yeepay.g3.app.databank.utils.sql.dialect.Dialect;
import com.yeepay.g3.core.druid.sql.parser.ParserException;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <p>Title: 快速查询控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-2 下午16:32
 */
@Controller
public class QuickQueryAction extends ActionSupport {

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    @Autowired
    private NoteManager noteManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private LogManager logManager;

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private QueryHelper queryHelper;

    private JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();

    /**
     * 查询数据库处理方法
     *
     * @param dsId         数据源编号
     * @param schema       Schema
     * @param originalSql  SQL 语句
     * @param type         操作类型
     * @param quickMode    快速查询
     * @param skipSqlCheck 跳过语法检查器
     * @param lineNum      查询个数
     * @param noteId       笔记编号
     * @return 结果
     */
    public String query(@Param("dsId") Long dsId,
                        @Param("schema") String schema,
                        @Param("sql") String originalSql,
                        @Param("type") String type,
                        @Param("isolation") IsolationTypeEnum isolation,
                        @Param("quickMode") String quickMode,
                        @Param("skipSqlCheck") String skipSqlCheck,
                        @Param("num") Long lineNum,
                        @Param("noteId") Long noteId) {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");

        // 界面显示
        if (null == dsId) {
            TreeSet<DataBankSource> dsList = dataBankSourceManager.getAllByUserId(userDTO.getUserId());

            TreeMap<String, String> schemaList = Maps.newTreeMap();
            try {
                UserEntity user = userManager.get(userDTO.getUserId());

                Long lastDsId = user.getLastDs();
                addModelObject("lastDsId", lastDsId);

                if (null != lastDsId && !dataBankSourceManager.checkDS(userDTO.getUserId(), lastDsId)) {
                    // 使用过，加载最后一次使用过的数据源下面的Schema
                    schemaList = MetaInfoProviderFactory.loadSchema(dataBankSourceManager.get(lastDsId));
                } else if (null != dsList && dsList.size() > 0) {
                    // 没有使用过，加载第一个数据源下面的Schema
                    schemaList = MetaInfoProviderFactory.loadSchema(dataBankSourceManager.get(dsList.first().getId()));
                } else {
                    // 没有使用过且没有任何数据源权限
                    schemaList.put("", "未指定");
                }
            } catch (Exception e) {
                if (null == schemaList) {
                    schemaList = Maps.newTreeMap();
                }
            } finally {
                addModelObject("schemaList", schemaList);
            }

            // TODO 异步加载
            List<NoteEntity> noteList = noteManager.getByUserId(userDTO.getUserId());
            for (NoteEntity note : noteList) {
                note.setQuery(note.getQuery().replace("'", "&acute;"));
            }
            addModelObject("dsList", dsList);
            addModelObject("noteList", jsonMapper.toJson(noteList));
            return SUCCESS;
        }

        // 频度控制
        try {
            logManager.frequencyLimit(userDTO.getLoginName());
        } catch (DatabankException e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            return JSON;
        }

        try {
            // 复核用户对数据源的权限
            if (dataBankSourceManager.checkDS(userDTO.getUserId(), dsId)) {
                throw DatabankException.NOT_ALLOW_ACCESS_DS_EXCEPTION
                        .newInstance("无权访问的数据源 #{0}", dsId);
            }

            // 更新笔记使用次数
            if (null != noteId) {
                BaseEventUtils.sendEvent(EventNameEnum.UPDATE_NOTE_USED_TIMES.toString(), noteId);
            }

            DataBankSource dataBankSource = dataBankSourceManager.get(dsId);
            Dialect dialect = new AbstractDialect(dataBankSource.getDbType().getValue());

            if (null == isolation) {
                isolation = IsolationTypeEnum.UR;
            }
            QueryResult queryResult = queryHelper.queryPrepare(dialect, schema, originalSql,
                    lineNum, isolation, quickMode, skipSqlCheck, dsId, userDTO.getUserId());
            queryResult.setSchema(schema);
            queryResult.setLoginName(userDTO.getLoginName());
            queryResult.setLevel(LogLevelEnum.SELECT);
            queryResult = queryHelper.executeSelect(dataBankSource, queryResult);
            this.setJsonModel(queryResult);
        } catch (ParserException e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        } catch (DatabankException e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        } catch (SQLException e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return JSON;
    }

    /**
     * 下载查询结果处理方法
     *
     * @param dsId        数据源编号
     * @param schema      Schema
     * @param originalSql SQL 语句
     * @param type        下载类型
     * @return 结果
     */
    public String down(@Param("dsId") Long dsId,
                       @Param("schema") String schema,
                       @Param("sql") String originalSql,
                       @Param("type") String type,
                       @Param("isolation") IsolationTypeEnum isolation,
                       @Param("num") Long lineNum,
                       @Param("noteId") Long noteId) {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");

        try {
            // 复核用户对数据源的权限
            if (dataBankSourceManager.checkDS(userDTO.getUserId(), dsId)) {
                throw DatabankException.NOT_ALLOW_ACCESS_DS_EXCEPTION
                        .newInstance("无权访问的数据源 #{0}", dsId);
            }

            // 更新笔记使用次数
            if (null != noteId) {
                // noteManager.updateUsedTimes(noteId);
                BaseEventUtils.sendEvent(EventNameEnum.UPDATE_NOTE_USED_TIMES.toString(), noteId);
            }

            DataBankSource dataBankSource = dataBankSourceManager.get(dsId);
            Dialect dialect = new AbstractDialect(dataBankSource.getDbType().getValue());

            if (null == isolation) {
                isolation = IsolationTypeEnum.UR;
            }
            QueryResult queryResult = queryHelper.queryPrepare(dialect, schema, originalSql, lineNum, isolation, null, null, dsId, userDTO.getUserId());
            queryResult.setSchema(schema);
            queryResult.setLoginName(userDTO.getLoginName());
            queryResult.setLevel(LogLevelEnum.DOWN);
            this.setJsonModel(new JsonMessageResult(dbHelper.executeSelectToFile(dataBankSource, queryResult, type)));
        } catch (DatabankException e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return JSON;
    }

    /**
     * 出错码查询
     *
     * @return
     */
    public String error() {
        return SUCCESS;
    }

}
