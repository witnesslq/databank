<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑查询</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <form id="reportForm" action="<%=request.getContextPath()%>/report/<e:property value='@method'/>.action"
                  class="form-horizontal" method="POST">
                <div class="control-group">
                    <label class="control-label">数据源</label>
                    <div class="controls">
                        <select class="select">
                            <option value="config_1">config_1</option>
                            <option value=""></option>
                            <option value=""></option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputDescription">SQL 语句</label>
                    <div class="controls">
                        <textarea id="inputDescription" class="input-xlarge" rows="4"></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn_sure fw" style="margin-right:10px">校 验</button>
                        <button type="submit" class="btn_sure fw" style="margin-right:10px">保 存</button>
                        <button type="reset" class="btn_cancel fw" style="margin-right:10px">重 置</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    function onDbTypeChange() {
        var dbType = $("#dbType").val();
        if (dbType == "db2")
            $("#inputConnStr").val("jdbc:db2://<host>:<port>/<database_name>");
        else if (dbType == "mysql")
            $("#inputConnStr").val("jdbc:mysql://[host][,failoverhost...][:port]/[database]");
        else if (dbType == "h2")
            $("#inputConnStr").val("jdbc:h2:<os_path_to_file>");
        else
            $("#inputConnStr").val("What? Are you crazy?");
    }
    $(function () {
        $("#reportForm").validate();
    });
</script>
</body>
</html>