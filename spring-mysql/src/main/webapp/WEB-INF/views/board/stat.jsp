<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
td {
	text-align: center;
}

.num {
	text-align: right;
}

#stat-div {
	float: left;
	width: 15%;
	padding-left: 50px;
	height: 600px;
}

#chart-div {
	float: right;
	width: 80%;
	height: 600px;
}
</style>
<title>stat</title>
<!-- stylesheet -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css" />

</head>
<body>
	<!-- javascript -->
	<script src="https://d3js.org/d3.v3.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
	<div id="stat-div">
		<select name="stat" onchange="stat()">
			<option value="">
				<c:choose>
					<c:when test="${param.date eq 'd'}">일 기준</c:when>
					<c:when test="${param.date eq 'm'}">월 기준</c:when>
					<c:when test="${param.date eq 'h'}">시 기준</c:when>
					<c:otherwise>정렬 기준</c:otherwise>
				</c:choose>
			</option>
			<option value="d">일</option>
			<option value="m">월</option>
			<option value="h">시</option>
		</select> <a href="./excelstat?date=${param.date }">엑셀</a> <br>
		<table border="3">
			<tr>
				<th>시간대</th>
				<th>개수</th>
			</tr>
			<c:forEach var="map" items="${stat}">
				<tr>
					<td>${map.stat}</td>
					<td class="num"><fmt:formatNumber value="${map.cnt}"
							type="number"></fmt:formatNumber></td>
				</tr>
			</c:forEach>
			<tr>
				<td>합계</td>
				<td class="num"><fmt:formatNumber value="${calstat.sum}"
						type="number"></fmt:formatNumber></td>
			</tr>
			<tr>
				<td>평균</td>
				<td class="num"><fmt:formatNumber value="${calstat.avg}"
						type="number"></fmt:formatNumber></td>
			</tr>
		</table>
	</div>
	<select name="chartType" onchange="showchart()">
		<option value="">차트 선택</option>
		<option value="area-spline" selected="selected">라인</option>
		<option value="bar">막대</option>
		<option value="pie">원형</option>
	</select>
	<div id="chart-div"></div>

	<script>
	
	function stat() {
		var dateType = $("select[name=stat]").val()
		var chartType = $("select[name=chartType]").val()
		$.ajax({
			type : "GET",

			success : function(data) {
				location.replace("stat?date=" + dateType + "&chart=" + chartType)
			},
			error : function() {
				alert("ERROR")
			}
		})
	}
	
	function showchart() {
		var dateType = $("select[name=stat]").val()
		var chartType = $("select[name=chartType]").val()
		$.ajax({
			type : "GET",
			
			success : function(data){
				
				c3.generate({
					bindto : '#chart-div',
					data : {
						x: 'x',
						columns : [
							${x},
							${chartdata}
						],
						types : {
							개수 : chartType
						}
					}
				})
			},
			error : function(data) {
				alert("error")
			}
			
		})
	} 
	var chartType = $("select[name=chartType]").val()
	var chart = c3.generate({
		
		bindto : '#chart-div',
		data : {
			x: 'x',
			columns : [ 
				${x}, 
				${chartdata} 
			],
			types : {
				개수: chartType
			}
		}
	})
	
	</script>

</body>
</html>