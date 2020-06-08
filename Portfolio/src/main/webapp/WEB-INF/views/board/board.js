/**
 * 
 */

function go_list() {
	document.frm_bw.action = "board";
	document.frm_bw.submit();
}

function go_admin_list() {
	document.frm_bw.action = "admin_board";
	document.frm_bw.submit();
}

function go_delete() {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		document.frm_bw.action = "board_delete";
		document.frm_bw.submit();
	} else {
		return false;
	}
	
}

function insert_reply() {
	document.frm_bw.action = "insert_reply";
	document.frm_bw.submit();
}

function reply_update_form(rno, re_content, bseq) {
	var url = "reply_update_form?rno="+rno+"&re_content="+re_content+"&bseq="+bseq;
	
	window.open(url, "_blank_1", 
			"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=600, height=300, left=700, top=400");
	
	//document.commentListForm.action = "reply_update_form?rno="+rno+"&re_content="+re_content+"&bseq="+bseq;
	//document.commentListForm.submit();
}

function update_reply(re_content) {
	document.co_frm.action = "reply_update";
	document.co_frm.submit();
	opener.document.location.reload();
	self.close();
	
}

function reply_delete(rno, bseq) {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		document.commentListForm.action = "reply_delete?rno="+rno+"&bseq="+bseq;
		document.commentListForm.submit();
	} else {
		return false;
	}
}

function ok_close() {
	opener.document.location.reload();
	self.close();
}

function save_comment1() {
	document.commentForm.action = "save_comment1";
	document.commentForm.submit();
}


