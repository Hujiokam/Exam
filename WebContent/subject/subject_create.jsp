<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Subject" %>

<%@include file="../header.html" %>

<link rel="stylesheet" href="templates/subject_style.css">
<link rel="stylesheet" href="templates/subject_style2.css">

<jsp:include page="../kadai/menu-tab.jsp" />

<div class="subjectcrate-menu">
  <h2 class="student-heading">科目情報登録</h2>

  <!-- JavaScriptのバリデーションと連携 -->
  <form action="SubjectCreate.action" method="post" class="subject-form" onsubmit="return validateForm()">

    <!-- 学校コード（隠し項目） -->
    <input type="hidden" name="school_cd" value="oom">

    <!-- 科目コード -->
    <div class="form-group">
      <label for="cd">科目コード</label>
      <input type="text" id="cd" name="cd" value="${cd}" maxlength="3"
             placeholder="科目コードを入力してください" required>
    </div>

    <!-- 科目名 -->
    <div class="form-group">
      <label for="name">科目名</label>
      <input type="text" id="name" name="name" value="${name}" maxlength="20"
             placeholder="科目名を入力してください" required>
    </div>

    <!-- エラーメッセージ表示 -->
    <c:if test="${not empty error}">
      <p class="error-message">${error}</p>
    </c:if>

    <!-- 登録ボタン -->
    <form action="<%= request.getContextPath() %>/subject/SubjectCreateDone.action" method="post">
    <div class="form-group">
      <input type="submit" value="登録" class="submit-button">
    </div>
    </form>

    <!-- 戻るリンク -->
    <div class="form-group">
      <a href="<%= request.getContextPath() %>/subject/SubjectList.action" class="back-link">戻る</a>
    </div>

  </form>
</div>

<script>
function validateForm() {
  const cd = document.getElementById("cd").value.trim();
  if (cd.length !== 3) {
    alert("科目コードは3文字で打ってください");
    return false;
  }
  return true;
}
</script>

<%@include file="../footer.html" %>

