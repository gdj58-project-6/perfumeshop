<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>order List</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
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
		<h3>년도별 누적 접속자 수</h3>
		<div class="bg0 p-t-2 p-b-2">
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
									<tr class="table_row">
										<td>${y.counterDate}</td>
										<td>${y.counterNum}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h3>월별 누적 접속자 수</h3>
		<div class="bg0 p-t-2 p-b-2">
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
									<tr class="table_row">
										<td>${m.counterDate}</td>
										<td>${m.counterNum}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h3>일자별 누적 접속자 수</h3>
		<div class="bg0 p-t-2 p-b-2">
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
									<tr class="table_row">
										<td>${d.counterDate}</td>
										<td>${d.counterNum}</td>
									</tr>
								</c:forEach>
							</table>
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
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>