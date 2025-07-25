<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page import="java.util.List"%>
<%@page import="bean.ScoreView"%>

<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="templates/score.css">

<div class="right-menu">
  <div class="menu-heading">成績参照</div>

  <!-- search-form.jsp -->
<%@page import="java.util.List"%>
<div class="student-search-box">
  <form action="TestList.action" method="get" class="score-form">
    <div class="form-row">

      <!-- 入学年度 -->
      <div class="form-group">
        <label>入学年度</label>
        <select name="entYear">
          <option value="">--------</option>
          <%
            List<String> entYears = (List<String>) request.getAttribute("entYears");
            String entYear = (String) request.getAttribute("entYear");
            for (String y : entYears) {
          %>
          <option value="<%= y %>" <%= y.equals(entYear) ? "selected" : "" %>><%= y %></option>
          <% } %>
        </select>
      </div>

      <!-- クラス -->
      <div class="form-group">
        <label>クラス</label>
        <select name="classNum">
          <option value="">--------</option>
          <%
            List<String> classNums = (List<String>) request.getAttribute("classNums");
            String classNum = (String) request.getAttribute("classNum");
            for (String c : classNums) {
          %>
          <option value="<%= c %>" <%= c.equals(classNum) ? "selected" : "" %>><%= c %></option>
          <% } %>
        </select>
      </div>

      <!-- 科目 -->
      <div class="form-group">
        <label>科目</label>
        <select name="subject">
          <option value="">--------</option>
          <%
            List<String> subjects = (List<String>) request.getAttribute("subjects");
            String subject = (String) request.getAttribute("subject");
            for (String s : subjects) {
          %>
          <option value="<%= s %>" <%= s.equals(subject) ? "selected" : "" %>><%= s %></option>
          <% } %>
        </select>
      </div>

      <!-- 科目検索ボタン -->
      <div class="form-button">
        <button type="submit" name="actionType" value="searchBySubject">検索</button>
      </div>
    </div>

    <!-- 学生番号 -->
    <div class="form-row studentId-row" style="margin-top: 10px;">
      <div class="form-group">
        <label>学生番号</label>
        <input type="text" name="studentNo" placeholder="学生番号を入力"
               value="<%= request.getAttribute("studentNo") != null ? request.getAttribute("studentNo") : "" %>" />
      </div>
      <div class="form-button">
        <button type="submit" name="actionType" value="searchByStudentNo">検索</button>
      </div>
    </div>
  </form>
</div>

 </div>

 <%@include file="../footer.html" %>