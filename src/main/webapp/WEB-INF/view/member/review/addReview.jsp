<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Review</title>
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
		</style>
	</head>
	<body>
		<div class="container">
			<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
				<a href="${pageContext.request.contextPath}/home" class="stext-109 cl8 hov-cl1 trans-04"> Home <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
				<a href="javascript:window.history.back();" class="stext-109 cl8 hov-cl1 trans-04"> 주문내역 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a>
				<span class="stext-109 cl4">Review </span>
			</div>
		</div>
		<div class="container m-t-20">
			<div class="row">
				<div class="col-sm-10 col-lg-7 col-xl-7 m-lr-auto m-b-30">
					<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-10 m-r-10 m-lr-0-xl p-lr-15-sm" style="height: 500px;">
						<div class="flex-w flex-t bor12 p-t-5 p-b-30">
							<div class="size-208 w-full-ssm p-t-10">
								<img src="${pageContext.request.contextPath}/upload/${orderOne.filename}" width="200" height="200" alt="${orderOne.goodsName}">
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-160">
								<span> ${orderOne.goodsName} </span>
							</div>
							<div class="size-208 w-full-ssm p-t-40">
								<span class="stext-110 cl2"> 상품 가격 </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-40">
								<p class="stext-111 cl6 p-t-2"><fmt:formatNumber value="${orderOne.orderGoodsPrice}" pattern="###,###,###" /></p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> 구매 개수 </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${orderOne.orderGoodsQuantity}</p>
							</div>
						</div>
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> Total Price </span>
							</div>
							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2"> <fmt:formatNumber value="${orderOne.orderGoodsPrice * orderOne.orderGoodsQuantity}" pattern="###,###,###" /> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-10 col-lg-7 col-xl-5 m-lr-auto m-b-30">
					<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-10 m-r-10 m-lr-0-xl p-lr-15-sm" style="height: 500px;">
						<form action="${pageContext.request.contextPath}/member/addReview?pointKind=적립" method="post">
							<div class="row p-b-25">
								<div class="col-12 p-t-20 p-b-5">
									<label class="stext-102 cl3" for="poinr">Point</label>
									<c:set var="point" value="${(orderOne.orderGoodsPrice * orderOne.orderGoodsQuantity) * 0.005}"></c:set>
									<input class="size-111 bor8 stext-102 cl2 p-lr-20" name="point" value="${Double.valueOf(point).intValue()}" readonly>
								</div>
								<div class="col-12 p-t-20 p-b-5">
									<label class="stext-102 cl3" for="id">ID</label>
									<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="" type="text" name="id" value="${orderOne.customerId}" readonly>
								</div>
								<div class="col-12 p-t-20 p-b-5">
									<label class="stext-102 cl3" for="review">Your review</label>
									<textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="reviewMemo"></textarea>
								</div>
							</div>
							<button type="submit" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
								Submit
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>