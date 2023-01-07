<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<!-- table 가로 출력... -->	
			<c:forEach var="memberOne" items="${memberOne}">
				<td>아이디 : ${memberOne.customerId}</td>
				<td>이름 : ${memberOne.customerName}</td>
				<td>전화번호 : ${memberOne.customerPhone}</td>
				<td>포인트 : ${memberOne.point}</td>
				<td>등급 : ${memberOne.authCode}</td>
				<td>주소 : ${memberOne.address}</td>
				<td>가입일자 : ${memberOne.createdate}</td>
			</c:forEach>
		</table>
		<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
		<a href="${pageContext.request.contextPath}/member/modifyMemberPw">비밀번호수정</a>
		<a href="${pageContext.request.contextPath}/member/modifyMember">회원정보수정</a>
	</body>
</html>