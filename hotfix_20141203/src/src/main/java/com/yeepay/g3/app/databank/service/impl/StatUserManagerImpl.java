/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.dao.StatUserDao;
import com.yeepay.g3.app.databank.entity.StatUserEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.helper.stat.LongDataView;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.StatUserManager;
import com.yeepay.g3.utils.common.json.JSONUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 按用户统计业务逻辑层实现类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午10:49
 */
@Component
@Transactional(readOnly = false)
public class StatUserManagerImpl implements StatUserManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatUserManager.class);

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private StatUserDao mapper;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Override
    public void create(StatUserEntity statUserEntity) {
        mapper.add(statUserEntity);
    }

    @Override
    public void generateBetween(String start, String end) {
        try {
            LOGGER.debug("生成统计数据，from {} to {}", start, end);
            Date startDate = sdf.parse(start);
            Date endDate = sdf.parse(end);
            while (startDate.before(endDate)) {
                generateByDate(startDate);
                startDate = this.addDay(startDate, 1);
            }
        } catch (ParseException e) {
            LOGGER.warn("时间解析失败", e);
        } catch (Exception e) {
            LOGGER.warn("未知异常", e);
        }
    }

    @Override
    public void generateByDay(String day) throws Exception {
        mapper.generateByDay(day);
    }

    private void generateByDate(Date day) throws Exception {
        mapper.generateByDay(sdf.format(day.getTime()));
    }

    @Override
    public String getByLoginName(String loginName, String start, String end) {
        LOGGER.debug("生成统计数据，loginName={}, from {} to {}", loginName, start, end);
        Date endDate = null;
        Date startDate = null;
        try {
            endDate = sdf.parse(end);
            startDate = sdf.parse(start);
        } catch (Exception e) {
            endDate = new Date();
            startDate = this.addDay(endDate, -30);
        }

        // 已缓存
        String key = CacheKeyEnum.STAT_USER_BY_LOGINNAME.toString() + ":" + loginName + ":" + sdf.format(startDate) + ":" + sdf.format(endDate);
        String json = (String) ehcacheManager.get(key);
        if (null == json || "".equals(json)) {
            // 时间
            List<Object> dateStr = Lists.newArrayList();

            // 查询次数
            List<Object> times = Lists.newArrayList();
            Long maxTimes = 0L;

            // 平均得分
            List<Object> avgScore = Lists.newArrayList();
            Long maxAvgScore = 0L;

            // 查询总用时
            List<Object> totalTime = Lists.newArrayList();
            Long maxTotalTimes = 0L;

            // 查询平均用时
            List<Object> avgTime = Lists.newArrayList();
            Long maxAvgTimes = 0L;

            List<StatUserEntity> statUserList = mapper.getByLoginName(loginName, sdf.format(startDate.getTime()), sdf.format(endDate.getTime()));
            for (StatUserEntity statUser : statUserList) {
                dateStr.add(statUser.getDate());
                times.add(statUser.getNum());
                if (statUser.getNum() > maxTimes) {
                    maxTimes = statUser.getNum();
                }
                avgScore.add(statUser.getAvgScore());
                if (statUser.getAvgScore() > maxAvgScore) {
                    maxAvgScore = statUser.getAvgScore();
                }
                totalTime.add(statUser.getSumTime());
                if (statUser.getSumTime() > maxTotalTimes) {
                    maxTotalTimes = statUser.getSumTime();
                }
                avgTime.add(statUser.getAvgTime());
                if (statUser.getAvgTime() > maxAvgTimes) {
                    maxAvgTimes = statUser.getAvgTime();
                }
            }

            Map<String, LongDataView> data = Maps.newHashMap();
            Map<String, List<Object>> map = Maps.newHashMap();
            map.put("dateStr", dateStr);
            LongDataView dataView1 = new LongDataView();
            dataView1.setData(map);
            data.put("dateStr", dataView1);

            map = Maps.newHashMap();
            map.put("times", times);
            LongDataView dataView2 = new LongDataView();
            dataView2.setMaxValue(maxTimes);
            dataView2.setData(map);
            data.put("times", dataView2);

            map = Maps.newHashMap();
            map.put("avgScore", avgScore);
            LongDataView dataView3 = new LongDataView();
            dataView3.setMaxValue(maxAvgScore);
            dataView3.setData(map);
            data.put("avgScore", dataView3);

            map = Maps.newHashMap();
            map.put("totalTime", totalTime);
            LongDataView dataView4 = new LongDataView();
            dataView4.setMaxValue(maxTotalTimes);
            dataView4.setData(map);
            data.put("totalTime", dataView4);

            map = Maps.newHashMap();
            map.put("avgTime", avgTime);
            LongDataView dataView5 = new LongDataView();
            dataView5.setMaxValue(maxAvgTimes);
            dataView5.setData(map);
            data.put("avgTime", dataView5);

            json = JSONUtils.toJsonString(data);
            ehcacheManager.put(key, json);
        }
        return json;
    }

    @Override
    public String getTotal(String start, String end) {
        // 已缓存
        String key = CacheKeyEnum.STAT_TOTAL.toString() + ":" + start + ":" + end;
        String json = (String) ehcacheManager.get(key);
        if (null == json || "".equals(json)) {
            // 时间
            List<Object> dateStr = Lists.newArrayList();

            // 查询次数
            List<Object> times = Lists.newArrayList();
            Long maxTimes = 0L;

            // 平均得分
            List<Object> avgScore = Lists.newArrayList();
            Long maxAvgScore = 0L;

            // 查询总用时
            List<Object> totalTime = Lists.newArrayList();
            Long maxTotalTimes = 0L;

            // 查询平均用时
            List<Object> avgTime = Lists.newArrayList();
            Long maxAvgTimes = 0L;

            // TODO
            List<StatUserEntity> statUserList = null;
            for (StatUserEntity statUser : statUserList) {
                dateStr.add(statUser.getDate());
                times.add(statUser.getNum());
                if (statUser.getNum() > maxTimes) {
                    maxTimes = statUser.getNum();
                }
                avgScore.add(statUser.getAvgScore());
                if (statUser.getAvgScore() > maxAvgScore) {
                    maxAvgScore = statUser.getAvgScore();
                }
                totalTime.add(statUser.getSumTime());
                if (statUser.getSumTime() > maxTotalTimes) {
                    maxTotalTimes = statUser.getSumTime();
                }
                avgTime.add(statUser.getAvgTime());
                if (statUser.getAvgTime() > maxAvgTimes) {
                    maxAvgTimes = statUser.getAvgTime();
                }
            }

            Map<String, LongDataView> data = Maps.newHashMap();
            Map<String, List<Object>> map = Maps.newHashMap();
            map.put("dateStr", dateStr);
            LongDataView dataView1 = new LongDataView();
            dataView1.setData(map);
            data.put("dateStr", dataView1);

            map = Maps.newHashMap();
            map.put("times", times);
            LongDataView dataView2 = new LongDataView();
            dataView2.setMaxValue(maxTimes);
            dataView2.setData(map);
            data.put("times", dataView2);

            map = Maps.newHashMap();
            map.put("avgScore", avgScore);
            LongDataView dataView3 = new LongDataView();
            dataView3.setMaxValue(maxAvgScore);
            dataView3.setData(map);
            data.put("avgScore", dataView3);

            map = Maps.newHashMap();
            map.put("totalTime", totalTime);
            LongDataView dataView4 = new LongDataView();
            dataView4.setMaxValue(maxTotalTimes);
            dataView4.setData(map);
            data.put("totalTime", dataView4);

            map = Maps.newHashMap();
            map.put("avgTime", avgTime);
            LongDataView dataView5 = new LongDataView();
            dataView5.setMaxValue(maxAvgTimes);
            dataView5.setData(map);
            data.put("avgTime", dataView5);

            json = JSONUtils.toJsonString(data);
            ehcacheManager.put(key, json);
        }
        return json;
    }

    private Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }

    @Autowired
    public void setMapper(StatUserDao mapper) {
        this.mapper = mapper;
    }

}
