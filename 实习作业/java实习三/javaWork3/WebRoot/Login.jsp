<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" %>
<%	String sess_id = (String)session.getAttribute("userId");
	if(sess_id!=null && !sess_id.equals("")){
		response.sendRedirect("UserMain.jsp");
		return;
	}
	request.setCharacterEncoding("utf-8");
	String str=request.getParameter("login");
	if(str!=null&&str.equals("登   录")){
			String userId = request.getParameter("userId");
			String userPassword = request.getParameter("pw");
			if(userId!=null&&!userId.equals("")&&userPassword!=null&&!userPassword.equals("")){
				String userType = request.getParameter("select");
				Sql sql = new Sql();
				if(userType.equals("manager")){
					if(sql.queryAdminiter(userId, userPassword)){
						sql.closeConnection();
						session.setAttribute("ManagerId", userId);
						response.sendRedirect("ManagerMain.jsp");
						return;
					}
				}
				else if(userType.equals("user")){
					if(sql.queryUser(userId, userPassword)){
						session.setAttribute("userId", userId);
						response.sendRedirect("UserMain.jsp");
						return;
					}
				}
			}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<style type="text/css">
.Login {
	width:40%;
	min-height: 300px;
	background-color: #e8e8e8;
	border:thin outset #cdcdcd;
	float: right;
	margin: 0 auto;    
}

</style>
<link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
<link href="SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css" />
<link href="System.css" rel="stylesheet" type="text/css">
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 500px;
	height: 300px;
	z-index: 1;
	left: 10%;
	top: 200px;
}
</style>
<link href="SpryAssets/SpryTooltip.css" rel="stylesheet" type="text/css" />
<script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
<script src="SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
<script src="SpryAssets/SpryEffects.js" type="text/javascript"></script>
<script src="SpryAssets/SpryTooltip.js" type="text/javascript"></script>
<script type="text/javascript">
function MM_effectBlind(targetElement, duration, from, to, toggle)
{
	Spry.Effect.DoBlind(targetElement, {duration: duration, from: from, to: to, toggle: toggle});
}
</script>
</head>

<body>
<div class="container">
	<p> </p>
    <div class="header">
    
    	<table width="100%" height="180" border="0">
        <tr>
        <td width="20%"><img src="image/ifttt_logo.png" width="100%" height="100" alt="Logo" /></td>
        <td width="80%"><img src="image/Title.png" width="100%" height="100" alt="title_image" /></td>
        </tr>
    <!-- end .header --> </table>
    </div>
    
     <div id="apDiv1" style="500px:height:300px; z-index:2;"><img src="image/login.png" width="100%" height="300" onclick="MM_effectBlind('apDiv1', 1000, '100%', '5%', true)" /></div>
  <div id="apDiv1" style="500px:height:300px; z-index:1;"><img src="image/login2.png" width="100%" height="300" id="sprytrigger1" /></div>
    <div class="Login">
<table align="center" cellspacing="20">
  <tr class="Title" align="center">
		<td height="50">IFTTT&nbsp; 系统登录</td>
    </tr>
  </table>
	<form name="login" method="post" action="">
    <table align="center" cellspacing="20">
    	<td>
            <p class="Label2">
              账号：<label for="userId"></label> 
              <span id="userIdSpry">
                <input name="userId" type="text" id="userId" size="20">
                <span class="textfieldRequiredMsg">请输入您的真实姓名。</span></span>
              </p>
            <p class="Label2">
              密码：
              <span id="passwordSpry">
                <input name="pw" type="password" id="pw" size="20">
                <span class="passwordRequiredMsg">请输入不少于6位字符且不多于16位字符的密码。</span>
                <span class="passwordMinCharsMsg">不符合最小字符数要求。</span>
                <span class="passwordMaxCharsMsg">已超过最大字符数。</span></span>
              </p>
            <p class="Label2">身份：
              <select name="select" id="select">
                <option value="user">会员用户</option>
                <option value="manager">管理员</option>
              </select>
              </p>
            <p align="center">
              <input type="submit" name="login" id="login" value="登   录" />
              &nbsp;&nbsp;&nbsp; &nbsp; 
              <a href="Register.jsp" target="_self"><input type="submit" name="register" id="register" value="注   册"  /></a>
              </p>
    	</td>    
	</table>
    </form>
    </div>
</div>
<div class="tooltipContent" id="sprytooltip1">赶快在右侧进行登录吧！</div>
<script type="text/javascript">
//var userIdSpry = new Spry.Widget.ValidationTextField("userIdSpry");
//var passwordSpry = new Spry.Widget.ValidationPassword("passwordSpry");
var sprytooltip1 = new Spry.Widget.Tooltip("sprytooltip1", "#sprytrigger1", {followMouse:true, closeOnTooltipLeave:true, useEffect:"fade"});
</script>
</body>
</html>