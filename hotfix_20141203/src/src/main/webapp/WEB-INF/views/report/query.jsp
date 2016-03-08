<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <title>快速查询</title>
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/style/font-awesome.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/style/databank.css"/>--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/style/databank.min.css?v=20150113"/>
</head>
<body>
<div id="top" class="Container">
    <div class="Content fontText">
        <div class="information" style="margin-top:0">
            <div class="search_tit">
                <h1 class="fw fleft">快速查询</h1>
                <a href="javascript:void(0)" id="help" style="margin-right:20px;float:right;color:blue">[帮助]</a>
                <a href="javascript:void(0)" id="mailTo" style="margin-right:20px;float:right;color:blue">[反馈]</a>
            </div>
            <div class="input_cont">
                <form id="queryForm" action="query" class="form-horizontal" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">数据源：</label>
                            <e:if test="@note.dsId > 0">
                                <e:select id="dsList" name="dsId" list="@dsList" listKey="id" listValue="name" class="select"
                                          value="@note.dsId" onchange="loadSchema()"/>
                                Schema：<e:select id="schemaList" name="schemaId" list="@schemaList" class="select"
                                                 value="@note.schema" onchange="loadIndex()"/>
                            </e:if>
                            <e:else>
                                <e:select id="dsList" name="dsId" list="@dsList" listKey="id" listValue="name" class="select"
                                          value="@lastDsId" onchange="loadSchema()"/>
                                Schema：<e:select id="schemaList" name="schemaId" list="@schemaList" class="select" value=""
                                                 onchange="loadIndex()"/>
                            </e:else>
                            <a href="#indexDiv" onclick="$('#indexDiv').show();" style="color:blue">[表约束]</a>
                            行数：<select id="lineNum" name="lineNum" class="select" style="width:65px">
                            <option value="1">1</option>
                            <option value="5">5</option>
                            <option value="20" selected="selected">20</option>
                            <option value="100">100</option>
                            <option value="200">200</option>
                            <option value="500">500</option>
                            <option value="1000">1000（谨慎）</option>
                        </select>
                            <%--&nbsp;隔离级别：<select id="isolation" name="isolation" class="select">--%>
                            <%--&lt;%&ndash;<option value="RR">可重复读 RR</option>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<option value="RS">读稳定性 RS</option>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<option value="CS">游标稳定性 CS(DB2默认,风险自付)</option>&ndash;%&gt;--%>
                                <%--<option value="UR" selected="selected">未提交读 UR(脏读)</option>--%>
                        <%--</select>--%>
                            &nbsp;<label style="float:none;font-size: 12px;"><input type="checkbox" id="quickMode" name="quickMode" value="1"/>
                            快速查询</label>
                            &nbsp;<label style="float:none;font-size: 12px;"><input type="checkbox" id="skipSqlCheck" name="skipSqlCheck" value="1"/>
                            跳过语法检查</label>
                        </li>
                        <li>
                            <label class="text_tit">SQL 语句：</label>
                            <input type="hidden" id="noteId" name="noteId" value="<e:property value='@note.id'/>"/>

                            <div class="full-width-hack">
                <textarea id="inputSQL" name="sql"
                          onchange="$('.fileName').val('');$('.queryResult').hide();"><e:property
                        value='@note.query'/></textarea>
                            </div>
                        </li>
                        <li>
                            <label class="text_tit">&nbsp;</label>

                            <div class="btn">
                                <button type="button" id="submit" class="btn_sure fw has-spinner" onclick="executeQuery(this);"><span
                                        class="spinner" style="padding:0 0 0 3px;"><i class="icon-spin icon-refresh"></i></span>
                                    执 行
                                </button>
                                <button type="reset" class="btn_cancel fw"
                                        onclick="reset();$('#inputSQL').trigger('update');cleanResult(this);">重 置
                                </button>
                                <button type="button" class="btn_sure fw" style="margin-left:20px" onclick="saveNote();">
                                    新笔记
                                </button>
                                <button type="button" class="btn_sure fw" onclick="showNote()">载笔记</button>
                                <button type="button" id="update" class="btn_sure fw" style="display:none" onclick="updateNote();">
                                    更笔记
                                </button>
                                <button type="button" id="showSQL" class="btn_sure fw queryResult" style="display:none;margin-left:20px"
                                        onclick="MessageBoxExt.popup('SQL',{width:600,buttons:[{text:'关闭',click:function(){$(this).dialog('close');}}]});">
                                    分析
                                </button>
                                <button type="button" id="excel" class="btn_sure fw queryResult" style="display:none"
                                        onclick="downQuery('excel', 1);">Excel
                                </button>
                                <button type="button" id="csv" class="btn_sure fw queryResult" style="display:none"
                                        onclick="downQuery('csv', 1);">CSV
                                </button>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="resultDiv" style="display:none;margin:0 auto">
    <h1>查询结果</h1>

    <div id="result" class="span12"></div>
    <div class='mt20'>
        <h1>以上最多显示 1000 条记录，如需更多数据请点击
            <button type="button" id="csv" class="btn_sure fw queryResult" style="display:none"
                    onclick="downQuery('csv', 0);">CSV
            </button>
        </h1>
    </div>
