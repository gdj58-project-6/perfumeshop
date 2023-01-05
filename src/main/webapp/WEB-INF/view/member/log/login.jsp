<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
	</head>
	<body>
		<h1>로그인</h1>
		<form action="${pageContext.request.contextPath}" method="post">
			<table border="1">
				<tr>
					<th>아이디</th>
					<th>비번</th>
				</tr>
				<tr>
					<td>
						<input type="text" name="memberId">
					</td>
					<td>
						<input type="password" name="memberPw">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>