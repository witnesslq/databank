package com.yeepay.g3.app.databank.dao;

import com.yeepay.g3.app.databank.entity.DataBankSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserEntity: dreambt
 * Date: 13-5-4
 * Time: 下午3:18
 */
public interface DataBankSourceDao extends GenericDao<DataBankSource> {

    List<DataBankSource> getByIds(@Param("dsIds") List<Long> ids);

    List<Long> getIdsByUserId(@Param("userId") Long userId);

}
