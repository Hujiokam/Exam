<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>

<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>

<link rel="stylesheet" href="templates/style.css">
<%@include file="../header.html" %>

<jsp:include page="menu-tab.jsp" />

<div class="student-menu">
  <h2 class="student-heading">学生管理</h2>
  <a href="<%= request.getContextPath() %>/kadai/studentinsertform" class="studentcreate">新規登録</a>

  <form action="StudentList.action" class="studentlist" method="post">
    <div class="labellabel">
      <label for="year">入学年度</label>
      <select name="f1" id="year">
        <option value="---">-------</option>
        <%
          List<String> years = (List<String>) request.getAttribute("years");
          if (years != null) {
            for (String y : years) {
        %>
          <option value="<%= y %>" <%= y.equals(request.getParameter("f1")) ? "selected" : "" %>><%= y %></option>
        <%
            }
          }
        %>
      </select>
    </div>

    <div class="labellabel">
      <label for="class">クラス</label>
      <select name="f2" id="class">
        <option value="---">-------</option>
        <%
          List<String> classes = (List<String>) request.getAttribute("classes");
          if (classes != null) {
            for (String c : classes) {
        %>
          <option value="<%= c %>" <%= c.equals(request.getParameter("f2")) ? "selected" : "" %>><%= c %></option>
        <%
            }
          }
        %>
      </select>
    </div>

    <div>
      <input type="checkbox" name="f3" id="status" <%= request.getParameter("f3") != null ? "checked" : "" %>>
      <label for="status">在学中</label>
    </div>

    <button type="submit">絞込み</button>
  </form>
</div>

<div class="search-result">
  検索結果：<%= request.getAttribute("count") != null ? request.getAttribute("count") : 0 %>件
</div>

<table class="student-table">
  <tr>
    <th>入学年度</th>
    <th>学生番号</th>
    <th>氏名</th>
    <th>クラス</th>
    <th>在学中</th>
    <th>変更</th>
  </tr>
  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null && !students.isEmpty()) {
      for (Student s : students) {
  %>
    <tr>
      <td><%= s.getEntYear() %></td>
      <td><%= s.getNo() %></td>
      <td><%= s.getName() %></td>
      <td><%= s.getClassNum() %></td>
      <td><%= s.isAttend() ? "○" : "×" %></td>
      <td><a href="StudentUpdate.action?no=<%= s.getNo() %>">変更</a></td>
    </tr>
  <%
      }
    } else {
  %>
    <tr>
      <td colspan="6">学生情報が存在しませんでした</td>
    </tr>
  <%
    }
  %>
</table>

<%@include file="../footer.html" %>
