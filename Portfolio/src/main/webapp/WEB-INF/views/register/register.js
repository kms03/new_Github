/**
 * 
 */

function outgoingsCheck() {
	if(document.out_frm.o_date.value == "") {
		alert("날짜를 입력해주세요!");
		document.out_frm.o_date.focus();
		return false;
	} else if(document.out_frm.oprice.value == "") {
		alert("금액을 입력해주세요!");
		document.out_frm.oprice.focus();
		return false;
	} else if(isNaN(document.out_frm.oprice.value)) {
		alert("숫자만 입력해주세요!");
		document.out_frm.oprice.focus();
		return false;
	} else if(document.out_frm.o_use.value == "") {
		alert("사용내역을 입력해주세요!");
		document.out_frm.o_use.focus();
		return false;
	} else if(document.out_frm.o_where.value == "") {
		alert("사용처를 입력해주세요!");
		document.out_frm.o_where.focus();
		return false;
	} else {
		document.out_frm.action = "register_write";
		document.out_frm.submit();
	}
}

function incomingsCheck() {
	if(document.in_frm.i_date.value == "") {
		alert("날짜를 입력해주세요!");
		document.in_frm.i_date.focus();
		return false;
	} else if(document.in_frm.iprice.value == "") {
		alert("금액을 입력해주세요!");
		document.in_frm.iprice.focus();
		return false;
	} else if(isNaN(document.in_frm.iprice.value)) {
		alert("숫자만 입력해주세요!");
		document.in_frm.iprice.focus();
		return false;
	} else if(document.in_frm.i_use.value == "") {
		alert("수입내역을 입력해주세요!");
		document.in_frm.i_use.focus();
		return false;
	} else {
		document.in_frm.action = "incomings_write";
		document.in_frm.submit();
	}
}


function go_regi_list() {
	document.out_frm.action = "register_main";
	document.out_frm.submit();
}

function go_regi_in_list() {
	document.in_frm.action = "register_main";
	document.in_frm.submit();
}

function regi_in_write_form() {
	document.out_frm.action = "in_write_form";
	document.out_frm.submit();
}

function reference_list() {
	document.refer_frm.action = "reference";
	document.refer_frm.submit();
}


function reference_top() {
	document.refer_frm.action = "#";
	document.refer_frm.submit();
}

function search_keyword(key) {
	document.out_frm.action = "register_main?key="+key;
	document.out_frm.submit();
}

function search_keyword_prev(key) {
	document.out_frm.action = "prev_month_search?key="+key;
	document.out_frm.submit();
}

function search_keyword_next(key) {
	document.out_frm.action = "next_month_search?key="+key;
	document.out_frm.submit();
}

function regi_out_write_form() {
	document.out_frm.action = "register_write_form";
	document.out_frm.submit();
}

function in_del_btn() {
	document.out_frm.action = "register_incomings_delete";
	document.out_frm.submit();
}


