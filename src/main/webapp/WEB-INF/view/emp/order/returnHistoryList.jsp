<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>반품 내역</title>
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
			
			a, a:hover, a:visited {
				color: #234200;
			}
		</style>
	</head>
	<body class="animsition">
		<div class="container">
			<div class="flex-w flex-sb-m">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
				</div>
				<form action="${pageContext.request.contextPath}/admin/returnHistoryList" id="returnHistory" method="get">
					<div class="flex-w flex-c-m m-tb-10">
						<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
							<select name="returnState" id="returnState" class="js-select2">
								<option value="" <c:out value="${state == '' ? 'selected':'' }"/>>승인여부</option>
								<option value="승인전" <c:out value="${state == '승인전' ? 'selected':'' }"/>>승인전</option>
								<option value="승인완료" <c:out value="${state == '승인완료' ? 'selected':'' }"/>>승인완료</option>
							</select>
							<div class="dropDownSelect2"></div>
						</div>
						<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
						<select name="sort" id="sort" class="js-select2">
							<option value="DESC" <c:out value="${sort == 'DESC' ? 'selected':'' }"/>>오래된순</option>
							<option value="ASC" <c:out value="${sort == 'ASC' ? 'selected':'' }"/>>최근결제순</option>
						</select>
						<div class="dropDownSelect2"></div>
						</div>
						<div class="bor17 of-hidden pos-relative">
							<input class="stext-103 cl2 plh4 size-116 p-l-28 p-r-55" type="text" name="customerId" placeholder="Search">
							<button class="flex-c-m size-122 ab-t-r fs-18 cl4 hov-cl1 trans-04" type="submit">
								<i class="zmdi zmdi-search"></i>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="bg0 p-t-2 p-b-2">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 m-lr-auto m-b-50">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th style="width: 100px;">주문코드</th>
									<th style="width: 200px;">주문 고객</th>
									<th style="width: 200px;">결제 금액</th>
									<th style="width: 200px;">주문 상태</th>
									<th style="width: 600px;">반품 사유</th>
									<th style="width: 300px;">반픔 승인 여부</th>
									<th style="width: 200px;">반품 신청 날짜</th>
								</tr>
								<c:forEach var="r" items="${list}">
									<tr class="table_row">
										<td>${r.orderCode}</td>
										<td>${r.customerId}</td>
										<td>${r.orderPrice}</td>
										<td>${r.orderState}</td>
										<td style="width: 500px;">${r.returnMemo}</td>
										<td>
											<c:if test="${r.returnState eq '승인전'}">					
												${r.returnState}<a class="btn" href="${pageContext.request.contextPath}/admin/modifyReturnByOrder?orderCode=${r.orderCode}">반품승인</a>
											</c:if>
											<c:if test="${r.returnState eq '승인완료'}">					
												${r.returnState}
											</c:if>
										</td>
										<td>${r.createdate}</td>
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