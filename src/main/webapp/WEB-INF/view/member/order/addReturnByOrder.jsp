<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/member/addReturnByOrder" method="post">
			<table>
				<tr>
					<td>반품할 주문</td>
					<td>
						<c:forEach var="o" items="${orderList}">
							<input type="hidden" name="orderCode" value="${o.orderCode}">
							<input type="hidden" name="id" value="${o.customerId}">
							[상품이름] ${o.goodsName} [상품가격] ${o.orderGoodsPrice} [상품개수] ${o.orderGoodsQuantity}
							<c:set var="totalPrice"  value="${totalPrice + (g.orderGoodsPrice * g.orderGoodsQuantity)}" ></c:set>
							<br>
						</c:forEach>
						[주문 총 가격] ${totalPrice}
					</td>
				</tr>
				<tr>
					<td>반품 사유</td>
					<td>
						<textarea name="memo" rows="7" cols="30"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit">반품신청</button>
		</form>
	</body>
</html>