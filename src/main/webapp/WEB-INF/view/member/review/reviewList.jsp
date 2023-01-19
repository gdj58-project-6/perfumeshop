<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('.modifyReview').click(function() {
					 let html = 
						`<textarea rows="5" cols="30" name="reviewMemo"></textarea>
						<button type="submit">수정하기</button>`
					$(this.form).children('div').html(html);
					// form안에 있는 div를 찾아서 html 넣는 코드
				})
			})
		</script>
	</head>
	<body>
		<table border="1"> 
			<c:forEach var="r" items="${reviewList}">
				<tr>
					<td>${r.orderCode}</td>
					<td><img src="${pageContext.request.contextPath}/upload/${r.filename}" width="100" height="100"></td>
					<td>${r.goodsName}</td>
				</tr>
				<tr>
					<td>${r.reviewMemo}</td>
					<td>${r.point}</td>
					<td>${r.createdate}</td>
				</tr>
				<tr>
					<td colspan="3">
						<form action="${pageContext.request.contextPath}/member/modifyReview" method="post">
							<input type="hidden" name="orderCode" value="${r.orderCode}">
							<input type="hidden" name="goodsCode" value="${r.goodsCode}">
							<div><button type="button" class="modifyReview">리뷰 수정</button></div>
						</form>
						<a href="${pageContext.request.contextPath}/member/removeRiview?orderCode=${r.orderCode}&goodsCode=${r.goodsCode}&point=${r.point}&id=${r.customerId}">리뷰 삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>