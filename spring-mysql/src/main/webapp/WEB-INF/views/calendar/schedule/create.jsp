<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>일정추가</title>
</head>
<body>
	<form action="../schedule/create" method="POST">
		<input name="userno" value="${param.userno}" style="display:none">
		<label>일정 이름 </label><input name="annititle" type="text"><br>
		<label>날짜</label><input name="annidate" type="date"><br>
		<label>휴일여부</label>
		<select name="isholiday">
			<option value="Y">예</option>
			<option value="N">아니오</option>
		</select>
		<button type="submit">submit</button>
	</form>
</body>
</html>