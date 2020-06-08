<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="../sidemenu.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div class="regi_div">
	<h1><a href="outgoings_category1_prev?o_date=${realdate}&o_category=${o_category}">&lt;</a>&nbsp;${date.get(0)}&nbsp;<a href="outgoings_category1_next?o_date=${realdate}&o_category=${o_category}">&gt;</a></h1>
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
          ['${o_category}',     ${osum1}]
        ]);

        var options = {
          title: '${date.get(0)}월 ${o_category} 총액',
          pieHole: 0.4,
          slices:{
        	  0:{color:'#ff0000'}
          }
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
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
          ['Year', '지출'],
          ["${date.get(11)}", ${totalPrice.get(11)}],
          ["${date.get(10)}", ${totalPrice.get(10)}],
          ["${date.get(9)}", ${totalPrice.get(9)}],
          ["${date.get(8)}", ${totalPrice.get(8)}],
          ["${date.get(7)}", ${totalPrice.get(7)}],
          ["${date.get(6)}", ${totalPrice.get(6)}],
          ["${date.get(5)}", ${totalPrice.get(5)}],
          ["${date.get(4)}", ${totalPrice.get(4)}],
          ["${date.get(3)}", ${totalPrice.get(3)}],
          ["${date.get(2)}", ${totalPrice.get(2)}],
          ["${date.get(1)}", ${totalPrice.get(1)}],
          ["${date.get(0)}", ${totalPrice.get(0)}]
        ]);

        var options = {
          chart: {
            title: '연간 ${o_category} 통계',
            subtitle: '현재 월로부터 1년전 월까지 표시됩니다.'
          },
          vAxis : {format: 'currency'},
          series:{
        	  0:{color:'#ff0000'}
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
    
  </head>
  <form>
  <input type="hidden" name="o_date" value='<fmt:formatDate value="${realdate}" pattern="yyyy-MM-dd"/>'>
  <input type="hidden" name="o_category" id="o_category" value="${o_category}">
  <div style="text-align: center; margin: auto; float: inherit;">
    <div id="donutchart" style="width: 600px; height: 500px; text-align: center; margin: auto; display: inline-block;">
    </div>
  </div>
  <div style="width: 100%; float: center; text-align: center;">
  <table border="1" style="width: 50%; text-align: center; margin: auto; border: 1px solid #000;">
	<tbody style="border: 1px solid #000;">
	<tr>
		<th style="text-align: center; width: 100px;">날짜</th>
		<th style="text-align: center;" width="150px;">금액</th>
		<th style="text-align: center; width: 150px;">사용내역</th>
		<th style="text-align: center;" width="150px;">사용처</th>
		<th style="text-align: center;" width="150px;">출금계좌</th>
		<th style="text-align: center;">분류</th>
		<th style="text-align: center;">메모</th>
	</tr>
	<c:if test="${!empty out_cate_list}">
	<c:forEach items="${out_cate_list}" var="outgoings">
	<tr>
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
		<td>
			${outgoings.o_category}
		</td>
		<td>${outgoings.o_memo}</td>
	</tr>
	</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" style="text-align: right;"><b style="color: red; margin: 10px;">
		${o_category} - <fmt:setLocale value="ko_KR"/><fmt:formatNumber type="currency" value="${osum1}"></fmt:formatNumber></b></td>
	</tr>
	</tbody>
</table>
  </div>
  </form>
  <form name="refer_frm">
  <a href="#" style="text-align: center; float: right; width: 5%; margin-right: 448px; margin-top: 8px;"><img src="images/top.png"></a>
  <button style="text-align: center; margin-top:10px; width: 9%; float: right; margin-right: 50px;" class="board_b_btn" type="button" onclick="reference_list()">전체 조회 페이지로</button>
  </form>
  <div style="clear: both;"></div>
  <br><br><br>
  <div id="columnchart_material" style="width: 800px; height: 500px; text-align: center; margin: auto;"></div>
<%@ include file="../footer.jsp" %>