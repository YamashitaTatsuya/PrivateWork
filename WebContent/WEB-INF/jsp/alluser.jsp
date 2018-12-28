<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/alluser.css">
<title>Insert title here</title>
</head>
<body>

<div class="header">

<p class="namename">${userInfo.name}さん</p>
<input type="hidden" name="name" value="${kanriname.name}">

<a href="LogoutServlet" class="logout">ログアウト</a>
</div>

<h1 class="hello">ユーザ一覧</h1>

	<c:if test="${userInfo.loginId == 'admin'}">
	<a href="UserNewCreateServlet">新規登録</a>
	</c:if>

	<a href="TopicListServlet" class="board">掲示板</a>


	<form class="form-signin" action="UserListServlet" method="post">
	<div class="loginid">
	<p class="login">ログインID</p>
	<input type="text" name="searchLoginId" class="botan1">
	</div>

	<div class="passwordid">
	<p class="password">ユーザ名</p>
	<input type="text" name="searchName" class="botan2">
	</div>

	<div class="seinengappi">
	<p class="seinen">生年月日</p>
	<div class="seinyu">
	<input type="date" name="searchBirthDate1" class="botan3" >～
	<input type="date" name="searchBirthDate2" class="botan4" >
	</div>
	</div>


	<input type="submit" value="検索" class="kensaku">
	</form>


	<hr>



	        <div class="table-responsive">
             <table class="hyou" border="1">
               <thead>
                 <tr class="koumoku">
                   <th>ログインID</th>
                   <th>ユーザ名</th>
                   <th>生年月日</th>
                   <th></th>
                 </tr>
               </thead>
               <tbody>
                 <c:forEach var="user" items="${userList}" >
                   <tr>
                     <td>${user.loginId}</td>
                     <td>${user.name}</td>
                     <td>${user.birthDate}</td>
                     <!-- TODO 未実装；ログインボタンの表示制御を行う -->


                     <td>
                       <a class="syou" href="UserDetailServlet?id=${user.id}">詳細</a>

						<c:if test="${userInfo.loginId == 'admin' }">
                       <a class="kouu" href="UserUpdateServlet?id=${user.id}">更新</a>
                       </c:if>

                       <c:if test="${userInfo.loginId == user.loginId }">
                       <a class="kouu" href="UserUpdateServlet?id=${user.id}">更新</a>
                       </c:if>

                       <c:if test="${userInfo.loginId == 'admin'}">
                       <a class="saku" href="UserDeleteServlet?id=${user.id}">削除</a>
                       </c:if>


                     </td>
                   </tr>
                 </c:forEach>





               </tbody>
             </table>
           </div>

</body>
</html>