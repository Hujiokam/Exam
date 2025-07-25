<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath()%>/kadai/templates/style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/kadai/templates/student_style.css">

<div class="student-menu">
  <h2 class="student-heading">学生情報登録</h2>
   <p class="label-success">${message}</p><br>

  <a href="<%= request.getContextPath() %>/action/studentinsert">戻る</a>
  <a href="${pageContext.request.contextPath}/kadai/StudentList.action">学生一覧</a>
</div>