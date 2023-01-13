<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div>주문 코드 : ${customerOne.orderCode}</div>
		<table>
			<tr>
				<c:forEach var="g" items="${goodsList}">
					<td><img src="${pageContext.request.contextPath}/upload/${g.filename}" width="100" height="100"></td>
					<td>상품 이름 : <a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${g.goodsCode}">${g.goodsName}</a></td>
					<td>상품 가격 : ${g.orderGoodsPrice}</td>
					<td>상품 개수 : ${g.orderGoodsQuantity}</td>
					<c:set var="totalPrice"  value="${totalPrice + (g.orderGoodsPrice * g.orderGoodsQuantity)}" ></c:set>
					<td>상품 총 가격 : ${totalPrice}</td>
					<td>
						<c:if test="${g.orderState ne '구매확정'}">
							${o.orderState}
						</c:if>
						<c:if test="${g.orderState eq '구매확정'}">
							<a href="${pageContext.request.contextPath}/member/addReview">리뷰쓰기</a>
						</c:if>
					</td>
					</tr><tr>
				</c:forEach>
		</table>
		<table>
			<tr><td>주문자 아이디 : ${customerOne.customerId}</td></tr>
			<tr><td>주문자 이름 : ${customerOne.customerName}</td></tr>
			<tr><td>주문자 번호 : ${customerOne.customerPhone}</td></tr>
			<tr><td>주문자 주소 : ${customerOne.address}</td></tr>
			<tr><td>총 가격 : ${customerOne.orderPrice}</td></tr>
			<tr><td>현재 상태 : ${customerOne.orderState}</td></tr>
			<tr><td>주문 메모 : ${customerOne.orderMemo}</td></tr>
			<tr><td>주문일자 : ${customerOne.createdtae}</td></tr>
		</table>
	</body>
</html>