function startPost(){
	   var nav_name = document.getElementById("nav_name").value;
	   var nav_feight = document.getElementById("nav_feight").value;
	   if(nav_name == ""){
		   document.getElementById("err_nav_name").innerHTML="<span class='err'>请输入类别名称。</span>";
		   return;
	   }
	   if(nav_feight == ""){
		   document.getElementById("err_nav_feight").innerHTML="<span class='err'>请输入类别排序权重，值越小越靠前。</span>";
		   return;
	   }
	   if(isNaN(parseInt(nav_feight))){
		   document.getElementById("err_nav_feight").innerHTML="<span class='err'>类别排序权重必须是整型，值越小越靠前。</span>";
		   return;
	   } 
	   document.getElementById("frm").submit();
}
