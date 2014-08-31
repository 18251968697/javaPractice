<%@LANGUAGE="VBSCRIPT"%>
<!--#include file="Connections/news.asp" -->
<%
Dim MM_editAction
MM_editAction = CStr(Request.ServerVariables("SCRIPT_NAME"))
If (Request.QueryString <> "") Then
  MM_editAction = MM_editAction & "?" & Server.HTMLEncode(Request.QueryString)
End If

' boolean to abort record edit
Dim MM_abortEdit
MM_abortEdit = false
%>
<%
' IIf implementation
Function MM_IIf(condition, ifTrue, ifFalse)
  If condition = "" Then
    MM_IIf = ifFalse
  Else
    MM_IIf = ifTrue
  End If
End Function
%>
<%
If (CStr(Request("MM_update")) = "form1") Then
  If (Not MM_abortEdit) Then
    ' execute the update
    Dim MM_editCmd

    Set MM_editCmd = Server.CreateObject ("ADODB.Command")
    MM_editCmd.ActiveConnection = MM_news_STRING
    MM_editCmd.CommandText = "UPDATE news SET news_title = ?, news_type = ?, news_content = ?, news_time = ? WHERE news_id = ?" 
    MM_editCmd.Prepared = true
    MM_editCmd.Parameters.Append MM_editCmd.CreateParameter("param1", 202, 1, 255, Request.Form("news_title")) ' adVarWChar
    MM_editCmd.Parameters.Append MM_editCmd.CreateParameter("param2", 202, 1, 255, Request.Form("news_type")) ' adVarWChar
    MM_editCmd.Parameters.Append MM_editCmd.CreateParameter("param3", 202, 1, 255, Request.Form("news_content")) ' adVarWChar
    MM_editCmd.Parameters.Append MM_editCmd.CreateParameter("param4", 202, 1, 255, Request.Form("news_time")) ' adVarWChar
    MM_editCmd.Parameters.Append MM_editCmd.CreateParameter("param5", 5, 1, -1, MM_IIF(Request.Form("MM_recordId"), Request.Form("MM_recordId"), null)) ' adDouble
    MM_editCmd.Execute
    MM_editCmd.ActiveConnection.Close

    ' append the query string to the redirect URL
    Dim MM_editRedirectUrl
    MM_editRedirectUrl = "admin_news.asp"
    If (Request.QueryString <> "") Then
      If (InStr(1, MM_editRedirectUrl, "?", vbTextCompare) = 0) Then
        MM_editRedirectUrl = MM_editRedirectUrl & "?" & Request.QueryString
      Else
        MM_editRedirectUrl = MM_editRedirectUrl & "&" & Request.QueryString
      End If
    End If
    Response.Redirect(MM_editRedirectUrl)
  End If
End If
%>
<%
Dim news__MMColParam
news__MMColParam = "1"
If (Request.QueryString("news_id") <> "") Then 
  news__MMColParam = Request.QueryString("news_id")
End If
%>
<%
Dim news
Dim news_cmd
Dim news_numRows

Set news_cmd = Server.CreateObject ("ADODB.Command")
news_cmd.ActiveConnection = MM_news_STRING
news_cmd.CommandText = "SELECT * FROM news WHERE news_id = ?" 
news_cmd.Prepared = true
news_cmd.Parameters.Append news_cmd.CreateParameter("param1", 5, 1, -1, news__MMColParam) ' adDouble

