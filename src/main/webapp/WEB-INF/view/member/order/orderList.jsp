<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Order List</title>
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
		<script>
			$(document).ready(function() {
				$('#orderState').change(function() {
					$('#orderStateForm').submit();
				})
				
				$('#sort').change(function() {
					$('#orderStateForm').submit();
				})
			})
		</script>
		<style>
			table, th, td {
				text-align: center;
			}
			
			a, a:hover, a:visited {
				color: #234200;
			}
			
			#flex {
				width: 30px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<div class="container">
			<div class="flex-w flex-sb-m">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
				</div>
				<form action="${pageContext.request.contextPath}/member/orderList" method="get" id="orderStateForm">
					<div class="flex-w flex-c-m m-tb-10">
						<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
							<select name="stateSearch" id="orderState" class="js-select2">
								<option value="" <c:out value="${state == '' ? 'selected':'' }"/>>모두</option>
								<option value="결제" <c:out value="${state == '결제' ? 'selected':'' }"/>>결제</option>
								<option value="취소" <c:out value="${state == '취소' ? 'selected':'' }"/>>취소</option>
								<option value="반품신청" <c:out value="${state == '반품신청' ? 'selected':'' }"/>>반품신청</option>
								<option value="반품완료" <c:out value="${state == '반품완료' ? 'selected':'' }"/>>반품완료</option>
								<option value="배송전" <c:out value="${state == '배송전' ? 'selected':'' }"/>>배송전</option>
								<option value="배송중" <c:out value="${state == '배송중' ? 'selected':'' }"/>>배송중</option>
								<option value="배송완료" <c:out value="${state == '배송완료' ? 'selected':'' }"/>>배송완료</option>
								<option value="구매확정" <c:out value="${state == '구매확정' ? 'selected':'' }"/>>구매확정</option>
							</select>
							<div class="dropDownSelect2"></div>
						</div>
						<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
						<select name="sort" id="sort" class="js-select2">
							<option value="ASC" <c:out value="${sort == 'ASC' ? 'selected':'' }"/>>오래된순</option>
							<option value="DESC" <c:out value="${sort == 'DESC' ? 'selected':'' }"/>>최근결제순</option>
						</select>
						<div class="dropDownSelect2"></div>
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
									<th style="width: 700px;" colspan="2">상품</th>
									<th style="width: 200px;">주문 총 가격</th>
									<th style="width: 200px;">주문상태</th>
									<th style="width: 200px;">주문일자</th>
									<th style="width: 200px;"></th>
								</tr>
								<c:forEach var="o" items="${list}">
									<tr class="table_row">
										<td style="width: 250px;">
											<img src="${pageContext.request.contextPath}/upload/${o.filename}" width="150" height="150">
										</td>
										<td style="width: 450px;">
											<a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${o.orderCode}">
												<c:if test="${o.cnt eq 0}">
													${o.goodsName}
												</c:if>
												<c:if test="${o.cnt > 0}">
													${o.goodsName} &nbsp; 외 &nbsp; ${o.cnt}건
												</c:if>
											</a>
										</td>
										<td style="width: 200px;">
											<fmt:formatNumber value="${o.orderPrice}" pattern="###,###,###" />
										</td>
										<td style="width: 200px;">
											${o.orderState}
										</td>
										<td style="width: 300px;">${fn:substring(o.createdate, 0, 10)}</td>
										<td style="width: 200px;">
											<c:if test="${o.orderState eq '배송중' || o.orderState eq '배송완료'}">
												<a href="${pageContext.request.contextPath}/member/addReturnByOrder?orderCode=${o.orderCode}" class="flex-c-m stext-101 cl2 size-102 bg0 bor13 hov-btn1 p-lr-15 trans-04 pointer" id="flex">반품신청</a><br>
											</c:if>
											<c:if test="${o.orderState eq '배송완료'}">
												<a href="${pageContext.request.contextPath}/member/modifyOrderState?orderCode=${o.orderCode}" class="flex-c-m stext-101 cl2 size-102 bg0 bor13 hov-btn1 p-lr-15 trans-04 pointer" id="flex">구매확정</a>
											</c:if>
										</td>
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