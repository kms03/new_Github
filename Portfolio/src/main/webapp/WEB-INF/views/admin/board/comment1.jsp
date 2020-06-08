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
        			<c:if test="${loginUser.id == reply.id}">
        			<a href="#" onclick="reply_update_form(${reply.rno}, '${reply.re_content}', ${reply.bseq})">수정</a> &nbsp;&nbsp;
        			<a href="#" onclick="reply_delete(${reply.rno}, ${reply.bseq})">삭제</a></c:if>
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

<!-- 
<script>
function save_comment() {
	$.ajax({
		type:'post',
		url:'save_comment',
		data:$("#commentForm").serialize(),
		success: function(data) {
			if(data="success") {
				boardReplyList();
				$("#re_content").val("");
			}
		},
			
		error: function(request, status, error) {
			alert("error:"+error);
		}
	});
}

function boardReplyList() {
	$.ajax({
		type:'GET',
		url:'comment_list',
		dataType:"json",
		data:$("#commentForm").serialize(),
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data) {
			var html="";
			var cCnt = data.length;
			var loginUser = ${loginUser.id};
			
			if(data.length > 0) {
				for(i=0; i<data.length; i++){
	                   html += "<div id=\"rno\">";
	                   html += "<div id=\"comment_item\"> <strong>작성자: "+ data[i].id+"</strong>&nbsp;"
	                   html += "<span style=\"text-align:right\" id=\"write_date\">/ 작성일 : " + displayTime(data[i].regdate) + "</span>&nbsp;&nbsp;&nbsp;"
	                   html += "<button type=\"button\" onclick=reply_update_form("+data[i].rno+","+data[i].re_content+","+data[i].bseq+");>수정</button>&nbsp;&nbsp;";
	                   html += "<a href=\"javascript:void(0)\" onclick=replyUpdate("+this.rno+","+this.re_content+");>삭제</a>";
	                   html += "<br>내용 : "+data[i].re_content+"<hr style=\"width:680px;\"><br></div>"
	                   html += "</div>";
	                   
	                   function displayTime(timeValue) {
						var today = new Date();
						
						var dateObj = new Date(timeValue)
						var str = "";
						
						var yy = dateObj.getFullYear();
						var mm = dateObj.getMonth() + 1;
						var dd = dateObj.getDate();
						
						return [yy + '.' + mm + '.' + dd].join();
					}
				}
			} else {
				html += "<div>";
				html += "<div><h6>등록된 댓글이 없습니다.</h6></div>";
				html += "</div>";
			}
			
			$("#cCnt").html(cCnt);
			
			$("#commentList").html(html);
		},
		error: function(request, status, error) {
			alert("댓글 목록을 조회하지 못했습니다.");
		}
	});
}

function replyUpdate(rno, re_content) {
	var html = "";
	html += "<div id=\"rno\""+rno+">";
	html += "<a onclick=\"fn_updateReply("+rno+", "+re_content+")저장</a>";
	html += "<a href=\"javascript:void(0)\" onclick=\"boardReplyList()취소</a>";
	html += "<textarea name=\"re_content\" id=\"re_content\">";
	html += re_content;
	html += "</textarea>";
	html += "</div>";
	$("#rno").replaceWith(html);
}


$(document).ready(function() {
	boardReplyList();
});
</script>
 -->
</body>
</html>
