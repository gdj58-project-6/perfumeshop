<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table>
			<tr>
				<th>상품 </th>
				<th>상품 이름</th>
				<th>상품 개수</th>
				<th>총 가격</th>
				<th>주문 상태</th>
				<th>주문 일자</th>
			</tr>
			<c:forEach var="o" items="${list}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/upload/${o.filename}" width="200" height="200"></td>
					<td><a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${o.orderCode}">${o.goodsName}</a></td>
					<td>${o.orderQuantity}</td>
					<td>${o.orderPrice}</td>
					<td>${o.orderState}</td>
					<td>${o.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>