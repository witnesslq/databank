------------------------
-- 在 boss 库执行
------------------------

-- 1.菜单整理
-- 更新菜单名称和顺序
delete from EMP.TBL_MENU where MENUID in (-330009,-330008,-330007);
--update EMP.TBL_MENU set MENUNAME = '统计分析', FUNCTIONID = -330040, SEQUENCE = 9 where MENUID = -330009;
--update EMP.TBL_MENU set MENUNAME = '报表管理', FUNCTIONID = -330036, SEQUENCE = 8 where MENUID = -330008;
--update EMP.TBL_MENU set MENUNAME = '任务队列', FUNCTIONID = -330036, SEQUENCE = 7 where MENUID = -330007;
update EMP.TBL_MENU set MENUNAME = '笔记管理', FUNCTIONID = -330036, SEQUENCE = 6 where MENUID = -330006;
update EMP.TBL_MENU set MENUNAME = '快速查询', FUNCTIONID = -330029, SEQUENCE = 5 where MENUID = -330005;
update EMP.TBL_MENU set MENUNAME = '查看日志', FUNCTIONID = -330021, SEQUENCE = 4 where MENUID = -330004;
update EMP.TBL_MENU set MENUNAME = '数据源列表', FUNCTIONID = -330017, SEQUENCE = 3 where MENUID = -330003;
update EMP.TBL_MENU set MENUNAME = '角色列表', FUNCTIONID = -330011, SEQUENCE = 2 where MENUID = -330002;
update EMP.TBL_MENU set MENUNAME = '用户列表', FUNCTIONID = -330005, SEQUENCE = 1 where MENUID = -330001;

-- 2.功能整理
-- 查询时默认不显示结果
update EMP.TBL_FUNCTION set functionurl='http://boss3g.yeepay.com/databank-boss/log/list.action?_queryable=false' where functionid = -330021;

-- 删除无用功能（已合并Action））
delete from EMP.TBL_FUNCTION where functionid in (-330001,-330003,-330007,-330009,-330013,-330015,-330022,-330024,-330028,-330034);

-- 更新功能名称
update EMP.TBL_FUNCTION set functionname='[数据银行]导入用户' where functionid = -330002;
update EMP.TBL_FUNCTION set functionname='[数据银行]更新用户' where functionid = -330004;
update EMP.TBL_FUNCTION set functionname='[数据银行]创建角色' where functionid = -330008;
update EMP.TBL_FUNCTION set functionname='[数据银行]更新角色' where functionid = -330010;
update EMP.TBL_FUNCTION set functionname='[数据银行]创建数据源' where functionid = -330014;
update EMP.TBL_FUNCTION set functionname='[数据银行]更新数据源' where functionid = -330016;
update EMP.TBL_FUNCTION set functionname='[数据银行]创建报表' where functionid = -330023;
update EMP.TBL_FUNCTION set functionname='[数据银行]更新报表' where functionid = -330025;
update EMP.TBL_FUNCTION set functionname='[数据银行]快速查询' where functionid = -330029;
update EMP.TBL_FUNCTION set functionname='[数据银行]更新笔记' where functionid = -330035;

-- 删除失效功能-角色关系
delete from EMP.TBL_ROLEANDFUNCTIONRELATION where FUNCTIONID in (-330001,-330003,-330007,-330009,-330013,-330015,-330022,-330024,-330028,-330034);

-- 3.新增功能
INSERT INTO EMP.TBL_FUNCTION (FUNCTIONID, FUNCTIONNAME, FUNCTIONURL, PREFUNCTIONID, FUNCTIONSTATUS, DESCRIPTION, RISKLEVEL, FUNCTIONTYPE, CREATETIME, CHECKFUNCTIONID, CHECKNEEDED, LOGNEEDED) VALUES (-330001, '[数据银行]加载Schema和表', 'https://boss3g.yeepay.com/databank-boss/datasource/loadTable.action', -99910033, 'ACTIVE', '加载指定数据源下面的Schema和表', 'MIDDLE', 'DEPARTMENT_PRIVATE', CURRENT_TIMESTAMP, null, null, null);
INSERT INTO EMP.TBL_ROLEANDFUNCTIONRELATION (ROLEID, FUNCTIONID) values (-2,-330001);
INSERT INTO EMP.TBL_ROLEANDFUNCTIONRELATION (ROLEID, FUNCTIONID) values (1,-330001);

-- 4.角色反向权限表
CREATE TABLE DATABANK.TBL_ROLE_NOTALLOW
(
	ROLE_ID BIGINT NOT NULL,
	NOT_ALLOW VARCHAR(40) NOT NULL
);

-- 加索引
CREATE INDEX DATABANK."ROLE_NOTALLOW_ID_index" ON DATABANK.TBL_ROLE_NOTALLOW(ROLE_ID);