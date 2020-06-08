<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div class="regi_div">
	<h1><a href="refer_prev_month">&lt;</a>&nbsp;${date.get(0)}&nbsp;<a href="refer_next_month">&gt;</a></h1>
</div>
    <script type="text/javascript">
      google.charts.load("current", {
    	  packages:["corechart"],
    	  language:'ko'
      });
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['식비',     ${osum1}],
          ['주거/통신',      ${osum2}],
          ['생활용품',  ${osum3}],
          ['의복/미용', ${osum4}],
          ['건강/문화',    ${osum5}],
          ['교육/육아',    ${osum6}],
          ['교통/차량',    ${osum7}],
          ['경조사/회비',    ${osum8}],
          ['세금/이자',    ${osum9}],
          ['용돈/기타',    ${osum10}]
        ]);

        var options = {
          title: '${date.get(0)}월 분류별 지출 총액',
          pieHole: 0.4
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load("current", {
    	  packages:["corechart"],
    	  language:'ko'
      });
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['주수입',     ${isum1}],
          ['부수입',      ${isum2}],
          ['전월이월',  ${isum3}],
          ['저축/보험', ${isum4}]
        ]);

        var options = {
          title: '${date.get(0)}월 분류별 수입 총액',
          pieHole: 0.4
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart2'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript">
      google.charts.load('current', {
    	  packages:['bar'],
    	  language:'ko'
      });
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', '수입', '지출'],
          ["${date.get(11)}", ${ototalPrice.get(11)}, ${totalPrice.get(11)}],
          ["${date.get(10)}", ${ototalPrice.get(10)}, ${totalPrice.get(10)}],
          ["${date.get(9)}", ${ototalPrice.get(9)}, ${totalPrice.get(9)}],
          ["${date.get(8)}", ${ototalPrice.get(8)}, ${totalPrice.get(8)}],
          ["${date.get(7)}", ${ototalPrice.get(7)}, ${totalPrice.get(7)}],
          ["${date.get(6)}", ${ototalPrice.get(6)}, ${totalPrice.get(6)}],
          ["${date.get(5)}", ${ototalPrice.get(5)}, ${totalPrice.get(5)}],
          ["${date.get(4)}", ${ototalPrice.get(4)}, ${totalPrice.get(4)}],
          ["${date.get(3)}", ${ototalPrice.get(3)}, ${totalPrice.get(3)}],
          ["${date.get(2)}", ${ototalPrice.get(2)}, ${totalPrice.get(2)}],
          ["${date.get(1)}", ${ototalPrice.get(1)}, ${totalPrice.get(1)}],
          ["${date.get(0)}", ${ototalPrice.get(0)}, ${totalPrice.get(0)}]
        ]);

        var options = {
          chart: {
            title: '연간 수입 / 지출 통계',
            subtitle: '현재 월로부터 1년전 월까지 표시됩니다.'
          },
          vAxis : {format: 'currency'}
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
  </head>
  <form>
  	<input type="hidden" name="o_date" value='<fmt:formatDate value="${realdate}" pattern="yyyy-MM-dd"/>'>
  
  <div style="text-align: center; margin: auto; float: inherit;">
    <div id="donutchart" style="width: 600px; height: 500px; text-align: center; margin: auto; display: inline-block;">
    </div>
    <div id="donutchart2" style="width: 600px; height: 500px; text-align: center; margin: auto; display: inline-block;"></div>
  </div>
  <div style="width: 50%; float: left; text-align: center; padding-left: 25%;">
  <table id="re_table_out">
  	<tr>
  		<th width="200px;">지출 총액</th>
  		<td><fmt:setLocale value="ko_KR"/><b><fmt:formatNumber type="currency" value="${totalPrice.get(0)}"></fmt:formatNumber></b></td>
  	</tr>
  	<c:if test="${osum1 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=식비&o_date=${realdate}">식비</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum1}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum2 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=주거/통신&o_date=${realdate}">주거/통신</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum2}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum3 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=생활용품&o_date=${realdate}">생활용품</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum3}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum4 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=의복/미용&o_date=${realdate}">의복/미용</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum4}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum5 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=건강/문화&o_date=${realdate}">건강/문화</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum5}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum6 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=교육/육아&o_date=${realdate}">교육/육아</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum6}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum7 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=교통/차량&o_date=${realdate}">교통/차량</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum7}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum8 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=경조사/회비&o_date=${realdate}">경조사/회비</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum8}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum9 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=세금/이자&o_date=${realdate}">세금/이자</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum9}"></fmt:formatNumber><br></td>
  	</tr>
  	</c:if>
  	<c:if test="${osum10 != 0}">
  	<tr>
  		<th width="200px;"><a href="outgoings_category1?o_category=용돈/기타&o_date=${realdate}">용돈/기타</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum10}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  </table>
  </div>
  <div style="width: 50%; float: right; padding-left: 6%;">
  <table id="re_table_in">
  	<tr>
  		<th width="200px;">수입 총액</th>
  		<td><fmt:setLocale value="ko_KR"/><b><fmt:formatNumber type="currency" value="${ototalPrice.get(0)}"></fmt:formatNumber></b></td>
  	</tr>
  	<c:if test="${isum1 != 0}">
  	<tr>
  		<th width="200px;"><a href="incomings_category1?i_category=주수입&i_date=${realdate}">주수입</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${isum1}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${isum2 != 0}">
  	<tr>
  		<th width="200px;"><a href="incomings_category1?i_category=부수입&i_date=${realdate}">부수입</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${isum2}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${isum3 != 0}">
  	<tr>
  		<th width="200px;"><a href="incomings_category1?i_category=전월이월&i_date=${realdate}">전월이월</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${isum3}"></fmt:formatNumber></td>
  	</tr>
  	</c:if>
  	<c:if test="${isum4 != 0}">
  	<tr>
  		<th width="200px;"><a href="incomings_category1?i_category=저축/보험&i_date=${realdate}">저축/보험</a></th>
  		<td><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${isum4}"></fmt:formatNumber><br></td>
  	</tr>
  	</c:if>
  	<tr><th height="20px;"></th><td></td></tr>
  	<tr>
  		<td colspan="2" style="text-align: right;"><b>${date.get(0)}월 순이익</b> 
  		<c:if test="${ototalPrice.get(0) - totalPrice.get(0) > 0}"><fmt:setLocale value="ko_KR"/><b style="color: blue;"><fmt:formatNumber type="currency" value="${ototalPrice.get(0)-totalPrice.get(0)}"></fmt:formatNumber></b></c:if>
  		<c:if test="${ototalPrice.get(0) - totalPrice.get(0) == 0}"><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${ototalPrice.get(0)-totalPrice.get(0)}"></fmt:formatNumber></c:if>
  		<c:if test="${ototalPrice.get(0) - totalPrice.get(0) < 0}"><fmt:setLocale value="ko_KR"/><b style="color: red;"><fmt:formatNumber type="currency" value="${ototalPrice.get(0)-totalPrice.get(0)}"></fmt:formatNumber></b></c:if>
  		</td>
  	</tr>
  	<tr>
  		<td colspan="2" style="text-align: right;"><b>${date.get(0)}까지 총 순이익</b>
  			<c:if test="${ito - oto > 0}"><fmt:setLocale value="ko_KR"/><b style="color: blue;"><fmt:formatNumber type="currency" value="${ito - oto}"></fmt:formatNumber></b></c:if>
  			<c:if test="${ito - oto == 0}"><fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${ito - oto}"></fmt:formatNumber></c:if>
  			<c:if test="${ito - oto < 0}"><fmt:setLocale value="ko_KR"/><b style="color: red;"><fmt:formatNumber type="currency" value="${ito - oto}"></fmt:formatNumber></b></c:if>
  		</td>
  	</tr>
  </table>
  </div>
  </form>
  <div style="clear: both;"></div>
  <br><br><br>
      <div id="columnchart_material" style="width: 800px; height: 500px; text-align: center; margin: auto;"></div>
<%@ include file="../footer.jsp" %>