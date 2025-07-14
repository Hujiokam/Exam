<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/kadai/templates/style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/kadai/templates/style2.css">


<div class="studentupdateresult-change">
  <h2 class="student-heading">学生情報変更</h2>
  <p class="completion-message">${message}</p>
  <a class="student-list-link" href="${pageContext.request.contextPath}/kadai/StudentList.action">学生一覧</a>

</div>

<%@include file="../footer.html" %>