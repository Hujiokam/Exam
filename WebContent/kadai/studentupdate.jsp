<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/kadai/templates/style2.css">

<div class="student-update-menu">
  <h2 class="student-heading">学生情報変更</h2>

  <form action="<%= request.getContextPath() %>/action/StudentUpdate.action" method="post" class="student-form">

    <!-- 学生番号（表示のみ、hiddenで送信） -->
    <div class="form-group">
      <label>学生番号</label>
      <div>${student.no}</div>
      <input type="hidden" name="no" value="${student.no}">
    </div>

    <!-- 入学年度（表示のみ、hiddenで送信） -->
    <div class="form-group">
      <label>入学年度</label>
      <div>${student.ent_year}</div>
      <input type="hidden" name="ent_year" value="${student.ent_year}">
    </div>

    <!-- 氏名（編集可） -->
    <div class="form-group">
      <label for="name">氏名</label>
      <input type="text" id="name" name="name" value="${student.name}" maxlength="30" required>
    </div>

    <!-- クラス（select） -->
    <div class="form-group">
      <label for="class_num">クラス</label>
      <select name="class_num" id="class_num" required>
        <option value="">--選択してください--</option>
        <c:forEach var="cls" items="${class_num_list}">
          <option value="${cls}" <c:if test="${cls == student.class_num}">selected</c:if>>${cls}</option>
        </c:forEach>
      </select>
    </div>

    <!-- 在学中チェック -->
    <div class="form-group form-group-attend">
  		<label for="is_attend">在学中</label>
  		<input type="checkbox" id="is_attend" name="is_attend" value="1" <c:if test="${student.is_attend}">checked</c:if>>
	</div>

    <!-- 送信ボタン -->
    <div class="form-group">
      <input type="submit" value="変更" class="submit-button">
    </div>

    <!-- 戻るリンク -->
    <div class="form-group">
      <a href="${pageContext.request.contextPath}/kadai/StudentList.action" class="back-link">戻る</a>
    </div>

  </form>
</div>

<%@include file="../footer.html" %>
