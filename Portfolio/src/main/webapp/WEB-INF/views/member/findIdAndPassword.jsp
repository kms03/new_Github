<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/loginForm.css">
	<link rel="stylesheet" href="css/Portfolio.css">
	<script type="text/javascript" src="member/member.js"></script>
</head>
<body>

<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal"><a href="main"><img class="main_image" src="images/1.png"></a></h5>
			</div>

<div id="wrap">
	
	<form name="findId" action="find_id" method="get" class="login-form">
	<h4>아이디 찾기</h4>
	<table>
	<tr>
		<td align="right"><label> 이름</label></td>
		<td><input type="text" name="name" value="" class="form-control" placeholder ="이름을 입력하세요."></td>
	</tr>	
	<tr>
		<td align="right"><label> 이메일</label></td>
		<td><input type="text" name="email" value="" class="form-control" placeholder="이메일을 입력하세요."></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="button" class="log-btn" value="아이디 찾기" onclick="findMemberId()"></td>
	</tr>
	</table>
	</form>
	<p><p><p><p><p><p><p><p><p><p><p><p>
	
	
	<form name="findPW" class="login-form">
	<h4>비밀번호 찾기</h4>
	<table>
	<tr>
		<td align="right"><label> 아이디</label></td>
		<td><input type="text" name="id" value="" class="form-control" placeholder="아이디를 입력하세요."></td>
	</tr>	
	<tr>
		<td align="right"><label> 이름</label></td>
		<td><input type="text" name="name" value="" class="form-control" placeholder="이름을 입력하세요."></td>
	</tr>	
	<tr>
		<td align="right"><label> 이메일</label></td>
		<td><input type="text" name="email" value="" class="form-control" placeholder="이메일을 입력하세요."></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="button" class="log-btn" value="비밀번호 찾기" onclick="findPassword()"></td>
	</tr>
	</table>
	</form>
</div>

<%@ include file="../footer.jsp" %>