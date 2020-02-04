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
</style>
<title>grade</title>
</head>
<body>
	<center>
		<p>성적</p>
		<table border="3">
			<tr>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>합계</th>
				<th>평균</th>
			</tr>
			<c:forEach var="map" items="${grade}">
				<tr>
					<td>${map.name}</td>
					<td>${map.korean}</td>
					<td>${map.english}</td>
					<td>${map.math}</td>
					<td>${map.sum}</td>
					<td>${map.avg}</td>
				</tr>
			</c:forEach>
			<tr>
				<td>총 합계</td>
				<td>${summary[0].korsum}</td>
				<td>${summary[0].engsum}</td>
				<td>${summary[0].mathsum}</td>
				<td>${summary[0].totalsum}</td>
				<td>${summary[0].avgsum}</td>
			</tr>
			<tr>
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