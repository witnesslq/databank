﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <title>用户申请数据源</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <div class="information">
            <h1 class="fw">用户申请数据源</h1>

            <div class="input_cont">
                <form id="roleForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">用户名：</label>

                            <p><e:property value="@userName"/></p>
                        </li>
                        <li style="overflow:visible;">
                            <label class="text_tit">数据源权限：</label>
                        </li>
                    </ul>
                    <div>
                        <e:iterator list="@dataSourcePermission">

                            <div>
                                <table style=" width: 85%" cellspacing="5" cellpadding="3">
                                    <tr>
                                        <td style="width: 20px;font-size:medium">

                                        </td>
                                        <td style="width: 20px;font-size:medium">
                                            允许表:
                                        </td>
                                        <td style="font-size:medium">
                                            <e:property value="@tablePermission.get(_this.id+'').get('allow')"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 25px;font-size:medium">
                                            <e:property value='@_this.name'/>
                                        </td>
                                        <td>
                                            <label style="width:auto;min-width:0px;font-size:medium">允许列:</label></td>
                                        <td style="width: 60px;font-size:medium">
                                            <e:property
                                                    value="@permissionColumn.get(_this.id+'').get('allowColumn')"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 25px;font-size:medium">
                                        </td>
                                        <td>
                                            <label style="width:auto;min-width:0px;font-size:medium">禁止表:</label></td>
                                        <td style="width: 20px;font-size:medium">
                                            <e:property
                                                    value="@tablePermission.get(_this.id+'').get('notAllow')"/>
                                        </td>
                                    <tr>
                                    <tr>
                                        <td style="width: 25px;font-size:medium">
                                        </td>
                                        <td>
                                            <label style="width:auto;min-width:0px;font-size:medium">禁止列:</label></td>
                                        <td style="width: 20px;font-size:medium">
                                            <e:property
                                                    value="@permissionColumn.get(_this.id+'').get('notAllowColumn')"/>
                                        </td>
                                    <tr>
                                </table>
                            </div>
                        </e:iterator>
                    </div>
                </form>
                &nbsp;
                <div>
                    <li><label class="text_tit" style="margin-left: 100px"></label>
                        <button type="button" class="btn_cancel fw" onclick="history.go(-1)">返 回</button>
                    </li>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
</body>
</html>