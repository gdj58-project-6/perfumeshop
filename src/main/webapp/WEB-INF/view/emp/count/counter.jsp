<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>order List</title>
		<link rel="icon" type="image/png" href="./images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="./vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="./fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="./fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="./fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="./vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="./vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="./css/util.css">
		<link rel="stylesheet" type="text/css" href="./css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<c:forEach var="o" items="${list}">
			<script>
				$(document).ready(function() {
					$('#orderState${o.orderCode}').change(function() {
						$('#orderStateForm${o.orderCode}').submit();
						alert('주문상태를 변경했습니다');
					})
				})
			</script>
		</c:forEach>
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
		<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		<div class="p-t-100">
			<div class="bg0 p-t-2 p-b-2">
				<div class="container p-t-20">
					<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
						<a href="${pageContext.request.contextPath}/counter" class="stext-109 cl8 hov-cl1 trans-04"> 누적 접속자 수 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
						<a class="stext-109 cl8 hov-cl1 trans-04"> 년도 </a>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 m-lr-auto m-b-50">
							<div class="wrap-table-shopping-cart">
								<table class="table-shopping-cart">
									<tr class="table_head">
										<th>일자</th>
										<th>누적 접속자 수</th>
									</tr>
									<c:forEach var="y" items="${yearList}">
										<tr class="table_row" style="height: 70px;">
											<td style="width: 200px;">${y.counterDate}</td>
											<td style="width: 400px;">${y.counterNum}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="bg0 p-t-2 p-b-2">
				<div class="container p-t-20">
					<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
						<a href="${pageContext.request.contextPath}/counter" class="stext-109 cl8 hov-cl1 trans-04"> 누적 접속자 수 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
						<a class="stext-109 cl8 hov-cl1 trans-04"> 월별 </a>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 m-lr-auto m-b-50">
							<div class="wrap-table-shopping-cart">
								<table class="table-shopping-cart">
									<tr class="table_head">
										<th>일자</th>
										<th>누적 접속자 수</th>
									</tr>
									<c:forEach var="m" items="${monthList}">
										<tr class="table_row" style="height: 70px;">
											<td style="width: 200px;">${m.counterDate}</td>
											<td style="width: 400px;">${m.counterNum}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="bg0 p-t-2 p-b-2">
				<div class="container p-t-20">
					<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
						<a href="${pageContext.request.contextPath}/counter" class="stext-109 cl8 hov-cl1 trans-04"> 누적 접속자 수 <i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i></a> 
						<a class="stext-109 cl8 hov-cl1 trans-04"> 일자별&nbsp;&nbsp;(최근 10개)</a>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-lg-12 m-lr-auto m-b-50">
							<div class="wrap-table-shopping-cart">
								<table class="table-shopping-cart">
									<tr class="table_head">
										<th>일자</th>
										<th>누적 접속자 수</th>
									</tr>
									<c:forEach var="d" items="${dayList}">
										<tr class="table_row" style="height: 70px;">
											<td style="width: 200px;">${d.counterDate}</td>
											<td style="width: 400px;">${d.counterNum}</td>
										</tr>
									</c:forEach>
								</table>
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
		<script src="../../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../../vendor/animsition/js/animsition.min.js"></script>
		<script src="../../vendor/bootstrap/js/popper.js"></script>
		<script src="../../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../../vendor/select2/select2.min.js"></script>
		<script src="../../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../../js/main.js"></script>
	</body>
</html>