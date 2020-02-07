<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
th {
	font-size: 20px;
	width: 100px;
	height: 30px;
	width: 100px;
}

td {
	width: 100px;
	height: 30px;
	text-align: center;
}

p {
	font-size: 50px;
}

.summary-tr {
	font-weight: bolder;
	border-color: red;
	background-color: #EAEAEA;
}

.green {
	background-color: #CEF279;
	width: 70px;
	padding-left: 10px;
}

.yellow {
	background-color: #FAED7D;
	width: 70px;
	padding-left: 10px;
}
</style>
<title>grade</title>
<script>
function sort(){
	var sort = $("select[name=sort]").val()
	
	$.ajax({
		type : "GET",

		success:function(data){
			location.replace("get?sort=" + sort);
		},
		error:function(){
			alert("ERROR")
		}
	})
}
</script>
</head>
<body>
	<center>
		<p>성적</p>
		<select name="sort" onchange="sort()">
			<option value="">정렬기준</option>
			<option value="korean">국어</option>
			<option value="english">영어</option>
			<option value="math">수학</option>
			<option value="sum">합계</option>
		</select>
		<table border="4">
			<tr>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>합계</th>
				<th>평균</th>
				<th>순위</th>
			</tr>
			<c:forEach var="map" items="${grade}">
				<tr>
					<td>${map.name}</td>
					<c:choose>
						<c:when test="${map.korean >= summary[1].koravg}">
							<td class="green">${map.korean}${map.korRank}</td>
						</c:when>
						<c:otherwise>
							<td class="yellow">${map.korean}${map.korRank}</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${map.english >= summary[1].engavg}">
							<td class="green">${map.english}${map.engRank}</td>
						</c:when>
						<c:otherwise>
							<td class="yellow">${map.english}${map.engRank}</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${map.math >= summary[1].mathavg}">
							<td class="green">${map.math}${map.mathRank}</td>
						</c:when>
						<c:otherwise>
							<td class="yellow">${map.math}${map.mathRank }</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${map.sum >= summary[1].sumavg}">
							<td class="green">${map.sum}${map.sumRank}</td>
						</c:when>
						<c:otherwise>
							<td class="yellow">${map.sum}${map.sumRank}</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${map.avg >= summary[1].totalavg}">
							<td class="green">${map.avg}</td>
						</c:when>
						<c:otherwise>
							<td class="yellow">${map.avg}</td>
						</c:otherwise>
					</c:choose>
					<td>${map.rank}</td>
				</tr>
			</c:forEach>
			<tr class="summary-tr">
				<td>총 합계</td>
				<td>${summary[0].korsum}</td>
				<td>${summary[0].engsum}</td>
				<td>${summary[0].mathsum}</td>
				<td>${summary[0].totalsum}</td>
				<td>${summary[0].avgsum}</td>
			</tr>
			<tr class="summary-tr">
				<td>총 평균</td>
				<td>${summary[1].koravg}</td>
				<td>${summary[1].engavg}</td>
				<td>${summary[1].mathavg}</td>
				<td>${summary[1].sumavg}</td>
				<td>${summary[1].totalavg}</td>
			</tr>
		</table>
	</center>
</body>
</html>