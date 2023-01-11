<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>공지사항 수정</h1>
		<form action="${pageContext.request.contextPath}/admin/modifyNotice" method="post">
			<input type="hidden" name="empId" value="${loginMember.getEmpId()}">
			<input type="hidden" name="noticeCode" value="${n.noticeCode}">
			<table border="1">
				<tr>
					<td>번호</td>
					<td>${n.noticeCode}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="noticeTitle" value="${n.noticeTitle}">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="noticeContent">${n.noticeContent}</textarea>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						${n.empId}
					</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${n.createdate}</td>
				</tr>
			</table>
			<div>
				<button type="submit">수정</button>
			</div>
		</form>
	</body>
</html>