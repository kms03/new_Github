<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sidemenu.jsp"%>
<div class="mypageMainDiv">
<h1>회원 정보 수정</h1>
<form method="post" action="" name="my_frm">
<input type="hidden" name="id" value="${loginUser.id}">
<table class="mypage_table">
	<tr>
		<th class="my_th">아이디</th>
		<td>${loginUser.id}</td>
	</tr>
	<tr>
		<th class="my_th"><label for="pwd">비밀번호</label></th>
		<td class="my_td"><input type="password" name="pwd" id="pwd"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="new_pwd">신규 비밀번호</label></th>
		<td class="my_td"><input type="password" name="new_pwd" id="new_pwd"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="new_pwd_re">신규 비밀번호 확인</label></th>
		<td class="my_td"><input type="password" name="new_pwd_re" id="new_pwd_re"></td>
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
		${message1}
	</div>
	<div class="mypage_message2">
		${message2}
	</div>
	<input type="button" value="수정" class="mypage_btn_up" onclick="mypageCheck()">&nbsp;&nbsp;&nbsp;
	<input type="reset" value="다시 작성" class="mypage_btn_re">
</div>
</form>
</div>
<%@ include file="../footer.jsp"%>