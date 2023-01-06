<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav>
	<div>
		<c:if test="${nowPage eq home}">
			<a href="${pageContext.request.contextPath}/home">홈</a>
			<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
			<a href="${pageContext.request.contextPath}/member/cart">장바구니</a>
			<a href="${pageContext.request.contextPath}/admin/adminOne">정보</a>
			[직원]${loginEmp.getEmpId()}님
			<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
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