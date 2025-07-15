///aa
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        /* ← ヘッダー最上部・少し小さめ文字に */
        .header {
            background-color: #D7EEFF;
            padding: 10px 20px;
        }

        .header h2 {
            margin: 0;
            font-size: 24px; /* ← 少しだけ小さく */
            text-align: left;
        }

        .logout {
            text-align: right;
            padding: 10px 20px;
            background-color: transparent;
        }

        .logout a {
            color: #007bff;
            text-decoration: underline;
            margin-left: 10px;
        }

        .left-menu {
            float: left;
            width: 200px;
            border-right: 1px solid #ccc;
            padding: 20px 10px;
            text-align: left;
            box-sizing: border-box;
            min-height: 50vh;
        }

        .right-menu {
            margin-left: 220px;
            padding: 20px;
            box-sizing: border-box;
            text-align: center;
        }

        .menu-heading {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: left;
            background-color: #dddddd;
            padding: 10px;
        }

        .form-row {
            display: flex;
            justify-content: flex-start;
            align-items: flex-end;
            gap: 20px;
            flex-wrap: nowrap;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            min-width: 150px;
        }

        .form-button {
            display: flex;
            align-items: flex-end;
        }

        select {
            padding: 5px;
            font-size: 16px;
        }

        button {
          padding: 8px 16px;
          font-size: 16px;
          background-color: #cccccc;
          color: #333;
          border: 1px solid #999;
          border-radius: 4px;
          cursor: pointer;
          white-space: nowrap;        /* ← 改行させない */
          min-width: 80px;            /* ← 幅を確保 */
          text-align: center;         /* ← 中央揃え */
            }


        button:hover {
            background-color: #b0b0b0;
        }
    </style>
</head>
<body>

    <!-- 🔵 得点管理システム（最上部・左寄せ・小さめ） -->
    <div class="header">
        <h2>得点管理システム</h2>
    </div>

    <!-- 🔵 ログアウト情報（水色に含めず） -->
    <div class="logout">
        大原 太郎 様　<a href="logout-in.jsp">ログアウト</a>
    </div>

    <div class="container">
        <div class="left-menu">
            <p><a href="login-out.jsp">メニュー</a></p>
            <p><a href="StudentList.action">学生管理</a></p>
            <p>成績管理</p>
            <ul class="submenu">
                <li><a href="entry.jsp">成績登録</a></li>
                <li><a href="searchForm.jsp">成績参照</a></li>
            </ul>
            <p><a href="#">科目管理</a></p>
        </div>

        <div class="right-menu">
            <div class="menu-heading">成績管理</div>
            <form action="searchScore.action" method="get">
                <div class="form-row">
                    <div class="form-group">
                        <label>入学年度</label>
                        <select name="year">
                            <option value="">----------</option>
                            <option value="2022">2022</option>
                            <option value="2023">2023</option>
                            <option value="2024">2024</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>クラス</label>
                        <select name="classNum">
                            <option value="">----------</option>
                            <option value="121">121</option>
                            <option value="131">131</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>科目</label>
                        <select name="subject">
                            <option value="">------</option>
                            <option value="A02">国語</option>
                            <option value="B01">Java</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>回数</label>
                        <select name="times">
                            <option value="">----------</option>
                            <option value="1">1回目</option>
                            <option value="2">2回目</option>
                        </select>
                    </div>

                    <div class="form-button">
                        <button type="submit">検索</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
