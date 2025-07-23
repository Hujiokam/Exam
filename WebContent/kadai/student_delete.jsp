<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<%@ page import="bean.Student" %>

<link rel="stylesheet" href="../kadai/templates/style.css">
<link rel="stylesheet" href="../kadai/templates/student_style.css">

<%@include file="../header.html" %>
<jsp:include page="../kadai/menu-tab.jsp" />

<div class="student-menu">
  <h2 class="student-heading">学生情報削除</h2>

  <%
    String studentNo = (String) request.getAttribute("student_no");
    String studentName = (String) request.getAttribute("student_name");
  %>

  <div class="student-menu-delete">
    <form action="<%= request.getContextPath() %>/student/StudentDelete.action" method="post">
      <input type="hidden" name="student_no" value="<%= studentNo %>">
      <p>「<%= studentName %>(<%= studentNo %>)」 を削除してもよろしいですか？</p>
      <button type="submit">削除</button>
    </form>
  </div>

  <a href="<%= request.getContextPath() %>/kadai/StudentList.action" class="back-link">戻る</a>
</div>

<%@include file="../footer.html" %>