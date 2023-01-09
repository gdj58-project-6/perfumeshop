<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goodsOne</title>
</head>
<body>
	<!-- 맨위에 include  -->
	<h3>goodsOne</h3>
	<!-- 가격, 수량조절, 장바구니로 갈지 결제창으로 갈지, 제품소개 등등.... -->
	<c:forEach var="m" items="${list}">
		<form action="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}" method="post" id="goodsOne">
			<table border="1">
				<tr>
					<td colspan="2">
						<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200">
					</td>
				</tr>
				<tr>
					<td>상품 이름</td>
					<td>${m.goodsName}</td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td>${m.goodsPrice}</td>
				</tr>
				<tr>
				<!--  수량이 변경되면 상품가격이 변경되야 함 -->
					<td>수량</td>
					<td><input type="number" value="1" name="cartQuantity"></td>
				</tr>
				<!-- 제품설명? 개별적인 설명이 나오게 하려면 어떻게?? -->
				<tr>
					<td>제품 설명</td>
					<td>대중적인 향으로 모두에게 어필할 수 있는 매력적인 향수입니다.</td>
				</tr>
				<!-- 또 추가할게 있으면 추가 -->
			</table>
			<!-- customerId값 히든으로 넘김 -->
			<input type="hidden" name="customerId" value="${loginMember.customerId}">
			<!-- 나중에 장바구니 버튼 누르면 장바구니에 담겼다는 창 한개 띄우기 -->
			<button type="submit" id="cartAddBtn">장바구니 담기</button>
		</form>	
		<!-- 장바구니, 결제창 이동-->
		<a href='${pageContext.request.contextPath}/member/cart'>장바구니로 이동</a>
		<a href='${pageContext.request.contextPath}/member/goodsPayMent'>바로 구매</a>
	</c:forEach>
	<script>
	// 장바구니 담기 버튼 누르면 성공했다는 창 하나 나오게
	let cartAddBtn = document.querySelector('#cartAddBtn');
	
	cartAddBtn.addEventListener('click', function(){
		// console.log('장바구니 추가 클릭!');
		alert('장바구니 담기 성공!');
	});
	</script>
	<a href='${pageContext.request.contextPath}/member/goodsList'>뒤로</a>
</body>
</html>