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
<h1>지출 내역 입력</h1>
<form method="post" action="" name="out_frm">
<table class="mypage_table">
	<tr>
		<th class="my_th">날짜</th>
		<td class="my_td" style="text-align: left;"><input type="text" id="datePicker" name="o_date" style="width: 200px;" placeholder="눌러서 날짜를 설정해주세요"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="oprice">금액</label></th>
		<td class="my_td"><input type="text" name="oprice" id="oprice" placeholder="금액을 입력해주세요"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="o_use">사용내역</label></th>
		<td class="my_td"><input type="text" name="o_use" id="o_use" placeholder="ex) 장보기"></td>
	</tr>
	<tr>
		<th class="my_th"><label for="o_where">사용처</label></th>
		<td class="my_td"><input type="text" name="o_where" id="o_where" placeholder="ex) OO마트"></td>
	</tr>
	<tr>
		<th class="my_th">출금계좌</th>
		<td class="my_td" style="text-align: left;">
		<span style="text-align: center;">
			<input type="radio" name="cash_money" checked="checked" value="1" style="width: 50px;">현금&nbsp;&nbsp;
			<input type="radio" name="cash_money" value="2" style="width: 50px;">카드
		</span>
		</td>
	</tr>
	<tr>
		<th class="my_th">분류</th>
		<td class="my_td" style="text-align: left;">
		<select name="o_category" style="text-align: left; width: 100px;">
			<c:forEach items="${o_category}" var="o_category" varStatus="status">
				<option value="${o_category}">${o_category}
			</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<th class="my_th"><label for="o_memo">메모</label></th>
		<td class="my_td"><input type="text" name="o_memo" id="o_memo"></td>
	</tr>
</table>
<div class="mypage_btn">
	<div class="mypage_message1">
		${message1}
	</div>
	<div class="mypage_message2">
		${message2}
	</div>
	<input type="button" value="입력" class="mypage_btn_up" onclick="outgoingsCheck()">&nbsp;&nbsp;&nbsp;
	<input type="reset" value="다시 작성" class="mypage_btn_re">&nbsp;&nbsp;&nbsp;
	<input type="button" value="목록" class="mypage_btn_li" onclick="go_regi_list()">
</div>
</form>
</div>
<%@ include file="../footer.jsp"%>