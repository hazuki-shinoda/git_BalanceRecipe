<%--
	Loginサーブレットからの呼び出し 
	ログイン画面または新規登録画面へ移動
	style.css, login-check.js使用	
	■registerSurvey経由(新規作成)の場合はregisteredNameに値がはいる
--%>
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty registeredName}">
    <p class="login-msg">
        ☑️<c:out value="${registeredName}"/> さん、登録が完了しました！ログインしてください。
    </p>
</c:if>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> 
<meta charset="UTF-8">

<title>ログイン画面</title>
</head>
<body>  
<% if ("success".equals(request.getParameter("logout"))) { %>
    <div class="logout-msg">ログアウトしました。</div>
<% } %>                                                                                                  
 <h1>ログイン</h1>                                                           
  <form action="ExecuteLogin" method="post">                                                        
    <p>ユーザーID：<br>                                                                                              
      <input type="text" name="ID" maxlength="20" id="ID_ID">                         
    </p>                                                                                                
    <p>パスワード：<br>                                                                                 
      <input type="password" name="PW" maxlength="20" id="ID_PW">                   
    </p>                                                                                              
    <input type="submit" value="ログイン" id="ID_SUBMIT" >  
  </form>

<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/RegisterSurvey">新規登録</a></li>
    </ul>
</nav>

<p>テスト用アカウント： ID：guest / PW：password</p>

<script type="text/javascript" src="js/login-check.js"></script>                                  		                                                                                       
</body>
</html>