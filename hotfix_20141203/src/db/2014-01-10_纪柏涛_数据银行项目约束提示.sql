------------------------
-- 在 boss 库执行
------------------------

-- 查询日志增加查询时间索引
CREATE INDEX DATABANK.LOG_START_DATESTR_index ON DATABANK.TBL_DB_LOG(LOG_START_DATESTR);

-- 将统计分析功能开放给所有用户
INSERT INTO EMP.TBL_ROLEANDFUNCTIONRELATION (ROLEID, FUNCTIONID) VALUES (152, -330040);