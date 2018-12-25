<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/homes.css">
<title>Insert title here</title>
</head>
<body>
	<h1 class="hello">ログイン画面</h1>

	<c:if test="${errMsg != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${errMsg}
		</div>
	</c:if>

	<form class="form-signin" action="LoginServlet" method="post">

	<div class="loginid">
	<p class="login">ログインID</p>
	<input type="text" name="loginId" class="botan1">
	</div>

	<div class="passwordid">
	<p class="password">パスワード</p>
	<input type="text" name="password" class="botan2">
	</div>


	<input type="submit" value="ログイン" class="loginbotan">

	</form>

</body>
</html>