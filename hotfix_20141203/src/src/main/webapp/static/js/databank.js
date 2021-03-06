/* 删除确认 */
function deleteConfirm(id, objStr, selecter) {
    MessageBoxExt.confirm("确认删除 " + objStr + "#" + id + " 吗？", function () {
        MessageBoxExt.ajax({
            url: GV.ctxPath + "/" + selecter + "/delete.action",
            type: 'POST',
            data: {
                "id": id
            },
            checkSuccess: function (data) {
                if (data.status == "OK") {
                    $("#" + id).parent().remove();
                    return true;
                }
            }
        });
    });
}
/* 选择数据库类型 */
function onDbTypeChange() {
    var dbType = $("#dbType").val();
    if (dbType == "DB2")
        $("#inputConnStr").val("jdbc:db2://<host>:<port>/<database_name>");
    else if (dbType == "MYSQL")
        $("#inputConnStr").val("jdbc:mysql://[host][,failoverhost...][:port]/[database]");
    else if (dbType == "H2")
        $("#inputConnStr").val("jdbc:h2:<os_path_to_file>");
    else {
        $("#inputConnStr").val("What? Are you crazy?").addClass("disable");
    }
}
/* 加载指定数据源下面的所有Schema */
function loadSchema() {
    var dsId = $("#dsList").val();
    var schemaList = $("#schemaList");
    $("#modal").show();
    MessageBoxExt.ajax({
        url: GV.ctxPath + "/datasource/loadTable.action?type=schema&id=" + dsId,
        type: 'POST',
        style: 'NONE',
        checkSuccess: function (data) {
            schemaList.empty();
            $.each(data, function () {
                schemaList.append("<option value='" + this + "'>" + this + "</option>");
            });
            $("#schemaList option:first").val("");
            $("#modal").hide();
            return true;
        },
        afterBizError: function () {
            $("#modal").hide();
            MessageBoxExt.alert("未知的异常，工程师稍后会跟进该问题。");
        }
    });
}
/* 加载指定Schema下面的所有约束 */
function loadIndex() {
    var dsId = $("#dsList").val();
    var schema = $("#schemaList").val();
    if (schema != '') {
        MessageBoxExt.ajax({
            url: GV.ctxPath + "/datasource/loadTable.action?type=index&id=" + dsId + "&schema=" + schema,
            type: 'POST',
            style: 'NONE',
            checkSuccess: function (data) {
                indexArray = new Array();
                var content = $("#indexDivBody");
                content.html("");
                $.each(data, function () {
                    var cols = this.split(",");
                    if (cols[3] == "P") {
                        cols[3] = "主键索引";
                    }
                    else if (cols[3] == "U") {
                        cols[3] = "唯一索引";
                    }
                    else if (cols[3] == "D") {
                        cols[3] = "普通索引";
                    }
                    content.append("<tr><td>" + cols[0] + "</td><td>" + cols[1] + "</td><td>" + cols[2] + "</td><td>" + cols[3] + "</td><td>" + cols[4] + "</td></tr>");
                    if (typeof indexArray[cols[2]] == "undefined") {
                        indexArray[cols[2]+' '] = cols[2];
                    }
                });
                return true;
            },
            afterBizError: function () {
                MessageBoxExt.alert("未知的异常，工程师稍后会跟进该问题。");
            }
        });
    }
    else {
        indexArray = undefined;
    }
}
/* 执行查询语句 */
function executeQuery(that) {
    var sql = processText($("#inputSQL").val());
    var dsId = $("#dsList").val();
    if (dsId == null) {
        MessageBoxExt.alert("亲，给俺指定个数据源呗！");
        return false;
    }
    var upperSQL = sql.toUpperCase();
    var whereIndex = upperSQL.indexOf(" WHERE ") + 7;
    var whereCon = upperSQL.substring(whereIndex, upperSQL.length);
    if (upperSQL.indexOf("SELECT") < 0) {
        MessageBoxExt.alert("想干嘛？我目前只支持 Select 语句！");
        return false;
    }
    $("#result").empty();
    var flag = true;
    if (whereIndex > 7 && typeof(indexArray) != 'undefined') {
        // 有where子句并且有表索引
        console.debug("Where子句:" + whereCon);
        console.debug("索引：" + indexArray);
        flag = false;
        for (var item in indexArray) {
            if (whereCon.indexOf(item) > 0) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            MessageBoxExt.confirm("WHERE 子句不包含索引字段，这将导致低效的查询操作。请再次确认该操作的必要性！", function () {
                execute(that, dsId, sql);
            });
        }
    }
    if (flag) {
        if ((upperSQL.indexOf("SELECT * ") >= 0 || upperSQL.indexOf("DISTINCT ") > 0 || upperSQL.indexOf(" ORDER BY ") > 0 || upperSQL.indexOf(" OUTER JOIN ") > 0 || upperSQL.indexOf(" WHERE ") < 0)) {
            MessageBoxExt.confirm("查询语句中包含 SELECT *、ORDER BY、OUTER JOIN、DISTINCT 或不包含 WHERE 子句，这将导致低效的查询操作。请再次确认该操作的必要性！", function () {
                execute(that, dsId, sql);
            });
        }
        else {
            execute(that, dsId, sql);
        }
    }
}
/* 执行查询 */
function execute(that, dsId, sql) {
    var startTime = new Date().getTime();
    $(that).attr("disabled", true).toggleClass('active');
    MessageBoxExt.ajax({
        url: GV.ctxPath + "/query/query.action",
        type: 'POST',
        data: {
            "dsId": dsId,
            "sql": sql,
            "num": $("#lineNum").val(),
            "noteId": $("#noteId").val(),
            "schema": $("#schemaList").val(),
            "isolation": $("#isolation").val(),
            "quickMode": $("#quickMode:checked").val(),
            "skipSqlCheck": $("#skipSqlCheck:checked").val()
        },
        style: 'NONE',
        checkSuccess: function (data) {
            if (data.status == "error") {
                MessageBoxExt.alert(data.message);
                $(".queryResult").hide();
                $("#resultDiv").hide();
                $("#result").empty();
            }
            else {
                $(".queryResult").show();
                data.printMilliSecond = printResultSet(data);
                data.totleMilliSecond = new Date().getTime() - startTime;
                printSQLStatus(data);
            }
            $(that).attr("disabled", false).toggleClass('active');
            return true;
        },
        afterBizError: function () {
            MessageBoxExt.alert("未知的异常，工程师稍后会跟进该问题。");
            $(that).attr("disabled", false).toggleClass('active');
        }
    });
}
var reg = /&#[0-9]{5};/g;
function utfIgnoreLength(str) {
    var len = str.length;
    var c = str.match(reg);
    if (null != c) {
        len -= c.length * 6;
    }
    return len;
}
/* 填充查询结果 */
function printResultSet(data) {
    var startTime = new Date().getTime();
    var resultDiv = $("#result");
    resultDiv.empty();

    // SQL 语句执行出错
    if (data.header == "") {
        resultDiv.append("<center><span class='label label-important'>注意：</span> 结果为空，可能您的 SQL 语句不正确。</center>");
        return;
    }

    var table = "<table class='list'><tr>";
    var totalWidth = 0;
    var maxlength = new Array();
    $(data.header).each(function (index, element) {
        maxlength[index] = utfIgnoreLength(element);
        table += "<th>" + element + "</th>";
    });

    // SQL 语句执行正确，但是结果集为空
    if (data.content == "") {
        table += "</tr><tr><td colspan='" + data.header.length + "'>注意：结果集为空。</td></tr>";
    }
    else {
        $(data.content).each(function (index, item) {
            if (index % 2 == 0)
                table += "</tr><tr>";
            else
                table += "</tr><tr class='even'>";
            var i = 0;
            for (var key in item) {
                var len = utfIgnoreLength(item[i]);
                if (len > maxlength[i]) {
                    maxlength[i] = len;
                }
                table += "<td>" + item[i++] + "</td>";
            }
        });
    }
    table += "</tr><tr>";
    for (var i = 0; i <= data.header.length - 1; ++i) {
        totalWidth += maxlength[i];
//		table += "<td>"+maxlength[i]+"</td>";
    }
    table = table.substring(0, table.length - 5);
    table += "</table>";
    resultDiv.append(table);
    $("#result").css("width", Math.max(totalWidth, 50) + 'em');
//	var clientWidth = document.body.clientWidth;
//	if (clientWidth > totalWidth*16) {
//		$("#result").css("width", clientWidth + 'px');
//	}
//	else {
//		$("#result").css("width", totalWidth + 'em');
//	}
    $("#resultDiv").show();
    return new Date().getTime() - startTime;
}
function cleanResult(that) {
    $(that).attr("disabled", false).toggleClass('active');
    $("#resultDiv").hide();
    var resultDiv = $("#result");
    resultDiv.empty();
}
/* 打印查询结果参数 */
function printSQLStatus(data) {
    $("#SQL").html("<p style='margin-left:10px;margin-right:10px;'>实际执行：" + data.actualSql + "</p><p style='margin-left:10px;margin-right:10px;'>返回条数：" + data.size + "<br>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>访表数量：" + data.tables + " 个</p><p style='margin-left:10px;margin-right:10px;'>访列数量：" + data.columns + " 个</p><p>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>条件数量：" + data.conditions + " 个</p><p style='margin-left:10px;margin-right:10px;'>关联关系：" + data.relationships + " 个</p><p>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>排序数量：" + data.orderBy + " 个</p><p style='margin-left:10px;margin-right:10px;'>分组数量：" + data.groupBy + " 个</p><p>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>开始时间：" + data.startDateTime + "</p><p style='margin-left:10px;margin-right:10px;'>分析用时：" + data.analysisMilliSecond + " ms</p><p>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>查询用时：" + data.usedMilliSecond + " ms</p><p style='margin-left:10px;margin-right:10px;'>显示用时：" + data.printMilliSecond + " ms</p><p>" +
        "</p><p style='margin-left:10px;margin-right:10px;'>共计用时：" + data.totleMilliSecond + " ms</p>")
}
/* 显示笔记让用户选择 */
function showNote() {
    $("#noteDiv").html("<ul></ul>");
    var noteList = $.parseJSON($('#json-notelist').html()) || {};
    $.each(noteList, function (index, element) {
        var label = "default";
        if (element.usedTimes > 1000) {
            label = "danger";
        }
        else if (element.usedTimes > 500) {
            label = "warning";
        }
        else if (element.usedTimes > 100) {
            label = "primary";
        }
        else if (element.usedTimes > 20) {
            label = "info";
        }
        var item = "<li><a href='javascript:void(0)' onclick='loadNote(\"" + index + "\");'><span class='label label-" + label + "'>" + element.name + "</span></a></li>";
        $("#noteDiv ul").append(item);
    });

    if (noteList.length == 0)
        MessageBoxExt.alert("木有笔记！");
    else
        MessageBoxExt.popup("noteDiv", {
            width: 650,
            title: "加载笔记",
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
/* 从记事本加载查询 */
function loadNote(index) {
    var oldDsId = $("#dsList").val();
    var noteList = $.parseJSON($('#json-notelist').html()) || {};
    var noteId = noteList[index].id;
    var noteName = noteList[index].name;
    var dsId = noteList[index].dsId;
    var schema = noteList[index].schema;
    var query = noteList[index].query;
    $("#dsList").val(dsId);
    if (dsId != $("#dsList").val()) {
        $("#dsList").val(oldDsId);
        MessageBoxExt.alert("您无权访问相应的数据源 #" + dsId + ". 请确认：<br/>1.该数据源是否已经被删除<br/>2.您是否有权访问该数据源");
    }
    else {
        $("#inputSQL").val(query.replace(/&acute;/g, "'")).trigger('update');
        MessageBoxExt.unpopup("noteDiv");
        var schemaList = $("#schemaList");
        if (null != schema && "" != schema && "undefined" != schema) {
            schemaList.append("<option value='" + schema + "'>" + schema + "</option>").val(schema);
        }
        else {
            schemaList.val("");
        }
        $("#noteId").val(noteId);
        $("#noteTip").html(noteName);
        $(".queryResult").hide();
        $("#update").show();
        schemaList.trigger("change");
    }
}
/* 保存查询到记事本 */
function saveNote() {
    var dsId = $("#dsList").val();
    var dsName = $("#dsList").find("option:selected").text();
//	var query = $.trim($("#inputSQL").val()).replace(/\s+/g, " ");
    var query = $("#inputSQL").val();//.replace(/\s+/g, " ");
    var noteId = $("#noteId").val();
    var notePrefix = ["笔记如花", "如意笔记", "芙蓉笔记"];
    var result = prompt("给要创建的笔记起个名字：", dsName + "-" + $("#schemaList").val() + "-" + notePrefix[Math.floor(Math.random() * notePrefix.length)] + Math.floor(Math.random() * 1000 + 1));
    if (result === null || result == "") {
        MessageBoxExt.alert("矮油, 不想保存也别调戏人家嘛 *^_^*");
    }
    else if (result.length > 80) {
        MessageBoxExt.alert("白素贞我叫你名字你敢答应么，敢啊!我的全名是素贞蒙蒂奇路飞卡利斯勒赤木晴子亚历山大卡卡西伊丽莎白。啊，名字这么长完全记不住啊!小青我叫你名字你敢答应么，敢啊!我的全名是小琉璃爱梦莲泪冰雪殇离陌梦爱樱沫渺落璃千梦然丝伤青，名字也这么长完全记不住啊!许仙我叫你一声你敢答应么，敢啊!我就叫许仙，但是我不是妖怪啊!<br><br>笔记名称好长，完全记不住啊亲!");
    } else {
        MessageBoxExt.ajax({
            url: GV.ctxPath + "/note/create.action",
            data: {
                "note.name": result.replace(/[,'"]/g, ""),
                "note.dsId": dsId,
                "note.schema": $("#schemaList").val(),
                "note.query": query
            },
            style: 'NONE',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                if (data.status === "OK") {
                    $("#noteDiv ul").append($("<li><a href='javascript:void(0)' onclick='loadNote(\"" + data.id + "\")'><span class='label label-primary'>" + result + "</span></a></li>"));
                }
                else {
                    MessageBoxExt.alert(data.message);
                }
            }
        });
    }
}
/* 更新记事本 */
function updateNote() {
    var dsId = $("#dsList").val();
    var query = $("#inputSQL").val();//.replace(/\s+/g, " ");
    var noteId = $("#noteId").val();
    var noteName = $("#noteTip").html();
    var result = prompt("给要更新的笔记起个新名字嘛：", noteName);
    if (result === null) {
        // 取消操作
    } else if (result == "") {
        MessageBoxExt.alert("矮油, 不想保存也别调戏人家嘛 *^_^*");
    } else {
        MessageBoxExt.ajax({
            url: GV.ctxPath + "/note/update.action",
            data: {
                "note.id": noteId,
                "note.name": result.replace(/[,'"]/g, ""),
                "note.dsId": dsId,
                "note.schema": $("#schemaList").val(),
                "note.query": query
            },
            style: 'NONE',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                if (data.status === "OK") {
                    $("#noteDiv > ul").append($("<li><a href='javascript:void(0)' onclick='loadNote(\"" + data.id + "\")'><span class='label label-primary'>" + result + "</span></a></li>"));
                    $("#noteTip").html(noteName);
                }
                else {
                    MessageBoxExt.alert(data.message);
                }
            }
        });
    }
}
/* 下载查询结果 */
function downQuery(fileType, type) {
    if (type == 1) {
        down(fileType, $("#lineNum").val());
    }
    else {
        MessageBoxExt.confirm("免责声明：本按钮无视返回结果集行数限制，请使用者自己确认查询字段、条件是否合适。如果导致线上数据库问题一概与本按钮无关！友情提示：指定字段、限制返回数量、避开交易高峰", function () {
            down("csv", 0);
        });
    }
}
function down(fileType, size) {
    var sql = processText($("#inputSQL").val());
    $("#modal").show();
    $.ajax({
        url: GV.ctxPath + "/query/down.action",
        type: "POST",
        data: {
            "dsId": $("#dsList").val(),
            "schema": $("#schemaList").val(),
            "isolation": $("#isolation").val(),
            "sql": sql,
            "type": fileType,
            "num": size,
            "noteId": $("#noteId").val()
        },
        style: 'NONE',
        success: function (data) {
            if (data.status == "error") {
                MessageBoxExt.alert(data.message);
            }
            else {
                downloadFile(GV.ctxPath + "/static-content?contentPath=/" + data.message + "&download=true");
            }
            $("#modal").hide();
        }
    });
}
/* 下载文件 */
function downloadFile(url) {
    var elemIF = document.createElement("iframe");
    elemIF.src = url;
    elemIF.style.display = "none";
    document.body.appendChild(elemIF);
}
/* 处理 textarea */
function processText(text) {
    var arr = text.split("\n");
    var newArr = "";
    var num = arr.length;
    for (loop = 0; loop < num; ++loop) {
        var line = $.trim(arr[loop]);
        if (line.indexOf("--") == -1) {
            newArr += line + " ";
        }
    }
    return newArr;
}
/* debug */
function dump(arr, level) {
    var dumped_text = "";
    if (!level) level = 0;

    //The padding given at the beginning of the line.
    var level_padding = "";
    for (var j = 0; j < level + 1; j++) level_padding += "    ";

    if (typeof(arr) == 'object') { //Array/Hashes/Objects
        for (var item in arr) {
            var value = arr[item];

            if (typeof(value) == 'object') { //If it is an array,
                dumped_text += level_padding + "'" + item + "' ...\n";
                dumped_text += dump(value, level + 1);
            } else {
                dumped_text += level_padding + "'" + item + "' => \"" + value + "\"\n";
            }
        }
    } else { //Stings/Chars/Numbers etc.
        dumped_text = "===>" + arr + "<===(" + typeof(arr) + ")";
    }
    return dumped_text;
}