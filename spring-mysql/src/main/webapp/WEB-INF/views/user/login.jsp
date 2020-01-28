<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 페이지</title>
</head>
<body>
	<form action="/user/login" method="POST">
		<div>아이디 : <input type="text" name="userid"></div>
		<div>비밀번호 : <input type="password" name="password"></div>
		<div><input type="submit"></div>
	</form>
</body>
</html>