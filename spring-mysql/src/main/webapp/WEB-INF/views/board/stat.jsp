<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<style>
</style>
<title>stat</title>
<script>
	function stat() {
		var dateType = $("select[name=stat]").val()
		$.ajax({
			type : "GET",

			success : function(data) {
				location.replace("stat?date=" + dateType);
			},
			error : function() {
				alert("ERROR")
			}
		})
	}
</script>
</head>
<body>
	<center>
		<select name="stat" onchange="stat()">
			<option value="">통계기준</option>
			<option value="d">일</option>
			<option value="m">월</option>
			<option value="h">시</option>
		</select>
		
		<br>
		<table border="3">
			<tr>
				<th>시간대</th>
				<th>개수</th>
			</tr>
			<c:forEach var="map" items="${stat}">
				<tr>
					<td>${map.stat}</td>
					<td>${map.cnt}</td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>