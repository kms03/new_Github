/**
 * 
 */

function mypageCheck() {
	if(document.my_frm.pwd.value == "") {
		alert("정보를 수정하려면 비밀번호를 입력해야합니다!");
		document.my_frm.pwd.focus();
		return;
	} else if(document.my_frm.new_pwd.value != document.my_frm.new_pwd_re.value) {
		alert("신규 비밀번호와 신규 비밀번호 확인이 다릅니다!");
		document.my_frm.new_pwd_re.focus();
		return;
	} else if(document.my_frm.name.value == "") {
		alert("이름을 입력해야합니다!");
		document.my_frm.name.focus();
		return;
	} else if(document.my_frm.email.value == "") {
		alert("이메일을 입력해야합니다!");
		document.my_frm.email.focus();
		return;
	} else if(document.my_frm.zip_num.value == "") {
		alert("우편번호를 입력해야합니다!");
		document.my_frm.zip_num.focus();
		return;
	} else if(document.my_frm.address.value == "") {
		alert("주소를 입력해야합니다!");
		document.my_frm.address.focus();
		return;
	} else if(document.my_frm.phone.value == "") {
		alert("연락처를 입력해야합니다!");
		document.my_frm.phone.focus();
		return;
	}
	document.my_frm.action = "mypage_member_update";
	document.my_frm.submit();
}

function go_admin_memberlist() {
	document.my_frm.action = "member_list";
	document.my_frm.submit();
}

function admin_member_update() {
	if(document.my_frm.name.value == "") {
		alert("이름을 입력해야합니다!");
		document.my_frm.name.focus();
		return;
	} else if(document.my_frm.email.value == "") {
		alert("이메일을 입력해야합니다!");
		document.my_frm.email.focus();
		return;
	} else if(document.my_frm.zip_num.value == "") {
		alert("우편번호를 입력해야합니다!");
		document.my_frm.zip_num.focus();
		return;
	} else if(document.my_frm.address.value == "") {
		alert("주소를 입력해야합니다!");
		document.my_frm.address.focus();
		return;
	} else if(document.my_frm.phone.value == "") {
		alert("연락처를 입력해야합니다!");
		document.my_frm.phone.focus();
		return;
	} else if(confirm("수정하시겠습니까?") == true) {
		document.my_frm.action = "admin_member_update";
		document.my_frm.submit();
	} else {
		return false;
	}
}


function admin_member_del() {
	const str = '<br><input type="password" name="pwd" style="width:250px;"" placeholder="관리자 비밀번호를 입력해주세요" />'
				+ '<button type="button" onclick="member_out()">삭제</button>'
	$(".adminMemberDelete").append(str);
}

function member_out() {
	if(document.my_frm.pwd.value == "") {
		alert("관리자 비밀번호를 입력해주세요.");
		document.my_frm.pwd.focus();
		return;
	} else if(confirm("정말 삭제하시겠습니까?") == true) {
		document.my_frm.action = "admin_member_out";
		document.my_frm.submit();
	} else {
		return false;
	}
}









