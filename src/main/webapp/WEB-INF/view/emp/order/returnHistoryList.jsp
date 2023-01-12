<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table>
			<tr>
				<th>주문 코드</th>
				<th>주문 고객</th>
				<th>주문 금액</th>
				<th>주문 상태</th>
				<th>반품 사유</th>
				<th>반품 승인</th>
				<th>반품 신청 일자</th>
			</tr>
			<c:forEach var="r" items="${list}">
				<tr>
					<td>${r.orderCode}</td>
					<td>${r.customerId}</td>
					<td>${r.orderPrice}</td>
					<td>${r.orderState}</td>
					<td>${r.returnMemo}</td>
					<td>
						<c:if test="${r.returnState eq '승인전'}">					
							${r.returnState}<a class="btn" href="${pageContext.request.contextPath}/admin/modifyReturnByOrder?orderCode=${r.orderCode}">반품승인</a>
						</c:if>
						<c:if test="${r.returnState eq '승인완료'}">					
							${r.returnState}
						</c:if>
					</td>
					<td>${r.createdate}</td>
					</tr>
			</c:forEach>
		</table>
	</body>
</html>