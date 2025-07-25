<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="true"%>
<%@page import="java.util.List"%>
<%@page import="bean.ScoreView"%>

<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="templates/score.css">

<%
  String actionType = request.getParameter("actionType");
%>

<div class="right-menu">
  <div class="menu-heading">
  <%
    String title = "成績参照";
    if ("searchBySubject".equals(actionType)) {
      title = "成績一覧(科目)";
    } else if ("searchByStudentNo".equals(actionType)) {
      title = "成績一覧(学生)";
    }
  %>
  <%= title %>
</div>

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
      </div> <!-- /.form-row -->

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

  <!-- エラーメッセージの条件分岐 -->
  <%
    boolean formSubmitted = actionType != null;
    boolean isSearchBySubject = "searchBySubject".equals(actionType);
    boolean isSearchByStudentNo = "searchByStudentNo".equals(actionType);

    String studentNoParam = request.getParameter("studentNo");

    boolean studentNoEmptySearch = isSearchByStudentNo && (studentNoParam == null || studentNoParam.trim().isEmpty());
    boolean subjectSearchError = isSearchBySubject && (
      (entYear == null || entYear.isEmpty()) ||
      (classNum == null || classNum.isEmpty()) ||
      (subject == null || subject.isEmpty())
    );

    boolean showGenericMessage = !formSubmitted || (!subjectSearchError && !studentNoEmptySearch && request.getAttribute("scores") == null);
  %>

  <% if (studentNoEmptySearch) { %>
    <div class="form-warning">
      学生番号を入力してください。
    </div>
  <% } else if (subjectSearchError) { %>
    <div class="form-warning">
      入学年度・クラス・科目をすべて選択してください。
    </div>
  <% } else if (showGenericMessage) { %>
    <div class="score-message">
      <h2>科目情報を選択または学生情報を入力してください</h2>
    </div>
  <% } %>

  <%
    List<ScoreView> scores = (List<ScoreView>) request.getAttribute("scores");
    String studentNo = (String) request.getAttribute("studentNo");
    String studentName = (String) request.getAttribute("studentName"); // Controllerで設定必須
  %>

  <% if (scores != null && !scores.isEmpty()) { %>

    <% if (studentNo != null && !studentNo.isEmpty()) { %>
      <div class="search-result2">
        <p>氏名：<%= scores.get(0).getName() %>（<%= scores.get(0).getStudentNo() %>）</p>
      </div>

      <table class="score-table">
        <tr>
          <th>科目名</th>
          <th>科目コード</th>
          <th>1回</th>
          <th>2回</th>
        </tr>
        <% for (ScoreView sv : scores) { %>
        <tr>
          <td><%= sv.getSubjectName() != null ? sv.getSubjectName() : "-" %></td>
          <td><%= sv.getSubjectCd() != null ? sv.getSubjectCd() : "-" %></td>
          <td><%= sv.getScore1() != null ? sv.getScore1() : "-" %></td>
          <td><%= sv.getScore2() != null ? sv.getScore2() : "-" %></td>
        </tr>
        <% } %>
      </table>

    <% } else { %>
      <div class="search-result2">
        <p>科目：<%= request.getAttribute("subject") %></p>
      </div>

      <table class="score-table">
        <tr>
          <th>入学年度</th>
          <th>クラス</th>
          <th>学生番号</th>
          <th>氏名</th>
          <th>1回</th>
          <th>2回</th>
        </tr>
        <% for (ScoreView sv : scores) { %>
        <tr>
          <td><%= sv.getEntYear() %></td>
          <td><%= sv.getClassNum() %></td>
          <td><%= sv.getStudentNo() %></td>
          <td><%= sv.getName() %></td>
          <td><%= sv.getScore1() != null ? sv.getScore1() : "-" %></td>
          <td><%= sv.getScore2() != null ? sv.getScore2() : "-" %></td>
        </tr>
        <% } %>
      </table>
    <% } %>

  <% } else if (scores != null && scores.isEmpty()) { %>

    <% if (studentNo != null && !studentNo.isEmpty()) { %>
      <div class="search-result2">
        <p>氏名：<%= studentName != null ? studentName : "不明" %>（<%= studentNo %>）</p>
        <div class="form-warning" style="margin-top: 5px;">
          成績情報が存在しませんでした。
        </div>
      </div>
    <% } else if (formSubmitted) { %>
      <p>学生情報が存在しませんでした。</p>
    <% } %>

  <% } %>
</div>

<%@include file="../footer.html" %>
