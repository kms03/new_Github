<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<meta charset="utf-8">
<title>로그인 화면</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/loginForm.css">
<script type="text/javascript" src="member/member.js"></script>
<link rel="stylesheet" href="css/Portfolio.css">
</head>
<body>
	<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal"><a href="main"><img class="main_image" src="images/1.png"></a></h5>
			</div>
	
	<div class="login-form">
		<h1>로그인</h1>
		<form method="post" action="login" name = "frm">
		<div class="form-group ">
			<input type="text" class="form-control" placeholder="UserId "
				id="UserName" name="id"> <i class="mdi mdi-account"></i>
		</div>
		<div class="form-group log-status">
			<input type="password" class="form-control" placeholder="Password"
				id="Passwod" name="pwd"> <i class="mdi mdi-lock"></i>
		</div>
		<span class="alert">Invalid Credentials</span> 
		
		<input type="button" class="log-btn" value="Log in" onclick="login_check()">
		<br><br>
		<input type="button" class="log-btn2" value="회원 가입" onclick="go_contract()">
		<span style="color: red;">${message}</span>
		<br><br>
		<input type="button" class="log-btn2" value="아이디 찾기 & 비밀번호 찾기" onclick="id_pwd_check()">
		</form>
	</div>

<%@ include file="../footer.jsp" %>