<%--
	■ SaveMealSurveyサーブレットから呼び出される
	入力された食事名がDBにヒットしなければ警告を出す
	ヒットすれば、各パラメーターをSaveMealSurveyにかえす　"action" ="apply"
 --%>
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <title>食品検索結果 - BalanceRecipe</title>
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	</head>
	<body>
		<div class="container">
		<h2>🔍 検索結果一覧</h2>
		<c:choose>
			<c:when test="${empty foodList}">
			<div class="status-card no-result-card">
		        <p class="no-result-text">
		            「<strong>${param.MEAL}</strong>」に一致する食品は見つかりませんでした。
		        </p>
		        <p>別のキーワードで検索するか、手入力してください。</p>
		    </div>
			</c:when>
		<c:otherwise>
			<p>該当する食品を選択し、摂取量を入力して「決定」を押してください。</p>
		<table class="search-table">
		    <thead>
		        <tr>
		            <th>食品名</th>
		            <th>100gあたり</th>
		            <th>摂取量</th>
		            <th></th>
		        </tr>
		    </thead>
		    <tbody>
		        <c:forEach var="food" items="${foodList}">
		            <tr>
		                <td><strong>${food.name}</strong></td>
		                <td>${food.calories} kcal</td>
		            <td>
					    <form action="SaveMealSurvey" method="get">
					        <input type="hidden" name="action" value="apply">
					        <input type="hidden" name="selectedId" value="${food.id}">
					        <input type="hidden" name="weight" value="${preWeight}">
					        <input type="number" name="grams" value="100" min="1" step="1" class="grams-input"> g
					        <button type="submit" class="btn-primary">決定</button>
					    </form>
					</td>
		            </tr>
		        </c:forEach>
		    </tbody>
		</table>
		</c:otherwise>
		</c:choose>
		<nav>
			<ul>
		  		<li><a href="javascript:history.back()">◀️　戻る</a> </li>
		  	</ul>
		</nav>
		  </div>
	</body>
</html>