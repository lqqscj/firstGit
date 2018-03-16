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
  		<h1 class="title">管理员信息列表<a href="admin/main.jsp" class="back-btn">返回主页</a></h1>
  		<table class="tab" cellspacing="0">
  			<tr><td>选择</td><td class="text-center">序号</td><td class="text-center">登录帐号</td><td class="text-center">真实姓名</td></tr>
  			<%
  			ActionBean actionBean = new ActionBean();
  			out.println(actionBean.queryManagerAll());
  			%>
  			<tr><td colspan="4" style="border-bottom:0"><a class="btn" href="admin/addManager.jsp">增加管理员</a>
  			<a class="btn" href="javascript:void(0)" id="update-btn" onclick="updateManager()">修改</a>
  			<a class="btn" href="javascript:void(0)" id="delete-btn" onclick="deleteManager()">删除</a>
  			</td></tr>
  		</table>
  	</div>
  </div>
  </div>
  <jsp:include page="footer.jsp"></jsp:include>
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
  	function updateManager(){
  		var obj = selectedRow();
  		if(obj==null)return;
  		location="admin/editManager.jsp?manager_id="+obj.value;
  	}
  	function deleteManager(){
  		var obj = selectedRow();
  		if(obj==null)return;
  		if(confirm("您确认要删除当前用户吗？")){
  			location="admin/deleteManager?manager_id="+obj.value;
  		}
  	}
  	</script>
  </body>
</html>
