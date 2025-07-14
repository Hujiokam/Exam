<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@ page import="bean.Subject" %>

<%@include file="../header.html" %>

<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/subject_style2.css">

<jsp:include page="../kadai/menu-tab.jsp" />


<div class="subject-menu">
  <h2 class="subject-heading">科目管理</h2>
  <a href="<%= request.getContextPath() %>/subject/SubjectCreate.action"  class="subjectcreate">新規登録</a>
</div>

<div class="subject-table-wrapper">
  <table class="subject-table">
    <tr>
      <th>科目コード</th>
      <th>科目名</th>
      <th></th>
      <th></th>
    </tr>

    <%
      List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
      if (subjects != null && !subjects.isEmpty()) {
        for (Subject s : subjects) {
    %>
      <tr>
        <td><%= s.getCode() %></td>
        <td><%= s.getName() %></td>
        <td><a href="SubjectUpdate.action?code=<%= s.getCode() %>">変更</a></td>
        <td><a href="<%= request.getContextPath() %>/subject/SubjectDelete.action?subject_cd=<%= s.getCode() %>&subject_name=<%= java.net.URLEncoder.encode(s.getName(), "UTF-8") %>">
    		削除</a>
  		</td>

      </tr>
    <%
        }
      } else {
    %>
      <tr>
        <td colspan="4">科目情報が存在しませんでした</td>
      </tr>
    <%
      }
    %>
  </table>
</div>

<%@include file="../footer.html" %>
