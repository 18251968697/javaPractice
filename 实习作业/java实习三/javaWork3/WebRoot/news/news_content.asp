<%@LANGUAGE="VBSCRIPT"%>
<!--#include file="Connections/news.asp" -->
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
.biankuang {
	border: 1px solid #EEF2C1;
}
    body,td,th {
	font-size: 12px;
	color: #FFFFFF;
}
    a {
	font-size: 12px;
	color: #FFFFFF;
}
a:visited {
	color: #FFFFFF;
}
a:hover {
	color: #FFFF00;
}
    </style>
    <title>TH_ERCO新闻报道</title>
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
	            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td height="30" colspan="2" bgcolor="#607F42" >【<strong><%=(news.Fields.Item("news_type").Value)%></strong>】<%=(news.Fields.Item("news_title").Value)%></td>
	                </tr>
	              <tr>
	                <td width="7%" rowspan="2" align="center" valign="top" bgcolor="#607F42"><p>&nbsp;</p>
	                  <p><strong>新</strong></p>
	                  <p><strong>闻</strong></p>
	                  <p><strong>内</strong></p>
	                  <p><strong>容</strong></p></td>
	                <td width="93%" height="42" align="center"><table width="98%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td height="25">发布时间：<%=(news.Fields.Item("news_time").Value)%></td>
	                    </tr>
	                  </table></td>
	                </tr>
	              <tr>
	                <td height="300" align="center" valign="middle"><table width="98%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td height="30" ><strong><%=(news.Fields.Item("news_title").Value)%></strong></td>
	                    </tr>
	                  <tr>
	                    <td height="260" valign="top"><%=(news.Fields.Item("news_content").Value)%></td>
	                    </tr>
	                  </table></td>
	                </tr>
                </table></td>
              </tr>
            </table>
	          <br>
	          <table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="25" align="center"><a href="news.asp">返 回</a></td>
              </tr>
            </table>
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
</BODY>
</HTML>
<%
news.Close()
Set news = Nothing
%>
