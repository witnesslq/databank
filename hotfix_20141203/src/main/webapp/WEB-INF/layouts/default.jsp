<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <title><sitemesh:title/> - 数据银行</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <link href="<%=request.getContextPath()%>/static/js/vendor/jquery-validation/1.10.0/validate.css" rel="stylesheet"
        type="text/css"/>
  <link href="<%=request.getContextPath()%>/static/style/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/static/style/bootstrap-responsive.min.css" rel="stylesheet"
        type="text/css"/>
  <link href="<%=request.getContextPath()%>/static/style/databank.min.css" rel="stylesheet" type="text/css"/>

  <script src="<%=request.getContextPath()%>/static/js/vendor/jquery-1.9.1.min.js" type="text/javascript"></script>
  <script src="<%=request.getContextPath()%>/static/js/vendor/bootstrap.min.js" type="text/javascript"></script>

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
  <script src="<%=request.getContextPath()%>/static/js/vendor/html5shiv.js"></script>
  <![endif]-->

  <sitemesh:head/>
</head>
<body>
<sitemesh:body/>
<%@ include file="/WEB-INF/layouts/footer.jsp" %>
<script src="<%=request.getContextPath()%>/static/js/vendor/jquery-validation/1.10.0/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/js/vendor/jquery-validation/1.10.0/messages_bs_zh.js"
        type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/js/vendor/bootbox.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116" type="text/javascript"></script>
</body>
</html>