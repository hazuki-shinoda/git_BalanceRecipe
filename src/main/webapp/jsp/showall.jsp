<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="BalanceRecipe.Dto.MealLogDto" %>
<%
    List<MealLogDto> list = (List<MealLogDto>)request.getAttribute("MEAL_LIST");
    String userName = (String)request.getAttribute("USER_NAME_RAW");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>食事履歴一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1><%= userName %> さんの食事履歴</h1>
    
    <div class="navigation">
        <p><a href="InputSurvey">◀ 新しい食事を記録する</a></p>
    </div>

    <table>
        <thead>
            <tr>
                <th>日付</th>
                <th>区分</th>
                <th>メニュー</th>
                <th>カロリー</th>
                <th>タンパク質</th>
                <th>脂質</th>
                <th>炭水化物</th>
                <th>体重</th>
            </tr>
        </thead>
		<tbody>
		    <%
		        if (list != null && !list.isEmpty()) {
		            for (MealLogDto dto : list) {
		    %>
		        <tr>
		            <td><%= dto.getMealDate() %></td>
		            <td><%= dto.getMealType() %></td>
		            <td class="menu-cell"><%= dto.getFoodName() %></td>
		            <td><strong><%= dto.getCalorie() %></strong> <small class="unit">kcal</small></td>
		            <td><%= dto.getProtein() %> <small class="unit">g</small></td>
		            <td><%= dto.getFat() %> <small class="unit">g</small></td>
		            <td><%= dto.getCarbohydrate() %> <small class="unit">g</small></td>
		            <td><%= dto.getWeight() %> <small class="unit">kg</small></td>
		        </tr>
		    <%
		            }
		        } else {
		    %>
		        <tr><td colspan="8">まだデータがありません。食事を記録しましょう！</td></tr>
		    <%
		        }
		    %>
		</tbody>
    </table>
    <nav>
        <ul>
            <li><a href="ExecuteLogout">🚪 ログアウトする</a><br></li>
    		<li><a href="javascript:history.back()">◀️　戻る</a> </li>
    	</ul>
    </nav>
</body>
</html>