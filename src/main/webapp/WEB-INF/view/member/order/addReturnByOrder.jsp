<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<style>
			textarea {
				resize: none;
			}
			
			table, th, td {
				text-align: center;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
				<a href="${pageContext.request.contextPath}/home" class="stext-109 cl8 hov-cl1 trans-04"> Home <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
				<a href="javascript:window.history.back();" class="stext-109 cl8 hov-cl1 trans-04"> 주문내역 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a>
				<span class="stext-109 cl4"> 환불신청 </span>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/member/addReturnByOrder" method="post">
			<div class="container m-t-20">
				<div class="row">
					<div class="col-lg-10 m-lr-auto m-b-50">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th style="width: 700px;" colspan="2">상품</th>
									<th style="width: 200px;">price</th>
									<th style="width: 200px;">구매개수</th>
									<th style="width: 200px;">총 가격</th>
								</tr>
								<c:forEach var="o" items="${orderList}">
									<tr class="table_row">
										<td>
											<input type="hidden" name="orderCode" value="${o.orderCode}">
											<input type="hidden" name="id" value="${o.customerId}">
											<img src="${pageContext.request.contextPath}/upload/${o.filename}" width="100" height="100">
										</td>
										<td>${o.goodsName}</td>
										<td><fmt:formatNumber value="${o.orderGoodsPrice}" pattern="###,###,###" /></td>
										<td>${o.orderGoodsQuantity}</td>
										<td>
											<fmt:formatNumber value="${o.orderGoodsPrice * o.orderGoodsQuantity}" pattern="###,###,###" />
											<c:set var="totalPrice"  value="${totalPrice + (g.orderGoodsPrice * g.orderGoodsQuantity)}" ></c:set>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-10 col-lg-7 col-xl-10 m-lr-auto m-b-30">
						<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-10 m-r-10 m-lr-0-xl p-lr-15-sm" style="height: 300px;">
							<div class="row p-b-25">
								<div class="col-12 p-t-20 p-b-5">
									<label class="stext-102 cl3" for="review">Memo</label>
									<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="memo"></textarea>
								</div>
							</div>
							<div align="right">
								<button type="submit" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10">
									반품신청
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top"> <i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/animsition/js/animsition.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/daterangepicker/moment.min.js"></script>
		<script src="../vendor/daterangepicker/daterangepicker.js"></script>
		<script src="../vendor/slick/slick.min.js"></script>
		<script src="../js/slick-custom.js"></script>
		<script src="../vendor/parallax100/parallax100.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/isotope/isotope.pkgd.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>