<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<jsp:include page="/inc/homeEmpMenu.jsp"></jsp:include>
		<table>
			<tr>
				<th>상품</th>
				<th>상품 이름</th>
				<th>상품 가격</th>
				<th>총 판매 개수</th>
				<th>상품별 총 매출</th>
			</tr>
			<c:forEach var="g" items="${list}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="200" height="200"></td>
					<td><a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a></td>
					<td>${g.goodsPrice}</td>
					<td>${g.quantitySum}</td>
					<td>${g.sum}</td>
				</tr>	
			</c:forEach>
		</table>
	</body>
</html>