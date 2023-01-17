<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/member/goodsPayMent" method="post">
			<table border="1">
				<tr>
					<th>상품</th>
					<th>상품 이름</th>
					<th>상품 가격</th>
					<th>상품 개수</th>
					<th>상품 총 가격</th>
				</tr>
				<tr>
					<td>
						<img src="${pageContext.request.contextPath}/upload/${goodsOne.filename}" width="100" height="100">
						
					</td>
					<td>${goodsOne.goodsName}</td>
					<td>${goodsOne.goodsPrice}</td>
					<td>${goodsQuantity}</td>
					<td>
						<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
						<input type="hidden" name="goodsPrice" value="${goodsOne.goodsPrice}">
						<input type="hidden" name="goodsQuantity" value="${goodsQuantity}">
						${goodsOne.goodsPrice * goodsQuantity}
					</td>
					<c:set var="totalPrice" value="${totalPrice + (goodsOne.goodsPrice * goodsQuantity)}" ></c:set>
				</tr>
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
						사용 가능 포인트 : ${savePoint - usePoint}
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