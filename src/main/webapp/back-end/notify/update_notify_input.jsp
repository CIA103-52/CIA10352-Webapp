<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.notify.model.*"%>

<% //��com.notify.model.NotifyServlet.java��163��s�Jreq��notifyVO���� (�����q��Ʈw���X��notifyVO, �]�i�H�O��J�榡�����~�ɪ�notifyVO����)
   NotifyVO notifyVO = (NotifyVO) request.getAttribute("notifyVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�q����ƭק� - update_notify_input.jsp</title>

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
		 <h3>�q����ƭק� - update_notify_input.jsp</h3>
		 <h4><a href="select_page_n.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
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
		<td>�q���s��:<font color=red><b>*</b></font></td>
		<td><%=notifyVO.getNotify_no()%></td>
	</tr>

	<tr>
		<td>�|���s��:</td>
		<td><input type="TEXT" name="mem_id"   value="<%=notifyVO.getMem_id()%>" size="45"/></td>
	</tr>
	
	<tr>
		<td>�q�����e:</td>
		<td><input type="TEXT" name="notify_content" value="<%=notifyVO.getNotify_content()%>" size="45"/></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="notify_no" value="<%=notifyVO.getNotify_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>