<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="board/board.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title> 댓글 답변 </title>
    
    <style type="text/css">
        #wrap {
            width: 550px;
            margin: 0 auto 0 auto;
            text-align :center;
        }
    
        #commentUpdateForm{
            text-align :center;
        }
    </style>  
</head>
<body>
<div id="wrap">
    <br>
    <b><font size="5" color="gray">댓글수정</font></b>
    <hr size="1" width="550">
    <br>
 
    <div id="commentUpdateForm">
        <form name="co_frm" action="reply_update">
        <input type="hidden" value="${reply.rno}" name="rno">
        <input type="hidden" value="${reply.bseq}" name="bseq">    
            <textarea rows="7" cols="70" name="re_content">${reply.re_content}</textarea>
            <br><br>
            <input type="submit" value="등록">
            <input type="button" value="창닫기" onclick="window.close()">
            <c:if test="${message == 1}">
            	댓글이 수정되었습니다.
            	<input type="button" value="확인" onclick="ok_close()">
            </c:if>
        </form>
    </div>
</div>    
</body>
</html>