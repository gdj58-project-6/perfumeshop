<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyEmp</title>
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
				$('.js-select2').change(function() {
					$(this.form).submit();
					alert('직원 등급을 변경했습니다');
				})
			})
		</script>
		<style>
			table, th, td {
				text-align: center;
			}
			
			td {
				height: 60px;
			}
			
			a, a:hover, a:visited {
				color: #234200;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="flex-w flex-sb-m">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<h3>직원 등급 관리</h3>
				</div>
			</div>
		</div>
		<div class="bg0 p-t-2 p-b-2">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 m-lr-auto m-b-50">
						<div class="wrap-table-shopping-cart">
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th>empCode</th>
									<th>empId</th>
									<th>empName</th>
									<th>active</th>
									<th>authCode</th>
									<th>createdate</th>
									<th>퇴사직원</th>
								</tr>
								<c:forEach var="m" items="${list}">
									<tr>
										<td>${m.empCode}</td>
										<td>${m.empId}</td>
										<td>${m.empName}</td>
										<td>${m.active}</td>
										<td style="width: 150px;">
											<form action="${pageContext.request.contextPath}/admin/modifyByAdmin" method="post">
												<input type="hidden" name="empCode" value="${m.empCode}">
												<input type="hidden" name="empId" value="${m.empId}">
												<input type="hidden" name="currentPage" value="${currentPage}">
												<input type="hidden" name="rowPerPage" value="${rowPerPage}">
												<div class="rs1-select2 rs2-select2 bor8" style="width: 130px;">
													<select name="authCode" class="js-select2">
														<option value="4" <c:out value="${m.authCode == 4 ? 'selected':'' }"/>>4</option>
														<option value="5" <c:out value="${m.authCode == 5 ? 'selected':'' }"/>>5</option>
														<option value="6" <c:out value="${m.authCode == 6 ? 'selected':'' }"/>>6</option>
														<option value="7" <c:out value="${m.authCode == 7 ? 'selected':'' }"/>>7</option>
													</select>
													<div class="dropDownSelect2"></div>
												</div>
											</form>
										</td>
										<td>${m.createdate}</td>
										<td>
											<a href="${pageContext.request.contextPath}/admin/removeByAdmin?empId=${m.empId}">퇴사직원</a>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div class="flex-l-m flex-w w-full p-t-10 m-lr--7">
							<!-- 고객 리스트 페이징 버튼 -->
							<div>
								<c:if test="${currentPage != 1}">
									<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=1" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">처음</a>
								</c:if>
								<c:if test="${currentPage > 1}">
									<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${currentPage-1}" class="flex-c-m how-pagination1 trans-04 m-all-7">이전</a>
								</c:if>
								${currentPage}
								<c:if test="${currentPage < lastPage}">
									<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${currentPage+1}" class="flex-c-m how-pagination1 trans-04 m-all-7">다음</a>
								</c:if>
								<c:if test="${currentPage != lastPage}">
									<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${lastPage}" class="flex-c-m how-pagination1 trans-04 m-all-7 active-pagination1">마지막</a>
								</c:if>
							</div>
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