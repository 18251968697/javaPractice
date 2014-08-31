<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" %>
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
<%	rs = sql.queryVip(userId);
	rs.next();
	float balance = rs.getFloat(2);
	float discount = rs.getFloat(5);
	int level = rs.getInt(3);
	int score = rs.getInt(4);
%>
<%	str = request.getParameter("submit");
	if(str!=null&&str.equals("提   交")){
		String amountStr = request.getParameter("addValue");
		double amount = Double.parseDouble(amountStr);
		balance += amount;
		score += ((int)amount)*10;
		if(score < 100)
			level = 0;
		else if(score < 500)
			level = 1;
		else if(score < 1000)
			level = 2;
		else if(score < 2000)
			level = 3;
		else if(score < 5000)
			level = 4;
		else if(score < 10000)
			level = 5;
		else if(score < 20000)
			level = 6;
		else if(score < 50000)
			level = 7;
		else if(score < 100000)
			level = 8;
		else if(score < 200000)
			level = 9;
		else
			level = 10;
	switch(level){
		case 0: discount = 0; break;
		case 1: discount = (float)9.8; break;
		case 2: discount = (float)9.6; break;
		case 3: discount = (float)9.0; break;
		case 4: discount = (float)8.8; break;
		case 5: discount = (float)8.6; break;
		case 6: discount = (float)8.0; break;
		case 7: discount = (float)7.8; break;
		case 8: discount = (float)7.6; break;
		case 9: discount = (float)7.0; break;
		case 10: discount = (float)6.0; break;
		default: discount = 0; 
	}
	sql.updateVip(userId, balance, level, score, discount);
	sql.addTranselation(userId, "w", amount, balance, "充值");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户会员中心界面</title>
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
      <a href="UserVIP.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('会员中心','','image/buttoned3.png',1)"><img src="image/buttoned3.png" alt="会员中心" width="100%" height="100" id="会员中心" /></a>
      <a href="UserTask.jsp" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('任务管理','','image/buttoned4.png',1)"><img src="image/button4.png" alt="任务管理" width="100%" height="100" id="任务管理" /></a>
    </ul>
  
    <p><!-- end .sidebar1 --></p>
  </div>
  <div class="content">
     <table width="100%" height="75" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
  
    <h3>会员信息：</h3>
      <table width="50%" border="0"  class="Label2" cellspacing="30" cellpadding="5">
      <tr>
        <td width="40%">等&nbsp;&nbsp; 级：</td>
        <td width="60%">&nbsp;<%=level %></td>
      </tr>
      <tr>
        <td>账户余额：</td>
        <td>&nbsp;<%=balance %></td>
      </tr>
      <tr>
        <td>账户积分：</td>
        <td>&nbsp;<%=score %></td>
      </tr>
      <tr>
        <td>所享折扣：</td>
        <td>&nbsp;<%=discount %></td>
      </tr>
   </table>
    <h3>会员充值：</h3>
    <form name="register" method="post" action="">
    <p class="Label2">
    充值：    <label for="addValue"></label> 
         <span id="addValueSpry">
         <input name="addValue" type="text" id="addValue" size="30">
         <span class="textfieldRequiredMsg">您输入的充值码有误。</span></span>
    </p>
    <p >&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
          <input type="submit" name="submit" id="submit" value="提   交">
         &nbsp; &nbsp;&nbsp; &nbsp; 
         <input type="reset" name="re_fill" id="re_fill" value="重   填">
    </p>
    </form>
    <table cellspacing="1" class="fix">
        <tr>
        <td><a href="UserPayEntry.jsp" onmouseout="MM_swapImgRestore()" 
        onmouseover="MM_swapImage('消费记录查看','','image/buttonedUV.png',1)">
        <img src="image/buttonUV.png" alt="消费记录查看" width="150" height="60" id="消费记录查看" /></a></td>
        </tr>
    </table>
    
    <p>&nbsp;</p>
    <h3>等级制度说明：</h3>
    <ol> 
    	<li>您的等级将与您在本系统执行任务所可以享受到的折扣优惠程度密切相关。</li>
        <li>您的等级高低是由您的积分多少所决定的。</li>
        <li>您每次在本系统支付一定的消费金额，并进行任务操作后，都可以获得一定的积分。</li>
        <li>具体等级与积分、折扣的对应关系，请以最近公告声明为准。</li>
    </ol>
    
    <h3>帮助：</h3>
    <p class="Label1"> 如果您有需要发布的消息，请联系开发人员。  </p>
  </div>
  <div class="footer">
    <p>系统开发人员：余训昭，杨源，杨征  ╮(╯▽╰)╭   JAVA实习3什么的</p>
    <!-- end .footer --></div>
  <!-- end .container --></div>
  
<script type="text/javascript">
var addValueSpry = new Spry.Widget.ValidationTextField("addValueSpry");
</script>
</body>
</html>