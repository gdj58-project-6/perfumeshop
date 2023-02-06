<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>memberOne</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#memberOneBtn').click(function() {
					if($('#name').val() == ''
						|| $('#phone').val() == ''
						|| $('#pw').val() == '') {
						alert('입력되지않은 항목이 있습니다');
					}
					
					$('#memberOneForm').submit();
					
				})
				$('#addressBtn').click(function() {
					if($('#adress').val() == '') {
						alert('주소를 입력해주세요');
					}
					
					$('#addressForm').submit();
				
				})
				
			})
		</script>
	</head>
	<body>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<div class="container p-t-60">
			<div class="bor10 m-t-50 p-t-43 p-b-40 col-sm-8 m-lr-auto">
				<div class="row">
					<div class="col-sm-10 col-lg-7 col-xl-4 m-lr-auto m-b-30">
						<h4 class="mtext-109 cl2 p-b-30 text-center">주문자 정보</h4>
						<div class="flex-w flex-t p-t-5 p-b-30">
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> ID </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">
									${memberOne.customerId}
									<!-- 회원정보 수정 modal -->
									<button type="button" class="m-l-20 stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5" data-bs-toggle="modal" data-bs-target="#memberOne">정보 수정</button>
								</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Name </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${memberOne.customerName}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Phone </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">
									<c:set var="phone" value="${memberOne.customerPhone}"></c:set>
									${fn:substring(phone, 0, 3)}-${fn:substring(phone, 3, 7)}-${fn:substring(phone, 7, 11)}
								</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Total Price </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${memberOne.totalPrice}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Point </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${memberOne.point}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Grade </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${memberOne.authCode}</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> Address </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">
									${memberOne.address}
									<!-- 회원 주소 수정 modal -->
									<button type="button" class="m-l-20 stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5" data-bs-toggle="modal" data-bs-target="#address">주소 수정</button>
								</p>
							</div>
							<div class="size-208 w-full-ssm p-t-10">
								<span class="stext-110 cl2"> createdate </span>
							</div>
							<div class="size-209 p-r-18 p-r-0-sm w-full-ssm p-t-10">
								<p class="m-l-15 stext-111 cl6 p-t-2">${memberOne.createdate}</p>
							</div>
						</div>
						<div class="text-center">
							<a href="/perfumeshop/member/removeMember" class="stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">회원탈퇴</a> 
							<a href="/perfumeshop/member/modifyMemberPw" class="stext-115 cl6 hov-cl1 trans-04 p-tb-8 p-lr-4">비밀번호수정</a>
						</div>
						<div>
							<!-- 회원정보 수정 modal -->
							<form action="${pageContext.request.contextPath}/member/modifyMember" method="post" id="memberOneForm">
								<div class="modal p-t-200" id="memberOne">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">회원 정보 수정</h4>
												<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
											</div>
											<div class="modal-body">
												<label class="stext-102 cl3" for="name">Name</label> 
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" type="text" id="name" name="name" value="${memberOne.customerName}"> 
												
												<label class="stext-102 cl3" for="phone">Phone</label> 
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" type="text" id="phone" name="phone" value="${memberOne.customerPhone}"> 
												
												<label class="stext-102 cl3" for="pw">Password</label> 
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" type="text" id="pw" name="pw">
											</div>
											<div class="modal-footer">
												<button type="button" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10" id="memberOneBtn">수정</button>
											</div>
										</div>
									</div>
								</div>
							</form>
							<!-- 회원 주소 수정 modal -->
							<form action="${pageContext.request.contextPath}/member/modifyMemberAddress" method="post" id="addressForm">
								<div class="modal p-t-200" id="address">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">주소 수정</h4>
												<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
											</div>
											<div class="modal-body">
												<label class="stext-102 cl3" for="address">Address</label> 
												<input class="size-111 bor8 stext-102 cl2 p-lr-20" type="text" id="address2" name="address" value="${memberOne.address}">
											</div>
											<div class="modal-footer">
												<button type="button" onclick="address_search()" class="m-l-20 stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5">주소검색</button>
											</div>
											<div class="modal-footer">
												<button type="button" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10" id="addressBtn">수정</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/daterangepicker/moment.min.js"></script>
		<script src="../vendor/slick/slick.min.js"></script>
		<script src="../js/slick-custom.js"></script>
		<script src="../vendor/parallax100/parallax100.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/isotope/isotope.pkgd.min.js"></script>
		<script src="../js/main.js"></script>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
			function address_search() {
			    new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
			
			            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
			            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			            var addr = ''; // 주소 변수
			
			            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
			                addr = data.roadAddress;
			            } else { // 사용자가 지번 주소를 선택했을 경우(J)
			                addr = data.jibunAddress;
			            }
			
			            // 우편번호와 주소 정보를 해당 필드에 넣는다.
			            document.getElementById("address2").value = addr;
			            // 커서를 상세주소 필드로 이동한다.
			            document.getElementById("address2").focus();
			        }
			    }).open();
			}
			</script>
	</body>
</html>