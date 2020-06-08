<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Portfolio Main</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker3.min.css">
<link rel="stylesheet" href="css/mypageMain.css">
<link rel="stylesheet" href="css/registerMain.css">
<script type="text/javascript" src="board/board.js"></script>
<script type="text/javascript" src="mypage/mypage.js"></script>
<script type="text/javascript" src="register/register.js"></script>
<script type="text/javascript" src="noticeBoard/noticeBoard.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/Portfolio.css">
<link rel="stylesheet" href="css/signForm.css">
<link rel="stylesheet" href="css/boardWrite.css">
<script type="text/javascript" src="member/member.js"></script>

</head>
<body>
	<header>
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm" style="height: 80px;">
			<h5 class="my-0 mr-md-auto font-weight-normal"><a style="height: 70px;" href="main"><img class="main_image" src="images/1.png"></a></h5>
			<nav class="my-2 my-md-0 mr-md-3">
			<c:if test="${empty adminUser}">
				<a class="p-2 text-dark" href="mypage">마이페이지</a> 
				<a class="p-2 text-dark" href="register_main">관리</a> 
				<a class="p-2 text-dark" href="reference">조회</a>
			</c:if>	 
				<a class="p-2 text-dark" href="board">게시판</a>
			<c:if test="${!empty adminUser}">	
				<a class="p-2 text-dark" href="member_list">회원 관리</a>
			</c:if>
			<c:if test="${empty loginUser && empty adminUser}">
				<a href="contract_form">회원가입</a>
			</c:if>
			</nav>
			<c:choose>
			<c:when test="${empty sessionScope.loginUser && empty sessionScope.adminUser}">
				<a class="btn btn-outline-primary" href="login_form">로그인</a>&nbsp;&nbsp;
				<a class="btn btn-outline-primary" href="admin_login_form">관리자 로그인</a>
			</c:when>
			<c:when test="${!empty sessionScope.loginUser}">
				${sessionScope.loginUser.name}(${sessionScope.loginUser.id})
				<a class="btn btn-outline-primary" href="logout">로그 아웃</a>
			</c:when>
			<c:otherwise>
			${sessionScope.adminUser.name}(${sessionScope.adminUser.id})
				<a class="btn btn-outline-primary" href="admin_logout">로그 아웃</a>
			</c:otherwise>
			</c:choose>
		</div>
	<br>
	</header>
