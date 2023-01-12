<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>주문완료</h1>
			<table>
			<tr>
				<c:forEach var="g" items="${list}">
					<td><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="100" height="100"></td>
					<td>상품 이름 : <a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a></td>
					<td>상품 가격 : ${g.orderGoodsPrice}</td>
					<td>상품 개수 : ${g.orderGoodsQuantity}</td>
					<c:set var="totalPrice"  value="${totalPrice + (g.orderGoodsPrice * g.orderGoodsQuantity)}" ></c:set>
					<td>상품 총 가격 : ${totalPrice}</td>
					</tr><tr>
				</c:forEach>
		</table>
	</body>
</html>