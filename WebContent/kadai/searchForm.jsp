<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>得点管理システム</title>
  <style>
    body {
      margin: 0 auto;
      padding: 0 100px 100px 100px;
      font-family: sans-serif;
    }
    .header {
      background-color: #D7EEFF;
      padding: 10px 20px;
    }
    .header h2 {
      margin: 0;
      font-size: 24px;
      text-align: left;
    }
    .logout {
      text-align: right;
      padding: 10px 20px;
      font-size: 14px;
      background-color: #D7EEFF;
    }
    .logout a {
      color: #007bff;
      text-decoration: underline;
    }
    .container {
      display: flex;
    }
    .sidebar {
      width: 200px;
      background-color: #ffffff;
      padding: 20px 10px;
      border-right: 1px solid #ccc;
    }
    .content {
      flex: 1;
      padding: 20px;
    }
    .title {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 20px;
      background-color: #dddddd;
      padding: 10px;
      text-align: left;
    }
    .form-row {
      display: flex;
      justify-content: flex-start;
      align-items: center;
      gap: 20px;
      flex-wrap: nowrap;
      margin-bottom: 20px;
    }
    /* 学生番号フォームだけ別クラスで縦中央揃え */
    .form-row.studentId-row {
      flex-wrap: nowrap;
      gap: 8px;
      align-items: center;
    }
    .form-group {
      display: flex;
      flex-direction: column;
      min-width: 150px;
      font-size: 14px;
    }
    .form-group label {
      margin-bottom: 5px;
    }
    .form-group select,
    .form-group input {
      padding: 5px;
      font-size: 16px;
      width: 150px;
      height: 36px;
      box-sizing: border-box;
    }
    .form-button {
      display: flex;
      align-items: center;
      height: 36px;
      margin-top: 22px;
    }
    button {
      padding: 8px 16px;
      font-size: 16px;
      background-color: #cccccc;
      color: #333;
      border: 1px solid #999;
      border-radius: 4px;
      cursor: pointer;
      white-space: nowrap;
      min-width: 80px;
      text-align: center;
    }
    button:hover {
      background-color: #b0b0b0;
    }
    table {
      border-collapse: collapse;
      width: 100%;
      margin-top: 20px;
    }
    th, td {
      border: 1px solid #ccc;
      padding: 8px;
      text-align: center;
      font-size: 14px;
      word-break: break-word;
      white-space: nowrap;
    }
    th {
      background-color: #eeeeee;
    }
    #search-error, #studentId-error {
      color: red;
      font-size: 14px;
      margin-bottom: 10px;
      display: none;
    }
  </style>

  <script>
    function validateSearchForm() {
      const year = document.querySelector('select[name="year"]').value;
      const classNum = document.querySelector('select[name="classNum"]').value;
      const subjectCode = document.querySelector('select[name="subjectCode"]').value;
      const studentId = document.querySelector('input[name="studentId"]').value.trim();
      const errorBox = document.getElementById("search-error");

      // 学生番号入力されていればOK
      if (studentId.length > 0) {
        errorBox.style.display = "none";
        return true;
      }

      if (!year || !classNum || !subjectCode) {
        errorBox.style.display = "block";
        return false;
      } else {
        errorBox.style.display = "none";
        return true;
      }
    }

    function validateStudentIdForm() {
      const studentIdInput = document.querySelector('input[name="studentId"]');
      const studentId = studentIdInput.value.trim();
      const errorBox = document.getElementById("studentId-error");

      if (studentId === "") {
        errorBox.style.display = "block";
        studentIdInput.focus();
        return false;
      } else {
        errorBox.style.display = "none";
        return true;
      }
    }
  </script>
