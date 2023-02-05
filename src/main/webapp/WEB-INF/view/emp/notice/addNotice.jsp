<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addNotice</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				
				$('#memo').focus();
				
				$("#btn").click(function(){
					var cancel = confirm("변경사항이 저장되지 않을 수 있습니다.");
					if(cancel == true) {
						$(location).attr("href", "${pageContext.request.contextPath}/admin/notice");
					} else {
						return false;
					}
		    	});
				
				$('#insert').click(function() {
					var insert = confirm("공지를 입력하시겠습니까?");
					if(insert == true) {
						alert("공지입력 완료");
					} else {
						return false;
					}
				});
				
			});
		</script>
	</head>
	<body>
		<!-- 직원 -->
		<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		<div align="center" class="po">
			<h1 class="text-center" style="margin-top:100px;">공지글 작성</h1>
			${msg}
			<br>
			<form action="${pageContext.request.contextPath}/admin/addNotice" method="post">
				<table class="table table-bordered" style="width:750px;">
					<tr>
						<td align="center" style="vertical-align:middle; style:100px;">제목</td>
						<td>
							<textarea cols="80" rows="1" name="noticeTitle" placeholder="제목을 입력해주세요...." id="memo"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle; style:100px;">내용</td>
						<td>
							<textarea cols="80" rows="8" name="noticeContent" placeholder="내용을 입력해주세요...."></textarea>
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle; style:100px;">작성자</td>
						<td>
							<input type="text" name="empId" value="${loginMember.getEmpId()}" readonly="readonly">
						</td>
					</tr>
				</table>
				<br>
				<div>
					<button id="btn" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						취소
					</button>
					<button id="insert" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit">
						입력
					</button>
				</div>
			</form>
		</div>
	</body>
</html>