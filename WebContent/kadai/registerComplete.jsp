<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>登録完了</title>
  <style>
    body { font-family: sans-serif; margin: 0; padding: 0; }
    .header { background-color: #e6f0ff; padding: 10px; text-align: center; }
    .container { padding: 20px; }
    .message {
      background-color: #dff0d8;
      color: #3c763d;
      padding: 15px;
      border-radius: 5px;
      margin-bottom: 20px;
    }
    .link-row {
      display: flex;
      justify-content: flex-start;
      align-items: center;
    }
    .link-row a:first-child {
      margin-right: 60px; /* ← 右側だけ60pxスペース */
    }
    .link-row a {
      color: #0066cc;
      text-decoration: none;
      font-weight: bold;
    }
    .link-row a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
  <div class="header">
    <h2>得点管理システム</h2>
  </div>
  <div class="container">
    <div class="message">
      登録が完了しました。
    </div>
    <div class="link-row">
      <a href="scoreManagement.jsp">← 成績管理へ戻る</a>
      <a href="searchForm.jsp">成績参照へ →</a>
    </div>
  </div>
</body>
</html>