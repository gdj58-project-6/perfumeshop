<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>orderQuestion</title>
	</head>
	<body>
		<h1>문의작성</h1>
		<form action="${pageContext.request.contextPath}/member/addQuestion" method="post">
			<table border="1">
				<tr>
					<td>주문번호</td>
					<td>
						<input type="text" name="orderCode" value="${orderCode}">
					</td>
				</tr>
				<tr>
					<td>분류</td>
					<td>
						<select name="category">
							<option value="">==선택==</option>
							<option value="할인">할인</option>
							<option value="포인트">포인트</option>
							<option value="구매">구매</option>
							<option value="결제">결제</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>문의내용</td>
					<td>
						<textarea cols="50" rows="5" name="questionMemo"></textarea>
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">입력</button>
			</div>	
		</form>
	</body>
</html>