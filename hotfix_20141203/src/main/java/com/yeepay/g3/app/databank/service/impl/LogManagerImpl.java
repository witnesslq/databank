/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service.impl;

import com.yeepay.g3.app.databank.dao.LogDao;
import com.yeepay.g3.app.databank.entity.LogEntity;
import com.yeepay.g3.app.databank.exception.DatabankException;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.utils.result.QueryResult;
import com.yeepay.g3.utils.config.ConfigurationUtils;
import com.yeepay.g3.utils.config.impl.DefaultConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: 日志业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午10:49
 */
@Component
@Transactional(readOnly = false)
public class LogManagerImpl implements LogManager {

    private LogDao mapper;

    @Override
    public LogEntity get(Long id) {
        return mapper.get(id);
    }

    @Override
    public LogEntity create(LogEntity object) {
        mapper.add(object);
        return object;
    }

    @Override
    public void update(LogEntity object) {
        // Do nothing
    }

    @Transactional(readOnly = false)
    @Override
    public Long create(Long dsId, QueryResult queryResult) {
        LogEntity log = new LogEntity();
        try {
            String actualSql = queryResult.getActualSql().replaceAll("\n", " ");
            if (actualSql.length() > 6000) {
                actualSql = actualSql.substring(6000);
            }

            log.setExecutor(queryResult.getLoginName());
            log.setDsId(dsId);
            log.setQuery(actualSql);
            log.setLevel(queryResult.getLevel());
            log.setScore(-1);
            log.setNum(-1);
            log.setStartDateTime(queryResult.getStartDateTime());
            log.setUsedMilliSecond(-1);
            log.setSchema(queryResult.getSchema());
            log.setStatus("XXXXX");
            log = this.create(log);
            return log.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void update(Long logId, QueryResult queryResult) {
        long score = queryResult.getUsedMilliSecond() * queryResult.getSize() / queryResult.getLevel().getBaseScore();

        LogEntity log = new LogEntity();
        log.setId(logId);
        log.setScore(score);
        log.setNum(queryResult.getSize());
        log.setUsedMilliSecond(queryResult.getUsedMilliSecond());
        log.setStatus(queryResult.getStatus());
        mapper.update(log);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void frequencyLimit(String executor) {
        DefaultConfigParam value = (DefaultConfigParam) ConfigurationUtils.getAppConfigParam("DATABANK_FREQUENCY_LIMIT");
        if (null != value && value.getValue() instanceof List) {
            List<String> params = (List<String>) value.getValue();
            for (String param : params) {
                String[] paramArray = param.split("\\|");
                if (mapper.countByExecutor(executor, paramArray[0]) > Integer.parseInt(paramArray[1])) {
                    throw DatabankException.FREQUENCY_LIMIT_EXCEPTION
                            .newInstance("慢点慢点，奴家都快忙活不过来了！<br/>{0} 秒内最多执行 {1} 次查询操作。", paramArray[0], paramArray[1]);
                }
            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Autowired
    public void setMapper(LogDao mapper) {
        this.mapper = mapper;
    }

}
