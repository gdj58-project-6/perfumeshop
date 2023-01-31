<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<style>
			.po {
				position : relative;
				top : 100px;
			}
			table {
				width : 1000px;
				height : 500px;
			}
		</style>
		<meta charset="UTF-8">
		<title>modifyQuestion</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="../vendor/slick/slick.css">
		<link rel="stylesheet" type="text/css" href="../vendor/MagnificPopup/magnific-popup.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
		    $(document).ready(function(){
		    	$("#btnCancel").click(function(){
		    		$(location).attr("href", "${pageContext.request.contextPath}/member/question")
		    	})
		    	
		    	$('#memo').focus();
		    });
		</script>
	</head>
	<body>
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		<div align="center" class="po">
			<h1>문의수정</h1>
			${msg}
			<br>
			<form action="${pageContext.request.contextPath}/member/modifyQuestion" method="post">
				<table class="table table-bordered" style="width:1000px;">
					<tr>
						<td align="center" style="vertical-align:middle;">문의번호</td>
						<td style="vertical-align:middle;">
							<input type="text" name="questionCode" value="${modifyQuestion.questionCode}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">주문번호</td>
						<td style="vertical-align:middle;">${modifyQuestion.orderCode}</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">작성자</td>
						<td style="vertical-align:middle;">${modifyQuestion.customerId}</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">분류</td>
						<td style="vertical-align:middle;">${modifyQuestion.category}</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">내용</td>
						<td>
							<textarea cols="50" rows="5" name="questionMemo" id="memo">${modifyQuestion.questionMemo}</textarea>
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">작성일자</td>
						<td style="vertical-align:middle;">${modifyQuestion.createdate}</td>
					</tr>
				</table>
				<div>
					<button id="btnCancel" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						취소하기
					</button>
					<button style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
						수정하기
					</button>
				</div>
			</form>
		</div>
	</body>
</html>