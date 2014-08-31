<%@ page contentType="text/html; charset=utf-8" import="java.sql.*" errorPage="" %>
<%@ page import="sql.Sql" %>
<%  Sql sql = new Sql();
	request.setCharacterEncoding("utf-8");
	
	String str = request.getParameter("submit");
	if(str!=null&&str.equals("提   交")){
		String userId = request.getParameter("userId").trim();
		String pw = request.getParameter("pw");
		String sex = request.getParameter("member_sex");
		String age = request.getParameter("age");
		String mail = request.getParameter("mail");
		sql.addNewUser(userId, pw);
		sql.addUserInfo(userId, sex, Integer.parseInt(age), mail);
		response.sendRedirect("Login.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
<style type="text/css">
.Register {
	width:80%;
	min-height: 300px;
	background-color: #e8e8e8;
	border:thin outset #cdcdcd;
	float: center;
	margin: 0 auto;    
}

</style>
    <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
    <link href="SpryAssets/SpryValidationPassword.css" rel="stylesheet" type="text/css">
    <link href="SpryAssets/SpryValidationConfirm.css" rel="stylesheet" type="text/css">
    <link href="System.css" rel="stylesheet" type="text/css">
    
    <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
	<script src="SpryAssets/SpryValidationPassword.js" type="text/javascript"></script>
	<script src="SpryAssets/SpryValidationConfirm.js" type="text/javascript"></script>
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
    
    <div class="Register">
	<table align="center" cellspacing="20">
 	<tr class="Title" align="center" >
		<td height="50">注册会员&nbsp; 加入IFTTT</td>
    </tr>
    </table>
	<form name="register" method="post" action="Register.jsp">
    <table width="80%" align="center" cellspacing="20">
		<tr border="0">
   			<td ><p class="Label1">填写会员信息（必填）</p></td>
		</tr>
    	<tr>
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
            <p class="Label2">
            确认密码：
                <span id="confirmSpry">
                <input name="confirm_pw" type="password" id="confirm_pw" size="20">
               	<span class="confirmRequiredMsg">请再次输入您的密码。</span>
                <span class="confirmInvalidMsg">确认密码与首次输入密码不符。</span></span> 
           </p>
           <p class="Label2">
           性别：<label for="male">男</label>
				<input type="radio" name="member_sex" id="male" value="male">
	            &nbsp;  &nbsp;  &nbsp; 
                <label for="female">女</label>     
	          	<input type="radio" name="member_sex" id="female" value="female">
           </p>
           <p class="Label2">
           年龄：<label for="age"></label> 
           		<span id="ageSpry">
                <input name="age" type="text" id="age" size="20">
           		<span class="textfieldRequiredMsg">请输入您的年龄。</span></span>
           </p>
           <p class="Label2">
           邮箱：<label for="mail"></label> 
           		<span id="mailSpry">
                <input name="mail" type="text" id="mail" size="20">
           		<span class="textfieldRequiredMsg">请输入您的邮箱。</span></span>
           </p>
          
           <p align="center">
                <input type="submit" name="submit" id="submit" value="提   交">
                &nbsp; &nbsp;&nbsp; &nbsp; 
                <input type="reset" name="re_fill" id="re_fill" value="重   填">
          </p>
			</td>
        </tr>
    </table>
	</form>
	</div>
</div>
<script type="text/javascript">
var userIdSpry = new Spry.Widget.ValidationTextField("userIdSpry");
var passwordSpry = new Spry.Widget.ValidationPassword("passwordSpry", {minChars:6, maxChars:16});
var confirmSpry = new Spry.Widget.ValidationConfirm("confirmSpry", "pw");
var ageSpry = new Spry.Widget.ValidationTextField("ageSpry");
var mailSpry = new Spry.Widget.ValidationTextField("mailSpry");
</script>
</body>
</html>