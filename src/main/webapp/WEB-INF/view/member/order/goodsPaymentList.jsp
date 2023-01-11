<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>goodsPaymentList</title>
	</head>
	<body>
		<h2>결제 페이지</h2>
		<form action="${pageContext.request.contextPath}/member/goodsPayMent" method="post">
			<table>
				<tr>
					<th>상품</th>
					<th>상품 이름</th>
					<th>상품 갯수</th>
					<th>상품 총 가격</th>
				</tr>
				<tr>
				<c:forEach var="g" items="${list}">
					<td><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="150" height="150"></td>
					<td>${g.goodsName}</td>
					<td>${g.goodsQuantity}</td>
					<td>${g.goodsPrice}</td>
				</c:forEach>
				</tr>
			</table>
		</form>
	</body>
</html>