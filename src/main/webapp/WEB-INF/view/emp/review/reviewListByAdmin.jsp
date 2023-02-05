<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Review List</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="/inc/homeEmpMenu.jsp"></jsp:include>
		<div class="bg0 p-t-80 p-b-2">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 m-lr-auto m-b-50">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th>상품</th>
									<th>상품 이름</th>
									<th>작성자</th>
									<th>리뷰 내용</th>
									<th>적립 포인트</th>
									<th>작성일자</th>
									<th>삭제</th>
								</tr>
								<c:forEach var="r" items="${reviewList}">
									<tr>
										<td><img src="${pageContext.request.contextPath}/upload/${r.filename}" width="200" height="200"></td>
										<td>${r.goodsName}</td>
										<td>${r.customerId}</td>
										<td>${r.reviewMemo}</td>
										<td>${r.point}</td>
										<td>${r.createdate}</td>
										<td><a href="${pageContext.request.contextPath}/member/removeRiview?orderCode=${r.orderCode}&goodsCode=${r.goodsCode}&point=${r.point}&id=${r.customerId}">리뷰 삭제</a></td>
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