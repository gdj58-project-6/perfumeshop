<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
<script>
	// 수량 변경할시 금액 변경되는 js
	let orderQuantityVal = orderQuantity.value;
	
</script>
</head>
<body>
	<!-- include -->
	<h3>장바구니</h3>
	<!-- 결제 페이지로 넘기기 -->
	<!-- goodsCode 넘길 필요없으면 삭제하기 -->
	<form action="${pageContext.request.contextPath}/member/goodsPayMent?goodsCode=${m.goodsCode}">
		<c:forEach var="m" items="${list}">
			<!-- loginMember.customerId == m.customerId일 경우에만 장바구니에 담긴 상품을 볼 수 있음  -->
			<c:if test="${loginMember.customerId == m.customerId}">
				<table border="1">
					<tr>
						<td>구매자</td>
						<td>${m.customerId}</td>
					<tr>
					<!-- 이름위에 상품사진 나오게? -> 사진크기 조금 줄임 -->
						<td colspan="2">
							<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="100" height="100">
						</td>
					<tr>
						<td>상품이름</td>
						<td><input type="text" name="goodsName" value="${m.goodsName}" readonly="readonly"></td>
					<tr>
					<tr>
						<td>구매수량</td>
						<td>
							<input type="number" name="orderQuantity" value="${m.cartQuantity}">
							<input type="button" value=" + " name="add">
							<input type="button" value=" - " name="minus">
						</td>
					<tr>
					<tr>
						<!-- 구매수량이 바뀔떄마다 가격도 바뀌게 -->
						<td>가격</td>
						<!-- readonly 사용하면 자동으로 값 변경도 안되는지?  -->
						<td><input type="number" name="goodsPrice" value="${m.goodsPrice}" readonly="readonly"></td>
					<tr>
					<tr>
						<!-- 장바구니에 담긴 상품 삭제하는 버튼 만들기, 상품을 여러게 담았을경우 각 상품마다 X표시 혹은 삭제버튼 따로따로 나오게  -->
						<!-- 이렇게 하는게 맞는지? 더 좋은방법이 있으면 바꿀예정 -->
						<td colspan="2"><a href='${pageContext.request.contextPath}/RemoveCartList?goodsCode=${m.goodsCode}'>장바구니에서 삭제</a></td>
					</tr>
					<!-- 맨 아래 혹은 옆에 총 가격도 나오게 해야함..  -->
				</table>
			</c:if>	
		</c:forEach>	
		<!-- 결제 페이지 이동 버튼 만들기, 어떤값들을 보내야 할지..?-->
		<button type="submit">구매하기</button>
	</form>
	<a href='${pageContext.request.contextPath}/home'>홈</a>
</body>
</html>