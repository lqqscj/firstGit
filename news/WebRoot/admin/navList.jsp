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
    
    <title>新闻类别管理</title>
    
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
  		<h1 class="title">新闻类别管理<a href="admin/main.jsp" class="back-btn">返回主页</a></h1>
  		<table class="tab" cellspacing="0">
  			<tr><td style="width:40px;">选择</td><td class="text-center" style="width:40px;">序号</td><td class="text-center">类别名称</td><td class="text-center">排序权重</td></tr>
  			<%
  			ActionBean actionBean = new ActionBean();
  			out.print(actionBean.queryNavAll());
  			%>
  			<tr><td colspan="4" style="border-bottom:0"><a class="btn" href="admin/addNav.jsp">增加类别</a>
  			<a class="btn" href="javascript:void(0)" id="update-btn" onclick="updateNav()">修改</a>
  			<a class="btn" href="javascript:void(0)" id="delete-btn" onclick="deleteNav()">删除</a>
  			</td></tr>
  		</table>
  	</div>
  </div>
  </div>
  <jsp:include page="footer.jsp"></jsp:include>
  <div class="win-box" id="win-box">
  	<h2 class="win-title">修改新闻类别<span onclick="closeNavWindow(0);">&times;</span></h2>
  	<iframe src="" id="update-win"></iframe>
  </div>
  	<script type="text/javascript">
  	function selectedRow(){
  		var ms = document.getElementsByName("rad");
  		var obj=null;
  		for(var i=0;i<ms.length;i++){
  			if(ms[i].checked){
  				obj=ms[i];
  				break;
  			}
  		}
  		if(null==obj){
  			alert("^_^ 请选择一行再操作。");
  		}
  		return obj;
  	}
  	function updateNav(){
  		var obj = selectedRow();
  		if(null == obj)return;
  		updateNavWindow("admin/editNav.jsp?nav_id="+obj.value);
  	}
  	
  	function deleteNav(){
  		var obj = selectedRow();
  		if(obj==null)return;
  		if(confirm("您确认要删除当前类别吗？")){
  			location="admin/deleteNav?nav_id="+obj.value;
  		}
  	}
  	function updateNavWindow(url){
  		document.getElementById("win-box").style.display="block";
  		document.getElementById("update-win").src=url;
  	}
  	function closeNavWindow(f){
  		document.getElementById("win-box").style.display="none";
  		if(f==1){
  			location = location;
  		}
  	}
  	</script>
  </body>
</html>
