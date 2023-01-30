<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>notice</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="../vendor/slick/slick.css">
		<link rel="stylesheet" type="text/css" href="../vendor/MagnificPopup/magnific-popup.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<!-- 고객or비로그인 -->
		<c:if test="${loginMember == null || loginMember.getAuthCode() < 4}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		
		<!-- 공지사항 -->
		<div class="container">
			<div class="row">
				<!-- 공지목록 -->
				<div class="col-md-10 col-lg-10 p-b-190 p-t-55 mx-auto">
					<h1 class="mtext-109 cl2 p-b-10">공지사항</h1>
					<table class="table text-center stext-110 cl2">
						<tr>
							<th class="text-center" style="width:15%;">번호</th>
							<th class="text-center w-50">제목</th>
							<th class="text-center" style="width:15%;">작성자</th>
							<th class="text-center" style="width:20%;">작성일</th>
						</tr>
						<c:forEach var="m" items="${list}">
							<tr>
								<td>${m.noticeCode}</td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/noticeOne?noticeCode=${m.noticeCode}">${m.noticeTitle}</a>
								</td>
								<td>${m.empId}</td>
								<td>${m.createdate}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 공지사항 페이징 -->
					<div>
						<!-- 
						<c:if test="${currentPage != 1}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=1">처음</a>				
						</c:if>
						 -->
						<c:if test="${currentPage > 1}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${currentPage-1}">이전</a>
						</c:if>
						${currentPage}
						<c:if test="${currentPage < lastPage}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${currentPage+1}">다음</a>
						</c:if>
						<!-- 
						<c:if test="${currentPage != lastPage}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${lastPage}">마지막</a>
						</c:if>
						 -->
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<footer class="bg3 p-t-75 p-b-32">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-lg-3 p-b-50">
						<h4 class="stext-301 cl0 p-b-30">
							GitHub
						</h4>
						<ul>
							<li class="p-b-10">
								<a href="https://github.com/rungmin2" class="stext-107 cl7 hov-cl1 trans-04">
									Seongmin
								</a>
							</li>
							<li class="p-b-10">
								<a href="https://github.com/eunseoa" class="stext-107 cl7 hov-cl1 trans-04">
									Eunseo
								</a>
							</li>
							<li class="p-b-10">
								<a href="https://github.com/ansankjh" class="stext-107 cl7 hov-cl1 trans-04">
									Jihoon
								</a>
							</li>
						</ul>
					</div>
	
					<div class="col-sm-6 col-lg-3 p-b-50">
						<h4 class="stext-301 cl0 p-b-30">
							Help
						</h4>
	
						<ul>
							<li class="p-b-10">
								<a href="${pageContext.request.contextPath}/admin/notice" class="stext-107 cl7 hov-cl1 trans-04">
									Notice
								</a>
							</li>
						</ul>
					</div>
	
					<div class="col-sm-6 col-lg-3 p-b-50">
						<h4 class="stext-301 cl0 p-b-30">
							GET IN TOUCH
						</h4>
	
						<p class="stext-107 cl7 size-201">
							Any questions? Let us know in store at 11th floor,  Gasan digital 2-ro, Geumcheon-gu, Seoul, call us on 02 716 6879
						</p>
	
						<div class="p-t-27">
							<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
								<i class="fa fa-facebook"></i>
							</a>
	
							<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
								<i class="fa fa-instagram"></i>
							</a>
	
							<a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
								<i class="fa fa-pinterest-p"></i>
							</a>
						</div>
					</div>
	
					<div class="col-sm-6 col-lg-3 p-b-50">
						<h4 class="stext-301 cl0 p-b-30">
							Newsletter
						</h4>
	
						<form>
							<div class="wrap-input1 w-full p-b-4">
								<input class="input1 bg-none plh1 stext-107 cl7" type="text" name="email" placeholder="gdj58@perfumeshop.com">
								<div class="focus-input1 trans-04"></div>
							</div>
	
							<div class="p-t-18">
								<button class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
									Subscribe
								</button>
							</div>
						</form>
					</div>
				</div>
	
				<div class="p-t-40">
					<div class="flex-c-m flex-w p-b-18">
						<a href="#" class="m-all-1">
							<img src="images/icons/icon-pay-01.png" alt="ICON-PAY">
						</a>
	
						<a href="#" class="m-all-1">
							<img src="images/icons/icon-pay-02.png" alt="ICON-PAY">
						</a>
	
						<a href="#" class="m-all-1">
							<img src="images/icons/icon-pay-03.png" alt="ICON-PAY">
						</a>
	
						<a href="#" class="m-all-1">
							<img src="images/icons/icon-pay-04.png" alt="ICON-PAY">
						</a>
	
						<a href="#" class="m-all-1">
							<img src="images/icons/icon-pay-05.png" alt="ICON-PAY">
						</a>
					</div>
	
					<p class="stext-107 cl6 txt-center">
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
		Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">GDJ58 Hot6</a>
		<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	
					</p>
				</div>
			</div>
		</footer>
	</body>
</html>