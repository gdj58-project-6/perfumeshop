<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>addComment</title>
	</head>
	<body>
		<h1>답글입력</h1>
		<form action="${pageContext.request.contextPath}/admin/addGoodsComment" method="post">
			<table border="1">
				<tr>
					<td>문의번호</td>
					<td>
						<input type="text" name="goodsQuestionCode" value="${code}">
					</td>
				</tr>
				<tr>
					<td>문의내용</td>
					<td>
						<textarea cols="50" rows="5" readonly="readonly">${memo}</textarea>
						
					</td>
				</tr>
				<tr>
					<td>답변내용</td>
					<td>
						<textarea cols="50" rows="5" name="goodsCommentMemo"></textarea>
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">답변입력</button>
			</div>
		</form>
	</body>
</html>