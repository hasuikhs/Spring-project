<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>${date.datestr}요일 일정</title>
<style type="text/css">
#footer {
	position: absolute;
	bottom: 20px;
}
</style>
</head>
<body>
	<center>
		<c:choose>
			<c:when test="${date.holy eq 'Y'}">
				<strong style="color:red;">${date.datestr}</strong>
			</c:when>
			<c:otherwise>
				<strong>${date.datestr}</strong>
			</c:otherwise>
		</c:choose>
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
		<div id="footer">
			<form action="./create" method="GET">
				<button type="submit" name="userno" value=${date.userno }>추가</button>
			</form>
			<input type="button" value="삭제" onClick="location.href='#'">
			<input type="button" value="수정" onClick="location.href='#'">
		</div>
	</center>

</body>
</html>