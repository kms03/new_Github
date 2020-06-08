<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="text-align: center">
<form method="post" action="board_write" name="frm_bw">
<c:if test="${!empty loginUser}">
	<input type="hidden" name="id" value="${loginUser.id}">
</c:if>
<c:if test="${!empty adminUser}">
	<input type="hidden" name="id" value="${adminUser.id}">
</c:if>
<table border="1" class="frm_bw">
<tr>
<th><label for="title">제목</label></th>
<td class="td_title"><input type="text" name="title" id="title" class="te_title"></td>
<th>글쓴이</th>
<td class="td_id">${loginUser.id}${adminUser.id}</td>
</tr>
<tr>
<th><label for="content">내용</label></th>
<td colspan="3"><textarea rows="10" cols="10" name="content" id="content" class="bw_text"></textarea></td>
</tr>
</table>
<input type="submit" value="입력" class="bo_wr_su">
<input type="reset" value="취소" class="bo_wr_re">
<button onclick="go_list()" class="bo_wr_li">목록</button>
</form>
</div>
<%@ include file="../footer.jsp" %>