<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<%@ page import="bean.Subject" %>

<%@include file="../header.html" %>

<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/style-subject.css">

<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subject-menu">
  <h2 class="subject-heading">科目情報変更</h2>

<div class="subject-update-menu">
  <form action="SubjectUpdateExecute.action" method="post">
    <label for="subject_cd">科目コード</label>
    <input type="text" id="subject_cd" name="subject_cd"
           value="<%= ((Subject)request.getAttribute("subject")).getCode() %>"><br>

    <label for="subject_name">科目名</label><br>
    <input type="text"
           id="subject_name"
           name="name"
           maxlength="20"
           required
           value=""><br>

    <div class="button-group">
      <button type="submit" name="event" value="45">変更</button>
      <a href="<%= request.getContextPath() %>/subject/SubjectList.action">戻る</a>
    </div>
  </form>
</div>

<%@include file="../footer.html" %>
