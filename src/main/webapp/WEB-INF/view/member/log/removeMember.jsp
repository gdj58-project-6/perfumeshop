<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 탈퇴</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<style>
			.po {
				position: relative;
				left: 190px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<div class="container">
			<div class="bor10 m-t-50 p-t-43 p-b-40 col-sm-8 m-lr-auto">
				<div class="po row">
					<div class="col-sm-10 col-md-8 col-lg-10 m-lr-auto">
						<div class="p-b-30 m-lr-15-sm">
							<form action="${pageContext.request.contextPath}/member/removeMember" method="post">
								<h5 class="mtext-108 cl2 p-b-7">회원 탈퇴</h5>
								<div class="row p-b-25">
									<div class="col-sm-6 p-b-5">
										<label class="stext-102 cl3" for="customerId">ID</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" type="text" id="customerId" name="customerId" value="${loginMember.customerId}" readonly="readonly">
										</div>
									</div>
								</div>
								<div class="row p-b-25">
									<div class="col-sm-6 p-b-5" >
										<label class="stext-102 cl3" for="customerPw">Password</label>
										<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="customerPw" type="password" name="customerPw">
									</div>
								</div>
								<div class="row p-b-25">
									<div class="col-sm-6 p-b-5" >
										<label class="stext-102 cl3" for="ckPw">Check Password</label>
										<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="ckPw" type="password" name="ckPw">
									</div>
								</div>
								<div class="row p-b-25">
									<div class="col-sm-6 p-b-5">
										${msg}
									</div>
								</div>
								<div>
									<button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10">
										탈퇴
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>	
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/daterangepicker/moment.min.js"></script>
		<script src="../vendor/slick/slick.min.js"></script>
		<script src="../js/slick-custom.js"></script>
		<script src="../vendor/parallax100/parallax100.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/isotope/isotope.pkgd.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>