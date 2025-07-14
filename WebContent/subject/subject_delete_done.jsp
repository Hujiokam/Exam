<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>

<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<link rel="stylesheet" href="../kadai/templates/style.css">
<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/subject_style2.css">

<%@include file="../header.html" %>

<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subjectcrate-menu">
  <h2 class="subject-heading">科目情報削除</h2>

  <p class="completion-message">削除が完了しました</p>

  <div class="button-group">
    <a href="<%= request.getContextPath() %>/subject/SubjectList.action" class="btn btn-list">科目一覧</a>
  </div>
</div>

<%@include file="../footer.html" %>