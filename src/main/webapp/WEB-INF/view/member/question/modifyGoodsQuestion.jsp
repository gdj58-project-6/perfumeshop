<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyGoodsQuestion</title>
	</head>
	<body>
		<h1>상품문의수정</h1>
		<form action="${pageContext.request.contextPath}/member/modifyGoodsQuestion" method="post">
			<table border="1">
				<tr>
					<td>번호</td>
					<td></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td></td>
				</tr>
				<tr>
					<td>분류</td>
					<td></td>
				</tr>
				<tr>
					<td>내용</td>
					<td></td>
				</tr>
				<tr>
					<td>작성일자</td>
					<td></td>
				</tr>
			</table>
			<div>
				<button type="submit">수정</button>
			</div>
		</form>
	</body>
</html>