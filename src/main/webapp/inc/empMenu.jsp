<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<header>
	<!-- Header desktop -->
	<div class="container-menu-desktop">
		<!-- Topbar -->
		<div class="top-bar">
			<div class="content-topbar flex-sb-m h-full container">
				<div class="left-top-bar">
					GDJ58 Team : Hot6
				</div>

				<div class="right-top-bar flex-w h-full">
					<c:if test="${loginMember != null && loginMember.getAuthCode() > 3}">
						<a href="${pageContext.request.contextPath}/member/question" class="flex-c-m trans-04 p-lr-25">
							Help & FAQs
						</a>
					</c:if>
					

					<a href="${pageContext.request.contextPath}/member/memberOne" class="flex-c-m trans-04 p-lr-25">
						My Account
					</a>

					<a href="https://map.naver.com/v5/search/%EB%8C%80%EB%A5%AD3%EC%B0%A8/place/18980791?placePath=%3Fentry=pll%26from=nx%26fromNxList=true&c=15,0,0,0,dh" class="flex-c-m trans-04 p-lr-25">
						GDJ
					</a>

					<a href="#" class="flex-c-m trans-04 p-lr-25">
						KRW
					</a>
				</div>
			</div>
		</div>
		
		<div class="wrap-menu-desktop">
			<nav class="limiter-menu-desktop container">
				
				<!-- Logo desktop -->		
				<a href="${pageContext.request.contextPath}/home" class="logo">
					<img src="${pageContext.request.contextPath}/images/icons/logo-01.png" alt="IMG-LOGO">
				</a>

				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li>
							<a href="${pageContext.request.contextPath}/home">홈</a>
						</li>
						<li class="label1" data-label1="hot">
							<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
						</li>

						<li>
							<a href="${pageContext.request.contextPath}/admin/notice">공지사항</a>
						</li>
					</ul>
				</div>	

				<!-- 오른쪽 아이콘 -->
				<!-- 로그인, 회원가입, 장바구니 -->
				<div class="wrap-icon-header flex-w flex-r-m h-full">
					<!-- 비로그인시 -->
					<c:if test="${loginMember == null}">
						<!-- 로그인 아이콘 -->
						<div class="flex-c-m h-full p-r-24">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
								<a href="${pageContext.request.contextPath}/member/login">
									<img src="images/icons/Signin.png" style="width:30px;">
								</a>
							</div>
						</div>
						<!-- 회원가입 아이콘 -->
						<div class="flex-c-m h-full p-r-24">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
								<a href="${pageContext.request.contextPath}/member/addMember">
									<img src="images/icons/Signup.png" style="width:30px;">
								</a>
							</div>
						</div>
					</c:if>
					<!-- 로그인시 -->
					<c:if test="${loginMember != null && loginMember.getAuthCode() > 3}">
						<!-- 내정보 아이콘 -->
						<div class="flex-c-m h-full p-r-24">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
								<ul class="main-menu">
									<li class="active-menu">
										<a href="${pageContext.request.contextPath}/home">
											<img src="${pageContext.request.contextPath}/images/icons/adminIcon.png" style="width:35px;">
										</a>
										<ul class="sub-menu">
											<c:if test="${loginMember.getAuthCode() == 4}">
												<li><a href="${pageContext.request.contextPath}/admin/addNotice">공지사항 입력</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a></li>
											</c:if>
											<!-- 직원 5등급 대리 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
											<c:if test="${loginMember.getAuthCode() == 5}">
												<li><a href="${pageContext.request.contextPath}/admin/addNotice">공지사항 입력</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a></li>
											</c:if>
											<!-- 직원 6등급 과장 상품수정, 삭제, 상품등록, 문의(답글,수정,삭제), 리뷰 삭제  -->
											<c:if test="${loginMember.getAuthCode() == 6}">
												<li><a href="${pageContext.request.contextPath}/admin/addNotice">공지사항 입력</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a></li>
											</c:if>
											<!-- 직원 7등급 팀장 다 가능(고객,직원 등급수정 팀장만 가능), 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
											<c:if test="${loginMember.getAuthCode() == 7}">
												<li><a href="${pageContext.request.contextPath}/admin/addNotice">공지사항 입력</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/goodsComment">상품문의답변</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/comment">주문문의답변</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/modifyMember">고객레벨수정</a></li>
												<li><a href="${pageContext.request.contextPath}/admin/modifyByAdmin">직원레벨수정</a></li>
											</c:if>
										</ul>
									</li>
								</ul>
							</div>
						</div>
					</c:if>
					<!-- cart 아이콘 -->
					<div class="flex-c-m h-full p-r-24">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
						<a href="${pageContext.request.contextPath}/member/cart">
							<img src="${pageContext.request.contextPath}/images/icons/cart.png" style="width:30px;">
						</a>
						</div>
					</div>
					<!-- 고객센터 아이콘 
					<div class="flex-c-m h-full p-r-24">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
						<a href="${pageContext.request.contextPath}/member/question">
							<img src="${pageContext.request.contextPath}/images/icons/center.png" style="width:30px;">
						</a>
						</div>
					</div>
					-->
					<!-- 로그아웃 아이콘 -->
					<div class="flex-c-m h-full p-r-24">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
							<a href="${pageContext.request.contextPath}/member/logout">
								<img src="${pageContext.request.contextPath}/images/icons/log-out.png" style="width:30px;">
							</a>
						</div>
					</div>
				</div>
			</nav>
		</div>	
	</div>
	<!-- Modal Search 
	<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
		<div class="container-search-header">
			<button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
				<img src="images/icons/icon-close2.png" alt="CLOSE">
			</button>

			<form class="wrap-search-header flex-w p-l-15">
				<button class="flex-c-m trans-04">
					<i class="zmdi zmdi-search"></i>
				</button>
				<input class="plh3" type="text" name="search" placeholder="Search...">
			</form>
		</div>
	</div>
	-->
</header>
<!-- 
<nav>
	<div>
		<c:if test="${nowPage eq home}">
			[직원]${loginMember.getEmpId()}님
			<a href="${pageContext.request.contextPath}/home">홈</a>
			<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
			<a href="${pageContext.request.contextPath}/member/cart">장바구니</a>
			<a href="${pageContext.request.contextPath}/admin/adminOne">직원정보</a>
			<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
		</c:if>
	</div>
</nav>
 -->
<!-- nav 
<a href="${pageContext.request.contextPath}/home">홈</a>
<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
<a href="${pageContext.request.contextPath}/member/cart">장바구니</a>
<a href="${pageContext.request.contextPath}/member/login">로그인</a>
=
<c:if test="${loginCustomer.getAuthCode() < 4}">
<a href="${pageContext.request.contextPath}/member/memberOne">정보</a>
</c:if>
<c:if test="${loginEmp.getAuthCode() > 4}">
<a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a>
<a href="${pageContext.request.contextPath}/admin/adminOne">정보</a>
</c:if>
<c:if test="${loginEmp.getAuthCode() == 7}">
<a href="${pageContext.request.contextPath}/admin/modifyMember">고객등급수정</a>
<a href="${pageContext.request.contextPath}/admin/modifyByAdmin">직원등급수정</a>
</c:if>
<c:if test="${loginEmp != null || loginCustomer != null}">
<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
</c:if>
<c:if test="${loginCustomer != null}">
${loginCustomer.getCustomerId()}님
</c:if>
<c:if test="${loginEmp != null}">
${loginEmp.getEmpId()}님
</c:if>
-->