<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../header.html" %>
<%@include file="menu.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/kadai/templates/style2.css">

<div class="content">
  <h2 class="menu-title">登録結果</h2>
  <p>${message}</p>
  <a href="<%= request.getContextPath() %>/action/studentlist">学生一覧に戻る</a>
  <a href="<%= request.getContextPath() %>/action/studentinsert">戻る</a>
</div>

<%@include file="../footer.html" %>