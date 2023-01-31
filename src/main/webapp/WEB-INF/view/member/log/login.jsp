<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
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
		<div class="container">
			<!-- 비로그인 -->
			<c:if test="${loginMember == null}">
				<jsp:include page="/inc/menu.jsp"></jsp:include>
			</c:if>
			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<div class="tab01">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							<a class="nav-link active" data-toggle="tab" href="#memberLogin" role="tab">고객 로그인</a>
						</li>
	
						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#empLogin" role="tab">직원 로그인</a>
						</li>
					</ul>
					<div class="tab-content p-t-43">
						<!-- 고객 로그인 -->
						<div class="tab-pane fade show active" id="memberLogin" role="tabpanel">
							<div class="row">
								<div class="col-sm-8 col-md-8 col-lg-6 col-sm-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										<form class="w-full" action="${pageContext.request.contextPath}/member/login" method="post">
											<h5 class="mtext-108 cl2 p-b-7">고객 로그인</h5>
											<div class="row p-b-25">
												<div class="col-sm-6 p-b-5">
													<label class="stext-102 cl3" for="customerId">ID</label>
													<div align="right">
														<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="customerId" type="text" name="customerId">
													</div>
												</div>
											</div>
											<div class="row p-b-25">
												<div class="col-sm-6 p-b-5" >
													<label class="stext-102 cl3" for="email">Password</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="customerPw" type="password" name="customerPw">
												</div>
											</div>
											<div class="row p-b-25">
												<div class="col-sm-6 p-b-5">
													${msg}
												</div>
											</div>
											<div class="row p-b-10">
												<div class="col-sm-6 p-b-5">
													<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10">
														로그인
													</button>
												</div>
												<div class="col-sm-6 p-b-5">
													<a href="${pageContext.request.contextPath}/member/addMember" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">회원가입</a>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 직원 로그인 -->
						<div class="tab-pane fade" id="empLogin" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										<form class="w-full" action="${pageContext.request.contextPath}/member/login" method="post">
											<h5 class="mtext-108 cl2 p-b-7">직원 로그인</h5>
											<div class="row p-b-25">
												<div class="col-sm-6 p-b-5">
													<label class="stext-102 cl3" for="customerId">ID</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="empId" type="text" name="empId">
												</div>
											</div>
											<div class="row p-b-25">
												<div class="col-sm-6 p-b-5">
													<label class="stext-102 cl3" for="email">Password</label>
													<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="empPw" type="password" name="empPw">
												</div>
											</div>
											<div class="row p-b-25">
												${msg}
											</div>
											<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
												Submit
											</button>
											<a href="${pageContext.request.contextPath}/admin/addAdmin">회원가입</a>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/animsition/js/animsition.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/daterangepicker/moment.min.js"></script>
		<script src="../vendor/daterangepicker/daterangepicker.js"></script>
		<script src="../vendor/slick/slick.min.js"></script>
		<script src="../js/slick-custom.js"></script>
		<script src="../vendor/parallax100/parallax100.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/isotope/isotope.pkgd.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>