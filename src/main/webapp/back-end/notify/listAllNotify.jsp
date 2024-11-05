<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.notify.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    NotifyService notifySvc = new NotifyService();
    List<NotifyVO> list = notifySvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有通知資料 - listAllNotify.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有通知資料 - listAllNotify.jsp</h3>
		 <h4><a href="select_page_n.jsp"><img src="<%= request.getContextPath() %>/back-end/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>通知編號</th>
		<th>會員編號</th>
		<th>通知內容</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="notifyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${notifyVO.notify_no}</td>
			<td>${notifyVO.mem_id}</td>
			<td>${notifyVO.notify_content}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/notify/notify.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="notify_no"  value="${notifyVO.notify_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			
<%-- 			  <FORM METHOD="post" type="" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="managerNo"  value="${managerVO.managerNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/notify/notify.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="notify_no"  value="${notifyVO.notify_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>