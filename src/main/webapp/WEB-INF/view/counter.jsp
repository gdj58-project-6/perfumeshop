<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		현재 접속자 수 : ${currentCount} 
		오늘 접속자 수 : ${todayCount} 
		누적 접속자 수 : ${totalCount} 
		
		<h3>년도별 누적 접속자 수</h3>
		<table border="1">
			<tr>
				<td>일자</td>
				<td>누적 접속자 수</td>
			</tr>
			<c:forEach var="y" items="${yearList}">
				<tr>
					<td>${y.counterDate}</td>
					<td>${y.counterNum}</td>
				</tr>
			</c:forEach>
		</table>
		
		<h3>월별 누적 접속자 수</h3>
		<table border="1">
			<tr>
				<td>일자</td>
				<td>누적 접속자 수</td>
			</tr>
			<c:forEach var="m" items="${monthList}">
				<tr>
					<td>${m.counterDate}</td>
					<td>${m.counterNum}</td>
				</tr>
			</c:forEach>
		</table>
		
		<h3>일자별 누적 접속자 수</h3>
		<table border="1">
			<tr>
				<td>일자</td>
				<td>누적 접속자 수</td>
			</tr>
			<c:forEach var="d" items="${dayList}">
				<tr>
					<td>${d.counterDate}</td>
					<td>${d.counterNum}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>