<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<%@include file="../header.html" %>
<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/subject_style2.css">

<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subjectcrate-menu">
  <h2 class="student-heading">科目情報登録</h2>

  <p class="completion-message">登録完了しました</p>

  <div class="button-group">
    <a href="<%= request.getContextPath() %>/subject/SubjectCreate.action" class="btn btn-back">戻る</a>
    <a href="<%= request.getContextPath() %>/subject/SubjectList.action" class="btn btn-list">科目一覧</a>
  </div>
</div>



<%@include file="../footer.html" %>
