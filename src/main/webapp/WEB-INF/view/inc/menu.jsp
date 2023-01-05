<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>menu</title>
	</head>
	<body>
		<header>
			<nav>
				<ul>
					<li><a href="${pageContext.request.contextPath}/home">홈</a></li>
					<li><a href="${pageContext.request.contextPath}/member/goodsList">상품</a></li>
					<li><a href="${pageContext.request.contextPath}/member/cart">장바구니</a></li>
					<li><a href="${pageContext.request.contextPath}/member/login">로그인</a></li>
					<!-- 테스트용 -->
					<li><a href="${pageContext.request.contextPath}/member/memberOne">개인정보</a></li>
				</ul>
			</nav>
		</header>
	</body>
</html>