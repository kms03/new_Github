<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="container">
    <div class="row header" style="text-align:center;color:green">
        <h3>게시판</h3>
    </div>
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th>글번호</th>
                <th class="title">글제목</th>
                <th>글쓴이</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.bseq}</td>
                <td><a href="board_detail?bseq=${board.bseq}">${board.title}</a></td>
                <td>${board.id}</td>
                <td>${board.regdate}</td>
                <td>${board.cnt}</td>
            </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>글번호</th>
                <th class="title">글제목</th>
                <th>글쓴이</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </tfoot>
    </table>
</div>

<%@ include file="../footer.jsp" %>