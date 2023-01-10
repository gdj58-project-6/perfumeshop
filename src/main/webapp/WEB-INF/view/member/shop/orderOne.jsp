<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table>
			<tr>
				<td>상품</td>
				<td>${orderOne.goodsName}</td>
			</tr>
			<tr>
				<td>상품 사진</td>
				<td>
					<img src="${pageContext.request.contextPath}/upload/${orderOne.filename}" width="200" height="200">
				</td>
			</tr>
			<tr>
				<td>상품 가격</td>
				<td>${orderOne.goodsPrice}</td>
			</tr>
			<tr>
				<td>구매 개수</td>
				<td>${orderOne.orderQuantity}</td>
			</tr>
			<tr>
				<td>구매자 아이디</td>
				<td>${orderOne.customerId}</td>
			</tr>
			<tr>
				<td>구매자 이름</td>
				<td>${orderOne.customerName}</td>
			</tr>
			<tr>
				<td>구매자 전화번호</td>
				<td>${orderOne.customerPhone}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${orderOne.address}</td>
			</tr>
			<tr>
				<td>주문 메모</td>
				<td>${orderOne.orderMemo}</td>
			</tr>
			<tr>
				<td>주문 상태</td>
				<td>${orderOne.orderState}</td>
			</tr>
			<tr>
				<td>주문 일자</td>
				<td>${orderOne.createdate}</td>
			</tr>
		</table>
	</body>
</html>