<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	function incomings_del() {
		var count = 0;
		if(document.out_frm.chbox.length == undefined) {
			if(document.out_frm.chbox.checked == true) {
				count++;
			}
		} else {
			for (var i =0; i<document.out_frm.chbox.length; i++) {
				if(document.out_frm.chbox[i].checked == true) {
					count++;
				}
			}
		}
		if(count == 0) {
			alert("삭제할 항목을 선택해 주세요.");
		} else if(count > 1) {
			alert("삭제할 항목은 1개만 선택해 주세요.");
		} else {
			document.out_frm.action = "register_incomings_delete";
			document.out_frm.submit();
		}
	}
</script>
<script type="text/javascript">
function outgoings_del() {
	var count = 0;
	if(document.out_frm.chbox.length == undefined) {
		if(document.out_frm.chbox.checked == true) {
			count++;
		}
	} else {
		for (var i =0; i<document.out_frm.chbox.length; i++) {
			if(document.out_frm.chbox[i].checked == true) {
				count++;
			}
		}
	}
	if(count == 0) {
		alert("삭제할 항목을 선택해 주세요.");
	} else if(count > 1) {
		alert("삭제할 항목은 1개만 선택해 주세요.");
	} else {
		document.out_frm.action = "register_outgoings_delete";
		document.out_frm.submit();
	}
}
</script>
<div class="regi_div">
<h1><a href="prev_month">&lt;</a>&nbsp;${date}&nbsp;<a href="next_month">&gt;</a></h1>
<form method="post" action="prev_month_search" name="out_frm">
<input type="hidden" value="${cnt}">
	<div style="text-align: right; margin-right: 300px;">
    	<input type="button" value="지출 등록" style="text-align: right" class="regi_out_btn" onclick="regi_out_write_form()">
    	<input type="button" value="수입 등록" style="text-align: right" class="regi_in_btn" onclick="regi_in_write_form()">
    </div>
<table style="width: 60%; text-align: center; margin: auto; border: 1px solid #000;">
	<tbody style="border: 1px solid #000;">
	<tr>
		<th style="text-align: center; width: 50px;">&times;</th>
		<th style="text-align: center; width: 179px;">날짜</th>
		<th style="text-align: center; width: 95px;">금액</th>
		<th style="text-align: center; width: 137px;">수입내역</th>
		<th style="text-align: center; width: 123px;">수입분류</th>
		<th style="text-align: center; width: 365px;">메모</th>
	</tr>
	<c:forEach items="${incomingsList}" var="incomings">
	<tr>
		<td><input type="checkbox" name="chbox" value="${incomings.iseq}"></td>
		<td>${incomings.i_date}</td>
		<td>${incomings.iprice}</td>
		<td>${incomings.i_use}</td>
		<td>
			${incomings.i_category}
		</td>
		<td>${incomings.i_memo}</td>
	</tr>
	</c:forEach>
	<c:if test="${empty incomingsList}">
	<tr>
		<td colspan="6">데이터가 없습니다.</td>
	</tr>
	</c:if>
	</tbody>
</table><br>
<div style="text-align: center; padding-bottom: 10px;">
	<select name="searchType1" style="margin-left: 250px;">
		<option value="i_use" <c:out value="${scri.searchType eq 'i_use' ? 'selected' : ''}"/>>수입내역</option>
		<option value="i_category" <c:out value="${scri.searchType eq 'i_category' ? 'selected' : ''}"/>>수입분류</option>
	</select>
	<input type="text" name="keyword1" id="keywordInput">&nbsp;&nbsp;
	<input style="margin-right: 250px;" type="submit" id="searchBtn" onclick="search_keyword_prev()" value="검색">
	<button style="padding: 5px; margin-top: 5px;" type="button" class="in_del_btn" onclick="incomings_del()">삭제</button>
</div><br>
<table style="width: 60%; text-align: center; margin: auto; border: 1px solid #000;">
	<tbody style="border: 1px solid #000;">
	<tr>
		<th style="text-align: center; width: 50px;">&times;</th>
		<th style="text-align: center;">날짜</th>
		<th style="text-align: center;">가격</th>
		<th style="text-align: center;">사용내역</th>
		<th style="text-align: center;">사용처</th>
		<th style="text-align: center;">출금계좌</th>
		<th style="text-align: center;">분류</th>
		<th style="text-align: center;">메모</th>
	</tr>
	<c:forEach items="${outgoingsList}" var="outgoings">
	<tr>
		<td><input type="checkbox" name="chbox" value="${outgoings.oseq}"></td>
		<td>${outgoings.o_date}</td>
		<td>${outgoings.oprice}</td>
		<td>${outgoings.o_use}</td>
		<td>${outgoings.o_where}</td>
		<c:choose>
		<c:when test="${outgoings.cash_money == 1}">
			<td>현금</td>
		</c:when>
		<c:otherwise>
			<td>카드</td>
		</c:otherwise>
		</c:choose>
		<td>${outgoings.o_category}</td>
		<td>${outgoings.o_memo}</td>
	</tr>
	</c:forEach>
	<c:if test="${empty outgoingsList}">
	<tr>
		<td colspan="8">데이터가 없습니다.</td>
	</tr>
	</c:if>
	<tr>
		<td colspan="8" style="text-align: right;"><b style="color: blue; margin: 10px;">
		+ 수입 <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${in_sum}"></fmt:formatNumber></b> &nbsp; <b style="color: red; margin: 10px;">
		- 지출 <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${out_sum}"></fmt:formatNumber></b></td>
	</tr>
	</tbody>
</table>
<br>
<div style="clear: both;"></div>
<div style="text-align: center; padding-bottom: 10px;">
	<select name="searchType" style="margin-left: 250px;">
		<option value="o_use" <c:out value="${scri.searchType eq 'o_use' ? 'selected' : ''}"/>>사용내역</option>
		<option value="o_where" <c:out value="${scri.searchType eq 'o_where' ? 'selected' : ''}"/>>사용처</option>
		<option value="cash_money" <c:out value="${scri.searchType eq 'cash_money' ? 'selected' : ''}"/>>출금계좌</option>
		<option value="o_category" <c:out value="${scri.searchType eq 'o_category' ? 'selected' : ''}"/>>분류</option>
	</select>
	<input type="text" name="keyword" id="keywordInput">&nbsp;&nbsp;
	<input style="margin-right: 250px;" type="submit" id="searchBtn" onclick="search_keyword_prev()" value="검색">
	<button style="padding: 5px; margin-top: 5px;" type="button" class="in_del_btn" onclick="outgoings_del()">삭제</button>
</div>
</form>
</div>
<a href="#" style="text-align: center; float: right; margin-right: 520px;"><img src="images/top.png"></a>
<div style="clear: both;"></div>
<%@ include file="../footer.jsp" %>