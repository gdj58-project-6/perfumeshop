<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>notice</title>
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
	</head>
	<body>
		<!-- 고객or비로그인 -->
		<c:if test="${loginMember == null || loginMember.getAuthCode() < 4}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		
		<!-- 공지사항 -->
		<div class="container">
			<div class="row">
				<!-- 공지목록 -->
				<div class="col-md-10 col-lg-10 p-b-190 p-t-55 mx-auto">
					<h1 class="mtext-109 cl2 p-b-10" style="margin-top:100px;">공지사항</h1>
					<table class="table text-center stext-110 cl2">
						<tr>
							<th class="text-center" style="width:15%;">번호</th>
							<th class="text-center w-50">제목</th>
							<th class="text-center" style="width:15%;">작성자</th>
							<th class="text-center" style="width:20%;">작성일</th>
						</tr>
						<c:forEach var="m" items="${list}">
							<tr>
								<td>${m.noticeCode}</td>
								<td>
									<a href="${pageContext.request.contextPath}/admin/noticeOne?noticeCode=${m.noticeCode}">${m.noticeTitle}</a>
								</td>
								<td>${m.empId}</td>
								<td>${m.createdate}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 공지사항 페이징 -->
					<div>
						<!-- 
						<c:if test="${currentPage != 1}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=1">처음</a>				
						</c:if>
						 -->
						<c:if test="${currentPage > 1}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${currentPage-1}">이전</a>
						</c:if>
						${currentPage}
						<c:if test="${currentPage < lastPage}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${currentPage+1}">다음</a>
						</c:if>
						<!-- 
						<c:if test="${currentPage != lastPage}">
							<a href="${pageContext.request.contextPath}/admin/notice?currentPage=${lastPage}">마지막</a>
						</c:if>
						 -->
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	</body>
</html>