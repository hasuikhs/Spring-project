<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>calendar</title>
<!-- style -->
<!-- jQuery -->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

function popup(year, month, day, userno){
	
	var url = "./schedule/get?year=" + year + "&month=" + month + "&day=" + day + "&userno=" + userno;
			
	var name = "popup"
	
	window.open(url, name, "width=400, height=300, toolbar=no, status=no, location=no, menubar=no, scrollbars=no, resizable=no")
	
}
</script>
<style>
th {
	font-size: 20px;
	width: 100px;
	height: 30px;
	width: 100px;
}

td {
	vertical-align: top;
	width: 100px;
	height: 100px;
	padding: 10px;
}

th, td {
	font-weight: bolder;
}

.holy {
	color: red;
}

.empty {
	background-color: #EAEAEA;
}

#title {
	font-size: 40px;
	font-weight: bolder;
}

a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>

	<button type="button"
		onclick="location.href='<c:url value='../board/list'/>'">게시판으로</button>

	<center>

		<p id="title">
			<button type="button"
				onclick="location.href='<c:url value='./get?year=${calendar.year}&month=${calendar.today}&ms=${calendar.ms}&userno=${calendar.userno}'/>'">오늘</button>
			<a
				href="<c:url value='./get?year=${calendar.year}&month=${calendar.month - 1}&ms=${calendar.ms}&userno=${calendar.userno}'/>">&lt;&emsp;</a>
			${calendar.calTitle} <a
				href="<c:url value='./get?year=${calendar.year}&month=${calendar.month + 1}&ms=${calendar.ms}&userno=${calendar.userno}'/>">&emsp;&gt;</a>

			<c:choose>
				<c:when test="${calendar.ms eq 0}">
					<button type="button"
						onclick="location.href='<c:url value='./get?year=${calendar.year}&month=${calendar.month}&ms=1&userno=${calendar.userno}'/>'">월~</button>
				</c:when>
				<c:otherwise>
					<button type="button"
						onclick="location.href='<c:url value='./get?year=${calendar.year}&month=${calendar.month}&ms=0&userno=${calendar.userno}'/>'">일~</button>
				</c:otherwise>
			</c:choose>
		</p>

		<table border="3">
			<tr>
				<c:choose>
					<c:when test="${calendar.ms eq 0}">
						<th class="holy">일</th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
						<th class="holy">토</th>
					</c:when>
					<c:otherwise>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
						<th class="holy">토</th>
						<th class="holy">일</th>
					</c:otherwise>
				</c:choose>
			</tr>

			<c:forEach var="calList" items="${calendar.collection}">
				<tr>
					<c:forEach var="weekList" items="${calList}">
						<c:forEach var="daySchedules" items="${weekList}">
							<td onClick="javascript: popup(${calendar.year}, ${calendar.month}, ${daySchedules.day}, ${calendar.userno})"
								onMouseOver="this.style.background='#FAF4C0'"
								onMouseOut="this.style.background='white'"
								style="cursor: pointer"><c:choose>
									<c:when test="${daySchedules.isHoliday eq 'Y'}">
										<div class="holy">
											${daySchedules.day}<br> ${daySchedules.anni }
										</div>
										${daySchedules.addAnni }
									</c:when>
									<c:otherwise>
										${daySchedules.day}<br>
										${daySchedules.anni }<br>
										${daySchedules.addAnni }
									</c:otherwise>
								</c:choose></td>
						</c:forEach>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>