<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page import="java.util.List"%>
<%@page import="bean.Student"%>

<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="templates/score.css">

<div class="right-menu">
  <div class="menu-heading">成績管理</div>

      <div class="student-search-box">
  <!-- 検索フォーム -->
  <form action="TestRegist" method="post" class="score-form">
    <div class="form-row">
      <div class="form-group">
        <label>入学年度</label>
        <select name="entYear">
          <option value="">--------</option>
          <%
            List<String> entYears = (List<String>) request.getAttribute("entYears");
            String selectedEntYear = (String) request.getAttribute("entYear");
            for (String y : entYears) {
          %>
            <option value="<%= y %>" <%= y.equals(selectedEntYear) ? "selected" : "" %>><%= y %></option>
          <%
            }
          %>
        </select>
      </div>

      <div class="form-group">
        <label>クラス</label>
        <select name="classNum">
          <option value="">--------</option>
          <%
            List<String> classNums = (List<String>) request.getAttribute("classNums");
            String selectedClassNum = (String) request.getAttribute("classNum");
            for (String c : classNums) {
          %>
            <option value="<%= c %>" <%= c.equals(selectedClassNum) ? "selected" : "" %>><%= c %></option>
          <%
            }
          %>
        </select>
      </div>

      <div class="form-group">
        <label>科目</label>
        <select name="subject">
          <option value="">--------</option>
          <%
            List<String> subjects = (List<String>) request.getAttribute("subjects");
            String selectedSubject = (String) request.getAttribute("subject");
            for (String s : subjects) {
          %>
            <option value="<%= s %>" <%= s.equals(selectedSubject) ? "selected" : "" %>><%= s %></option>
          <%
            }
          %>
        </select>
      </div>

      <div class="form-group">
        <label>回数</label>
        <select name="examNo">
          <option value="">--------</option>
          <%
            List<String> examNos = (List<String>) request.getAttribute("examNos");
            String selectedExamNo = (String) request.getAttribute("examNo");
            for (String e : examNos) {
          %>
            <option value="<%= e %>" <%= e.equals(selectedExamNo) ? "selected" : "" %>><%= e %></option>
          <%
            }
          %>
        </select>
      </div>

      <div class="form-button">
        <button type="submit">検索</button>
      </div>
    </div>
  </form>
  </div>

  <!-- 検索結果の表示 -->
  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null && !students.isEmpty()) {
  %>


    <form action="TestScoreInsert.action" method="post" class="score-form">

      <div class="search-result2">
      <p>科目:<%= selectedSubject %>(<%= selectedExamNo %>回）</p>
    </div>

      <table class="score-table">
        <tr>
          <th>学生番号</th>
          <th>氏名</th>
          <th>入学年度</th>
          <th>クラス</th>
          <th>点数</th>
        </tr>
        <% for (Student s : students) { %>
          <tr>
            <td><%= s.getNo() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEntYear() %></td>
            <td><%= s.getClassNum() %></td>
            <td><input type="number" name="score_<%= s.getNo() %>" min="0" max="100"></td>
          </tr>
        <% } %>
      </table>

      <!-- 検索条件を hidden で保持 -->
      <input type="hidden" name="entYear" value="<%= selectedEntYear %>">
      <input type="hidden" name="classNum" value="<%= selectedClassNum %>">
      <input type="hidden" name="subject" value="<%= selectedSubject %>">
      <input type="hidden" name="examNo" value="<%= selectedExamNo %>">

      <button type="submit">登録して終了</button>
    </form>
  <%
    } else if (request.getAttribute("entYear") != null) {
  %>
    <p>条件に一致する学生が見つかりませんでした。</p>
  <%
    }
  %>
</div>

<%@include file="../footer.html" %>