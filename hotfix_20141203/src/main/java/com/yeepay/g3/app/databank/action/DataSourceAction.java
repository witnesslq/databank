/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.database.MetaInfoProviderFactory;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import com.yeepay.g3.app.databank.helper.DBHelper;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>Title: DataSource 控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-2 下午12:02
 */
@Controller
public class DataSourceAction extends ActionSupport {

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    @Autowired
    private DBHelper dbHelper;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private UserManager userManager;


    /**
     * 新建数据源处理方法
     *
     * @param ds 数据源实体
     * @return 结果
     */
    public String create(@Param("ds") DataBankSource ds) {
        if (null == ds) {
            addModelObject("method", "create");
            return SUCCESS;
        } else {
            try {
                verifyDataSource(ds);
                dataBankSourceManager.create(ds);
                this.setJsonModel(new JsonMessageResult("保存数据源成功！"));
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "保存数据源失败！" + e.getMessage()));
            }
        }
        return JSON;
    }

    /**
     * 更新数据源处理方法
     *
     * @param id 数据源编号
     * @param ds 数据源实体
     * @return 结果
     */
    public String update(@Param("id") Long id,
                         @Param("ds") DataBankSource ds) {
        if (null == ds) {
            addModelObject("ds", dataBankSourceManager.get(id));
            addModelObject("method", "update");
            return SUCCESS;
        } else {
            try {
                verifyDataSource(ds);
                dataBankSourceManager.update(ds);
                this.setJsonModel(new JsonMessageResult("更新数据源成功！"));
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "更新数据源失败！" + e.getMessage()));
            }
        }
        return JSON;
    }

    /**
     * 显示所有数据源
     *
     * @return 结果
     */
    public String list() {
        addModelObject("dsTypeList", DatabaseTypeEnum.getValueMap());
        return SUCCESS;
    }

    /**
     * 获取用户拥有的数据源
     *
     * @return 结果
     */
    public String loadUserRs() {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        Long userId = userDTO.getUserId();
        addModelObject("dsTypeList", DatabaseTypeEnum.getValueMap());

        addModelObject("userId", userId);
        return SUCCESS;
    }


    public String loadUserApplRs(@Param("id") Long userId) {
        //根据userId获取申请的角色组
        RoleEntity role = roleManager.getByUserId(userId);
        if (null == role) {
            addModelObject("userName", userManager.get(userId).getLoginName());
            return SUCCESS;
        }
        //表权限
        Map<String, Map<String, String>> tablePermission = null;
        //列权限
        Map<String, Map<String, String>> columnPermission = null;
        //获取当前角色还有效的数据源
        List<String> applData = roleManager.getDsByRoleId(role.getId());
        if (CheckUtils.isEmpty(applData)) {
            addModelObject("userName", userManager.get(userId).getLoginName());
            return SUCCESS;
        }
        List<DataBankSource> dataSourcePermission = new ArrayList<DataBankSource>();
        tablePermission = roleManager.getTablePermission(applData, role.getId());
        columnPermission = roleManager.getTableColumnPermission(applData, role.getId());

        for (String rsId : applData) {
            dataSourcePermission.add(dataBankSourceManager.get(Long.valueOf(rsId)));
        }
        addModelObject("userName", userManager.get(userId).getLoginName());
        addModelObject("tablePermission", tablePermission);
        addModelObject("permissionColumn", columnPermission);
        addModelObject("allDataSource", dataBankSourceManager.getAll());
        addModelObject("dataSourcePermission", dataSourcePermission);
        return SUCCESS;
    }

    /**
     * 显示所有 Schema 和 Table
     *
     * @param type 加载数据类型
     * @param dsId 数据源编号
     * @return 结果
     */
    public String loadSchemaAndTable(@Param("type") String type,
                                     @Param("id") Long dsId,
                                     @Param("schema") String schemaName,
                                     @Param("table") String tableName) {
        if (null == dsId) {
            this.setJsonModel(new JsonMessageResult(ERROR, "该数据源不存在！"));
            return JSON;
        }
        try {
            if (null != type && "schema".equals(type)) {
                this.setJsonModel(MetaInfoProviderFactory.loadSchema(dataBankSourceManager.get(dsId)));
            } else if (null != type && "index".equals(type)) {
                this.setJsonModel(MetaInfoProviderFactory.loadTableIndex(dataBankSourceManager.get(dsId), schemaName, tableName));
            } else {
                this.setJsonModel(MetaInfoProviderFactory.loadSchemaAndTable(dataBankSourceManager.get(dsId)));
            }
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            logger.warn("从数据源 #" + dsId + " 加载 Schema 和 表 时出错", e);
        }
        return JSON;
    }


    /**
     * 获取某个表下面的所有列
     *
     * @param dsId
     * @param schemaAndTables
     * @return
     */
    public String loadTableColumn(@Param("id") Long dsId, @Param("schemaAndTable[]") String[] schemaAndTables) {
        if (null == schemaAndTables) {
            this.setJsonModel(new JsonMessageResult(ERROR, "对应的schema和表不存在！"));
            return JSON;
        }
        if (null == dsId) {
            this.setJsonModel(new JsonMessageResult(ERROR, "该数据源不存在！"));
            return JSON;
        }
        try {
            this.setJsonModel(MetaInfoProviderFactory.loadSchemaAndTableColumn(dataBankSourceManager.get(dsId), schemaAndTables));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            logger.warn("从数据源 #" + dsId + " 加载 Schema 和 表 时出错", e);
        }
        return JSON;
    }


    /**
     * 删除数据源处理方法
     *
     * @param id 数据源编号
     * @return 结果
     */
    public String delete(@Param("id") Long id) {
        try {
            dataBankSourceManager.delete(id);
            this.setJsonModel(new JsonMessageResult("删除成功！"));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            logger.warn("删除数据源 #" + id + " 时出错", e);
        }
        return JSON;
    }


    /**
     * 校验数据库连接是否正确
     *
     * @param ds 数据源
     */
    private void verifyDataSource(DataBankSource ds) throws Exception {
        HttpSession session = getSession();
        String loginName = (String) session.getAttribute("yeepay_sso_session_userid");

        // 如果没有密钥则从数据库加载
        if (ds.getId() != null && StringUtils.isBlank(ds.getPassword())) {
            ds.setPassword(dataBankSourceManager.get(ds.getId()).getPassword());
        }

        dbHelper.verifyConn(loginName, ds);
        this.setJsonModel(new JsonMessageResult("连接成功！"));
    }

    /**
     * 根据dsType,dbName进行条件检索
     *
     * @param dataBankSources
     * @param dsType
     * @return
     */
    public List<DataBankSource> filter(List<DataBankSource> dataBankSources, String dsType, String dsName) {
        if (CheckUtils.isEmpty(dsType) && CheckUtils.isEmpty(dsName)) {
            return dataBankSources;
        }
        Set<DataBankSource> filterSet = new HashSet<DataBankSource>();
        for (DataBankSource dataBankSource : dataBankSources) {
            if (!CheckUtils.isEmpty(dsType) && dataBankSource.getDbType().toString().indexOf(dsType) >= 0) {
                filterSet.add(dataBankSource);
            }
            if (!CheckUtils.isEmpty(dsName) && dataBankSource.getName().indexOf(dsName) >= 0) {
                filterSet.add(dataBankSource);
            }
        }
        return new ArrayList<DataBankSource>(filterSet);
    }
}