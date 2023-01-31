<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>sign up</title>
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
		<c:if test="${loginMember == null}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<div class="container">
			<!-- 비로그인 -->
			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<div class="tab-pane fade show active" id="memberLogin" role="tabpanel">
					<form action="${pageContext.request.contextPath}/member/addMember" method="post">
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
									<div class="row p-b-20">
										<div class="col-12 p-b-5">
											<label class="stext-102 cl3" for="email">Phone</label>
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="phone1" type="password" name="phone">
										</div>
									</div>
									<div class="row p-b-20">
										<div class="col-12 p-b-5">
											${msg}
										</div>
									</div>
								</div>
							</div>
							<div class="col-5">
								<div class="p-b-30 m-l-200">
									<div class="row p-t-83 p-b-20">
										<div class="col-sm-12 p-b-5">
											<label class="stext-102 cl3" for="address">Address</label>
											<div align="right">
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="address" type="text" name="address">
											</div>
										</div>
									</div>
									<div class="row p-b-20">
										<div class="col-12 p-b-5">
											<label class="stext-102 cl3" for="pw">Password</label>
											<div align="right">
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw" type="password" name="pw">
											</div>
										</div>
									</div>
									<div class="row p-b-20">
										<div class="col-12 p-b-5">
											<label class="stext-102 cl3" for="pw2">Check Password</label>
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw2" type="password">
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
		</div>
	</body>
</html>