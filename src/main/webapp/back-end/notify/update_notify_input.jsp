<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.notify.model.*"%>

<% //見com.notify.model.NotifyServlet.java第163行存入req的notifyVO物件 (此為從資料庫取出的notifyVO, 也可以是輸入格式有錯誤時的notifyVO物件)
   NotifyVO notifyVO = (NotifyVO) request.getAttribute("notifyVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>通知資料修改 - update_notify_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>通知資料修改 - update_notify_input.jsp</h3>
		 <h4><a href="select_page_n.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!-- com.notify.model.NotifyServlet -->
<FORM METHOD="post" ACTION="notify.do" name="form1">
<table>
	<tr>
		<td>通知編號:<font color=red><b>*</b></font></td>
		<td><%=notifyVO.getNotify_no()%></td>
	</tr>

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_id"   value="<%=notifyVO.getMem_id()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>通知內容:</td>
		<td><input type="TEXT" name="notify_content" value="<%=notifyVO.getNotify_content()%>" size="45"/></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="notify_no" value="<%=notifyVO.getNotify_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>

</html>