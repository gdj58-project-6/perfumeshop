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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
		    $(document).ready(function(){
		    	// 목록
		    	$("#list").click(function(){
		    		$(location).attr("href", "${pageContext.request.contextPath}/admin/notice")
		    	})
		    	
		    	// 수정
		    	$("#update").click(function(){
		    		$(location).attr("href", "${pageContext.request.contextPath}/admin/modifyNotice?noticeCode=${n.noticeCode}")
		    	})
		    	
		    	// 삭제
		    	$("#delete").click(function(){
		    		$(location).attr("href", "${pageContext.request.contextPath}/admin/removeNotice?noticeCode=${n.noticeCode}")
		    	})
		    });
		</script>
	</head>
	<body>
		<c:if test="${loginMember.getAuthCode() < 4 || loginMember == null}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		
		<div align="center" style="margin-top:80px;">
			<h1 class="text-center">공지사항</h1>
			<br>
			<table class="table table-bordered" style="width:700px;">
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
			<br>
			<c:if test="${loginMember.getAuthCode() < 4 || loginMember == null}">
				<div>
					<button id="list" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						목록
					</button>
				</div>
			</c:if>
			<c:if test="${loginMember.getAuthCode() > 3}">
				<div>
					<button id="list" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						목록
					</button>
					<button id="update" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
						수정
					</button>
					<button id="delete" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
						삭제
					</button>
				</div>
			</c:if>
		</div>
	</body>
</html>