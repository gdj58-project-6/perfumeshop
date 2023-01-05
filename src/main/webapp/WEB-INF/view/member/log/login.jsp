<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
	</head>
	<body>
		<h1>고객 로그인</h1>
		<form action="${pageContext.request.contextPath}/member/login" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="customerId">
					</td>
				</tr>
				<tr>
					<td>비번</td>
					<td>
						<input type="password" name="customerPw">
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">로그인</button>
			</div>
		</form>
		<a href="${pageContext.request.contextPath}/member/addMemberEmp">고객 회원가입</a>
		<h1>직원 로그인</h1>
		<form action="${pageContext.request.contextPath}/member/login" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="empId">
					</td>
				</tr>
				<tr>
					<td>비번</td>
					<td>
						<input type="password" name="empPw">
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">로그인</button>
			</div>
		</form>
		<a href="${pageContext.request.contextPath}/emp/addEmp">직원 회원가입</a>
	</body>
</html>