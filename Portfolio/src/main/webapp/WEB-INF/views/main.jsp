<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main role="main">
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron" style="margin: 0; padding: 0;">
		<img src="images/2.png" style="margin: 0; padding: 0;">	
	</div>
	<br>
	<div class="container">
		<!-- Example row of columns -->
		<div class="row" style="text-align: center; margin: auto;">
			<div class="col-md-4" style="text-align: center; margin: auto;">
				<h2>공지사항</h2>
				<table border="1">
				<tr>
					<td width="250px;">제목</td>
					<td>날짜</td>
				</tr>
				<c:forEach items="${noticeBoard}" var="noticeBoard">
				<input type="hidden" name="nseq" value="${noticeBoard.nseq}">
				<tr>
					<td><a href="noticeBoard_detail?nseq=${noticeBoard.nseq}">${noticeBoard.ntitle}</a></td>
					<td>${noticeBoard.regdate}</td>
				</tr>
				</c:forEach>
				</table><br>
					<a class="btn btn-secondary" href="noticeBoard" role="button">View
						details »</a>
			</div>
			<div class="col-md-4" style="text-align: center; margin: auto;">
				<h2>자유게시판</h2>
				<table border="1">
				<tr>
					<td width="250px;">제목</td>
					<td>날짜</td>
				</tr>
				<c:forEach items="${boardList}" var="board">
				<input type="hidden" name="nseq" value="${board.bseq}">
				<tr>
					<td><a href="board_detail?bseq=${board.bseq}">${board.title}</a></td>
					<td>${board.regdate}</td>
				</tr>
				</c:forEach>
				</table><br>
					<a class="btn btn-secondary" href="board" role="button">View
						details »</a>
			</div>

		</div>

		<hr>

	</div>
	<!-- /container -->

</main>
</body>

<%@ include file="footer.jsp"%>