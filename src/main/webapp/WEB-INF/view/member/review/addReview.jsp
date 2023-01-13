<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/member/addReview?pointKind=적립" method="post">
			<table border="1">
				<tr>
					<td>상품</td>
					<td>
						<input type="hidden" name="orderCode" value="${orderOne.orderCode}">
						<input type="hidden" name="goodsCode" value="${orderOne.goodsCode}">
						<input type="hidden" name="id" value="${orderOne.customerId}">
						<c:set var="point" value="${(orderOne.orderGoodsPrice * orderOne.orderGoodsQuantity) * 0.01}"></c:set>
						<img src="${pageContext.request.contextPath}/upload/${orderOne.filename}" width="100" height="100">
						${orderOne.goodsName}
					</td>
				</tr>
				<tr>
					<td>상품 개수</td>
					<td>
						${orderOne.orderGoodsQuantity}
					</td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td>
						${orderOne.orderGoodsPrice}
					</td>
				</tr>
				<tr>
					<td>총 가격</td>
					<td>
						${orderOne.orderGoodsPrice * orderOne.orderGoodsQuantity}
					</td>
				</tr>
				<tr>
					<td>적립받을 포인트</td>
					<td>
						<fmt:formatNumber var="point" type="number" maxFractionDigits="0"  value="${point}" />
						<input type="text" name="point" value="${point}">
					</td>
				</tr>
			</table>
			<table border="1">
				<tr>
					<td>리뷰</td>
					<td><textarea rows="5" cols="20" name="reviewMemo"></textarea></td>
				</tr>
			</table>
			<button type="submit">리뷰등록</button>
		</form>
	</body>
</html>