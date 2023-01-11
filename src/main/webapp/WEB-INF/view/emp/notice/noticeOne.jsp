<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/home">홈</a>
		<h1>공지사항</h1>
		<table border="1">
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