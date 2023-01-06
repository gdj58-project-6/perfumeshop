<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>adminPage</title>
	</head>
	<body>
		<h1>관리자 페이지</h1>
		<!-- 위의 직급은 아래 직급의 권한을 다 가짐 -->
		<!-- 직원 4등급 신입 문의(답글,수정), 리뷰 삭제 -->
		<c:if test="${loginEmp.getAuthCode() == 4}">
		
		</c:if>
		<!-- 직원 5등급 대리 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
		<c:if test="${loginEmp.getAuthCode() == 5}">
		
		</c:if>
		<!-- 직원 6등급 과장 상품수정, 삭제, 상품등록, 문의(답글,수정,삭제), 리뷰 삭제  -->
		<c:if test="${loginEmp.getAuthCode() == 6}">
		
		</c:if>
		<!-- 직원 7등급 팀장 다 가능(고객,직원 등급수정 팀장만 가능), 상품등록, 문의(답글,수정,삭제), 리뷰 삭제 -->
		<c:if test="${loginEmp.getAuthCode() == 7}">
		
		</c:if>
	</body>
</html>