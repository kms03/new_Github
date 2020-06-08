<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>

<div style="width: 800px; text-align: center; margin-left: 600px;">
<h1>회원 탈퇴</h1>
<hr>
<div style="text-align: left;">
회원 탈퇴 전 꼭 확인해 주세요!<br><br>

회원을 탈퇴하면 계정 정보 및 서비스 이용 기록을 포함한 모든 정보가 삭제됩니다. <br>탈퇴한 후에는 더 이상 회원계정으로 로그인 할 수 없습니다.

<br><br>* 탈퇴된 회원계정 정보와 서비스 이용기록 등은 복구할 수 없으니 신중하게 선택하시길 바랍니다.
</div>
<hr>
<form method="post" name="frm">
<input type="password" name="pwd" placeholder="비밀번호를 입력해주세요."><br><br>
<div style="color: red;">${message}</div>
<br>
<button type="button" class="mem_delcl_btn" onclick="go_mypage()">탈퇴 취소</button>
<button type="button" class="mem_del_btn" onclick="go_member_delete()">확인</button>
</form>
</div>
<br><br><br><br><br><br><br><br><br><br>
<%@ include file="../footer.jsp" %>