﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <title>数据源申请详情</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <div class="information">
            <h1 class="fw">数据源申请详情</h1>

            <div class="input_cont">
                <form id="myForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">数据源名称：</label>

                            <p><e:property value='@ds.name'/>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">数据库类型：</label>

                            <p><e:property value='@ds.dbType'/></p>
                        </li>
                        <li>
                            <label class="text_tit">使用时间：</label>

                            <p><e:property value='@allowUseTime'/></p>
                        </li>

                        <li>
                            <label class="text_tit">申请状态：</label>

                            <p><e:property value='@applyEntity.applyStatus'/></p>
                        </li>
                        <li>
                            <label class="text_tit">申请理由：</label>

                            <p><e:property value='@applyEntity.applyReason'/></p>
                        </li>
                        <li>
                            <label class="text_tit">审核理由：</label>

                            <p><e:property value='@applyEntity.reviewMessage'/></p>
                        </li>
                        <li>
                            <label class="text_tit">审核人姓名：</label>

                            <p><e:property value='@applyEntity.reviewName'/></p>
                        </li>
                        <br/>
                        <li style="margin-left: 100px">
                            <button type="button" class="btn_cancel fw" onclick="history.go(-1)">返 回</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
</body>
</html>