<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cartList</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				let orderQuantity = document.querySelectorAll('.orderQuantity');
				let plusBtn = document.querySelectorAll('.plusBtn');
				let minusBtn = document.querySelectorAll('.minusBtn');
				let goodsPrice = document.querySelectorAll('.goodsPrice');
				let totalPrice = document.querySelectorAll('.totalPrice');
				let modifyCartList = document.querySelector('#modifyCartList');
				
				let plusBtn2 = document.querySelectorAll('.plusBtn2');
				let minusBtn2 = document.querySelectorAll('.minusBtn2');
				
				// 비로그인 상태
				$('.plusBtn2').click(function() {
					 alert('수량변경은 로그인 후 하실 수 있습니다');
				})
				
				$('.minusBtn2').click(function() {
					 alert('수량변경은 로그인 후 하실 수 있습니다');
				})
			
				// console.log(orderQuantity.length);
		
				// 로그인 상태 
				for (let i = 0; i < orderQuantity.length; i++) {
					plusBtn[i].addEventListener('click',function() {
						let quantity = parseInt(document.querySelectorAll('.orderQuantity')[i].value);
						orderQuantity[i].value = quantity + 1;
						totalPrice[i].textContent = parseInt(goodsPrice[i].textContent) * (quantity + 1);
						modifyCartList.submit();
					
				});
					minusBtn[i].addEventListener('click',function() {
						let quantity = parseInt(document.querySelectorAll('.orderQuantity')[i].value);
						if (orderQuantity[i].value > 1) {
							orderQuantity[i].value = quantity - 1;
							totalPrice[i].textContent = parseInt(goodsPrice[i].textContent) * (quantity - 1);
							modifyCartList.submit();
							
						}
					});
				}
			
						
			});
		</script>
	</head>
	<body>
		<!-- include -->
		<a href='${pageContext.request.contextPath}/home'>홈</a>
		<div>
			<h3>장바구니</h3>
		</div>
		<div>
			<c:if test="${loginCustomer != null }">
				<span>구매자 ${loginCustomer.customerId}</span>
			</c:if>
		</div>	
		<div>
			<c:if test="${loginCustomer == null }">
				<span>구매하실려면 로그인을 해주세요</span>
			</c:if>
		</div>	
		<div>
			<c:if test="${list.size() == null}">
				<div>
					<img src="${pageContext.request.contextPath}/upload/장바구니X.png" width="300" height="300">
				</div>
				<a href='${pageContext.request.contextPath}/member/goodsList'>쇼핑하러 가기</a>
			</c:if>
		</div>
		<form action="${pageContext.request.contextPath}/member/ModifyCartList" method="post" id="modifyCartList">	
			<c:forEach var="m" items="${list}">	
				<!-- totalPrice 구하기 --> 
				<c:set var="totalPrice" value="${totalPrice + (m.goodsPrice * m.cartQuantity)}"></c:set>
					<input type="hidden" name="goodsCode" value="${m.goodsCode}">
					<input type="hidden" name="totalPrice" class="totalPrice" value="${totalPrice}">
					<input type="hidden" name="customerId" value="${m.customerId}">
					<table border="1">
						<tr>
							<!-- 이름위에 상품사진 나오게?  -->
							<td colspan="2">
								<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="100" height="100">
							</td>
						<tr>
							<td>상품이름</td>
							<td><input type="text" name="goodsName" value="${m.goodsName}" readonly="readonly"></td>
						<tr>
						<tr>
							<td>구매수량</td>
							<c:if test="${loginCustomer != null }">
								<td>
									<input type="button" value=" - " name="minus" class="minusBtn"> 
									<input type="text"name="orderQuantity" class="orderQuantity" value="${m.cartQuantity}" min="1" readonly="readonly"> 
									<input type="button" value=" + " name="plus" class="plusBtn">
								</td>
							</c:if>
							<c:if test="${loginCustomer == null}">
								<td>
									<input type="button" value=" - " name="minus2" class="minusBtn2"> 
									<input type="text"name="orderQuantity" class="orderQuantity" value="${m.cartQuantity}" min="1" readonly="readonly"> 
									<input type="button" value=" + " name="plus2" class="plusBtn2">
								</td>
							</c:if>
						<tr>
						<tr>
							<!-- 구매수량이 바뀔떄마다 가격도 바뀌게 -->
							<td>가격</td>
							<td>
								<input type="text" name="goodsPrice" class="goodsPrice" value="${m.goodsPrice*m.cartQuantity}"readonly="readonly">
							</td>
						<tr>
							<!-- 장바구니에 담긴 상품 삭제하는 버튼 만들기, 상품을 여러게 담았을경우 각 상품마다 X표시 혹은 삭제버튼 따로따로 나오게  -->
							<c:if test="${loginCustomer != null }">
								<td colspan="2">
									<a href='${pageContext.request.contextPath}/member/RemoveCartList?goodsCode=${m.goodsCode}'>장바구니에서삭제</a>
								</td>
							</c:if>
						</tr>
					</table>
			</c:forEach>
			<div>
				<span>총가격 :${totalPrice}원</span>
			</div>
		</form>	
		<div>
			<c:if test="${loginCustomer != null }">	
				<a href='${pageContext.request.contextPath}/member/RemoveAllCartList?customerId=${loginMember.getCustomerId()}'>장바구니 전부 비우기</a>
			</c:if>
		</div>
		<div>
			<c:if test="${loginCustomer == null}">
				<a href='${pageContext.request.contextPath}/member/RemoveAllCartList'>장바구니 전부 비우기</a>
			</c:if>
		</div>	
		<c:if test="${loginCustomer != null }">
			<form action="${pageContext.request.contextPath}/member/goodsPayMentCart" method="get">
			<button type="submit">구매하기</button>
			</form>
		</c:if>		
		<c:if test="${loginCustomer == null }">
			<a href='${pageContext.request.contextPath}/member/login'>로그인 후 구매</a>	
		</c:if>	
	</body>
</html>