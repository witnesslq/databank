/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service;

import com.yeepay.g3.app.databank.entity.DataBankSource;

import java.util.List;
import java.util.TreeSet;

/**
 * <p>Title: 数据源管理业务逻辑层接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午6:27
 */
public interface DataBankSourceManager extends GenericManager<DataBankSource, Long> {

    /**
     * 获取所有数据源
     *
     * @return
     */
    public List<DataBankSource> getAll();

    /**
     * 获取所有数据源
     *
     * @param userId 用户编号
     * @return
     */
    public TreeSet<DataBankSource> getAllByUserId(Long userId);

    /**
     * 检查权限并记录最后一次使用的数据源
     *
     * @param userId 用户编号
     * @param dsId   数据源编号
     * @return
     */
    public boolean checkDS(Long userId, Long dsId);

}
