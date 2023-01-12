<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>question</title>
	</head>
	<body>
		<h1>고객센터</h1>
		<!-- 자주찾는 질문 DB안쓰고 몇개만 직접작성 이건 CRUD 없이 회원, 포인트, 주문/결제, 배송, 취소/반품/교환 -->
		<!-- 나의 문의내역으로 가기 -->
		<a href="${pageContext.request.contextPath}/member/myQuestionList">문의내역 확인하기</a>
		<!-- 문의하기 -->
		<c:if test="${loginMember != null}">
			<h2>문의글 작성</h2>
			<form action="${pageContext.request.contextPath}/member/question" method="post">
				<table border="1">
					<tr>
						<td>주문번호</td>
						<td>
							
						</td>
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
						<td>문의내용</td>
						<td></td>
					</tr>
				</table>
				<div>
					<button type="submit">문의글입력</button>
				</div>
			</form>
		</c:if>
	</body>
</html>