</head>
<body>

  <div class="header">
    <h2>得点管理システム</h2>
  </div>

  <div class="logout">
    大原 太郎 様　
    <a href="${pageContext.request.contextPath}/kadai/logout-in.jsp">ログアウト</a>
  </div>

  <div class="container">
    <div class="sidebar">
      <p><a href="${pageContext.request.contextPath}/kadai/login-out.jsp">メニュー</a></p>
      <p><a href="StudentList.action">学生管理</a></p>
      <p>成績管理</p>
      <ul>
        <li><a href="${pageContext.request.contextPath}/kadai/entry.jsp">成績登録</a></li>
        <li><a href="${pageContext.request.contextPath}/kadai/searchForm.jsp">成績参照</a></li>
      </ul>
      <p><a href="#">科目管理</a></p>
    </div>

    <div class="content">
      <div class="title">成績参照</div>

      <!-- 科目別検索フォーム -->
      <form action="${pageContext.request.contextPath}/SearchScore" method="get" onsubmit="return validateSearchForm();">
        <div class="form-row">
          <div class="form-group">
            <label>入学年度</label>
            <select name="year">
              <option value="">---</option>
              <c:forEach begin="2015" end="2024" var="y">
                <option value="${y}" ${param.year == y ? "selected" : ""}>${y}</option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label>クラス</label>
            <select name="classNum">
              <option value="">---</option>
              <option value="121" ${param.classNum == '121' ? 'selected' : ''}>121</option>
              <option value="131" ${param.classNum == '131' ? 'selected' : ''}>131</option>
            </select>
          </div>
          <div class="form-group">
            <label>科目</label>
            <select name="subjectCode">
              <option value="">---</option>
              <option value="A02" ${param.subjectCode == 'A02' ? 'selected' : ''}>国語</option>
              <option value="B01" ${param.subjectCode == 'B01' ? 'selected' : ''}>Java</option>
            </select>
          </div>
          <div class="form-button">
            <button type="submit">検索</button>
          </div>
        </div>
      </form>

      <!-- JS用エラーメッセージ -->
      <div id="search-error">
        ※入学年度・クラス・科目をすべて選択してください。
      </div>

      <!-- 学生番号検索フォーム -->
      <form action="${pageContext.request.contextPath}/SearchScore" method="get" onsubmit="return validateStudentIdForm();">
        <div class="form-row studentId-row">
          <div class="form-group">
            <label>学生番号</label>
            <input type="text" name="studentId" placeholder="学生番号を入力してください" value="${param.studentId != null ? param.studentId : ''}">
          </div>
          <div class="form-button">
            <button type="submit">検索</button>
          </div>
        </div>
        <div id="studentId-error">
          ※学生番号を入力してください。
        </div>
      </form>

      <!-- 検索結果表示 -->
      <c:if test="${not empty scoreList}">
        <div class="title">検索結果（科目：${subjectName}）</div>
        <table>
          <colgroup>
            <col style="width: 100px;">
            <col style="width: 100px;">
            <col style="width: 120px;">
            <col style="width: 150px;">
            <col style="width: 80px;">
            <col style="width: 80px;">
            <col style="width: 80px;">
          </colgroup>
          <thead>
            <tr>
              <th>入学年度</th>
              <th>クラス</th>
              <th>学生番号</th>
              <th>氏名</th>
              <th>1回目</th>
              <th>2回目</th>
              <th>3回目</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="row" items="${scoreList}">
              <tr>
                <td><c:out value="${row.year}" /></td>
                <td><c:out value="${row.className}" /></td>
                <td><c:out value="${row.studentId}" /></td>
                <td><c:out value="${row.name}" /></td>
                <td><c:out value="${row.score1 != 0 ? row.score1 : '-'}" /></td>
                <td><c:out value="${row.score2 != 0 ? row.score2 : '-'}" /></td>
                <td><c:out value="${row.score3 != 0 ? row.score3 : '-'}" /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${empty scoreList && (not empty param.subjectCode or not empty param.studentId)}">
        <p style="color:red;">※該当する成績データがありませんでした。</p>
      </c:if>
    </div>
  </div>

</body>
</html>
