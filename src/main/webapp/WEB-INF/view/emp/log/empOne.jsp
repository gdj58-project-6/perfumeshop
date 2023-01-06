<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>${empOne.empId}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${empOne.empName}</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>${empOne.authCode}</td>
			</tr>
			<tr>
				<td>입사일자</td>
				<td>${empOne.createdate}</td>
			</tr>
		</table>
		<!-- 디버깅용 -->
		<a href="${pageContext.request.contextPath}/admin/removeByAdmin">탈퇴</a>
	</body>
</html>