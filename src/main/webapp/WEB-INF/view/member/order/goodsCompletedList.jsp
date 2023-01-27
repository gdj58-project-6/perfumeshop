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
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body class="animsition">
		<div class="container m-t-20">
			<div class="row">
				<div class="col-sm-10 col-lg-7 col-xl-12 m-lr-auto m-b-30">
					<div class="bor10 p-lr-50 p-t-30 p-b-30 m-l-45 m-r-10 m-lr-0-xl p-lr-15-sm">
						<h2 class="mtext-139 cl2 p-b-30" style="text-align: center;">주문이 완료되었습니다</h2>
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
		<h1>주문완료</h1>
			<table>
				<c:forEach var="g" items="${list}">
					<tr>
						<td><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="100" height="100"></td>
						<td>상품 이름 : <a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a></td>
						<td>상품 가격 : ${g.orderGoodsPrice}</td>
						<td>상품 개수 : ${g.orderGoodsQuantity}</td>
						<td>상품 총 개수 : ${g.orderGoodsPrice * g.orderGoodsQuantity}</td>
						<c:set var="totalPrice"  value="${g.orderPrice}" ></c:set>
					</tr>
				</c:forEach>
				<tr>
					<td>주문 총 가격 : ${totalPrice}</td>
				</tr>
		</table>
	</body>
</html>