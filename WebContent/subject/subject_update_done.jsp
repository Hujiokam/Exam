<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>

<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<link rel="stylesheet" href="../kadai/templates/style.css">
<link rel="stylesheet" href="templates/style-subject.css">
<%@include file="../header.html" %>
<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subject-menu">
  <h2 class="student-heading">科目情報変更</h2>

      <label class="label-success">変更が完了しました</label><br><br>

      <a href="<%= request.getContextPath() %>/subject/SubjectList.action">科目一覧へ戻る</a>
</div>



<%@include file="../footer.html" %>