<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/subject_style2.css">

<%@include file="../header.html" %>
<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subject-menu">
  <h2 class="subject-heading">科目情報削除</h2>

  <%
    String subjectCd = (String) request.getAttribute("subject_cd");
    String subjectName = (String) request.getAttribute("subject_name");
  %>

  <div class="subject-menu-delete">
  <form action="<%= request.getContextPath() %>/subject/SubjectDelete.action" method="post">
    <input type="hidden" name="subject_cd" value="<%= subjectCd %>">
    <p>「<%= subjectName %>(<%= subjectCd %>)」 を削除してもよろしいですか？</p>
    <button type="submit">削除</button>
  </form>
</div>

  <a href="<%= request.getContextPath() %>/subject/SubjectList.action" class="back-link">戻る</a>
</div>

<%@include file="../footer.html" %>
