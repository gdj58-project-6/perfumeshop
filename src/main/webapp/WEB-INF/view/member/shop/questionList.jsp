<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>QuestionList</title>
	</head>
	<body>
		<h1>Perfume고객센터</h1>
		<h2>나의 문의내역</h2>
		<form action="${pageContext.request.contextPath}/member/questionList" method="post">
			<table class="table table-bordered">
				<tr>
					<th></th>
				</tr>
			</table>
		</form>
	</body>
</html>