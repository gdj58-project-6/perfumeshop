<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#btn").click(function(){
		    		$(location).attr("href", "${pageContext.request.contextPath}/admin/noticeOne?noticeCode=${n.noticeCode}")
		    	})
		    	
		    	$('#memo').focus();
			})
		</script>
		<style>
			.po {
				position : relative;
				top : 100px;
			}
		</style>
	</head>
	<body>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		<div align="center" class="po">
			<h1>공지사항 수정</h1>
			${msg}
			<br>
			<form action="${pageContext.request.contextPath}/admin/modifyNotice" method="post">
				<input type="hidden" name="empId" value="${loginMember.getEmpId()}">
				<input type="hidden" name="noticeCode" value="${n.noticeCode}">
				<table class="table table-bordered" style="width:700px;">
					<tr>
						<td align="center" style="vertical-align:middle;">번호</td>
						<td>${n.noticeCode}</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">제목</td>
						<td>
							<input type="text" name="noticeTitle" value="${n.noticeTitle}" id="memo">
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">내용</td>
						<td>
							<textarea cols="50" rows="5" name="noticeContent" id="memo">${n.noticeContent}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">작성자</td>
						<td>
							${n.empId}
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">작성일</td>
						<td>${n.createdate}</td>
					</tr>
				</table>
				<br>
				<div>
					<button id="btn" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						취소
					</button>
					<button id="update" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
						수정
					</button>
				</div>
			</form>
		</div>
	</body>
</html>