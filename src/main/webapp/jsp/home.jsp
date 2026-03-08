<%--
	Homeサーブレットから呼び出される
	Homeサーブレットで取得したその日の摂取栄養素を表示する
	CSSの適用
	InputSurvey, ShowAllSurvey, ExecuteLogout に移動可能リンク
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="BalanceRecipe.Util" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム </title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<c:if test="${not empty MSG}">
    <p class="success-msg">${MSG}</p>
</c:if>
    <header>
        <h1>こんにちは、<%= Util.replaceEscapeChar((String)request.getAttribute("USER_NAME_RAW")) %> さん</h1>
    </header>

    <div class="status-card">
        <h3>📊 本日の摂取栄養素（合計）</h3>
        <div class="grid">
            <div class="item">エネルギー<br><span class="value">${TOTAL_CAL}</span> kcal</div>
            <div class="item">タンパク質<br><span class="value">${TOTAL_PRO}</span> g</div>
            <div class="item">脂質<br><span class="value">${TOTAL_FAT}</span> g</div>
            <div class="item">炭水化物<br><span class="value">${TOTAL_CARB}</span> g</div>
        </div>
    </div>
<hr>
    <nav>
        <ul>
            <li><a href="InputSurvey">🍴 今日の食事を記録する</a></li>
            <li><a href="ShowAllSurvey">📅 過去の記録を見る</a></li>
            <li><a href="ExecuteLogout">🚪 ログアウトする</a></li>
        </ul>
    </nav>

</body>
</html>