</div>
<div id="indexDiv" style="display:none;margin:0 auto;text-align:left">
    <h1>查看表索引（<a href="#top" style="color:blue">回到查询界面</a>）</h1>
    <table class="table table-striped table-hover table-condensed">
        <thead>
        <tr>
            <th style="min-width:100px">Schema</th>
            <th>表名</th>
            <th>索引字段</th>
            <th style="min-width:100px">约束类型</th>
            <th style="min-width:80px">系统定义?</th>
        </tr>
        </thead>
        <tbody id="indexDivBody">
        <tr>
            <td colspan="5">请先选择数据源和Schema</td>
        </tr>
        </tbody>
    </table>
</div>
<div id="noteDiv" style="display:none;text-align:left">
    <ul></ul>
</div>
<span id="noteTip" style="display:none"><e:property value='@note.name'/></span>
<span id="SQL" style="display:none;text-align:left"></span>

<div id="modal">
    <div id="modalBackground">
    </div>
    <div id="modalWarpper">
        <button type="button" onclick="$('#modal').hide();">&times;</button>
        <h2>奴家已经很努力了，客官请稍候...</h2>

        <div id="progress-bar"></div>
    </div>
</div>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common.js"></script>--%>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.js"></script>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20150113"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.elastic.js?v=20150113"></script>
<script>
    $(function () {
        $("#inputSQL").elastic();
        $("#inputSQL").trigger('update');
        $("#help").click(function () {
            MessageBoxExt.ajax({
                url: GV.ctxPath + "/note/delete.action",
                style: 'NONE',
                success: function (data) {
                    $("#noteDiv").html(data.value);
                    MessageBoxExt.popup("noteDiv", {
                        width: 600,
                        title: "使用帮助",
                        buttons: [
                            {
                                text: '关闭',
                                click: function () {
                                    $(this).dialog("close");
                                }
                            }
                        ]
                    });
                }
            });
        });
        $("#mailTo").click(function () {
            var url = decodeURIComponent(location.search);
            var content = "mailto:baitao.ji@yeepay.com?subject=DataBank 使用反馈&body="
            content += "问题描述：<br/><br/><br/><br/>------信息采集，请勿修改------<br/>";
            content += "<br/>来源页面：" + url;
            content += "<br/>数据源号：" + $("#dsList").val();
            content += "<br/>条数限制：" + $("#lineNum").val();
            content += "<br/>查询语句：" + $("#inputSQL").val();
            window.location = content;
        });
        <e:if test="@type == 'query'">loadIndex();
        setTimeout('$("#submit").click()', 1000);
        $("#update").show();
        </e:if>
        <e:if test="@type == 'edit'">$("#update").show();
        </e:if>
        <e:elseif test="@type == 'excel'">$("#excel").click();
        </e:elseif>
        <e:elseif test="@type == 'csv'">$("#csv").click();
        </e:elseif>
    });
</script>
<script id="json-notelist" type="json"><e:property value="@noteList" escape="false"/></script>
<footer class="footer">
    <div class="container">
    </div>
</footer>
</body>
</html>