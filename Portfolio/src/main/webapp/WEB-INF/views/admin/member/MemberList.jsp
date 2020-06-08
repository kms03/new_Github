<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<%@ include file="../../sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	.search {list-style: none; text-align:center; padding: 6px; display: inline-block;}
</style>
<form method="get" action="">
<div class="container">
    <div class="row header" style="text-align:center;color:green">
        &nbsp;&nbsp;&nbsp;<h3>회원 목록</h3>
    </div><br>
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th style="text-align: center" width="80">아이디</th>
                <th style="text-align: center" width="80">이름</th>
                <th style="text-align: center" width="80">이메일</th>
                <th style="text-align: center" width="500">주소</th>
                <th style="text-align: center" width="140">연락처</th>
                <th style="text-align: center" width="120">회원 가입일</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td><a href="admin_member_detail?id=${member.id}">${member.id}</a></td>
                <td>${member.name}</td>
                <td>${member.email}</td>
                <td>${member.zip_num}&nbsp;${member.address}</td>
                <td>${member.phone}</td>
                <td>${member.regdate}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    
</div>
<div style="text-align: center; padding-bottom: 10px;">

	<select name="searchType">
      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
      <option value="i"<c:out value="${scri.searchType eq 'i' ? 'selected' : ''}"/>>아이디</option>
      <option value="n"<c:out value="${scri.searchType eq 'n' ? 'selected' : ''}"/>>이름</option>
      <option value="e"<c:out value="${scri.searchType eq 'e' ? 'selected' : ''}"/>>이메일</option>
    </select>
	
	<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}">&nbsp;&nbsp;
	<button type="button" id="searchBtn">검색</button>
	<script>
      $(function(){
        $('#searchBtn').click(function() {
          self.location = "member_list" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
        });
      });   
    </script>
</div>
<div align="center">
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li class="search"><a href="member_list${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li class="search"><a href="member_list${pageMaker.makeSearch(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="search"><a href="member_list${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
</form>
<%@ include file="../../footer.jsp" %>