//a
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>得点管理</title>
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

        .logout-container {
            text-align: right;
            padding: 10px 20px;
            background-color: transparent;
            font-size: 14px;
        }

        .logout-container a {
            color: #007bff;
            text-decoration: underline;
            margin-left: 10px;
        }

        .container {
            display: flex;
        }

        .sidebar {
            width: 200px;
            border-right: 1px solid #ccc;
            padding: 20px 10px;
            text-align: left;
            box-sizing: border-box;
            min-height: 50vh;
            background-color: #ffffff;
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
        }

        th {
            background-color: #f0f0f0;
        }

        .message {
            color: green;
            margin: 10px 0;
        }

        .error {
            color: red;
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="header">
    <h2>得点管理システム</h2>
</div>
<div class="logout-container">大原 太郎 様　<a href="logout-in.jsp">ログアウト</a></div>

<div class="container">
    <div class="sidebar">
        <p><a href="login-out.jsp">メニュー</a></p>
        <p><a href="StudentList.action">学生管理</a></p>
        <p>成績管理</p>
        <ul>
            <li><a href="entry.jsp">成績登録</a></li>
            <li><a href="searchForm.jsp">成績参照</a></li>
        </ul>
        <p><a href="#">科目管理</a></p>
    </div>

    <div class="content">
        <div class="title">成績検索</div>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="message">${message}</div>
        </c:if>

        <form action="searchScore.action" method="get">
            <div class="form-row">
                <div class="form-group">
                    <label>入学年度</label>
                    <select name="year">
                        <option value="">----------</option>
                        <option value="2022" ${param.year == '2022' ? 'selected' : ''}>2022</option>
                        <option value="2023" ${param.year == '2023' ? 'selected' : ''}>2023</option>
                        <option value="2024" ${param.year == '2024' ? 'selected' : ''}>2024</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>クラス</label>
                    <select name="classNum">
                        <option value="">----------</option>
                        <option value="121" ${param.classNum == '121' ? 'selected' : ''}>121</option>
                        <option value="131" ${param.classNum == '131' ? 'selected' : ''}>131</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>科目</label>
                    <select name="subject">
                        <option value="">------</option>
                        <option value="A02" ${param.subject == 'A02' ? 'selected' : ''}>国語</option>
                        <option value="B01" ${param.subject == 'B01' ? 'selected' : ''}>Java</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>回数</label>
                    <select name="times">
                        <option value="">----------</option>
                        <option value="1" ${param.times == '1' ? 'selected' : ''}>1回目</option>
                        <option value="2" ${param.times == '2' ? 'selected' : ''}>2回目</option>
                        <option value="3" ${param.times == '3' ? 'selected' : ''}>3回目</option>
                    </select>
                </div>
                <div class="form-button">
                    <button type="submit">検索</button>
                </div>
            </div>
        </form>

        <c:if test="${searchPerformed}">
            <h3>検索結果</h3>
            <c:choose>
                <c:when test="${empty scoreList}">
                    <p>検索結果が見つかりませんでした。</p>
                </c:when>
                <c:otherwise>
                    <form action="registerScore.action" method="post">
                        <input type="hidden" name="year" value="${param.year}">
                        <input type="hidden" name="classNum" value="${param.classNum}">
                        <input type="hidden" name="subject" value="${param.subject}">
                        <input type="hidden" name="times" value="${param.times}">

                        <table>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>得点</th>
                            </tr>
                            <c:forEach var="s" items="${scoreList}">
                                <tr>
                                    <td>${s.year}</td>
                                    <td>${s.className}</td>
                                    <td>
                                        <input type="hidden" name="studentId" value="${s.studentId}" />
                                        ${s.studentId}
                                    </td>
                                    <td>${s.name}</td>
                                    <td><input type="number" name="score" value="${s.score}" min="0" max="100" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br>
                        <button type="submit">登録して終了</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>

</body>
</html>