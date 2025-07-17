<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="left-menu">
  <ul class="menu-list">
  	<li><a href="${pageContext.request.contextPath}/kadai/login-out.jsp">メニュー</a></li>
  	<li><a href="${pageContext.request.contextPath}/kadai/StudentList.action">学生管理</a></li>
    <li>成績管理
      <ul class="submenu">
        <li><a href="${pageContext.request.contextPath}/kadai/entry.jsp">成績登録</a></li>
        <li><a href="${pageContext.request.contextPath}/kadai/searchForm.jsp">成績参照</a></li>
      </ul>
    </li>
    <li><a href="${pageContext.request.contextPath}/subject/SubjectList.action">科目管理</a></li>
  </ul>
</div>
