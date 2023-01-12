<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cartList</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	</head>
	<body>
		<!-- include -->
		<h3>장바구니</h3>
		<!-- 결제 페이지로 넘기기 -->
		<!-- goodsCode 넘길 필요없으면 삭제하기 -->
		<form action="${pageContext.request.contextPath}/member/goodsPayMent?goodsCode=${m.goodsCode}">
			<c:forEach var="m" items="${list}">
				<c:set var="totalPrice"  value="${totalPrice + (m.goodsPrice * m.cartQuantity)}" ></c:set> <!-- totalPrice 구하기 -->
				<!-- loginMember.customerId == m.customerId일 경우에만 장바구니에 담긴 상품을 볼 수 있음  -->
				<c:if test="${loginMember.customerId == m.customerId}">
					<table border="1">
						<tr>
							<td><!--장바구니가 비었을때--></td>
						</tr>
						<tr>
							<td>구매자</td>
							<td><input type="text" name="customerId" value="${m.customerId}" readonly="readonly"></td>
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
								<input type="button" value=" - " name="minus" id="minusBtn">
								<input type="text" name="orderQuantity" value="${m.cartQuantity}" id="quantity">
								<input type="button" value=" + " name="plus" id="plusBtn">
							</td>
						<tr>
						
						<tr>
							<!-- 구매수량이 바뀔떄마다 가격도 바뀌게 -->
							<td>가격</td>
							<!-- readonly 사용하면 자동으로 값 변경도 안되는지?  -->
							<td><input type="text" name="goodsPrice" id="goodsPrice" value="${m.goodsPrice*m.cartQuantity}" readonly="readonly"></td>
						<tr>
						<tr>
							<!-- 장바구니에 담긴 상품 삭제하는 버튼 만들기, 상품을 여러게 담았을경우 각 상품마다 X표시 혹은 삭제버튼 따로따로 나오게  -->
							<!-- 이렇게 하는게 맞는지? 더 좋은방법이 있으면 바꿀예정 -->
							<td colspan="2"><a href='${pageContext.request.contextPath}/member/RemoveCartList?goodsCode=${m.goodsCode}'>장바구니에서 삭제</a></td>
						</tr>
					</table>
					<!-- 맨 아래 혹은 옆에 총 가격도 나오게 해야함..  -->
					<div>
						총 가격 <input type="text" name="totalPrice" value="${totalPrice}">
					</div>	
				</c:if>	
			</c:forEach>
			<!-- 결제 페이지 이동 버튼 만들기, 어떤값들을 보내야 할지..?-->
			<button type="submit">구매하기</button>
		</form>
		<div>
		 	<a href='${pageContext.request.contextPath}/member/RemoveAllCartList?customerId=${loginMember.getCustomerId()}'>장바구니 전부 비우기</a>
		</div>	
		<a href='${pageContext.request.contextPath}/home'>홈</a>
		
		<script>
			// 수량 변경할시 금액 변경되는 js
			/*
			let goodsQuantity = document.querySelectorAll('#quantity');
			let goodsPrice = document.querySelectorAll('#goodsPrice');
			let minusBtn = document.querySelector('#minusBtn');
			let plusBtn = document.querySelectorAll('#plusBtn');
			console.log();
			// console.log(goodsPrice);
			minusBtn.addEventListener('click', function(){
				if(goodsQuantity.value > 1) {
					goodsQuantity.value == --goodsQuantity.value;
				}
			});
			*/
		</script>
	</body>
</html>