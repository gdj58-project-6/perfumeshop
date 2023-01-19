<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addComment</title>
	</head>
	<body>
		<h1>답글입력</h1>
		${msg}
		<form action="${pageContext.request.contextPath}/admin/addComment" method="post">
			<table border="1">
				<tr>
					<td>문의번호</td>
					<td>
						<input type="text" name="questionCode" value="${code}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>문의내용</td>
					<td>
						<textarea cols="50" rows="5" name="questionMemo" readonly="readonly">${memo}</textarea>
						
					</td>
				</tr>
				<tr>
					<td>답변내용</td>
					<td>
						<textarea cols="50" rows="5" name="commentMemo"></textarea>
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">답변입력</button>
			</div>
		</form>
	</body>
</html>