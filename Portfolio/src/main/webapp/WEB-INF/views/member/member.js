/**
 * 
 */

function login_check() {
	if(document.frm.id.value == "") {
		alert("아이디를 입력해주세요!");
		document.frm.id.focus();
		return;
	} else if(document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요!");
		document.frm.pwd.focus();
		return;
	} else {
		document.frm.action = "login";
		document.frm.submit();
	}
}

function go_contract() {
	document.frm.action = "contract_form";
	document.frm.submit();
}

function go_next() {
	if(document.formm.okon1[0].checked == true) {
		document.formm.action = "insert_member_form";
		document.formm.submit();	
	} else if(document.formm.okon1[1].checked == true) {
		alert("약관에 동의하셔야 합니다!");
	}
}

function go_insertForm() {
	document.frm.action = "insert_member_form";
	document.frm.submit();
}

function join_check() {
	if(document.frm.id.value == "") {
		alert("아이디를 입력해주세요!");
		document.frm.id.focus();
		return;
	} else if (document.frm.reid.value == "") {
		alert("중복체크를 해주세요!");
		document.frm.id.focus();
		return;
	} else if (document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요!");
		document.frm.pwd.focus();
		return;
	} else if (document.frm.re_pwd.value == "") {
		alert("비밀번호 확인을 입력해주세요!");
		document.frm.re_pwd.focus();
		return;
	} else if (document.frm.pwd.value != document.frm.re_pwd.value) {
		alert("비밀번호가 다릅니다!");
		document.frm.re_pwd.focus();
		return;
	} else if (document.frm.name.value == "") {
		alert("이름을 입력해주세요!");
		document.frm.name.focus();
		return;
	} else if (document.frm.email.value == "") {
		alert("이메일을 입력해주세요!");
		document.frm.email.focus();
		return;
	} else if (document.frm.zip_num.value == "") {
		alert("주소 찾기 버튼을 눌러주세요!");
		document.frm.zip_num.focus();
		return;
	} else if (document.frm.addr2.value == "") {
		alert("상세주소를 입력해주세요!");
		document.frm.addr2.focus();
		return;
	} else if (document.frm.phone.value == "") {
		alert("전화번호를 입력해주세요!");
		document.frm.phone.focus();
		return;
	} else {
		document.frm.action = "member_join";
		document.frm.submit();
	}
}

function idCheck() {
	if(document.frm.id.value == "") {
		alert("아이디를 입력해주세요!");
		document.frm.id.focus();
		return;
	}
	
	var url = "id_check_form?id="+document.frm.id.value;
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200, left=800, top=400");
}

function addr_search() {
	
	var url = "addr_search_form";
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200, left=800, top=400");
}

function addr_search2() {
	
	var url = "addr_search_form2";
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200, left=800, top=400");
}

function id_pwd_check() {
	document.frm.action = "id_pwd_check_form";
	document.frm.submit();
}

function findMemberId() {
	if(document.findId.name.value == "") {
		alert("이름을 입력해주세요!");
		document.findId.name.focus();
	} else if(document.findId.email.value == "") {
		alert("이메일을 입력해주세요!");
		document.findId.email.focus();
	} else {
		var url = "find_member_id?name="+document.findId.name.value
		+"&email="+document.findId.email.value;
		
		window.open(url, "_blank_1", 
		"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200, left=800, top=400");
	}
	
}

function findPassword() {
	if(document.findPW.id.value == "") {
		alert("아이디를 입력해주세요!");
		document.findPW.id.focus();
	} else if(document.findPW.name.value == "") {
		alert("이름을 입력해주세요!");
		document.findId.name.focus();
	} else if(document.findPW.email.value == "") {
		alert("이메일을 입력해주세요!");
		document.findId.email.focus();
	} else {
		var url = "find_member_pwd?id="+document.findPW.id.value
		+"&name="+document.findPW.name.value
		+"&email="+document.findPW.email.value;
		
		window.open(url, "_blank_1", 
		"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=330, height=200, left=800, top=400");
	}
}

function go_mypage() {
	document.frm.action = "mypage";
	document.frm.submit();
}

function go_member_delete() {
	if(document.frm.pwd.value == "") {
		alert("비밀번호를 입력해주세요!");
		document.frm.pwd.focus();
		return false;
	} else {
		if(confirm("정말 회원 탈퇴 하시겠습니까?") == true) {
			document.frm.action = "member_delete";
			document.frm.submit();
		}
		return false;
	}
	
}


