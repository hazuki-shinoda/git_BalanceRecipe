<%--
	savemeal-check.js, CSS適用
	■ InputSurveyサーブレットから呼び出される
	 	摂取した食事を記録する
	■ SavemealSurveyから呼び出される
		各パラメータを自動入力する
		
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="BalanceRecipe.Dto.UserInfoDto" %>
<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String today = LocalDate.now().toString();
    String msg = (String)request.getAttribute("MSG");
    if ("success".equals(msg)) {
%>
    <div class="alert-msg">
        <strong>☑️ 登録完了！</strong> 今日の食事を記録しました。
    </div>
<% } %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">

<title>食事記録</title>                    
</head>
<body>
<c:if test="${not empty ERROR_MSG}">
	<p class = "success-msg" > ${MSG} </p>
</c:if>
  <h2>食事入力フォーム</h2>                                    
  <form action="SaveMealSurvey" method="post">                      
    <p>名前： ${USER_NAME_ESC}
  <input type="hidden" name="NAME" value="${USER_NAME_RAW}"></p>
日付：<input type="date" name="mealDate" value="<%= today %>" required>
    <p>
            区分：<select name="mealType">
                <option value="朝食">朝食</option>
                <option value="昼食">昼食</option>
                <option value="夕食">夕食</option>
                <option value="間食">間食</option>
            	</select>
    </p>                                                           
<p>
  今日のメニュー：
  <input type="text" name="MEAL" id="ID_MEAL" value="${MEAL_NAME}"> 
  <button type="button" onclick="goToSearch()">検索して栄養計算</button>  
</p>

<div id="SEARCH_MODAL" class="search-modal">
    <h3>食品データベース検索結果</h3>
    <div id="SEARCH_RESULTS_LIST" class="search-results-list">
        </div>
    <button type="button" onclick="document.getElementById('SEARCH_MODAL').style.display='none'">閉じる</button>
</div>

<p>カロリー(kcal): 
  <input type="number" name="calorie" id="ID_CALORIE" step="0.1" min="0" 
         value="${CALORIE}" required>
</p>
<p>タンパク質(g): 
  <input type="number" name="protein" value="${PROTEIN}" step="0.1">
</p>
<p>脂質(g): 
  <input type="number" name="fat" value="${FAT}" step="0.1">
</p>
<p>炭水化物(g): 
  <input type="number" name="carbohydrate" value="${CARB}" step="0.1">
</p>
<p>
  今日の体重（kg）： 
  <input type="number" id="ID_WEIGHT" name="weight" step="0.1" min="0.1" value="${SAVED_WEIGHT}" required>
</p>
  <input type="submit" value="食事を登録" id="ID_SUBMIT" >
  </form>           
                                      
<nav>
    <ul style="list-style: none; padding: 0;">
        <li>
            <a href="Home">🏠 ホーム（今日の合計）へ</a>
        </li>
        <li>
            <a href="ShowAllSurvey">📅 過去の記録一覧へ</a>
        </li>
        <li>
            <a href="ExecuteLogout">🚪 ログアウトする</a>
        </li>
    </ul>
</nav>

  <script type="text/javascript" src="js/savemeal-check.js"></script>                 
</body>                                                                          
</html>                                                                          
