<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Point List</title>
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
				height: 75px;
			}
		</style>
	</head>
	<body class="animsition">
		<div class="container">
			<div class="flex-w flex-sb-m">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
				</div>
				<!-- 현재 포인트 보여줌 -->
			</div>
		</div>
		<div class="bg0 p-t-2 p-b-2">
			<div class="container">
				<div class="row">
					<div class="col-lg-10 m-lr-auto m-b-50">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th style="width: 300px;">주문 코드</th>
									<th style="width: 300px;">포인트 내용</th>
									<th style="width: 300px;">포인트</th>
									<th style="width: 300px;">메모</th>
									<th style="width: 300px;">적립 일자</th>
								</tr>
								<c:forEach var="p" items="${list}">
									<tr class="table_row" style="height: 75px;">
										<td>${p.orderCode}</td>
										<td>${p.pointKind}</td>
										<td>${p.point}</td>
										<td>${p.memo}</td>
										<td>${p.createdate}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="flex-l-m flex-w w-full p-t-10 m-lr--7">
							<c:if test="${prev == true}">
								<a href="${pageContext.request.contextPath}/member/orderList?currentPage=${currentPage - 1}" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">
									&lt;
								</a>
							</c:if>
							<c:if test="${prev == false}">
								<a href="#" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">
									&lt;
								</a>
							</c:if>
							<c:forEach var="b" begin="${startPage}" end="${endPage}" step="1">
								<a href="${pageContext.request.contextPath}/member/orderList?currentPage=${b}" class="flex-c-m how-pagination1 trans-04 m-all-7">
									${b}
								</a>
							</c:forEach>
							<c:if test="${next == true}">
								<a href="${pageContext.request.contextPath}/member/orderList?currentPage=${currentPage + 1}" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">
									&gt;
								</a>
							</c:if>
							<c:if test="${next == false}">
								<a href="#" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">
									&gt;
								</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top">
				<i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/animsition/js/animsition.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/select2/select2.min.js"></script>
		<script>
			$(".js-select2").each(function(){
				$(this).select2({
					minimumResultsForSearch: 20,
					dropdownParent: $(this).next('.dropDownSelect2')
				});
			})
		</script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>