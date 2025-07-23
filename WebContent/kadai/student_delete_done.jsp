<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="../kadai/templates/style.css">
<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>

<div class="student-menu">
  <h2 class="student-heading">削除完了</h2>
  <p>学生情報を削除しました。</p>
  <a href="<%= request.getContextPath() %>/kadai/StudentList.action" class="back-link">一覧へ戻る</a>
</div>

<%@include file="../footer.html" %>