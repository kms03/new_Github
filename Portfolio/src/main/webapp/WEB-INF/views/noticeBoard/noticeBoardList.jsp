<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
	.search {list-style: none; text-align:center; padding: 6px; display: inline-block;}
</style>
<form method="get" action="noticeBoard_write_form">
<div class="container">
    <div class="row header" style="text-align:center;color:green">
        &nbsp;&nbsp;&nbsp;<h3>공지사항 게시판</h3>
    </div>
    <c:if test="${!empty adminUser}">
     <div style="text-align: right">
    	<input type="submit" value="글쓰기" style="text-align: right" class="board_b_btn">
    </div>
    </c:if>
    <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
                <th style="text-align: center" width="80">글번호</th>
                <th style="text-align: center" class="title" width="530">글제목</th>
                <th style="text-align: center" width="200">글쓴이</th>
                <th style="text-align: center" width="200">작성일</th>
                <th style="text-align: center" width="100">조회수</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${noticeBoardList}" var="noticeBoard">
            <tr>
                <td>${noticeBoard.nseq}</td>
                <td><a href="noticeBoard_detail?nseq=${noticeBoard.nseq}">${noticeBoard.ntitle}</a></td>
                <td>${noticeBoard.id}</td>
                <td>${noticeBoard.regdate}</td>
                <td>${noticeBoard.cnt}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    
</div>
<div style="text-align: center; padding-bottom: 10px;">

	<select name="searchType">
      <option value="n"<c:out value="${scri.searchType == null ? 'selected' : ''}"/>>-----</option>
      <option value="t"<c:out value="${scri.searchType eq 't' ? 'selected' : ''}"/>>제목</option>
      <option value="c"<c:out value="${scri.searchType eq 'c' ? 'selected' : ''}"/>>내용</option>
      <option value="tc"<c:out value="${scri.searchType eq 'tc' ? 'selected' : ''}"/>>제목+내용</option>
    </select>
	
	<input type="text" name="keyword" id="keywordInput" value="${scri.keyword}">&nbsp;&nbsp;
	<button type="button" id="searchBtn">검색</button>
	<script>
      $(function(){
        $('#searchBtn').click(function() {
          self.location = "noticeBoard" + '${pageMaker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val() + "&keyword=" + encodeURIComponent($('#keywordInput').val());
        });
      });   
    </script>
</div>
<div align="center">
  <ul>
    <c:if test="${pageMaker.prev}">
    	<li class="search"><a href="noticeBoard${pageMaker.makeSearch(pageMaker.startPage - 1)}">이전</a></li>
    </c:if> 

    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
    	<li class="search"><a href="noticeBoard${pageMaker.makeSearch(idx)}">${idx}</a></li>
    </c:forEach>

    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
    	<li class="search"><a href="noticeBoard${pageMaker.makeSearch(pageMaker.endPage + 1)}">다음</a></li>
    </c:if> 
  </ul>
</div>
</form>
<%@ include file="../footer.jsp" %>