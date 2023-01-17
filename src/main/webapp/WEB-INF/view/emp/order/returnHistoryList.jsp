<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/admin/returnHistoryList" id="returnHistory" method="get">
			<select name="returnState" id="returnState">
				<option value="" <c:out value="${state == '' ? 'selected':'' }"/>>승인여부</option>
				<option value="승인전" <c:out value="${state == '승인전' ? 'selected':'' }"/>>승인전</option>
				<option value="승인완료" <c:out value="${state == '승인완료' ? 'selected':'' }"/>>승인완료</option>
			</select>
			<select name="sort" id="sort">
				<option value="DESC" <c:out value="${sort == 'DESC' ? 'selected':'' }"/>>오래된순</option>
				<option value="ASC" <c:out value="${sort == 'ASC' ? 'selected':'' }"/>>최근결제순</option>
			</select>
			<input type="text" name="customerId">
			<button type="submit">검색</button>
		</form>
		<table>
			<tr>
				<th>주문 코드</th>
				<th>주문 고객</th>
				<th>주문 금액</th>
				<th>주문 상태</th>
				<th>반품 사유</th>
				<th>반품 승인 여부</th>
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