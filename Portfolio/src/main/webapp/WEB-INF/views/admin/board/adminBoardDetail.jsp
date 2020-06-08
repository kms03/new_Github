<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../adminHeader.jsp" %>
<%@ include file="../adminSidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="text-align: center; width: 100%;">
<form method="post" action="board_update_form" name="frm_bw" id="frm_bw">
<input type="hidden" name="bseq" value="${board.bseq}">
<table border="1" class="frm_bw" style="margin-right: 580px;">
<tr style="width: 500px;">
<th style="width: 50px;"><label for="title">제목</label></th>
<td class="td_title">${board.title}</td>
<th style="width: 50px;">글쓴이</th>
<td class="td_id">${board.id}</td>
</tr>
<tr>
<th><label for="title">작성일</label></th>
<td class="td_title">${board.regdate}</td>
<th>조회수</th>
<td class="td_id">${board.cnt}</td>
</tr>
<tr>
<th><label for="content">내용</label></th>
<td colspan="3"><textarea rows="10" cols="10" name="content" id="content" class="bw_text" readonly="readonly">${board.content}</textarea></td>
</tr>
</table>
<input type="submit" value="수정" class="bo_wr_su">
<input type="button" value="삭제" class="bo_wr_re" onclick="go_delete()">
<button type="button" class="bo_wr_li" onclick="go_admin_list()">목록</button>
<br>
</form>
</div>
<div style="clear: both;"></div>
<%@ include file="comment1.jsp" %>
<%@ include file="../../footer.jsp" %>