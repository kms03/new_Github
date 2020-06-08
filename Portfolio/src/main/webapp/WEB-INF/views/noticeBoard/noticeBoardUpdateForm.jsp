<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="text-align: center">
<form method="post" action="notice_board_update" name="frm_bw">
<input type="hidden" name="nseq" value="${noticeBoard.nseq}">
<input type="hidden" name="id" value="${noticeBoard.id}">
<table border="1" class="frm_bw">
<tr>
<th><label for="title">제목</label></th>
<td class="td_title"><input type="text" name="ntitle" id="title" value="${noticeBoard.ntitle}"></td>
<th>글쓴이</th>
<td class="td_id">${noticeBoard.id}</td>
</tr>
<tr>
<th><label for="title">작성일</label></th>
<td class="td_title">${noticeBoard.regdate}</td>
<th>조회수</th>
<td class="td_id">${noticeBoard.cnt}</td>
</tr>
<tr>
<th><label for="content">내용</label></th>
<td colspan="3"><textarea rows="10" cols="10" name="n_content" id="content" class="bw_text">${noticeBoard.n_content}</textarea></td>
</tr>
</table>
<input type="submit" value="수정" class="bo_wr_su">
<button onclick="go_notice_list()" class="bo_wr_li">목록</button>
</form>
</div>
<%@ include file="../footer.jsp" %>