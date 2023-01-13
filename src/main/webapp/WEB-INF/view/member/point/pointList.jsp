<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div>적립 포인트 : ${savePoint}</div>
		<div>사용 포인트 : ${usePoint}</div>
		<div>사용 포인트 : ${savePoint - usePoint}</div>
		<h1>포인트 적립 내역</h1>
		<table border="1">
			<tr>
				<th>주문 코드</th>
				<th>주문 상품</th>
				<th>포인트 내용</th>
				<th>포인트</th>
				<th>적립 일자</th>
			</tr>
			<c:forEach var="p" items="${list}">
				<tr>
					<td>${p.orderCode}</td>
					<td>${p.goodsCode}</td>
					<td>${p.pointKind}</td>
					<td>${p.point}</td>
					<td>${p.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>