<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyGoodsQuestion</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="../vendor/slick/slick.css">
		<link rel="stylesheet" type="text/css" href="../vendor/MagnificPopup/magnific-popup.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
	</head>
	<body>
		<h1>상품문의수정</h1>
		${msg}
		<form action="${pageContext.request.contextPath}/member/modifyGoodsQuestion" method="post">
			<table border="1">
				<tr>
					<td>문의번호</td>
					<td>
						<input type="text" name="goodsQuestionCode" value="${goodsQuestion.goodsQuestionCode}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>상품번호</td>
					<td>${goodsQuestion.goodsCode}</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>${goodsQuestion.customerId}</td>
				</tr>
				<tr>
					<td>분류</td>
					<td>${goodsQuestion.category}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="goodsQuestionMemo">${goodsQuestion.goodsQuestionMemo}</textarea>
					</td>
				</tr>
				<tr>
					<td>작성일자</td>
					<td>${goodsQuestion.createdate}</td>
				</tr>
			</table>
			<div>
				<button type="submit">수정</button>
			</div>
		</form>
	</body>
</html>