Set news = news_cmd.Execute
news_numRows = 0
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
    textarea {
	font-size: 12px;
	color: #FFF;
	background-color: #144A19;
	border: 1px solid #EEF2C1;
}
    input {
	font-size: 12px;
	color: #FFF;
	background-color: #144A19;
	border: 1px solid #EEF2C1;
}
    select {
	font-size: 12px;
	color: #FFF;
	background-color: #144A19;
	border: 1px solid #EEF2C1;
}
    </style>
    <link href="SpryAssets/SpryValidationTextField.css" rel="stylesheet" type="text/css">
    <link href="SpryAssets/SpryValidationSelect.css" rel="stylesheet" type="text/css">
    <link href="SpryAssets/SpryValidationTextarea.css" rel="stylesheet" type="text/css">
    <title>TH_ERCO���ű���</title>
    <script src="SpryAssets/SpryValidationTextField.js" type="text/javascript"></script>
    <script src="SpryAssets/SpryValidationSelect.js" type="text/javascript"></script>
    <script src="SpryAssets/SpryValidationTextarea.js" type="text/javascript"></script>
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
	          <table width="850" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#D4D4D4" class="biankuang">
	          <tr>
	            <td valign="top"><form ACTION="<%=MM_editAction%>" METHOD="POST" name="form1">
	              <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td height="41" colspan="2" align="center" bgcolor="#607F42" class="text1" >�� �� �� ��</td>
	                  </tr>
	                <tr>
	                  <td width="9%" align="center" bgcolor="#607F42"><p>���ű��⣺</p></td>
	                  <td width="91%" height="37"><span id="sprytextfield1">
	                    <input name="news_title" type="text" id="news_title" value="<%=(news.Fields.Item("news_title").Value)%>" size="60">
	                    <span class="textfieldRequiredMsg">��Ҫ�ṩһ��ֵ��</span></span></td>
	                  </tr>
	                <tr>
	                  <td align="center" bgcolor="#607F42">�������ͣ�</td>
	                  <td height="39"><span id="spryselect1">
	                    <select name="news_type" id="news_type">
	                      <option value="�ȵ�" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If ("�ȵ�" = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%>>�ȵ�</option>
	                      <option value="����" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If ("����" = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%>>����</option>
	                      <option value="����" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If ("����" = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%>>����</option>
	                      <option value="�" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If ("�" = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%>>�</option>
	                      <option value="����" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If ("����" = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%>>����</option>
	                      <%
While (NOT news.EOF)
%>
	                      <option value="<%=(news.Fields.Item("news_type").Value)%>" <%If (Not isNull((news.Fields.Item("news_type").Value))) Then If (CStr(news.Fields.Item("news_type").Value) = CStr((news.Fields.Item("news_type").Value))) Then Response.Write("selected=""selected""") : Response.Write("")%> ><%=(news.Fields.Item("news_type").Value)%></option>
	                      <%
  news.MoveNext()
Wend
If (news.CursorType > 0) Then
  news.MoveFirst
Else
  news.Requery
End If
%>
                        </select>
	                    <span class="selectRequiredMsg">��ѡ��һ����Ŀ��</span></span></td>
	                  </tr>
	                <tr>
	                  <td align="center" bgcolor="#607F42">��ϸ���ݣ�</td>
	                  <td height="44"><span id="sprytextarea1">
	                    <textarea name="news_content" id="news_content" cols="120" rows="20"><%=(news.Fields.Item("news_content").Value)%></textarea>
	                    <span class="textareaRequiredMsg">��Ҫ�ṩһ��ֵ��</span></span></td>
	                  </tr>
	                <tr>
	                  <td height="49" colspan="2" align="center"><input name="news_time" type="hidden" id="news_time" value="<%=now()%>">
	                    <input type="submit" name="button" id="button" value="�ύ�޸�">
	                    &nbsp; &nbsp;&nbsp; <input type="reset" name="button2" id="button2" value="�����޸�"></td>
	                  </tr>
	                </table>
                  <input type="hidden" name="MM_update" value="form1">
                  <input type="hidden" name="MM_recordId" value="<%= news.Fields.Item("news_id").Value %>">
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
var spryselect1 = new Spry.Widget.ValidationSelect("spryselect1");
var sprytextarea1 = new Spry.Widget.ValidationTextarea("sprytextarea1");
    </script>
</BODY>
</HTML>
<%
news.Close()
Set news = Nothing
%>
