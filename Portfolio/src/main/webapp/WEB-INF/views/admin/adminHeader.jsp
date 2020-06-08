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
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal"><a href="main">Company name</a></h5>
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="member_list">회원 관리</a> 
				<a class="p-2 text-dark" href="board">게시판 관리</a>
			</nav>
			${sessionScope.adminUser.name}(${sessionScope.adminUser.id})
				<a class="btn btn-outline-primary" href="admin_logout">로그 아웃</a>
		</div>

	</header>
