<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addMember</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/member/addMember" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" name="phone1">
						- <input type="text" name="phone2">
						- <input type="text" name="phone3">
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<textarea name="address" rows="5" cols="20"></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pw">
					</td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td>
						<input type="password" name="pw2">
					</td>
				</tr>
			</table>
			<button type="submit">회원가입</button>
		</form>
		<a href="${pageContext.request.contextPath}/member/login">취소</a>
	</body>
</html>