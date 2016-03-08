package com.yeepay.g3.app.databank.action;

import com.yeepay.g3.app.databank.entity.ApplyEntity;
import com.yeepay.g3.app.databank.entity.RoleEntity;
import com.yeepay.g3.app.databank.enumtype.ApplyStatusEnum;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import com.yeepay.g3.app.databank.service.*;
import com.yeepay.g3.app.databank.utils.result.JsonMessageResult;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.MessageFormater;
import com.yeepay.g3.utils.web.emvc.ActionSupport;
import com.yeepay.g3.utils.web.emvc.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hgs on 15/12/16.
 */
@Controller
public class ApplicationAction extends ActionSupport {

    private final static String APPLYSUCCESS = "1";
    @Autowired
    private ApplyManager applyManager;
    @Autowired
    private DataBankSourceManager dataBankSourceManager;
    @Autowired
    private UserManager userManage;
    @Autowired
    private RoleManager roleManager;
    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    private final MessageFormater messageFormater = new MessageFormater();

    /**
     * 申请数据源
     *
     * @param applyEntity
     * @return 结果
     */
    public String apply(@Param("apply") ApplyEntity applyEntity) {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        Long userId = userDTO.getUserId();
        applyEntity.setUserId(userId);
        applyEntity.setApplyStatus(ApplyStatusEnum.APPLY_REVIEW.toString());
        ApplyEntity storeApply = applyManager.getByUserIdAndDsId(userId, applyEntity.getDsId());
        //判断是否已经申请
        if (!CheckUtils.isEmpty(storeApply)) {
            this.setJsonModel(new JsonMessageResult("你申请的信息正在审核中,不要重复申请!"));
            return JSON;
        }
        Map parmas = getRequest().getParameterMap();
        String[] allow = (String[]) parmas.get("allow");
        String[] notallow = (String[]) parmas.get("notAllow");
        String[] allowColumn = (String[]) parmas.get("allowColumn");
        String[] notAllowColumn = (String[]) parmas.get("notAllowColumn");
        if (!CheckUtils.isEmpty(allow)) {
            applyEntity.setAllowTable(applyEntity.getPermissionList(Arrays.asList(allow)));
        }
        if (!CheckUtils.isEmpty(notallow)) {
            applyEntity.setNotAllowTable(applyEntity.getPermissionList(Arrays.asList(notallow)));
        }
        if (!CheckUtils.isEmpty(allowColumn)) {
            applyEntity.setAllowColumn(applyEntity.getPermissionList(Arrays.asList(allowColumn)));
        }
        if (!CheckUtils.isEmpty(notAllowColumn)) {
            applyEntity.setNotAllowColumn(applyEntity.getPermissionList(Arrays.asList(notAllowColumn)));
        }
        //保存申请信息
        applyManager.create(applyEntity);
        this.setJsonModel(new JsonMessageResult("申请成功，请等待审核。"));
        return JSON;
    }


    /**
     * 显示所有申请的数据源信息
     *
     * @return 结果
     */
    public String list() {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        addModelObject("dsTypeList", DatabaseTypeEnum.getValueMap());
        String reviewName = userDTO.getLoginName();
        addModelObject("reviewName", reviewName);
        return SUCCESS;
    }

    /**
     * 显示用户申请的数据源信息
     *
     * @return 结果
     */
    public String listApplyByUser(@Param("userId") Long userId) {
        addModelObject("dsTypeList", DatabaseTypeEnum.getValueMap());
        addModelObject("statusList", ApplyStatusEnum.getValueMap());
        addModelObject("userId", userId);
        return SUCCESS;
    }


    /**
     * 查看审核人所有审核信息
     *
     * @return
     */
    public String listViewInfo(@Param("reviewName") String reviewName) {
        HttpSession session = getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
        addModelObject("dsTypeList", DatabaseTypeEnum.getValueMap());
        addModelObject("statusList", ApplyStatusEnum.getValueMap());
        // String reviewName = userDTO.getLoginName();
        addModelObject("reviewName", reviewName);
        addModelObject("_messageFormater", messageFormater);
        return SUCCESS;
    }

