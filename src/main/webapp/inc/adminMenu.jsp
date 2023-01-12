<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<div>
	<!-- 위의 직급은 아래 직급의 권한을 다 가짐 -->
	<!-- 직원 4등급 신입 문의(답글,수정), 리뷰 삭제 -->
	<c:if test="${loginMember.getAuthCode() == 4}">
		<a>문의답변</a> <!-- 미구현 -->
		<a>문의수정</a> <!-- 미구현 -->
		<a>리뷰삭제</a> <!-- 미구현 -->
		<a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a>
	</c:if>
	<!-- 직원 5등급 대리 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
	<c:if test="${loginMember.getAuthCode() == 5}">
		<a>문의답변</a> <!-- 미구현 -->
		<a>문의수정</a> <!-- 미구현 -->
		<a>문의삭제</a> <!-- 미구현 -->
		<a>리뷰삭제</a> <!-- 미구현 -->
		<a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a>
		<a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a>
	</c:if>
	<!-- 직원 6등급 과장 상품수정, 삭제, 상품등록, 문의(답글,수정,삭제), 리뷰 삭제  -->
	<c:if test="${loginMember.getAuthCode() == 6}">
		<a>문의답변</a> <!-- 미구현 -->
		<a>문의수정</a> <!-- 미구현 -->
		<a>문의삭제</a> <!-- 미구현 -->
		<a>리뷰삭제</a> <!-- 미구현 -->
		<a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a>
		<a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a>
	</c:if>
	<!-- 직원 7등급 팀장 다 가능(고객,직원 등급수정 팀장만 가능), 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
	<c:if test="${loginMember.getAuthCode() == 7}">
		<a>문의답변</a> <!-- 미구현 -->
		<a>문의수정</a> <!-- 미구현 -->
		<a>문의삭제</a> <!-- 미구현 -->
		<a>리뷰삭제</a> <!-- 미구현 -->
		<a href="${pageContext.request.contextPath}/admin/orderList">주문 관리</a>
		<a href="${pageContext.request.contextPath}/admin/addGoods">상품등록</a>
		<a href="${pageContext.request.contextPath}/admin/modifyMember">고객레벨수정</a>
		<a href="${pageContext.request.contextPath}/admin/modifyByAdmin">직원레벨수정</a>
	</c:if>
</div>
</nav>

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