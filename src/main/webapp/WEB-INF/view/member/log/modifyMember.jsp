<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form>
			<table>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" value="${customerOne.customerId}">
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" value="${customerOne.customerName}">
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<input type="text" value="${customerOne.customerPhone}">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>