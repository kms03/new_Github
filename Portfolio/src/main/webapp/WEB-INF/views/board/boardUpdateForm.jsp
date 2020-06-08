<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="text-align: center">
<form method="post" action="board_update" name="frm_bw">
<input type="hidden" name="bseq" value="${board.bseq}">
<input type="hidden" name="id" value="${board.id}">
<table border="1" class="frm_bw">
<tr>
<th><label for="title">제목</label></th>
<td class="td_title"><input type="text" name="title" id="title" value="${board.title}"></td>
<th>글쓴이</th>
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
<td colspan="3"><textarea rows="10" cols="10" name="content" id="content" class="bw_text">${board.content}</textarea></td>
</tr>
</table>
<c:if test="${loginUser.id == board.id || !empty adminUser}">
<input type="submit" value="수정" class="bo_wr_su">
</c:if>
<button onclick="go_list()" class="bo_wr_li">목록</button>
</form>
</div>
<%@ include file="../footer.jsp" %>