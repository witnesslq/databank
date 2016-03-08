/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 角色控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-12 下午12:00
 */
@Controller
public class RoleAction extends ActionSupport {

    private static final String ROLE = "role";

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private DataBankSourceManager dataBankSourceManager;

    /**
     * 新建角色处理方法
     *
     * @param role           角色实体
     * @param permissionList 数据源权限列表
     * @return 结果
     */
    public String create(@Param(ROLE) RoleEntity role,
                         @Param("permissionList") List<String> permissionList) {
        if (null == role) {
            addModelObject("method", "create");
            addModelObject(ROLE, new RoleEntity());
            addModelObject("allDataSource", dataBankSourceManager.getAll());
            return SUCCESS;
        } else if (StringUtils.isNotEmpty(role.getName())) {
            if (null != permissionList) {
                role.setPermissionList(permissionList);
            }
            try {
                roleManager.create(role, getRequest().getParameterMap());
                this.setJsonModel(new JsonMessageResult("保存角色成功！"));
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "保存角色失败！"));
            }
        } else {
            this.setJsonModel(new JsonMessageResult(ERROR, "请填写角色名称！"));
        }
        return JSON;
    }

    /**
     * 更新角色处理方法
     *
     * @param id             编号
     * @param role           角色实体
     * @param permissionList 数据源权限列表
     * @return 结果
     */
    public String update(@Param("id") Long id,
                         @Param(ROLE) RoleEntity role,
                         @Param("permissionList") List<String> permissionList) {
        if (null == role) {
            role = roleManager.get(id);
            if (null == role) {
                this.setJsonModel(new JsonMessageResult(ERROR, "木有这个角色！"));
                return JSON;
            }
            //表权限
            Map<String, Map<String, String>> tablePermission = null;
            //列权限
            Map<String, Map<String, String>> columnPermission = null;
            //数据源
            List<DataBankSource> dataSourcePermission = new ArrayList<DataBankSource>();
            if (null != role.getPermissionList()) {
                tablePermission = roleManager.getTablePermission(role.getPermissionList(), role.getId());
                columnPermission = roleManager.getTableColumnPermission(role.getPermissionList(), role.getId());
                List<String> rsIds = role.getPermissionList();
                for (String rsId : rsIds) {
                    dataSourcePermission.add(dataBankSourceManager.get(Long.valueOf(rsId)));
                }
            }
            addModelObject("method", "update");
            addModelObject(ROLE, role);
            addModelObject("tablePermission", tablePermission);
            addModelObject("permissionColumn", columnPermission);
            addModelObject("allDataSource", dataBankSourceManager.getAll());
            addModelObject("dataSourcePermission", dataSourcePermission);
            return SUCCESS;
        } else if (StringUtils.isNotEmpty(role.getName())) {
            role.setPermissionList(permissionList);
            try {
                roleManager.update(role, getRequest().getParameterMap());
                this.setJsonModel(new JsonMessageResult("操作成功, 点击确定跳转到角色列表"));
            } catch (Exception e) {
                this.setJsonModel(new JsonMessageResult(ERROR, "更新角色失败，角色名不允许重复！"));
            }
        } else {
            this.setJsonModel(new JsonMessageResult(ERROR, "请输入角色名"));
        }
        return JSON;
    }

    /**
     * 角色列表
     *
     * @return 结果
     */
    public String list() {
        List<DataBankSource> dsList = dataBankSourceManager.getAll();
        addModelObject("dsList", dsList);
        return SUCCESS;
    }

    /**
     * 删除角色
     *
     * @param id 角色编号
     * @return 结果
     */
    public String delete(@Param("id") Long id) {
        try {
            roleManager.delete(id);
            this.setJsonModel(new JsonMessageResult("删除成功！"));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
            logger.warn("删除角色 #" + id + " 时出错", e);
        }
        return JSON;
    }
}
