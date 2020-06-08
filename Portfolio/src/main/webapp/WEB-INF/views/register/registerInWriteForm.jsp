<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sidemenu.jsp"%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.min.js"></script>
<script>
$.datepicker.setDefaults($.datepicker.regional['ko']); //한국어 설정
$(function() {
    $('#datePicker').datepicker({    // #input 태그 아이디와 동일해야 함. 여러개 구분사용 가능
        dateFormat:"yy-mm-dd",    // 날짜 출력폼 설정
        onSelect:function(d){    // 날짜가 선택되었을때 실행하는 함수
        }
    });
});
</script>
<div class="mypageMainDiv">
<h1>수입 내역 입력</h1>
<form method="post" action="" name="in_frm">
<table class="mypage_table">
	<tr>
		<th class="my_th">날짜</th>
		<td class="my_td" style="text-align: left;"><input type="text" id="datePicker" name="i_date" style="width: 200px;" placeholder="눌러서 날짜를 설정해주세요"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="iprice">금액</label></th>
		<td class="my_td"><input type="text" name="iprice" id="iprice" placeholder="금액을 입력해주세요"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="i_use">수입내역</label></th>
		<td class="my_td"><input type="text" name="i_use" id="i_use" placeholder="ex) 월급"></td>
	</tr>
	<tr>
		<th class="my_th">분류</th>
		<td class="my_td" style="text-align: left;">
		<select name="i_category" style="text-align: left; width: 100px;">
			<c:forEach items="${i_category}" var="i_category">
				<option value="${i_category}">${i_category}
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<th class="my_th"><label for="i_memo">메모</label></th>
		<td class="my_td"><input type="text" name="i_memo" id="i_memo"></td>
	</tr>
</table>
<div class="mypage_btn">
	<div class="mypage_message1">
		${message1}
	</div>
	<div class="mypage_message2">
		${message2}
	</div>
	<input type="button" value="입력" class="mypage_btn_up" onclick="incomingsCheck()">&nbsp;&nbsp;&nbsp;
	<input type="reset" value="다시 작성" class="mypage_btn_re">&nbsp;&nbsp;&nbsp;
	<input type="button" value="목록" class="mypage_btn_li" onclick="go_regi_in_list()">
</div>
</form>
</div>
<%@ include file="../footer.jsp"%>