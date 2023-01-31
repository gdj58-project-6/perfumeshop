<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>notice One</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="../vendor/slick/slick.css">
		<link rel="stylesheet" type="text/css" href="../vendor/MagnificPopup/magnific-popup.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/home">홈</a>
		<h1 class="text-center">공지사항</h1>
		<table class="table table-bordered">
			<tr>
				<td>번호</td>
				<td>${n.noticeCode}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${n.noticeTitle}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea cols="50" rows="5" readonly="readonly">${n.noticeContent}</textarea>
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${n.empId}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${n.createdate}</td>
			</tr>
		</table>
		<c:if test="${loginMember.getAuthCode() > 3}">
			<div>
				<a href="${pageContext.request.contextPath}/admin/modifyNotice?noticeCode=${n.noticeCode}">수정</a>
				<a href="${pageContext.request.contextPath}/admin/removeNotice?noticeCode=${n.noticeCode}">삭제</a>
			</div>
		</c:if>
		
	</body>
</html>