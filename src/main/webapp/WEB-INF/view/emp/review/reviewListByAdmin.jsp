<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>상품</th>
				<th>상품 이름</th>
				<th>작성자</th>
				<th>리뷰 내용</th>
				<th>적립 포인트</th>
				<th>작성일자</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="r" items="${reviewList}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/upload/${r.filename}" width="200" height="200"></td>
					<td>${r.goodsName}</td>
					<td>${r.customerId}</td>
					<td>${r.reviewMemo}</td>
					<td>${r.point}</td>
					<td>${r.createdate}</td>
					<td><a href="${pageContext.request.contextPath}/member/removeRiview?orderCode=${r.orderCode}&goodsCode=${r.goodsCode}&point=${r.point}&id=${r.customerId}">리뷰 삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>