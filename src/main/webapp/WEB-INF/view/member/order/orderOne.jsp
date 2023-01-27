<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Order one</title>
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
			table, th, td {
				text-align: center;
			}
			
			td {
				height: 150px;
			}
			
			a, a:hover, a:visited {
				color: #234200;
			}
			
			.flex-c-m {
				width: 30px;
			}
		</style>
	</head>
	<body class="animsition">
		<div class="container">
			<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
				<a href="${pageContext.request.contextPath}/home" class="stext-109 cl8 hov-cl1 trans-04"> Home <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
				<a href="javascript:window.history.back();" class="stext-109 cl8 hov-cl1 trans-04"> 주문내역 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a>
				<span class="stext-109 cl4"> ${customerOne.orderCode} </span>
			</div>
		</div>
		<div class="container m-t-20">
			<div class="row">
				<div class="col-lg-10 col-xl-8 m-lr-auto m-b-50">
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th style="width: 550px;" colspan="2">상품</th>
									<th style="width: 100px;">상품 가격</th>
									<th style="width: 50px;">개수</th>
									<th style="width: 100px;">총 가격</th>
									<th style="width: 200px;"></th>
								</tr>
								<c:forEach var="g" items="${goodsList}">
									<tr>
										<td style="width: 150px;"><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="100" height="100"></td>
										<td style="width: 400px;"><a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a></td>
										<td style="width: 100px;"><fmt:formatNumber value="${g.orderGoodsPrice}" pattern="###,###,###" /></td>
										<td style="width: 50px;">${g.orderGoodsQuantity}</td>
										<td style="width: 100px;"><fmt:formatNumber value="${g.orderGoodsPrice * g.orderGoodsQuantity}" pattern="###,###,###" /></td>
										<c:set var="totalPrice"  value="${totalPrice + (g.orderGoodsPrice * g.orderGoodsQuantity)}" ></c:set>
										<td style="width: 200px;">
											<c:if test="${g.orderState ne '구매확정'}">
												${o.orderState}
											</c:if>
											<c:if test="${g.orderState eq '구매확정'}">
												<a href="${pageContext.request.contextPath}/member/addReview?orderCode=${g.orderCode}&goodsCode=${g.goodsCode}" class="flex-c-m stext-101 cl2 size-102 bg0 bor13 hov-btn1 p-lr-15 trans-04 pointer">리뷰쓰기</a>
											</c:if>
										</td>
									</tr>
									<!-- 취소 시 넘길 goodsCode -->
									<c:set var="goodsCode"  value="${g.goodsCode}" ></c:set>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-10 col-lg-7 col-xl-4 m-lr-auto m-b-30">
					<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-45 m-r-10 m-lr-0-xl p-lr-15-sm">
						<h4 class="mtext-109 cl2 p-b-30">주문자 정보</h4>
						<div class="flex-w flex-t bor12 p-t-5 p-b-30">
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> ID </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.customerId}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Name </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.customerName}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Phone </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">
									<c:set var="phone" value="${customerOne.customerPhone}"></c:set>
									${fn:substring(phone, 0, 3)}-${fn:substring(phone, 3, 7)}-${fn:substring(phone, 7, 11)}
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Address </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.address}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Memo </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.orderMemo}</p>
							</div>
						</div>
						<h4 class="mtext-109 cl2 p-t-20 p-b-30">주문 정보</h4>
						<div class="flex-w flex-t bor12 p-t-5 p-b-30">
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Order State </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.orderState}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Date </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.createdtae}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Use Point </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="stext-111 cl6 p-t-2">${customerOne.point}</p>
							</div>
						</div>
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2"> Total Price </span>
							</div>
							<div class="size-208 m-l-20">
								<span class="mtext-110 cl2"> <fmt:formatNumber value="${customerOne.orderPrice}" pattern="###,###,###" /> </span>
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
		<script src="../vendor/select2/select2.min.js"></script>
		<script>
			$(".js-select2").each(function() {
				$(this).select2({
					minimumResultsForSearch : 20,
					dropdownParent : $(this).next('.dropDownSelect2')
				});
			})
		</script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>