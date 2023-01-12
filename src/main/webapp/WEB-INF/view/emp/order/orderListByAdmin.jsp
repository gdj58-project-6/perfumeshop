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
				$('.orderState').change(function() {
					$(this.form).submit();
					alert('주문상태를 변경했습니다');
				})
			})
		</script>
	</head>
	<body>
		<table>
			<tr>
				<th>상품 </th>
				<th>상품 이름</th>
				<th>주문 고객</th>
				<th>총 가격</th>
				<th>주문 상태</th>
				<th>주문 일자</th>
			</tr>
			<c:forEach var="o" items="${list}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/upload/${o.filename}" width="200" height="200"></td>
					<td>
						<a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${o.orderCode}">
							<c:if test="${o.cnt eq 0}">
								${o.goodsName}
							</c:if>
							<c:if test="${o.cnt > 0}">
								${o.goodsName}외 ${o.cnt}건
							</c:if>
						</a>
					</td>
					<td>${o.customerId}</td>
					<td>${o.goodsPrice}</td>
					<td>
						<form action="${pageContext.request.contextPath}/admin/modifyOrderState?orderCode=${o.orderCode}" method="post">
							<select name="orderState" class="orderState">
								<c:if test="${o.orderState eq '결제'}">
									<option value="결제" selected="selected">결제</option>
									<option value="취소">취소</option>
									<option value="배송전">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료">배송완료</option>
								</c:if>
								<c:if test="${o.orderState eq '취소'}">
									<option value="결제">결제</option>
									<option value="취소" selected="selected">취소</option>
									<option value="배송전">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료">배송완료</option>
								</c:if>
								<c:if test="${o.orderState eq '배송전'}">
									<option value="결제">결제</option>
									<option value="취소">취소</option>
									<option value="배송전" selected="selected">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료">배송완료</option>
								</c:if>
								<c:if test="${o.orderState eq '배송중'}">
									<option value="결제">결제</option>
									<option value="취소">취소</option>
									<option value="배송전">배송전</option>
									<option value="배송중" selected="selected">배송중</option>
									<option value="배송완료">배송완료</option>
								</c:if>
								<c:if test="${o.orderState eq '배송완료'}">
									<option value="결제">결제</option>
									<option value="취소">취소</option>
									<option value="배송전">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료" selected="selected">배송완료</option>
								</c:if>
							</select>
						</form>
					</td>
					<td>${o.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>