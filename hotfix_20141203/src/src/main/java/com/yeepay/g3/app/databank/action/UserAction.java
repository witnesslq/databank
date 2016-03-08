/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.convertor.SSOUserConvertor;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.app.databank.service.UserManager;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.facade.employee.facade.UserFacade;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.facade.employee.user.enums.UserStatusEnum;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.rmi.RemoteServiceFactory;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>Title: 用户控制器</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-12 下午12:00
 */
@Controller
public class UserAction extends ActionSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAction.class);

    private static final String USER = "user";

    private UserFacade userFacade = (UserFacade) RemoteServiceFactory.getService(UserFacade.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleManager roleManager;

    /**
     * 新建用户处理方法
     *
     * @param user     用户实体
     * @param roleList 角色列表
     * @return 结果
     */
    public String create(@Param(USER) UserEntity user,
                         @Param("roleList") List<Long> roleList) {
        // 非法提交
        if (null == user) {
            addModelObject("method", "create");
            addModelObject(USER, new UserEntity());

            addModelObject("allRoles", roleManager.getAll());

            // 被师傅做掉了
//        List<DepartmentDTO> departmentList = userFacade.queryAllDepartments();
//        DepartmentDTO departmentDTO = new DepartmentDTO();
//        departmentDTO.setDepartmentName("请选择部门");
//        departmentList.add(0, departmentDTO);
//        addModelObject("departmentList", departmentList);
            return SUCCESS;
        } else {
            // 登录名，方便权限修改
            UserDTO userDTO = new UserDTO();
            userDTO.setLoginName(user.getLoginName());
            userDTO.setUserstatus(UserStatusEnum.ACTIVE);
            List<UserDTO> userList = userFacade.queryUser(userDTO);
            if (null == userList || userList.size() == 0) {
                this.setJsonModel(new JsonMessageResult(ERROR, "查无此人!"));
                return JSON;
            }
            user.setId(userList.get(0).getUserId());
            user.setLoginName(userList.get(0).getLoginName());

            // 权限
            try {
                userManager.create(user, roleList);
            } catch (Exception e) {
                LOGGER.warn("创建用户时报错, user=" + user, e);
                this.setJsonModel(new JsonMessageResult(ERROR, "保存用户失败！系统中可能存在该用户，无需重复添加！"));
                return JSON;
            }
            this.setJsonModel(new JsonMessageResult("保存用户成功！"));
        }
        return JSON;
    }

    /**
     * 更新用户处理方法
     *
     * @param id   编号
     * @param user 用户实体
     * @return 结果
     */
    public String update(@Param("id") Long id,
                         @Param(USER) UserEntity user,
                         @Param("roleList") List<Long> roleList) {
        if (null == user) {
            addModelObject("method", "update");

            user = userManager.get(id);
            if (null == user) {
                return INPUT;
            }
            addModelObject(USER, user);

            addModelObject("allRoles", roleManager.getAll());
            return SUCCESS;
        }
        // 数据校验
        try {
            userManager.update(user, roleList);
            this.setJsonModel(new JsonMessageResult("更新用户成功！"));
        } catch (Exception e) {
            LOGGER.warn("更新用户时报错, user=" + user, e);
            this.setJsonModel(new JsonMessageResult(ERROR, "更新用户失败！"));
        }
        return JSON;
    }

    /**
     * 用户列表
     *
     * @return 结果
     */
    public String list() {
        List<RoleEntity> roleList = roleManager.getAll();
        addModelObject("roleList", roleList);
        return SUCCESS;
    }

    /**
     * 删除指定 id 的用户
     *
     * @param id 用户编号
     * @return 结果
     */
    public String delete(@Param("id") Long id) {
        try {
            userManager.delete(id);
            this.setJsonModel(new JsonMessageResult("删除成功！"));
        } catch (Exception e) {
            logger.warn("删除用户 #" + id + " 时出错", e);
            this.setJsonModel(new JsonMessageResult(ERROR, e.getMessage()));
        }
        return JSON;
    }

    /**
     * 根据部门编号获取用户列表
     *
     * @param departmentId 部门编号
     * @return 结果
     */
    public String userList(@Param("departmentId") Long departmentId) {
        List<UserDTO> userList = userFacade.queryDepartmentUsers(departmentId);
        this.setJsonModel(SSOUserConvertor.convert(userList));
        return JSON;
    }

    /**
     * 根据用户登录名获取用户
     *
     * @param loginName 用户登录名
     * @return 结果
     */
    public String userInfo(@Param("loginName") String loginName) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLoginName(loginName);
        userDTO.setUserstatus(UserStatusEnum.ACTIVE);
        List<UserDTO> userList = userFacade.queryUser(userDTO);
        if (null == userList || userList.size() == 0) {
            this.setJsonModel(new JsonMessageResult(ERROR, "查无此人！"));
        } else if (userList.size() > 1) {
            this.setJsonModel(new JsonMessageResult(ERROR, "存在多个该名为 " + loginName + " 的用户，请与管理员取得联系!"));
            logger.warn("登录名 #{} 有多个用户实例：{}", loginName, userList);
        } else if (null != userManager.getByLoginname(loginName)) {
            this.setJsonModel(new JsonMessageResult(ERROR, "已导入该用户，无需重复添加！"));
        } else {
            this.setJsonModel(new JsonMessageResult(userList.get(0).getUserName()));
        }
        return JSON;
    }

}
