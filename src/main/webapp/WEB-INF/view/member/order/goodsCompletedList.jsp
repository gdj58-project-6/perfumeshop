<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Order Completed</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<style>
			table, th, td {
				text-align: center;
			}
			
			a, a:hover, a:visited {
				color: #234200;
			}
			
		</style>
	</head>
	<body>
		<jsp:include page="/inc/homeMenu.jsp"></jsp:include>
		<div class="container m-t-20 p-t-80">
			<div class="row">
				<div class="col-sm-10 col-lg-7 col-xl-12 m-lr-auto m-b-30">
					<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-45 m-r-10 m-lr-0-xl p-lr-15-sm" style="text-align: center;">
						<h2 class="mtext-139 cl2 p-b-30" style="text-align: center;">주문이 완료되었습니다</h2>
						<div class="flex-t bor12 p-t-5 p-b-20">
							<c:forEach var="g" items="${list}">
								<div class="w-25 p-t-10">
									<img src="${pageContext.request.contextPath}/upload/${g.filename}" width="100" height="100">
								</div>
								<div class="w-25 p-t-10">
									<span class="stext-110 cl2"><a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a> </span>
								</div>
								<div class="w-25 p-t-10">
									<span class="stext-110 cl2"><fmt:formatNumber value="${g.orderGoodsPrice * g.orderGoodsQuantity}" pattern="###,###,###" /></span>
									<c:set var="totalPrice"  value="${g.orderPrice}" ></c:set>
								</div>
							</c:forEach>
						</div>
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> Total Price </span>
							</div>
							<div class="size-208 m-l-20">
								<span class="mtext-110 cl2"> <fmt:formatNumber value="${totalPrice}" pattern="###,###,###" /> </span>
							</div>
						</div>
						<div class="flex-t p-t-5 p-b-20">
							<div class="w-50 p-t-10 m-l-20 m-r-20">
								<a href="${pageContext.request.contextPath}/member/goodsList" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn1 p-lr-15 trans-04 pointer" style="color: white;">쇼핑 계속하기</a>
							</div>
							<div class="w-50 p-t-10 m-l-20 m-r-20">
								<a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${orderCode}" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn1 p-lr-15 trans-04 pointer" style="color: white;">주문 상세내역</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/animsition/js/animsition.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>