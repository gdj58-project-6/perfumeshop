<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyMemberLevel</title>
	</head>
	<body>
		<!-- 고객 리스트 조회 -->
		<h1>고객 레벨수정</h1>
		${loginEmp.getEmpName()}(등급:${loginEmp.getAuthCode()})
		<form action="${pageContext.request.contextPath}/admin/modifyMember" method="post">
			<table border="1">
				<tr>
					<th>customerCode</th>
					<th>customerId</th>
					<th>customerName</th>
					<th>customerPhone</th>
					<th>point</th>
					<th>authCode</th>
					<th>createdate</th>
				</tr>
				<c:forEach var="m" items="${list}">
					<tr>
						<td>${m.customerCode}</td>
						<td>
							<input type="text" name="customerId" value="${m.customerId}" readonly="readonly">
						</td>
						<td>${m.customerName}</td>
						<td>${m.customerPhone}</td>
						<td>${m.point}</td>
						<td>${m.authCode}</td>
						<td>${m.createdate}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<!-- 고객 리스트 페이징 버튼 -->
		<div>
			<c:if test="${currentPage != 1}">
				<a href="${pageContext.request.contextPath}/admin/modifyMember?currentPage=1">처음</a>
			</c:if>
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/admin/modifyMember?currentPage=${currentPage-1}">이전</a>
			</c:if>
			${currentPage}
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/admin/modifyMember?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${currentPage != lastPage}">
				<a href="${pageContext.request.contextPath}/admin/modifyMember?currentPage=${lastPage}">마지막</a>
			</c:if>
		</div>
	</body>
</html>