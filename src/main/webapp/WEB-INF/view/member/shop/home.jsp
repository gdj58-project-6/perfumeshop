<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>home</title>
	</head>
	<body>
		<h1>home</h1>
		<!-- nav -->
		<a href="${pageContext.request.contextPath}/home">홈</a>
		<a href="${pageContext.request.contextPath}/member/goodsList">상품</a>
		<a href="${pageContext.request.contextPath}/member/cart">장바구니</a>
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
		<!-- 테스트용 -->
		<c:if test="${loginEmp.getAuthCode() < 4}">
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
		<!-- 큰 사진 -->
		<div style="text-align:center;"><img src="${pageContext.request.contextPath}/img/camera.jpg" width="1500" height="700"></div>
		<!-- 공지목록 페이징 5개씩 -->
		<h2>공지사항</h2>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.noticeCode}</td>
					<td>${m.noticeTitle}</td>
					<td>${m.noticeContent}</td>
					<td>${m.empId}</td>
					<td>${m.createdate}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 공지목록 페이징 5개씩 -->
		<div>
			<c:if test="${currentPage != 1}">
				<a href="${pageContext.request.contextPath}/home?currentPage=1">처음</a>				
			</c:if>
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${currentPage-1}">이전</a>
			</c:if>
			${currentPage}
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${currentPage != lastPage}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${lastPage}">마지막</a>
			</c:if>
		</div>
	</body>
</html>