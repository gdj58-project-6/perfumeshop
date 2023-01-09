<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyEmp</title>
	</head>
	<body>
		<!-- 직원 리스트 조회 -->
		<h1>직원 레벨수정</h1>
		${loginMember.getEmpName()}(등급:${loginMember.getAuthCode()})
		<table border="1">
			<tr>
				<th>empCode</th>
				<th>empId</th>
				<th>empName</th>
				<th>active</th>
				<th>authCode</th>
				<th>createdate</th>
				<th>퇴사직원</th>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.empCode}</td>
					<td>${m.empId}</td>
					<td>${m.empName}</td>
					<td>${m.active}</td>
					<td>
						<form action="${pageContext.request.contextPath}/admin/modifyByAdmin" method="post">
							<input type="hidden" name="empCode" value="${m.empCode}">
							<input type="hidden" name="empId" value="${m.empId}">
							<input type="hidden" name="currentPage" value="${currentPage}">
							<input type="hidden" name="rowPerPage" value="${rowPerPage}">
							${m.authCode}
							<select name="authCode">
								<c:if test="${m.authCode == 4}">
									<option value="4" selected="selected">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
								</c:if>
								<c:if test="${m.authCode == 5}">
									<option value="4">4</option>
									<option value="5" selected="selected">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
								</c:if>
								<c:if test="${m.authCode == 6}">
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6" selected="selected">6</option>
									<option value="7">7</option>
								</c:if>
								<c:if test="${m.authCode == 7}">
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7" selected="selected">7</option>
								</c:if>
							</select>
							<button type="submit">수정</button>
						</form>
					</td>
					<td>${m.createdate}</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/removeByAdmin?empId=${m.empId}">퇴사직원</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 고객 리스트 페이징 버튼 -->
		<div>
			<c:if test="${currentPage != 1}">
				<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=1">처음</a>
			</c:if>
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${currentPage-1}">이전</a>
			</c:if>
			${currentPage}
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${currentPage != lastPage}">
				<a href="${pageContext.request.contextPath}/admin/modifyByAdmin?currentPage=${lastPage}">마지막</a>
			</c:if>
		</div>
		<a href="${pageContext.request.contextPath}/home">홈</a>
	</body>
</html>