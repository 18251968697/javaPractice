<%@LANGUAGE="VBSCRIPT"%>
<!--#include file="Connections/news.asp" -->
<%
' *** Validate request to log in to this site.
MM_LoginAction = Request.ServerVariables("URL")
If Request.QueryString <> "" Then MM_LoginAction = MM_LoginAction + "?" + Server.HTMLEncode(Request.QueryString)
MM_valUsername = CStr(Request.Form("admin_id"))
If MM_valUsername <> "" Then
  Dim MM_fldUserAuthorization
  Dim MM_redirectLoginSuccess
  Dim MM_redirectLoginFailed
  Dim MM_loginSQL
  Dim MM_rsUser
  Dim MM_rsUser_cmd
  
  MM_fldUserAuthorization = ""
  MM_redirectLoginSuccess = "admin_news.asp"
  MM_redirectLoginFailed = "login_fail.asp"

  MM_loginSQL = "SELECT admin_id, admin_pw"
  If MM_fldUserAuthorization <> "" Then MM_loginSQL = MM_loginSQL & "," & MM_fldUserAuthorization
  MM_loginSQL = MM_loginSQL & " FROM [admin] WHERE admin_id = ? AND admin_pw = ?"
  Set MM_rsUser_cmd = Server.CreateObject ("ADODB.Command")
  MM_rsUser_cmd.ActiveConnection = MM_news_STRING
  MM_rsUser_cmd.CommandText = MM_loginSQL
  MM_rsUser_cmd.Parameters.Append MM_rsUser_cmd.CreateParameter("param1", 200, 1, 255, MM_valUsername) ' adVarChar
  MM_rsUser_cmd.Parameters.Append MM_rsUser_cmd.CreateParameter("param2", 200, 1, 255, Request.Form("admin_pw")) ' adVarChar
  MM_rsUser_cmd.Prepared = true
  Set MM_rsUser = MM_rsUser_cmd.Execute

  If Not MM_rsUser.EOF Or Not MM_rsUser.BOF Then 
    ' username and password match - this is a valid user
    Session("MM_Username") = MM_valUsername
    If (MM_fldUserAuthorization <> "") Then
      Session("MM_UserAuthorization") = CStr(MM_rsUser.Fields.Item(MM_fldUserAuthorization).Value)
    Else
      Session("MM_UserAuthorization") = ""
    End If
    if CStr(Request.QueryString("accessdenied")) <> "" And false Then
      MM_redirectLoginSuccess = Request.QueryString("accessdenied")
    End If
    MM_rsUser.Close
    Response.Redirect(MM_redirectLoginSuccess)
  End If
  MM_rsUser.Close
  Response.Redirect(MM_redirectLoginFailed)
End If
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
	<HEAD>
		<META CONTENT="text/html; charset=gb2312" HTTP-EQUIV="Content-Type">
		<META NAME="Generator" CONTENT="PhotoImpact">
    <style type="text/css">
    body {
	background-image: url(images/bg.png);
	background-repeat: repeat-x;
	background-color: #144A19;
}
.text1 {
	font-size: 12px;
	font-weight: bold;
	color: #FFF;
}
.biankuang {
	border: 1px solid #EEF2C1;
}
    body,td,th {
	font-size: 12px;
	color: #FFFFFF;
}
    input {
	font-size: 12px;
	color: #FFF;
	background-color: #144A19;
	border: 1px solid #EEF2C1;
}
    </style>
    <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
    <title>TH_ERCO新闻报道</title>
    <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
</HEAD>
	<BODY TOPMARGIN="0" LEFTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">
	<table width="100" border="0" align="center" cellpadding="0" cellspacing="0">
	  <tr>
	    <td><TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0">
	      <TR>
	        <TD><IMG SRC="images/ziyuan_1.gif" WIDTH="49" BORDER="0" HEIGHT="53"></TD>
	        <TD COLSPAN="4"><IMG SRC="images/ziyuan_2.png" WIDTH="897" BORDER="0" HEIGHT="53"></TD>
	        <TD><IMG SRC="images/ziyuan_3.gif" WIDTH="54" BORDER="0" HEIGHT="53"></TD>
          </TR>
	      <TR>
	        <TD ROWSPAN="3">&nbsp;</TD>
	        <TD height="13" COLSPAN="4" bgcolor="#EEF2C1"><IMG SRC="images/news.png" WIDTH="282" BORDER="0" HEIGHT="61"></TD>
	        <TD ROWSPAN="3">&nbsp;</TD>
          </TR>
	      <TR>
	        <TD height="432" COLSPAN="4" valign="top"><p>&nbsp;</p>
	          <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#D4D4D4" class="biankuang">
	          <tr>
	            <td valign="top"><form name="form1" method="POST" action="<%=MM_LoginAction%>">
	              <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td height="41" colspan="2" align="center" bgcolor="#607F42" class="text1" >管 理 员 登 录</td>
	                  </tr>
	                <tr>
	                  <td width="16%" align="center" bgcolor="#607F42"><p>管理账号：</p></td>
	                  <td width="84%" height="37"><span id="sprytextfield1">
	                    <input name="admin_id" type="text" id="admin_id" size="30">
	                    <span class="textfieldRequiredMsg">需要提供一个值。</span></span></td>
	                  </tr>
	                <tr>
	                  <td align="center" bgcolor="#607F42">登录密码：</td>
	                  <td height="39"><span id="sprytextfield2">
	                    <input name="admin_pw" type="password" id="admin_pw" size="30">
	                    <span class="textfieldRequiredMsg">需要提供一个值。</span></span></td>
	                  </tr>
	                <tr>
	                  <td height="44" colspan="2" align="center" bgcolor="#607F42"><input type="submit" name="button" id="button" value="登录系统">
	                    &nbsp; &nbsp; <input type="reset" name="button2" id="button2" value="重新填写"></td>
	                  </tr>
	                </table>
                </form></td>
              </tr>
            </table>
	          <br>
            <p>&nbsp;</p></TD>
          </TR>
	      <TR>
	        <TD colspan="4" valign="bottom"><hr></TD>
          </TR>
	      <TR>
	        <TD><IMG SRC="images/ziyuan_14.gif" WIDTH="49" BORDER="0" HEIGHT="92"></TD>
	        <TD COLSPAN="4"><IMG SRC="images/ziyuan_15.png" WIDTH="897" BORDER="0" HEIGHT="92"></TD>
	        <TD><IMG SRC="images/ziyuan_16.gif" WIDTH="54" BORDER="0" HEIGHT="92"></TD>
          </TR>
	      <TR>
	        <TD><IMG SRC="images/space.gif" WIDTH="49" BORDER="0" HEIGHT="1"></TD>
	        <TD><IMG SRC="images/space.gif" WIDTH="5" BORDER="0" HEIGHT="1"></TD>
	        <TD><IMG SRC="images/space.gif" WIDTH="344" BORDER="0" HEIGHT="1"></TD>
	        <TD><IMG SRC="images/space.gif" WIDTH="256" BORDER="0" HEIGHT="1"></TD>
	        <TD><IMG SRC="images/space.gif" WIDTH="292" BORDER="0" HEIGHT="1"></TD>
	        <TD><IMG SRC="images/space.gif" WIDTH="54" BORDER="0" HEIGHT="1"></TD>
          </TR>
        </TABLE></td>
      </tr>
    </table>
    <script type="text/javascript">
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2");
    </script>
</BODY>
</HTML>
