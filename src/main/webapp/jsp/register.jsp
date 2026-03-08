<%--
RegisterSurveyから呼び出される
新規登録の情報入力画面

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% if ("1".equals(request.getParameter("error"))) { %>
    <p class="error-msg"> 登録に失敗しました。IDが既に使われている可能性があります。</p>
<% } else if ("99".equals(request.getParameter("error"))) { %>
    <p class="error-msg"> 入力内容に不備があります。</p>
<% } %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>新規アカウント登録</title>
</head>
<body>
    <h2>新規アカウント登録</h2>
    <form action="${pageContext.request.contextPath}/RegisterSurvey" method="post">
        ID*: <input type="text" name="id" id = "ID_id" required ><br>
        パスワード*: <input type="password" name="password" id = "ID_password" required><br>
        氏名*: <input type="text" name="name" id = "ID_name" required><br>
        誕生日: <input type="date" name="birthday" id = "ID_birthday"><br>
        性別: <input type="radio" name="gender" value="M" id = "ID_gender_m" checked required>男性
				<input type="radio" name="gender" value="F" id = "ID_gender_f">女性<br>
        身長 (cm): <input type="number" step="0.1" name="height" id = "ID_height"><br>
        現在の体重 (kg): <input type="number" step="0.1" name="weight" id = "ID_weight"><br>
        目標体重 (kg): <input type="number" step="0.1" name="target_weight" id = "ID_target_weight"><br>
        <button type="submit">登録する</button>
    </form>
<a href ="Login">戻る</a>
<script type="text/javascript" src="js/register-check.js"></script>  
</body>
</html>