<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp"%>
<%@ include file="../../sidemenu.jsp"%>
<div class="mypageMainDiv">
<h1>회원 정보 수정</h1>
<form method="post" action="" name="my_frm">
<input type="hidden" name="id" value="${member.id}">
<table class="mypage_table">
	<tr>
		<th class="my_th">아이디</th>
		<td>${member.id}</td>
	</tr>
	<tr>
		<th class="my_th"><label for="name">이름</label></th>
		<td class="my_td"><input type="text" name="name" id="name" value="${member.name}"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="email">이메일</label></th>
		<td class="my_td"><input type="text" name="email" id="email" value="${member.email}"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="zip_num">우편번호</label></th>
		<td class="my_td_zip_num"><input type="text" name="zip_num" id="zip_num" value="${member.zip_num}" readonly="readonly">
		&nbsp;<input type="button" value="주소 찾기" id="search_addr" onclick="addr_search2()"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="address">주소</label></th>
		<td class="my_td"><input type="text" name="address" id="address" value="${member.address}"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="phone">연락처</label></th>
		<td class="my_td"><input type="text" name="phone" id="phone" value="${member.phone}"></td>
	</tr>
</table>
<div class="mypage_btn">
	<div class="mypage_message1">
		${message}
	</div>
	<input type="button" value="수정" class="mypage_btn_up" onclick="admin_member_update()">&nbsp;&nbsp;&nbsp;
	<input type="button" onclick="go_admin_memberlist()" value="목록" class="mypage_btn_li">&nbsp;&nbsp;&nbsp;
	<input type="button" onclick="admin_member_del()" value="회원 삭제" class="mypage_btn_re">
	<div class="adminMemberDelete"></div>
</div>
</form>
</div>
<%@ include file="../../footer.jsp"%>