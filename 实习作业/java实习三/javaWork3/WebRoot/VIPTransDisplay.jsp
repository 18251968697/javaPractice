<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql"%>
<% 	Sql sql = new Sql();
	ResultSet rs;
	request.setCharacterEncoding("utf-8");

	String userId=(String)session.getAttribute("ManagerId");
	if(userId==null||userId.equals("")){
		response.sendRedirect("Login.jsp");
		return;
	}

	String str=request.getParameter("cancle");
	if(str!=null && str.equals("true")){
		session.setAttribute("ManagerId", null);
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<%	String vipId = request.getParameter("vipId");
	if(vipId==null&&vipId.equals("")){
		response.sendRedirect("VIPManager.jsp");
		return;
	}
%>
<%	rs = sql.queryTranselate(vipId);
	String transId=null, transDesc=null, transDate=null;
	float transAmount=-1, transBalance=-1;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员管理界面</title>
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
  <table width="1000px" height="40px" align="center"  background="image/BarBackground2.png">
    <tr>
      <td width="80%">&nbsp;&nbsp;欢迎您，管理员：<%=userId %></td>
      <td width="10%" align="center"><a href="mailto:b111220167@smail.nju.edu.cn">联系我们</a></td>
      <td width="10%" align="center"><a href="ManagerMain.jsp?cancle=true">注销</a></td>
    </tr>
  </table>
  <p> </p>
  <div class="sidebar1">
     <ul>
     <a href="ManagerMain.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('首页公告','','image/buttoned1.png',1)"><img src="image/button1.png" alt="首页公告" width="100%" height="100" id="首页公告" /></a>
       <a href="VIPManage.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('会员管理','','image/buttoned3.png',1)"><img src="image/buttoned3.png" alt="会员管理" width="100%" height="100" id="会员管理" /></a>
      <a href="TaskManage.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('任务管理','','image/buttoned4.png',1)"><img src="image/button4.png" alt="任务管理" width="100%" height="100" id="任务管理" /></a>
    </ul>
 
    <p><!-- end .sidebar1 --></p>
  </div>
  <div class="content">

<h2>用户： <%=vipId %> 交易记录：</h2>
	<table align="center" width="80%" border="1" cellspacing="1" cellpadding="1" background="image/tableBackground.png">
      <tr class="Label3" align="center">
        <td width="20%">账单号</td>
        <td width="30%">交易日期</td>
        <td width="30%">充值详情/任务详情</td>
        <td width="10%">金额</td>
        <td width="10%">余额</td>
      </tr>

<%	while(rs.next()){
		transId = rs.getString(2).trim();
		transDate = rs.getString(7).trim();
		transDesc = rs.getString(6).trim();
		transAmount = rs.getFloat(4);
		transBalance = rs.getFloat(5);
		out.print("<tr><td align=center>"+transId+"</td><td align=center>"+transDate+"</td><td align=center>"
		+transDesc+"</td><td align=center>"+transAmount+"</td><td align=center>"+transBalance+"</td></tr>");
	}
%>
    </table>
    
  </div>
  <p>&nbsp;</p>
  <div class="footer">
    <p>系统开发人员：余训昭，杨源，杨征  ╮(╯▽╰)╭   JAVA实习3什么的</p>
 	<p>&nbsp;</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>
