<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyQuestion</title>
	</head>
	<body>
		<h1>문의수정</h1>
		${msg}
		<form action="${pageContext.request.contextPath}/member/modifyQuestion" method="post">
			<table border="1">
				<tr>
					<td>문의번호</td>
					<td>
						<input type="text" name="questionCode" value="${modifyQuestion.questionCode}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>주문번호</td>
					<td>${modifyQuestion.orderCode}</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${modifyQuestion.customerId}</td>
				</tr>
				<tr>
					<td>분류</td>
					<td>${modifyQuestion.category}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="questionMemo">${modifyQuestion.questionMemo}</textarea>
					</td>
				</tr>
				<tr>
					<td>작성일자</td>
					<td>${modifyQuestion.createdate}</td>
				</tr>
			</table>
			<div>
				<button type="submit">수정</button>
			</div>
		</form>
	</body>
</html>