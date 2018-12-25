<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/syousaistyle.css">
<title>Insert title here</title>
</head>
<body>


<div class="header">
<p class="namename">${userInfo.name}さん</p>
<a href="LogoutServlet" class="logout">ログアウト</a>
</div>



<h1 class="hello">ユーザー情報詳細参照</h1>

	<div class="loginid">
	<p class="login">ログインID</p>
	<p class="id">${syouser.loginId}</p>
	</div>

	<div class="username">
	<p class="user">ユーザー名</p>
	<p class="namae">${syouser.name}</p>
	</div>


	<div class="seinengappi">
	<p class="seinen">生年月日</p>
	<p class="seg">${syouser.birthDate}</p>
	</div>

	<div class="tourokunitiji">
	<p class="touroku">登録日時</p>
	<p class="trn">${syouser.createDate}</p>
	</div>


	<div class="kousinnitiji">
	<p class="kousin">更新日時</p>
	<p class="ksn">${syouser.updateDate}</p>
	</div>


	<a href="UserListServlet" class="modoru">戻る</a>

</body>
</html>