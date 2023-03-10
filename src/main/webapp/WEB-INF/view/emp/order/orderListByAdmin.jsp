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
		<div class="container p-t-80">
			<div class="flex-w flex-sb-m">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
				</div>
				<form action="${pageContext.request.contextPath}/admin/orderList" method="get">
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
									<th style="width: 650px;" colspan="2">상품 </th>
									<th style="width: 150px;">주문 고객</th>
									<th style="width: 150px;">총 가격</th>
									<th style="width: 200px;">주문 상태</th>
									<th style="width: 150px;">주문 일자</th>
								</tr>
								<c:forEach var="o" items="${list}">
									<tr>
										<td style="width: 250px;"><img src="${pageContext.request.contextPath}/upload/${o.filename}" width="200" height="200"></td>
										<td style="width: 400px;">
											<a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${o.orderCode}">
												<c:if test="${o.cnt eq 0}">
													${o.goodsName}
												</c:if>
												<c:if test="${o.cnt > 0}">
													${o.goodsName} &nbsp; 외 &nbsp; ${o.cnt}건
												</c:if>
											</a>
										</td>
										<td style="width: 150px;">${o.customerId}</td>
										<td style="width: 150px;"><fmt:formatNumber value="${o.orderPrice}" pattern="###,###,###" /></td>
										<td style="width: 150px;">
											<c:if test="${o.orderState ne '반품신청' && o.orderState ne '반품' && o.orderState ne '구매확정' && o.orderState ne '반품완료' && o.orderState ne '취소'}">
												<form action="${pageContext.request.contextPath}/admin/modifyOrderState?orderCode=${o.orderCode}" method="post" id="orderStateForm${o.orderCode}">
													<div class="rs1-select2 rs2-select2 bor8" style="width: 130px;">
														<select name="orderState" class="js-select2" id="orderState${o.orderCode}">
															<!-- orderState의 상태가 true이면 selected 속성 -->
															<option value="결제" <c:out value="${o.orderState == '결제' ? 'selected':'' }"/>>결제</option>
															<option value="취소" <c:out value="${o.orderState == '취소' ? 'selected':'' }"/>>취소</option>
															<option value="배송전" <c:out value="${o.orderState == '배송전' ? 'selected':'' }"/>>배송전</option>
															<option value="배송중" <c:out value="${o.orderState == '배송중' ? 'selected':'' }"/>>배송중</option>
															<option value="배송완료" <c:out value="${o.orderState == '배송완료' ? 'selected':'' }"/>>배송완료</option>
														</select>	
														<div class="dropDownSelect2"></div>
													</div>
												</form>
											</c:if>
											<c:if test="${o.orderState eq '반품신청'}">
												<a href="${pageContext.request.contextPath}/admin/returnHistoryList">반품승인</a>
											</c:if>
											<c:if test="${o.orderState eq '반품' || o.orderState eq '구매확정' || o.orderState eq '취소' || o.orderState eq '반품완료'}">
												${o.orderState}
											</c:if>
										</td>
										<td style="width: 150px;">${fn:substring(o.createdate, 0, 10)}</td>
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