    /**
     * 更新审核信息
     *
     * @param id
     * @return
     */
    public String updateView(@Param("id") Long id) {
        ApplyEntity applyEntity = applyManager.get(id);
        Long userId = applyEntity.getUserId();
        Long dsId = applyEntity.getDsId();
        //获取申请得到的roleId
        RoleEntity role = roleManager.getByUserId(userId);
        if (!CheckUtils.isEmpty(role)) {
            Long roleId = role.getId();
            Long dId = applyEntity.getDsId();
            List<String> dsIds = new ArrayList<String>();
            dsIds.add(dId.toString());
            //表权限
            Map<String, Map<String, String>> tablePermission = null;
            //列权限
            Map<String, Map<String, String>> columnPermission = null;
            //根据roleId和dsId获取申请数据源禁止和允许的表
            columnPermission = roleManager.getTableColumnPermission(dsIds, roleId);
            tablePermission = roleManager.getTablePermission(dsIds, roleId);
            addModelObject("columnPermission", columnPermission.get(dsId.toString()));
            addModelObject("tablePermission", tablePermission.get(dsId.toString()));
        }
        addModelObject("role", role);
        addModelObject("user", userManage.get(applyEntity.getUserId()));
        addModelObject("ds", dataBankSourceManager.get(applyEntity.getDsId()));
        addModelObject("applyTime", getStringDate(applyEntity.getApplyUseDateTime()));
        addModelObject("applyEntity", applyEntity);
        return SUCCESS;
    }


    /**
     * 更新申请信息
     *
     * @return 结果
     */
    public String updateApply(@Param("apply") ApplyEntity applyEntity,
                              @Param("id") Long id, @Param("flag") String flag) {
        if (CheckUtils.isEmpty(applyEntity)) {
            applyEntity = applyManager.get(id);
            addModelObject("applyEntity", applyEntity);
            addModelObject("user", userManage.get(applyEntity.getUserId()));
            addModelObject("ds", dataBankSourceManager.get(applyEntity.getDsId()));
            addModelObject("applyTime", getStringDate(applyEntity.getApplyUseDateTime()));
            return SUCCESS;
        }
        try {
            HttpSession session = getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("yeepay_session_user");
            Long userId = userDTO.getUserId();
            //设置审核人姓名
            applyEntity.setReviewName(userDTO.getLoginName());
            //通过
            if (APPLYSUCCESS.equals(flag)) {
                agreeApply(applyEntity);
            } else {
                applyEntity.setApplyStatus(ApplyStatusEnum.APPLY_FAILURE.toString());
                applyEntity.setAllowUseDateTime(null);
            }
            //更新用户信息
            applyManager.update(applyEntity);
            this.setJsonModel(new JsonMessageResult("更新申请信息成功！"));
        } catch (Exception e) {
            this.setJsonModel(new JsonMessageResult(ERROR, "更新申请信息失败！" + e.getMessage()));
        }
        return JSON;
    }


    /**
     * 审核通过
     *
     * @param applyEntity
     */
    public void agreeApply(ApplyEntity applyEntity) {
        Long dsId = applyEntity.getDsId();
        applyEntity.setApplyStatus(ApplyStatusEnum.APPLY_SUCCESS.toString());
        Map paramterMap = getRequest().getParameterMap();
        RoleEntity role = roleManager.getByUserId(applyEntity.getUserId());
        if (CheckUtils.isEmpty(role)) {
            RoleEntity roleEntity = new RoleEntity();
            //用申请人的userId作为名称（申请角色）
            roleEntity.setName(String.valueOf(applyEntity.getUserId()));
            //申请的角色拥有用户的唯一标志
            roleEntity.setUserId(applyEntity.getUserId());
            roleManager.addApplyRole(roleEntity, paramterMap, applyEntity.getAllowUseDateTime(), dsId.toString());
        } else {
            //用户申请角色存在，更新角色和数据源的关系
            roleManager.updateApplyRole(role, paramterMap, applyEntity.getAllowUseDateTime(), dsId, role.getId());
        }
    }

    /**
     * 查看审核详情
     *
     * @param id
     * @return
     */
    public String viewDetail(@Param("id") Long id) {
        if (!CheckUtils.isNull(id)) {
            ApplyEntity applyEntity = applyManager.get(id);
            //转换状态
            applyEntity.setApplyStatus(ApplyStatusEnum.parse(applyEntity.getApplyStatus()).getDisplayName());
            addModelObject("applyEntity", applyEntity);
            addModelObject("allowUseTime", getStringDate(applyEntity.getAllowUseDateTime()));
            addModelObject("ds", dataBankSourceManager.get(applyEntity.getDsId()));
            return SUCCESS;
        }
        return JSON;
    }

    /**
     * 时间转换函数
     *
     * @param currentTime
     * @return
     */
    public static String getStringDate(Date currentTime) {
        String dateString = null;
        if (null != currentTime || "".equals(currentTime)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(currentTime);
        }
        return dateString;
    }
}
