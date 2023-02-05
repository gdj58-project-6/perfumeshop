<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addEmp</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<jsp:include page="/inc/homeEmpMenu.jsp"></jsp:include>
		<div class="container p-t-80">
			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<form action="${pageContext.request.contextPath}/admin/addAdmin" method="post">
					<div class="row">
						<div class="col-5">
							<div class="p-b-30 m-l-200">
								<h5 class="mtext-108 cl2 p-b-7">회원 가입</h5>
								<div class="row p-t-50 p-b-20">
									<div class="col-sm-12 p-b-5">
										<label class="stext-102 cl3" for="id">ID</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="id" type="text" name="id">
										</div>
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="name">Name</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-5">
							<div class="p-b-30 m-l-200">
								<div class="row p-t-83 p-b-20">
									<div class="col-sm-12 p-b-5">
										<label class="stext-102 cl3" for="pw">Password</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw" type="password" name="pw">
										</div>
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="pw2">Check Password</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw2" type="password" name="pw2">
										</div>
									</div>
								</div>
								<div class="row p-b-10">
									<div class="col-12 p-b-5">
										<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10">
											회원가입
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
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
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>