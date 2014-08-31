<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" import="task.Translate" import="task.SubTask"	import="task.Task" %>
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
<%	str = request.getParameter("submit");
	if(str!=null&&str.equals("创   建")){
		String tskName = request.getParameter("submit_tName");
		String ifType = request.getParameter("submit_tIfType");
		String thenType = request.getParameter("submit_tThenType");
		String ifStr=null, thenStr=null;
		if(ifType.equals(Integer.toString(SubTask.ORDERTIME))){
			String date = request.getParameter("submit_date");
			String time = request.getParameter("submit_time");
			ifStr = Translate.OrderTimeToString(date, time);
		}
		else if(ifType.equals(Integer.toString(SubTask.RECVMAIL))){
			String mailId = request.getParameter("submit_recvMailId");
			String mailPw = request.getParameter("submit_recvMailPw");
			ifStr = mailId+"\n"+mailPw;
		}
		String senderId = request.getParameter("submit_id");
		String senderPw = request.getParameter("submit_pw");
		String sendCst = request.getParameter("submit_cst");
		if(thenType.equals(Integer.toString(SubTask.SENDWEIBO))){
			thenStr = senderId+"\n"+senderPw+"\n"+sendCst;
		}
		else if(thenType.equals(Integer.toString(SubTask.SENDMAIL))){
			String toAddr = request.getParameter("submit_toAddr"); 
			String title = request.getParameter("submit_title");
			thenStr =  senderId+"\n"+senderPw+"\n"+toAddr+"\n"+title+"\n"+sendCst;
		}		
		sql.addTask(userId, tskName, Integer.parseInt(ifType), ifStr, Integer.parseInt(thenType), thenStr,	"创建成功！");
		float pay = 10;
		sql.constume(userId, pay, "创建任务："+tskName);
		response.sendRedirect("UserTask.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户任务创建界面</title>
<style type="text/css">
.resize{
	resize:none;
}
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
<script>
var it=-1, tt=-1;

function clickIf(n){
	it = n;
	a = document.getElementById("dateLable");
	b = document.getElementById("timeLable");
	c = document.getElementById("mailIdLable");
	d = document.getElementById("mailPwLable");
	if(n == 1){
		a.style.visibility = "visible";
		b.style.visibility = "visible";
		c.style.visibility = "hidden";
		d.style.visibility = "hidden";	
	}
	else if(n == 2){
		a.style.visibility = "hidden";
		b.style.visibility = "hidden";
		c.style.visibility = "visible";
		d.style.visibility = "visible";
	}
}

function clickThen(n){
	tt = n;
	a = document.getElementById("acountLable");
	b = document.getElementById("pwLable");
	c = document.getElementById("titleLable");
	d = document.getElementById("cstLable");
	e = document.getElementById("toMailAddrLable");
	
	a.style.visibility = "visible";
	b.style.visibility = "visible";
	d.style.visibility = "visible";
	if(n==4){
		c.style.visibility = "visible";
		e.style.visibility = "visible";
	}
	else{
		c.style.visibility = "hidden";
		e.style.visibility = "hidden";
	}
}
function clickCreate(){
	a = document.getElementById("submit_tn");
	b = document.getElementById("submit_tit");
	c = document.getElementById("submit_ttt");
	d = document.getElementById("submit_d");
	e = document.getElementById("submit_time");
	f = document.getElementById("submit_rmi");
	g = document.getElementById("submit_rmp");
	h = document.getElementById("submit_i");
	i = document.getElementById("submit_p");
	j = document.getElementById("submit_c");
	k = document.getElementById("submit_title");
	l = document.getElementById("submit_td");
	
	b.value = it;
	c.value = tt;
	if(it==1){
		a_tmp = document.getElementById("tskName");
		d_tmp = document.getElementById("date");
		e_tmp = document.getElementById("time");
		a.value = a_tmp.value;
		d.value = d_tmp.value;
		e.value = e_tmp.value;
	}
	else if(it==2){
		a_tmp = document.getElementById("tskName");
		f_tmp = document.getElementById("mailId");
		g_tmp = document.getElementById("mailPw");
		a.value = a_tmp.value;
		f.value = f_tmp.value;
		g.value = g_tmp.value;
	}
	h_tmp = document.getElementById("acount");
	i_tmp = document.getElementById("pw");
	j_tmp = document.getElementById("cst");
	h.value = h_tmp.value;
	i.value = i_tmp.value;
	j.value = j_tmp.value;
	if(tt == 4){
		k_tmp = document.getElementById("title");
		l_tmp = document.getElementById("toMailAddr");
		k.value = k_tmp.value;
		l.value = l_tmp.value;
	}
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

<h2>新建您的任务</h2>
<p>&nbsp;</p>
<p class="Label3"> <label>任务名： </label>
 	<input type="text" name="tskName" id="tskName" />
  	</p>
<h3>请选择使用下列哪种事件作为THIS事件：</h3>
<table width="630" border="0" cellspacing="50" cellpadding="0">
  <tr>
    <td width="150" height="150"><img src="image/clock.png" width="150" height="150" onclick="clickIf(1)"/></td>
    <td width="150" height="150"><img src="image/Gmail.png" width="150" height="150" onclick="clickIf(2)"/></td>
    <td width="150" height="150">&nbsp;</td>
    <td width="150" height="150">&nbsp;</td>
  </tr>
</table>

<table>
<tr>
<td style="visibility:hidden;" id="dateLable">
	<p class="Label3"> <label>日  期</label>
 	<input type="text" name="date" id="date" />
  	</p>
</td>
<td style="visibility:hidden;" id="mailIdLable">
 	<p class="Label3"> <label >账  号</label>
  	<input name="mailId" type="text" id="mailId" />
  	</p>
</td>
</tr>
<tr>
<td style="visibility:hidden;" id="timeLable">
  <p class="Label3"><label >时   间</label>
  <input type="text" name="time" id="time" />
  </p>
  </td>
  <td style="visibility:hidden;" id="mailPwLable">
  <p class="Label3"><label >密  码</label>
  <input name="mailPw" type="password" id="mailPw" />
  </p>
  </td>
  </tr>
</table>

<p>&nbsp;</p>
<h3>请选择使用下列哪种事件作为THAT事件：</h3>
<table width="630" border="0" cellspacing="50" cellpadding="0">
  <tr>
    <td width="150" height="150"><img src="image/weibo.png" width="150" height="150" onclick="clickThen(3)"/></td>
    <td width="150" height="150"><img src="image/Gmail.png" width="150" height="150" onclick="clickThen(4)"/></td>
    <td width="150" height="150">&nbsp;</td>
    <td width="150" height="150">&nbsp;</td>
  </tr>
</table>
<table>
<tr>
<td style="visibility:hidden;" id="acountLable">
	<p class="Label3"><label >账号</label>
  	<input type="text" name="acount" id="acount" />
	</p>
</td>
</tr>
<tr>
<td style="visibility:hidden;" id="pwLable">
	<p class="Label3"><label >密码</label>
  	<input name="pw" type="password" id="pw" />
	</p>
</td>	
</tr>
<tr>
<td style="visibility:hidden;" id="titleLable">
	<p class="Label3"><label >邮件标题</label>
  	<input type="text" name="title" id="title" />
	</p>
</td>
</tr>
<tr>
<td style="visibility:hidden;" id="cstLable">
	<p class="Label3"><label >内容</label>
  	<textarea name="cst" cols="50" class="resize" id="cst" ></textarea>
	</p>
</td>
</tr>
<tr>
<td style="visibility:hidden;" id="toMailAddrLable">
	<p class="Label3"><label >收件地址
 	 </label><input name="toMailAddr" type="text" id="toMailAddr" />
	</p>
</td>
</tr>
</table>
<form name="register"  method="post" action="">
<input type="hidden" name="submit_tName" id="submit_tn"/>
<input type="hidden" name="submit_tIfType" id="submit_tit"/>
<input type="hidden" name="submit_tThenType" id="submit_ttt"/>
<input type="hidden" name="submit_date" id="submit_d"/>
<input type="hidden" name="submit_time" id="submit_time"/>
<input type="hidden" name="submit_recvMailId" id="submit_rmi"/>
<input type="hidden" name="submit_recvMailPw" id="submit_rmp"/>
<input type="hidden" name="submit_id" id="submit_i"/>
<input type="hidden" name="submit_pw" id="submit_p"/>
<input type="hidden" name="submit_cst" id="submit_c"/>
<input type="hidden" name="submit_title" id="submit_title"/>
<input type="hidden" name="submit_toAddr" id="submit_td"/>
<p align="center">
   		<input type="submit" name="submit" id="submit" value="创   建" onclick="return clickCreate();">
        &nbsp; &nbsp;&nbsp; &nbsp; 
        <input type="reset" name="re_fill" id="re_fill" value="重   置">
</p>

</form>

<p>&nbsp;</p>
    
    
    <table cellspacing="1" class="fix">
    	<td><a href="UserTask.jsp" onmouseout="MM_swapImgRestore()" 
    	onmouseover="MM_swapImage('返回','','image/buttonedGetBack.png',1)">
    	<img src="image/buttonGetBack.png" alt="返回" width="150" height="60" id="返回" /></a></td>
        </tr>
    </table>
    
    <h3>帮助：</h3>
    <p class="Label1"> 如果您有需要发布的消息，请联系开发人员。  </p>
  </div>
  <div class="footer">
    <p>系统开发人员：余训昭，杨源，杨征  ╮(╯▽╰)╭   JAVA实习3什么的</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
</body>
</html>