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
					<li><a href="${pageContext.request.contextPath}">홈</a></li>
					<li><a href="${pageContext.request.contextPath}">상품</a></li>
					<li><a href="${pageContext.request.contextPath}">로그인</a></li>
					<li><a href="${pageContext.request.contextPath}">회원가입</a></li>
					<li><a href="${pageContext.request.contextPath}">로그아웃</a></li>
				</ul>
			</nav>
		</header>
	</body>
</html>