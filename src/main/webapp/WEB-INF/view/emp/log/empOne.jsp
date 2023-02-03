<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<body>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<div class="container p-t-60">
			<div class="bor10 m-t-50 p-t-43 p-b-40 col-sm-8 m-lr-auto">
				<div class="row">
					<div class="col-sm-10 col-lg-7 col-xl-4 m-lr-auto m-b-30">
						<h4 class="mtext-109 cl2 p-b-30 text-center">관리자 정보</h4>
						<div class="flex-w flex-t p-t-5 p-b-30">
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> ID </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${empOne.empId}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Name </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${empOne.empName}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Grade </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${empOne.authCode}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> createdate </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${empOne.createdate}</p>
							</div>
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