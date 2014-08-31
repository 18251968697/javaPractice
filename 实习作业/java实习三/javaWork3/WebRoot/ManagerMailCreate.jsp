<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" import="java.util.Date" import="java.text.SimpleDateFormat"%>

<%	Sql sql = new Sql();
	ResultSet rs=null;
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
<%	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String mailDate = sdf.format(new Date());
	str = request.getParameter("createAMail");
	if(str!=null && str.equals("发   布")){
		String mailTitle = request.getParameter("mailTitle");
		String mailCst = request.getParameter("textarea");
		String toAddr = request.getParameter("toAddr");
		sql.sendMail(userId, toAddr, mailTitle, mailCst);
		response.sendRedirect("ManagerMail.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员公告发布界面</title>
<style type="text/css">

</style>
<!--[if lte IE 7]>
<style>
.content { margin-right: -1px; }  /* 此 1px 负边距可以放置在此布局中的任何列中，且具有相同的校正效果。 */
ul.nav a { zoom: 1; }             /* 缩放属性将为 IE 提供其需要的 hasLayout 触发器，用于校正链接之间的额外空白 */
</style>
<![endif]-->
<link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
<link href="System.css" rel="stylesheet" type="text/css">
    
<script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
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
      <a href="ManagerMain.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('首页公告','','image/buttoned1.png',1)"><img src="image/buttoned1.png" alt="首页公告" width="100%" height="100" id="首页公告" /></a>
      <a href="VIPManage.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('会员中心','','image/buttoned3.png',1)"><img src="image/button3.png" alt="会员中心" width="100%" height="100" id="会员中心" /></a>
      <a href="TaskManage.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('任务管理','','image/buttoned4.png',1)"><img src="image/button4.png" alt="任务管理" width="100%" height="100" id="任务管理" /></a>
    </ul>
   
    <p><!-- end .sidebar1 --></p>
  </div>
  
  <div class="content" >
  <table width="100%" height="75" border="0">

      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
    
    <h2>新站内信： </h2>
    <p>&nbsp;</p>
    <form name="UserMailCreate" method="post" action="">
    <table width="700px" height="300px" align="center" border="2" cellpadding="2" cellspacing="2">
      <tr>
        <td width="10%" rowspan="4">
        <p>信</p>
        <p>件</p>
        <p>内</p>
        <p>容：</p>
        </td>
        <td width="90%" height="30" class="Label3">
        	日&nbsp;&nbsp;&nbsp;期：
            <input type="text" size=50 name="mailDate" id="mailDate" value=<%=mailDate %> />
        </td>
      </tr>
            <tr>
        <td height="30" class="Label3">
            收件人：<label for="mailTitle"></label> 
            <span id="mailTitleSpry">
            <input name="toAddr" type="text" id="toAddr" size="50" />
          	<span class="textfieldRequiredMsg">请输入信件的标题。</span></span>
        </td>
      </tr>
      <tr>
        <td height="30" class="Label3">
            标&nbsp;&nbsp;&nbsp;题：<label for="mailTitle"></label> 
            <span id="mailTitleSpry">
            <input name="mailTitle" type="text" id="mailTitle" size="50" />
          	<span class="textfieldRequiredMsg">请输入信件的标题。</span></span>
        </td>
      </tr>
      <tr>
        <td height="210">
          <label for="messageContent"></label>
          <textarea name="textarea" id="textarea" cols="90" rows="18">在此输入公告内容：日期为自动填充当前时间</textarea></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p align="center">
                <input type="submit" name="createAMail" id="submit" value="发   布">
                &nbsp; &nbsp;&nbsp; &nbsp; 
                <input type="reset" name="re_fill" id="re_fill" value="重   置">
    </p>
    </form>
  
	<table cellspacing="10" class="fix">
    	<td><a href="ManagerMain.jsp" title="返回公告首页" target="_self">返回站内信首页</a></td>
    </table>
    <p>&nbsp;</p>
  	

<p>&nbsp;</p>
    
  <h4>帮助：</h4>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 如果您有需要发布的消息，请联系管理员。  </p>
  </div>
  
  <div class="footer">
    <p>系统开发人员：余训昭，杨源，杨征  ╮(╯▽╰)╭   JAVA实习3什么的</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
  
<script type="text/javascript">
var messageTitleSpry = new Spry.Widget.ValidationTextField("messageTitleSpry");
</script>
</body>
</html>
