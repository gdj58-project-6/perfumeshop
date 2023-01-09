<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<body>
	<!-- include? -->
	<h3>장바구니</h3>
	<c:forEach var="m" items="${list}">
		<!-- loginMember.customerId == m.customerId일 경우에만 장바구니에 담긴 상품을 볼 수 있음  -->
		<c:if test="${loginMember.customerId == m.customerId}">
			<table border="1">
				<tr>
					<td>구매자</td>
					<td>${m.customerId}</td>
				<tr>
				<!-- 이름옆에 상품사진 나오게? -->
				<tr>
					<td>상품이름</td>
					<td>${m.goodsName}</td>
				<tr>
				<tr>
					<td>구매수량</td>
					<td><input type="number" value="${m.cartQuantity}"></td>
				<tr>
				<tr>
					<!-- 구매수량이 바뀔떄마다 가격도 바뀌게 -->
					<td>가격</td>
					<td>${m.goodsPrice}</td>
				<tr>
				<!-- 결제 페이지 이동 버튼 만들기 -->
			</table>
		</c:if>	
	</c:forEach>	
	<a href='${pageContext.request.contextPath}/member/goodsPayMent'>구매하기</a>
</body>
</html>