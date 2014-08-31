<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" import="task.SubTask" import="task.Task" %>
<%	Sql sql = new Sql();
	ResultSet rs=null;
	request.setCharacterEncoding("utf-8");
	
	String userId=(String)session.getAttribute("userId");
	if(userId==null||userId.equals("")){
		response.sendRedirect("Login.jsp");
		return;
	}
		
	String str=request.getParameter("cancle");
	if(str!=null && str.equals("true")){
		session.setAttribute("userId", null);
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<%	rs = sql.queryTask(userId);
	String tskName=null, ifThis=null, thenThat=null, tskState;
	int ifType=-1, thenType=-1;
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户任务管理界面</title>
<style type="text/css">

</style>
<!--[if lte IE 7]>
<style>
.content { margin-right: -1px; }  /* 此 1px 负边距可以放置在此布局中的任何列中，且具有相同的校正效果。 */
ul.nav a { zoom: 1; }             /* 缩放属性将为 IE 提供其需要的 hasLayout 触发器，用于校正链接之间的额外空白 */
</style>
<![endif]-->
<link href="System.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>
</head><body onload="MM_preloadImages('image/buttoned1.png','image/buttoned2.png','image/buttoned3.png','image/buttoned4.png')">


<div class="container">
 <p> </p>
<div class="header">
    
    <table width="100%" height="120">
      <tr>
        <td width="20%"><img src="image/ifttt_logo.png" width="100%" height="100" alt="Logo" /></td>
        <td width="80%"><img src="image/Title.png" width="100%" height="100" alt="title_image" /></td>
      </tr>
    <!-- end .header --> </table>
  </div>
  <table width="1000px" height="40px" align="center"  background="image/BarBackground.png">
    <tr>
      <td width="70%">&nbsp;&nbsp;欢迎您，会员：<%=userId %></td>
      <td width="10%" align="center"><a href="UserMail.jsp">站内信</td>
      <td width="10%" align="center"><a href="mailto:b111220167@smail.nju.edu.cn">联系我们</a></td>
      <td width="10%" align="center"><a href="UserMain.jsp?cancle=true">注销</a></td>
    </tr>
  </table>
  <p> </p>


  <div class="sidebar1">
     <ul>
      <a href="UserMain.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('首页公告','','image/buttoned1.png',1)"><img src="image/button1.png" alt="首页公告" width="100%" height="100" id="首页公告" /></a>
      <a href="UserPersonInfo.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('个人信息','','image/buttoned2.png',1)"><img src="image/button2.png" alt="个人信息" width="100%" height="100" id="个人信息" /></a>
      <a href="UserVIP.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('会员中心','','image/buttoned3.png',1)"><img src="image/button3.png" alt="会员中心" width="100%" height="100" id="会员中心" /></a>
      <a href="UserTask.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('任务管理','','image/buttoned4.png',1)"><img src="image/buttoned4.png" alt="任务管理" width="100%" height="100" id="任务管理" /></a>
    </ul>
 
    <p><!-- end .sidebar1 --></p>
  </div>
  <div class="content">
    
  <h1>
	<img src="image/Task_title.png" alt="任务管理，从这里开始" name="Task_title" width="100%" height="150" id="Task_title" style="background-color: #8090AB; display:block;" />
 </h1>

<h2>查看、删除、开始、停止您的任务</h2>
	<table width="70%", border="1", align="center" background="image/tableBackground.png" >
	<tr><td width="80%" align="center">任务名</td><td width="20%" align="center" >状态</td></tr>
<%	while(rs.next()){
		tskName = rs.getString(2).trim();
		tskState = rs.getString(7).trim();
		out.print("<tr><td><a href=UserTaskDetail.jsp?tskName="+tskName+">"+tskName+"</td><td align=center>"+tskState+"</td></tr>");
	} 

%>
	</table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    
    <table cellspacing="1" class="fix">
    	<td><a href="UserTaskCreate.jsp?tskName" 
    	onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('新建任务','','image/buttonedUTC.png',1)">
    	<img src="image/buttonUTC.png" alt="新建任务" width="150" height="60" id="新建任务" /></a></td>
    </table>
    
    <h3>帮助：</h3>
    <p class="Label1"> 如果您有需要发布的消息，请联系开发人员。  </p>
  </div>
  <p>&nbsp;</p>
  <div class="footer">
    <p>系统开发人员：余训昭，杨源，杨征  ╮(╯▽╰)╭   JAVA实习3什么的</p>
 	<p>&nbsp;</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>