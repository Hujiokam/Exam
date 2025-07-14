<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="right-menu">
  <h2 class="menu-heading">メニュー</h2>
  <div class="card-container">

  <div class="card card-1">
    <a href="StudentList.action">学生管理</a>
  </div>

  <div class="card card-2">
    <div class="card-title">成績管理</div>
    <a href="scoreRegister.action">成績登録</a><br>
    <a href="scoreView.action">成績参照</a>
  </div>

  <div class="card card-3">
    <a href="${pageContext.request.contextPath}/subject/SubjectList.action">科目管理</a>

  </div>

</div>

</div>
