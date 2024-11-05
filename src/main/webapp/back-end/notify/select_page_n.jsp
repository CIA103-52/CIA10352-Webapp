<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Notify: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Notify: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Notify: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllNotify.jsp'>List</a> all Notifies.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="notify.do" >
        <b>��J�q���s�� (�p1):</b>
        <input type="text" name="notify_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="notifySvc" scope="page" class="com.notify.model.NotifyService" />
  
  <li>
     <FORM METHOD="post" ACTION="notify.do" >
       <b>��ܳq���s��:</b>
       <select size="1" name="notify_no">
         <c:forEach var="notifyVO" items="${notifySvc.all}" > 
          <option value="${notifyVO.notify_no}">${notifyVO.notify_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="notify.do" > -->
<!--        <b>��ܳq������:</b> -->
<!--        <select size="1" name="notify_no"> -->
<%--          <c:forEach var="notifyVO" items="${notifySvc.all}" >  --%>
<%--           <option value="${notifyVO.notify_no}">${notifyVO.notify_content} --%> <%-- tag --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>�q���޲z</h3>

<ul>
  <li><a href='addNotify.jsp'>Add</a> a new Notify.</li>
</ul>

</body>
</html>