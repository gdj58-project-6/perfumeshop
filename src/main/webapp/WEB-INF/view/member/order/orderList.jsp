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
				<th>상품 </th>
				<th>상품 이름</th>
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
					<td>${o.goodsPrice}</td>
					<td>
						<c:if test="${o.orderState eq '결제' || o.orderState eq '배송전'}">
							${o.orderState} <a href="${pageContext.request.contextPath}/member/modifyOrderState?orderCode=${o.orderCode}&orderState=취소">취소신청</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/member/addQuestion?orderCode=${o.orderCode}">문의작성</a>
						<c:if test="${o.orderState eq '배송중' || o.orderState eq '배송완료'}">
							${o.orderState} <a href="${pageContext.request.contextPath}/member/addReturnByOrder?orderCode=${o.orderCode}">환불신청</a>
						</c:if>
						<c:if test="${o.orderState eq '배송완료'}">
							<a href="${pageContext.request.contextPath}/member/modifyOrderState?orderCode=${o.orderCode}&orderState=구매확정">구매확정</a>
						</c:if>
						<c:if test="${o.orderState ne '결제' && o.orderState ne '배송전' && o.orderState ne '배송중' && o.orderState ne '배송완료'}">
							${o.orderState}
						</c:if>
					</td>
					<td>${o.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>