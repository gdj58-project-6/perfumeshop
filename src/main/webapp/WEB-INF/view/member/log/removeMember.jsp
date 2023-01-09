<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/member/removeMember" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="customerId" value="${loginMember.customerId}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="customerPw"></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="ckPw"></td>
				</tr>
			</table>
			<button type="reset">취소</button>
			<button type="submit">회원탈퇴</button>
		</form>
		<a href="${pageContext.request.contextPath}/member/memberOne">뒤로</a>
	</body>
</html>