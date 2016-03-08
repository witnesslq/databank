/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.convertor;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.entity.UserEntity;
import com.yeepay.g3.facade.employee.user.dto.UserDTO;

import java.util.List;

/**
 * <p>Title: UserDTO 转换</p>
 * <p>Description: 将 UserDTO 转换为 用户编号-用户名</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-5-6 下午2:20
 */
public class SSOUserConvertor {

    /**
     * 将单个 UserDTO 转化为 UserEntity
     *
     * @param userDTO 单个 UserDTO
     * @return
     */
    public static UserEntity convert(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getUserId());
        user.setLoginName(userDTO.getLoginName());
        return user;
    }

    /**
     * 批量将 UserDTO 转化为 UserEntity
     *
     * @param userDTOList UserDTO 列表
     * @return
     */
    public static List<UserEntity> convert(List<UserDTO> userDTOList) {
        List<UserEntity> userList = Lists.newLinkedList();
        for (UserDTO userDTO : userDTOList) {
            userList.add(convert(userDTO));
        }
        return userList;
    }

}
