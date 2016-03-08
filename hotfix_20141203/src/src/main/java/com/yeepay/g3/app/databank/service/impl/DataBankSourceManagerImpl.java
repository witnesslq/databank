/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.Constant;
import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.app.databank.dao.DataBankSourceDao;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.service.DataBankSourceManager;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.RoleManager;
import com.yeepay.g3.utils.common.encrypt.AES;
import com.yeepay.g3.utils.common.encrypt.HmacSign;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * <p>Title: 数据源管理业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午6:29
 */
@Component
@Transactional(readOnly = false)
public class DataBankSourceManagerImpl implements DataBankSourceManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBankSourceManagerImpl.class);

    @Autowired
    private DataBankSourceDao dataBankSourceMapper;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Override
    public DataBankSource get(Long id) {
        // 已缓存
        String key = CacheKeyEnum.DS_BY_DSID.toString() + ":" + id;
        DataBankSource ds = (DataBankSource) ehcacheManager.get(key);
        if (null == ds) {
            LOGGER.info("加载数据源 #{}", id);
            ds = dataBankSourceMapper.get(id);
            ehcacheManager.put(key, ds);
        }

        checkSignture(ds);
        decrypt(ds);
        return ds;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DataBankSource> getAll() {
        // 已缓存
        String key = CacheKeyEnum.DS_ALL.toString();
        List<DataBankSource> dsList = (List<DataBankSource>) ehcacheManager.get(key);
        if (null == dsList) {
            LOGGER.info("加载所有数据源");
            dsList = dataBankSourceMapper.getAll();
            ehcacheManager.put(key, dsList);
        }
        return dsList;
    }

    // TODO 移动到 Biz 层
    @SuppressWarnings("unchecked")
    @Override
    public TreeSet<DataBankSource> getAllByUserId(Long userId) {
        // 已缓存
        String key = CacheKeyEnum.DS_BY_USERID + ":" + userId.toString();
        TreeSet<DataBankSource> dsSet = (TreeSet<DataBankSource>) ehcacheManager.get(key);
        if (null == dsSet) {
            LOGGER.info("加载用户 {} 的数据源", userId);
            List<Long> dsIds = roleManager.getDsIdsByUserId(userId);
            List<DataBankSource> dsList = dataBankSourceMapper.getByIds(dsIds);

            // 去重
            dsSet = new TreeSet<DataBankSource>(new Comparator<DataBankSource>() {
                @Override
                public int compare(DataBankSource o1, DataBankSource o2) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
            for (DataBankSource dataSource : dsList) {
                dsSet.add(dataSource);
            }

            ehcacheManager.put(key, dsSet);
        }
        return dsSet;
    }

    // TODO 移动到 Biz 层
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkDS(Long userId, Long dsId) {
        // 已缓存
        List<Long> dsIds = roleManager.getDsIdsByUserId(userId);

        // 异步更新用户最后一次使用过的数据源
        // userMapper.updateLastDs(userId, dsId);
        BaseEventUtils.sendEvent(EventNameEnum.UPDATE_LAST_DS.toString(), userId, dsId);
        return !dsIds.contains(dsId);
    }

    @Override
    @Transactional(readOnly = false)
    public DataBankSource create(DataBankSource databankSource) {
        encrypt(databankSource);
        databankSource.setSignture(sign(databankSource));
        dataBankSourceMapper.add(databankSource);
        return databankSource;
    }

    @Override
    @Transactional(readOnly = false)
    public void update(DataBankSource databankSource) {
        encrypt(databankSource);
        databankSource.setSignture(sign(databankSource));
        dataBankSourceMapper.update(databankSource);
        String key = CacheKeyEnum.DS_BY_DSID.toString() + ":" + databankSource.getId();
        ehcacheManager.put(key, null);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dataBankSourceMapper.delete(id);
        String key = CacheKeyEnum.DS_BY_DSID.toString() + ":" + id;
        ehcacheManager.put(key, null);
    }

    private void checkSignture(DataBankSource dataBankSource) {
        String signture = sign(dataBankSource);

        // TODO 临时开关
        if (StringUtils.isNotBlank(dataBankSource.getSignture()) &&
                !signture.equals(dataBankSource.getSignture())) {
            LOGGER.info("数据源 #{} 的数据被篡改, signture:{}", dataBankSource.getId(), signture);
//            throw DatabankException.DATASOURCE_DATA_TAMPERING_EXCEPTION
//                    .newInstance("数据源 #{0} 的数据被篡改", dataBankSource.getId());
        }
    }

    private String sign(DataBankSource dataBankSource) {
        String parameters = dataBankSource.getId()
                + ":" + dataBankSource.getName()
                + ":" + dataBankSource.getDbType()
                + ":" + dataBankSource.getConnStr()
                + ":" + dataBankSource.getUsername()
                + ":" + dataBankSource.getPasswordCipher();
        LOGGER.info("sign params:{}", parameters);
        return HmacSign.signToBase64(parameters, Constant.SIGN_SECRET_KEY);
    }

    private DataBankSource encrypt(DataBankSource dataBankSource) {
        if (StringUtils.isNotBlank(dataBankSource.getPassword())) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("加密数据源前:{}", dataBankSource);
            }
            dataBankSource.setPasswordCipher(AES.encryptWithKeyBase64(dataBankSource.getPassword(), Constant.DATASOURCE_KEY));
            dataBankSource.setPassword("");
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("加密数据源后:{}", dataBankSource);
            }
        }
        return dataBankSource;
    }

    private DataBankSource decrypt(DataBankSource dataBankSource) {
        if (StringUtils.isNotBlank(dataBankSource.getPasswordCipher())) {
            LOGGER.info("数据源 #{} 已经加密", dataBankSource.getId());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("解密数据源前:{}", dataBankSource);
            }
            dataBankSource.setPassword(AES.decryptWithKeyBase64(dataBankSource.getPasswordCipher(), Constant.DATASOURCE_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("解密数据源后:{}", dataBankSource);
            }
        } else {
            LOGGER.info("发现一个尚未加密的数据源 #{}，请尽快编辑！！！", dataBankSource.getId());
        }
        return dataBankSource;
    }

}
