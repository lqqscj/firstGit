<%@page import="com.action.bean.ActionBean,com.entity.Nav,com.entity.Article"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ActionBean actionBean = new ActionBean();
String article_id = request.getParameter("article_id");
Map resultMap = actionBean.queryArticleMap4Edit(article_id);
List <Nav>list = (List)resultMap.get("navList");
//actionBean.queryArticleId();
Article article = (Article)resultMap.get("article");
//error ?
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改新闻</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/style.css">
	<link rel="stylesheet" href="<%=basePath %>admin/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath %>admin/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath %>admin/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="<%=basePath %>admin/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="<%=basePath %>admin/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=basePath %>admin/upload_json.jsp',
				fileManagerJson : '<%=basePath %>admin/file_manager_json.jsp',
				allowFileManager : true,
				afterBlur:function(){ 
		            this.sync(); 
		        },
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
	<script language="javascript" type="text/javascript" src="<%=basePath %>datePicker/WdatePicker.js"></script>
  </head>
  
  <body>
  <div class="main-frame">
  <jsp:include page="top.jsp"></jsp:include>
  <jsp:include page="left.jsp"></jsp:include>
  <div class="right">
	  <div class="admin">
	  	<h1 class="title">修改新闻<a href="admin/articleList.jsp" class="back-btn">返回</a></h1>
	    <form name="frm" action="admin/editArtitlce" id="frm" method="post">
	    <input name="article_id" value="<%=article.getArticle_id()%>" type="hidden"/>
	    <table>
	    	<tr>
		    	<td>新闻标题：</td>
		    	<td><input id="article_title" name=article_title 
		    	autocomplete="off" maxlength="300" type="text" value="<%=article.getArticle_title()%>"/></td>
		    	<td id="err_article_title"></td>
	    	</tr>
	    	<tr><td>新闻类别：</td><td>
				<select id="nav_id" name="nav_id" autocomplete="off">
					<option value="">==请选择新闻类别==</option>
					<%
					
					for(Nav nav : list){
						//String sel = nav.getNav_id().equals(article.getNav_id())?" selected='selected' ":"";
						%>
						<option <%=nav.getNav_id().equals(article.getNav_id())?" selected='selected' ":"" %> value="<%=nav.getNav_id()%>"><%=nav.getNav_name() %></option>
						<%
					}
					%>
				</select>
				</td>
				<td id="err_nav_id"></td>
			</tr>
			<tr><td>发布时间：</td>
				<td>
					<input id="article_date" name="article_date" 
					autocomplete="off" maxlength="20" type="text" value="<%=article.getArticle_date() %>" 
					onClick="WdatePicker()" readonly="readonly"/>
				</td>
				<td id="err_article_date"></td></tr>
	    	<tr>
	    		<td valign="top">新闻正文：</td>
	    		<td colspan="2">
	    			<textarea name="content1" rows="" cols="" style="width:680px;height:400px;"><%=com.tools.MyFuns.htmlspecialchars(article.getArticle_content()) %></textarea>
	    		</td>
	    	</tr>
	    	<tr><td></td><td><a class="btn" onclick="startPost();" href="javascript:void(0)">保存</a></td></tr>
	    </table>
	    </form>
	   </div>
	  </div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include> 
   <script type="text/javascript">
   function valiInput(id,msg,n){
	   var val = document.getElementById(id).value;
	   if(val=="" || val.length<n){
		   document.getElementById("err_"+id).innerHTML="<span class='err'>"+msg+"</span>";
		   return false;
	   }else{
		   document.getElementById("err_"+id).innerHTML="<span class='success'>验证通过。</span>";
		   return true;
	   }
   }
   function startPost(){
	   var ipts=["article_title","nav_id","article_date"];
	   var lens=[2,1,10];
	   var msg=["请输入标题，至少两个字符。","请选择新闻类别","请输入发布时间"];
	   var flag=true;
	   for(var i=0;i<ipts.length;i++){
		   flag = valiInput(ipts[i],msg[i],lens[i])&&flag;
	   }
	   if(!flag)return;
	   document.getElementById("frm").submit();
   }
   </script>
  </body>
</html>