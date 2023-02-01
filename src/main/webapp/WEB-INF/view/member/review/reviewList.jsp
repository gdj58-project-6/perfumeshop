<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Review List</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('.flex-c-m').click(function() {
					 let html = 
						`<textarea name="reviewMemo" class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10"></textarea>
						<br>
						<button type="submit" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">수정하기</button>`
					$(this.form).children('div').html(html);
					// form안에 있는 div를 찾아서 html 넣는 코드
				})
			})
		</script>
		<style>
			textarea {
				resize: none;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row m-lr-200">
				<c:forEach var="r" items="${reviewList}">
					<div class="col-md-8 col-lg-8 p-b-50">
						<div class="p-r-45 p-r-0-lg">
							<div class="p-t-32">
								<span class="flex-w flex-m stext-111 cl2 p-b-5">
									<span>
										${r.orderCode}
									</span>
								</span>
	
								<span>
									<img src="${pageContext.request.contextPath}/upload/${r.filename}" width="100" height="100">	
								</span>
								<span class="m-l-30">${r.goodsName}</span>
	
								<p class="stext-117 cl6 p-b-10 m-l-150">
									${r.reviewMemo}
								</p>
								
								<p class="stext-117 cl6 p-b-10 m-l-150">
									${r.createdate}
								</p>
							</div>
	
							<div class="flex-w flex-t p-t-16" align="right">
								<div class="flex-w size-217">
									<form action="${pageContext.request.contextPath}/member/modifyReview" method="post">
										<span>
											<input type="hidden" name="orderCode" value="${r.orderCode}">
											<input type="hidden" name="goodsCode" value="${r.goodsCode}">
										</span>
										<div><button type="button" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">리뷰 수정</button></div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-lg-4">
						<div class="flex-w flex-t p-t-90">
							<div class="flex-w size-217">
								<a href="${pageContext.request.contextPath}/member/removeRiview?orderCode=${r.orderCode}&goodsCode=${r.goodsCode}&point=${r.point}&id=${r.customerId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 trans-04 m-r-5 m-b-5">
									삭제
								</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</body>
</html>