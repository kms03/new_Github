/**
 * 
 */

function go_notice_list() {
	document.frm_bw.action = "noticeBoard";
	document.frm_bw.submit();
}

function go_noticeboard_delete() {
	if(confirm("삭제하시겠습니까?") == true) {
		document.frm_bw.action = "notice_board_delete";
		document.frm_bw.submit();
	} else {
		return false;
	}
}