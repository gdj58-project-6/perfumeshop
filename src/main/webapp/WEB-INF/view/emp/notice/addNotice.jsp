<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addNotice</title>
	</head>
	<body>
		<h1>공지글 작성</h1>
		<form action="${pageContext.request.contextPath}/admin/addNotice" method="post">
			<table border="1">
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="noticeTitle">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="noticeContent"></textarea>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name="empId" value="${loginMember.getEmpId()}">
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">입력</button>
			</div>
		</form>
	</body>
</html>