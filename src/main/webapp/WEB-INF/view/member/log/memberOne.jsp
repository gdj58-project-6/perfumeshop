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
				<td>${customerOne.customerId}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${customerOne.customerName}</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>${customerOne.customerPhone}</td>
			</tr>
			<tr>
				<td>포인트</td>
				<td>${customerOne.point}</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>${customerOne.authCode}</td>
			</tr>
			<tr>
				<td>가입일자</td>
				<td>${customerOne.createdate}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
		<a href="${pageContext.request.contextPath}/member/modifyMemberPw">비밀번호수정</a>
	</body>
</html>