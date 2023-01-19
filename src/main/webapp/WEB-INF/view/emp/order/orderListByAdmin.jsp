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
		<form action="${pageContext.request.contextPath}/admin/orderList" id="orderStateForm" method="get">
			<select name="stateSearch" id="orderState">
				<option value="" <c:out value="${state == '' ? 'selected':'' }"/>>주문상태</option>
				<option value="결제" <c:out value="${state == '결제' ? 'selected':'' }"/>>결제</option>
				<option value="취소" <c:out value="${state == '취소' ? 'selected':'' }"/>>취소</option>
				<option value="반품신청" <c:out value="${state == '반품신청' ? 'selected':'' }"/>>반품신청</option>
				<option value="반품완료" <c:out value="${state == '반품완료' ? 'selected':'' }"/>>반품완료</option>
				<option value="배송전" <c:out value="${state == '배송전' ? 'selected':'' }"/>>배송전</option>
				<option value="배송중" <c:out value="${state == '배송중' ? 'selected':'' }"/>>배송중</option>
				<option value="배송완료" <c:out value="${state == '배송완료' ? 'selected':'' }"/>>배송완료</option>
				<option value="구매확정" <c:out value="${state == '구매확정' ? 'selected':'' }"/>>구매확정</option>
			</select>
			<select name="sort" id="sort">
				<option value="ASC" <c:out value="${sort == 'ASC' ? 'selected':'' }"/>>오래된순</option>
				<option value="DESC" <c:out value="${sort == 'DESC' ? 'selected':'' }"/>>최근결제순</option>
			</select>
			<input type="text" name="customerId">
			<button type="submit">검색</button>
		</form>
		<table border="1">
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
					<td>${o.orderPrice}</td>
					<td>
						<form action="${pageContext.request.contextPath}/admin/modifyOrderState?orderCode=${o.orderCode}" method="post">
							<c:if test="${o.orderState ne '반품신청' && o.orderState ne '반품' && o.orderState ne '구매확정' && o.orderState ne '반품완료' && o.orderState ne '취소'}">
								<select name="orderState" class="orderState">
									<!-- orderState의 상태가 true이면 selected 속성 -->
									<option value="결제" <c:out value="${o.orderState == '결제' ? 'selected':'' }"/>>결제</option>
									<option value="취소" <c:out value="${o.orderState == '취소' ? 'selected':'' }"/>>취소</option>
									<option value="배송전" <c:out value="${o.orderState == '배송전' ? 'selected':'' }"/>>배송전</option>
									<option value="배송중" <c:out value="${o.orderState == '배송중' ? 'selected':'' }"/>>배송중</option>
									<option value="배송완료" <c:out value="${o.orderState == '배송완료' ? 'selected':'' }"/>>배송완료</option>
								</select>	
							</c:if>
						</form>
						<c:if test="${o.orderState eq '반품신청'}">
							<a href="${pageContext.request.contextPath}/admin/returnHistoryList">반품승인</a>
						</c:if>
						<c:if test="${o.orderState eq '반품' || o.orderState eq '구매확정' || o.orderState eq '취소' || o.orderState eq '반품완료'}">
							${o.orderState}
						</c:if>
					</td>
					<td>${o.createdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>