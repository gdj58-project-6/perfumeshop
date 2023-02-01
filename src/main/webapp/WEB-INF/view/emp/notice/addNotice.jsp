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
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
		<link rel="stylesheet" type="text/css" href="../vendor/slick/slick.css">
		<link rel="stylesheet" type="text/css" href="../vendor/MagnificPopup/magnific-popup.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
	</head>
	<body>
		<h1 class="text-center" style="margin-top:100px;">공지글 작성</h1>
		${msg}
		<form action="${pageContext.request.contextPath}/admin/addNotice" method="post">
			<table class="table">
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="noticeTitle">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea cols="50" rows="5" name="noticeContent"></textarea>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name="empId" value="${loginMember.getEmpId()}" readonly="readonly">
					</td>
				</tr>
			</table>
			<div>
				<button type="submit">입력</button>
			</div>
		</form>
	</body>
</html>