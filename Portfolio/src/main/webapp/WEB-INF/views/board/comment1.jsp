<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- link rel="stylesheet" href="css/bootstrap.min.css"> -->
</head>
<body>
<div class="container">
    <form id="commentForm" name="commentForm" method="post">
    <br><br>
        <div>
            <div>
                <span><h3>댓글 목록</h3></span> <span id="cCnt"></span>
            </div>
            <input type="hidden" name="bseq" value="${board.bseq}">
            <div>
            <c:if test="${(loginUser != null) || (!empty adminUser)}">
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style="width: 650px" rows="3" cols="30" id="re_content" name="re_content" placeholder="댓글을 입력하세요"></textarea>
                            <br>
                            <div>
                                <a href='#' onClick="save_comment1()" class="btn pull-right btn-success">등록</a>
                            </div>
                        </td>
                    </tr>
                </table>
                </c:if>
            </div>
        </div>      
    </form>
</div>
<div class="container">
    <form id="commentListForm" name="commentListForm" method="post">
    <input type="hidden" name="bseq" value="${board.bseq}">
        <div id="commentList">
        	<c:forEach items="${replyList}" var="reply">
        		<div style="text-align: center; width: 600px; margin: auto;">
        			<input type="hidden" name="rno" value="${reply.rno}">
        			<input type="hidden" name="bseq" value="${reply.bseq}">
        				<b>작성자 : ${reply.id}</b>&nbsp;&nbsp; / &nbsp;&nbsp;
        			작성일 : ${reply.regdate} &nbsp;&nbsp;
        			<c:if test="${(loginUser.id == reply.id) || (!empty adminUser)}">
        			<a href="#" onclick="reply_update_form('${reply.rno}', '${reply.re_content}', '${reply.bseq}')">수정</a> &nbsp;&nbsp;
        			<a href="#" onclick="reply_delete('${reply.rno}', '${reply.bseq}')">삭제</a></c:if>
        			<br>
        			내용 : ${reply.re_content}
        			<hr>
        		</div>
        	</c:forEach>
        </div>
        <div style="text-align: center; margin: auto;">
  <ul style="width: 100px; text-align: center; margin: auto; height: 20px;">
    <c:if test="${pageMaker.prev}">
    	<li class="search"><a href="board_detail${pageMaker.makeSearch(pageMaker.startPage - 1)}&bseq=${board.bseq}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li style="float: left; padding-right: 10px; margin-left: 10px;" class="search"><a href="board_detail${pageMaker.makeSearch(idx)}&bseq=${board.bseq}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="search"><a href="board_detail${pageMaker.makeSearch(pageMaker.endPage + 1)}&bseq=${board.bseq}">다음</a></li>
    </c:if> 
  </ul>
</div>
    </form>
    <br>
</div>
</body>
</html>
