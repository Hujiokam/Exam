<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../header.html" %>
<%@include file="menu-tab.jsp" %>
<link rel="stylesheet" href="templates/style.css">
<link rel="stylesheet" href="templates/score.css">


<div class="right-menu">
      <div class="menu-heading">成績管理</div>
            <form action="searchScore.action" method="get" class="score-form">
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

<%@include file="../footer.html" %>
