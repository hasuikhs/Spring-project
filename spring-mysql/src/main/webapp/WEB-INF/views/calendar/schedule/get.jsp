<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>${date}요일 일정</title>
</head>
<body>
	<center>
		<strong>${date}요일 일정</strong>
		<table border="3">
			<tr>
				<c:choose>
					<c:when test="${schedule.cno != null}">
						<td>${schedule.annititle}</td>
					</c:when>
					<c:otherwise>
						<td>일정없음</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>
	</center>

</body>
</html>