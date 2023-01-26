<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header -->
<header class="header-v2">
	<!-- Header desktop -->
	<div class="container-menu-desktop trans-03">
		<div class="wrap-menu-desktop">
			<nav class="limiter-menu-desktop p-l-45">
				<!-- Logo desktop -->		
				<a href="#" class="logo">
					<img src="images/icons/logo-01.png" alt="IMG-LOGO">
				</a>
				<!-- Menu desktop -->
				<div class="menu-desktop">
					<ul class="main-menu">
						<li class="active-menu">
							<a href="${pageContext.request.contextPath}/home">홈</a>
						</li>

						<li>
							<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
						</li>

						<li class="label1" data-label1="hot">
							<a href="shoping-cart.html">공지사항</a>
						</li>

						<li>
							<a href="about.html">회사소개</a>
						</li>
					</ul>
				</div>	
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
					<c:if test="${loginMember != null && loginMember.getAuthCode() < 4}">
						<!-- 내정보 아이콘 -->
						<div class="flex-c-m h-full p-r-24">
							<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
								<ul class="main-menu">
									<li class="active-menu">
										${loginMember.customerName}
										<ul class="sub-menu">
											<li><a href="${pageContext.request.contextPath}/member/memberOne">내 정보</a></li>
											<li><a href="${pageContext.request.contextPath}/member/orderList">주문내역</a></li>
											<li><a href="${pageContext.request.contextPath}/member/pointHistory">내 포인트</a></li>
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
							<img src="images/icons/cart.png" style="width:30px;">
						</a>
						</div>
					</div>
					<!-- 고객센터 아이콘 -->
					<div class="flex-c-m h-full p-r-24">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
						<a href="${pageContext.request.contextPath}/member/question">
							<img src="images/icons/center.png" style="width:30px;">
						</a>
						</div>
					</div>
					<!-- 로그아웃 아이콘 -->
					<div class="flex-c-m h-full p-r-24">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
							<a href="${pageContext.request.contextPath}/member/logout">
								<img src="images/icons/log-out.png" style="width:30px;">
							</a>
						</div>
					</div>
				</div>
			</nav>
		</div>	
	</div>
</header>
<!-- 
<nav>
	<div>
		<c:if test="${nowPage eq home}">
		<a href="${pageContext.request.contextPath}/home">홈</a>
		<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
		<a href="${pageContext.request.contextPath}/member/cart">장바구니</a>
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
		<a href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/question">고객센터</a>
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