<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>

		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">

		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">

		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">

		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">

		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">

		<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">

		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">

		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">

		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
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
				
				let loginBtn = document.querySelector('#loginBtn');
				
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
						orderQuantity[i].value = quantity;
						totalPrice[i].textContent = parseInt(goodsPrice[i].textContent) * (quantity + 1);
						modifyCartList.submit();
					
				});
					minusBtn[i].addEventListener('click',function() {
						let quantity = parseInt(document.querySelectorAll('.orderQuantity')[i].value);
						if (orderQuantity[i].value > 1) {
							orderQuantity[i].value = quantity;
							totalPrice[i].textContent = parseInt(goodsPrice[i].textContent) * (quantity - 1);
							modifyCartList.submit();
							
						}
					});
				}
			
						
			});
		</script>
	</head>
	<body class="animsition">
		<!-- Header -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<!-- breadcrumb -->
		<div class="container" style="margin-top:70px;">
			<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
				<a href="${pageContext.request.contextPath}/home" class="stext-109 cl8 hov-cl1 trans-04">
					Home
					<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
				</a>
	
				<span class="stext-109 cl4">
					Shoping Cart
				</span>
			</div>
		</div>
		<!-- Shoping Cart -->
		<form action="${pageContext.request.contextPath}/member/ModifyCartList" method="post" id="modifyCartList" class="bg0 p-t-75 p-b-85">	
			<c:forEach var="m" items="${list}">	
				<div class="container">
					<div class="row">
						<c:set var="totalPrice" value="${totalPrice + (m.goodsPrice * m.cartQuantity)}"></c:set>
						<input type="hidden" name="goodsCode" value="${m.goodsCode}">
						<input type="hidden" name="totalPrice" class="totalPrice" value="${totalPrice}">
						<input type="hidden" name="customerId" value="${m.customerId}">
						<input type="hidden" name="goodsName" value="${m.goodsName}">
						<input type="hidden" name="goodsPrice" class="goodsPrice" value="${m.goodsPrice*m.cartQuantity}">
						<div class="col-lg-10 col-xl-7 m-lr-auto m-b-50">
							<div class="m-l-25 m-r--38 m-lr-0-xl">
								<div class="wrap-table-shopping-cart">
									<table class="table-shopping-cart">
										<tr class="table_head">
											<th class="column-1">상품이름</th>
											<th class="column-2"></th>
											<th class="column-3">상품가격</th>
											<th class="column-4">구매수량</th>
											<th class="column-5">총가격</th>
										</tr>
		
										<tr class="table_row">
											<td class="column-1">
												<div class="how">
													<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="100" height="100">
												</div>
											</td>
											<td class="column-2">${m.goodsName}</td>
											<td class="column-3">${m.goodsPrice}원</td>
											<c:if test="${loginCustomer != null }">
												<td class="column-4">
													<div class="wrap-num-product flex-w m-l-auto m-r-0">
														<input type="button" value=" - " name="minus" class="minusBtn btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m"> 
														<input type="text" name="orderQuantity" class="orderQuantity mtext-104 cl3 txt-center num-product" value="${m.cartQuantity}" min="1" readonly="readonly"> 
														<input type="button" value=" + " name="plus" class="plusBtn btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
													</div>
												</td>			
											</c:if>
											<c:if test="${loginCustomer == null}">
												<td class="column-4">
													<div class="wrap-num-product flex-w m-l-auto m-r-0">
														<input type="button" value=" - " name="minus2" class="minusBtn2 btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m"> 
														<input type="text" name="orderQuantity" class="orderQuantity mtext-104 cl3 txt-center num-product" value="${m.cartQuantity}" min="1" readonly="readonly"> 
														<input type="button" value=" + " name="plus2" class="plusBtn2 btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
													</div>
												</td>
											</c:if>						
											<td class="column-5">${m.goodsPrice*m.cartQuantity}원</td>
										</tr>
									</table>
								</div>
								<div class="flex-w flex-sb-m bor15 p-t-18 p-b-15 p-lr-40 p-lr-15-sm">
									<c:if test="${loginCustomer != null }">
										<div class="flex-c-m">	
											<a href='${pageContext.request.contextPath}/member/RemoveCartList?goodsCode=${m.goodsCode}' class="stext-101 cl2 size-50 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-10">장바구니 삭제</a>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>		
			</c:forEach>
		</form>
		<div class="row">
			<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-50">
				<div class="bor10 p-lr-40 p-t-30 p-b-40 m-l-63 m-r-40 m-lr-0-xl p-lr-15-sm">
					<h4 class="mtext-109 cl2 p-b-30">
						Cart Totals
					</h4>	
					<c:if test="${list.size() == 0 || list.size() == null}">
						<div class="size-300">
							<span class="mtext-101 cl2">
								장바구니가 비었습니다
							</span>
							<div>
								<a class="stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5" href='${pageContext.request.contextPath}/member/goodsList'>쇼핑하러 가기</a>
							</div>
						</div>
					</c:if>	
					<div class="flex-w flex-t p-t-27 p-b-33">
						<div class="size-208">
							<span class="mtext-101 cl2">
								Total:
							</span>
						</div>

						<div class="size-209 p-t-1">
							<span class="mtext-110 cl2">
								${totalPrice}원
							</span>
						</div>
					</div>
					<div class="m-b-15">
						<c:if test="${loginCustomer != null }">	
							<a href='${pageContext.request.contextPath}/member/RemoveAllCartList?customerId=${loginMember.getCustomerId()}' class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">장바구니 전부 비우기</a>
						</c:if>
					</div>
					<div class="m-b-15">
						<c:if test="${loginCustomer == null}">
							<a href='${pageContext.request.contextPath}/member/RemoveAllCartList' class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">장바구니 전부 비우기</a>
						</c:if>
					</div>
					<div class="m-b-15">
						<c:if test="${loginCustomer != null && list.size() != 0}">
							<form action="${pageContext.request.contextPath}/member/goodsPayMentCart" method="get">
								<button type="submit" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">구매하기</button>
							</form>
						</c:if>		
					</div>
					<div class="m-b-15">
						<c:if test="${loginCustomer == null }">
							<a class="stext-101 cl2 size-118 bg8 bor13 hov-btn3 p-lr-15 trans-04 pointer m-tb-5" href='${pageContext.request.contextPath}/member/login'>로그인 후 구매</a>	
						</c:if>
					</div>	
				</div>
			</div>
		</div>	
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
		
		<!-- Back to top -->
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top">
				<i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	
	<!--===============================================================================================-->	
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/select2/select2.min.js"></script>
		<script>
			$(".js-select2").each(function(){
				$(this).select2({
					minimumResultsForSearch: 20,
					dropdownParent: $(this).next('.dropDownSelect2')
				});
			})
		</script>
	<!--===============================================================================================-->
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script>
			$('.js-pscroll').each(function(){
				$(this).css('position','relative');
				$(this).css('overflow','hidden');
				var ps = new PerfectScrollbar(this, {
					wheelSpeed: 1,
					scrollingThreshold: 1000,
					wheelPropagation: false,
				});
	
				$(window).on('resize', function(){
					ps.update();
				})
			});
		</script>
	<!--===============================================================================================-->
		<script src="../js/main.js"></script>
	</body>
</html>