<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
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
      background-color: transparent;
      font-size: 14px;
    }

    .logout a {
      color: #007bff;
      text-decoration: underline;
      margin-left: 10px;
    }

    .container {
      display: flex;
    }

    .sidebar {
      width: 200px;
      background-color: #ffffff;
      padding: 20px 10px;
      box-sizing: border-box;
      border-right: 1px solid #ccc;
    }

    .content {
      flex: 1;
      padding: 20px;
      box-sizing: border-box;
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
      align-items: flex-end;
      gap: 20px;
      flex-wrap: nowrap;
      margin-bottom: 20px;
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

    .form-group select, .form-group input {
      padding: 5px;
      font-size: 16px;
      width: 150px;
    }

    .form-button {
      display: flex;
      align-items: flex-end;
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

    .notice {
      color: blue;
      font-size: 13px;
      margin-top: 10px;
    }
  </style>
</head>
<body>

  <div class="header">
    <h2>得点管理システム</h2>
  </div>

  <div class="logout">大原 太郎 様　<a href="logout-in.jsp">ログアウト</a></div>

  <div class="container">
    <div class="sidebar">
      <p><a href="login-out.jsp">メニュー</a></p>
      <p><a href="StudentList.action">学生管理</a></p>
      <p><a>成績管理</a></p>
      <ul>
        <li><a href="entry.jsp">成績登録</a></li>
        <li><a href="searchForm.jsp">成績参照</a></li>
      </ul>
      <p><a href="#">科目管理</a></p>
    </div>

    <div class="content">
      <div class="title">成績参照</div>

      <form action="${pageContext.request.contextPath}/SearchScore" method="get">
        <div class="form-row">
          <div class="form-group">
            <label>入学年度</label>
            <select name="year">
              <option value="">---</option>
              <option value="2015">2015</option>
              <option value="2016">2016</option>
              <option value="2017">2017</option>
              <option value="2018">2018</option>
              <option value="2019">2019</option>
              <option value="2020">2020</option>
              <option value="2021">2021</option>
              <option value="2022">2022</option>
              <option value="2023">2023</option>
              <option value="2024">2024</option>
            </select>
          </div>
          <div class="form-group">
            <label>クラス</label>
            <select name="classNum">
              <option value="">---</option>
              <option value="121">121</option>
              <option value="131">131</option>
            </select>
          </div>
          <div class="form-group">
            <label>科目</label>
            <select name="subjectCode">
              <option value="">---</option>
              <option value="A02">国語</option>
              <option value="B01">Java</option>
            </select>
          </div>
          <div class="form-button">
            <button type="submit">検索</button>
          </div>
        </div>
      </form>

      <form action="${pageContext.request.contextPath}/SearchScore" method="get">
        <div class="form-row">
          <div class="form-group">
            <label>学生番号</label>
            <input type="text" name="studentId" placeholder="学生番号を入力してください" style="width: 200px;">
    </div>
    <div class="form-button">
      <button type="submit">検索</button>
    </div>
  </div>
</form>


      <div class="notice">
        ※検索条件を指定せずに検索した場合は全件が表示されます。
      </div>
    </div>
  </div>
</body>
</html>