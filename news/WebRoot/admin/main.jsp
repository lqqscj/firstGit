<%@page import="com.action.bean.ActionBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员信息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/style.css">
  </head>
  
  <body>
  <div class="main-frame">
  <jsp:include page="top.jsp"></jsp:include>
  <jsp:include page="left.jsp"></jsp:include>
  <div class="right">
    <div class="admin">
  		<h1 class="title">后台主页</h1>
  		<div class="welcome">欢迎您使用新闻发布系统</div>
  	</div>
  </div>
  </div>
  <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
