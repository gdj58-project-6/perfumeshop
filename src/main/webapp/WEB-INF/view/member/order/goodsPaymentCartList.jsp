<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>goodsPaymentList</title>
	</head>
	<body>
		<h2>결제 페이지</h2>
		<form action="${pageContext.request.contextPath}/member/goodsPayMentCart" method="post">
			<table>
				<tr>
					<th>상품</th>
					<th>상품 이름</th>
					<th>상품 가격</th>
					<th>상품 갯수</th>
					<th>상품 총 가격</th>
				</tr>
				<c:forEach var="g" items="${list}">
					<tr>
						<td><img src="${pageContext.request.contextPath}/upload/${g.fileName}" width="150" height="150"></td>
						<td><input type="hidden" name="goodsCode" value="${g.goodsCode}"> ${g.goodsName}</td>
						<td>${g.goodsPrice}</td>
						<td>${g.cartQuantity}</td>
						<td>${g.goodsPrice * g.cartQuantity}</td>
						<c:set var="totalPrice"  value="${totalPrice + (g.goodsPrice * g.cartQuantity)}" ></c:set>
					</tr>
				</c:forEach>
			</table>
			<h4>구매자 정보</h4>
			<table>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="id" value="${customerOne.customerId}">
						<input type="text" value="${customerOne.customerName}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" value="${customerOne.customerPhone}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<input type="hidden" name="addressCode" value="${customerOne.addressCode}">
						${customerOne.address}
					</td>
				</tr>
				<tr>
					<td>요청사항</td>
					<td><textarea rows="5" cols="10" name="orderMemo"></textarea></td>
				</tr>
				<tr>
					<td>포인트 사용</td>
					<td>
						<input type="text" name="point" value="0">
						사용 가능 포인트 : ${customerOne.point}
					</td>
				</tr>
				<tr>
					<td>총 가격</td>
					<td><input type="text" name="orderPrice" value="${totalPrice}"></td>
				</tr>
			</table>
			<button type="submit">구매하기</button>
		</form>
	</body>
</html>