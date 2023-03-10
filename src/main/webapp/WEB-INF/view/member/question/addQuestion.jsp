<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<title>orderQuestion</title>
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
		    $(document).ready(function(){

		    	$("#memo").focus();
		    	
		    	$("#btnCancel").click(function(){
		    		var cancel = confirm("변경사항이 저장되지 않을 수 있습니다.");
		    		if(cancel == true) {
		    			$(location).attr("href", "${pageContext.request.contextPath}/member/orderOne?orderCode=${orderCode}");
		    		} else {
		    			return false;
		    		}
		    		
		    	});
		    	
		    	$("#btn").click(function() {
		    		if($("#category option:selected").val() == '') {
		    			alert("분류를 선택해주세요.");
		    		} else if($("#memo").val().length == 0) {
		    			alert("문의내용을 입력해주세요.");
		    		} else {
		    			var insert = confirm("문의사항을 등록하시겠습니까?");
		    			if(insert == true) {
		    				alert("문의사항 등록완료");
		    			} else {
		    				return false;
		    			}
		    		}
		    	});
		    });
		</script>
	</head>
	<body>
		<!-- 고객or비로그인 -->
		<c:if test="${loginMember == null || loginMember.getAuthCode() < 4}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<div align="center" class="po p-t-80">
			<h1>문의작성</h1>
			<br>
			<form action="${pageContext.request.contextPath}/member/addQuestion" method="post">
				<table class="table table-bordered" style="width:1000px;">
					<tr>
						<td align="center">주문번호</td>
						<td>
							<input type="text" name="orderCode" value="${orderCode}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td align="center">분류</td>
						<td>
							<select class="js-select2" name="category" id="category">
								<option value="">분류</option>
								<option value="할인">할인</option>
								<option value="포인트">포인트</option>
								<option value="구매">구매</option>
								<option value="결제">결제</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" style="vertical-align:middle;">문의내용</td>
						<td>
							<textarea cols="100" rows="20" name="questionMemo" id="memo">${msg}</textarea>
						</td>
					</tr>
				</table>
				<br>
				<div>
					<button id="btnCancel" style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="button">
						취소하기
					</button>
					<button style="display: inline-block;" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10" type="submit" id="btn">
						등록하기
					</button>
				</div>
			</form>
		</div>
	</body>
</html>