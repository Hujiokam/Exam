<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kadai/templates/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/kadai/templates/student_style.css">

<div class="student-insert-menu">
  <h2 class="menu-heading">学生情報登録</h2>

  <form action="<%= request.getContextPath() %>/action/studentinsert" method="post">

    <!-- 入学年度 -->
    <label for="ent_year">入学年度</label><br>
    <select name="ent_year" id="ent_year" required>
      <option value="">--選択してください--</option>
      <c:forEach var="year" items="${ent_year_list}">
        <option value="${year}" <c:if test="${param.ent_year == year}">selected</c:if>>${year}</option>
      </c:forEach>
    </select><br>

    <!-- 学生番号 -->
    <label for="no">学生番号</label><br>
    <input type="text" name="no" id="no" maxlength="10"
           required placeholder="学生番号を入力してください"
           value="${param.no}"><br>

    <!-- 学生番号のエラーメッセージ -->
    <c:if test="${not empty errorMessage}">
      <div class="form-warning" style="color: red; margin-top: 4px; font-size: 0.9em;">
        ${errorMessage}
      </div>
    </c:if>

    <!-- 氏名 -->
    <label for="name">氏名</label><br>
    <input type="text" name="name" id="name" maxlength="30"
           required placeholder="氏名を入力してください"
           value="${param.name}"><br>

    <!-- クラス -->
    <label for="class_num">クラス</label><br>
    <select name="class_num" id="class_num" required>
      <option value="">--選択してください--</option>
      <c:forEach var="cls" items="${class_num_list}">
        <option value="${cls}" <c:if test="${param.class_num == cls}">selected</c:if>>${cls}</option>
      </c:forEach>
    </select><br>

    <div class="button-wrapper">
      <button type="submit">登録して終了</button><br>
      <a href="${pageContext.request.contextPath}/kadai/StudentList.action">戻る</a>
    </div>

  </form>
</div>

<%@include file="../footer.html" %>
