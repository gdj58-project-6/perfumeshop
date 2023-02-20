<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>sign up</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
		$(document).ready(function() {
			$('#addBtn').click(function() {
				if($('#id').val() == ''
				|| $('#name').val() == ''
				|| $('#phone').val() == ''
				|| $('#address').val() == ''
				|| $('#pw').val() == ''
				|| $('#pw2').val() == '') {
					alert('입력되지않은 항목이 있습니다');
					return;
				}

				if($('#id').val() < 3) {
					alert('아이디는 3자이상 입력해주세요');
					return;
				}
				
				if($('#name').val() < 2) {
					alert('이름은 2자이상 입력해주세요');
					return;
				}
				
				if($('#phone').val() < 11) {
					alert('전화번호 11자리를 입력해주세요');
					return;
				}
				
				if($('#pw').val() < 4) {
					alert('비밀번호는 4자이상 입력해주세요');
					return;
				}
				
				if($('#pw').val() != $('#pw').val()) {
					alert('비밀번호를 확인해주세요');
					return;
				}
				
				$('#addForm').submit();
			})
		})
		</script>
	</head>
	<body>
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<div class="container">
			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<form action="${pageContext.request.contextPath}/member/addMember" method="post" id="addForm">
					<div class="row">
						<div class="col-5">
							<div class="p-b-30 m-l-200">
								<h5 class="mtext-108 cl2 p-b-7">회원 가입</h5>
								<div class="row p-t-50 p-b-20">
									<div class="col-sm-12 p-b-5">
										<label class="stext-102 cl3" for="id">ID</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="id" type="text" name="id" oninput="this.value = this.value.replaceAll(/[^a-z|A-Z|0-9]/g, '')">
										</div>
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="name">Name</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name" oninput="this.value = this.value.replaceAll(/[^a-z|A-Z|ㄱ-ㅎ|가-힣]/g, '')">
										</div>
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="phone">Phone</label>
										<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="phone" type="text" name="phone" placeholder="-는 제외해주세요" oninput="this.value = this.value.replaceAll(/\D/g, '')">
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										${msg}
									</div>
								</div>
							</div>
						</div>
						<div class="col-5">
							<div class="p-b-30 m-l-200">
								<div class="row p-t-83 p-b-20">
									<div class="col-sm-12 p-b-5">
										<label class="stext-102 cl3" for="address">Address</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="address" type="text" name="address">
										</div>
										<button type="button" onclick="address_search()" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10">주소검색</button>
									</div>
								</div>
								
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="pw">Password</label>
										<div align="right">
											<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw" type="password" name="pw" oninput="this.value = this.value.replaceAll(/[^a-z|A-Z|0-9]/g, '')">
										</div>
									</div>
								</div>
								<div class="row p-b-20">
									<div class="col-12 p-b-5">
										<label class="stext-102 cl3" for="pw2">Check Password</label>
										<input class="size-111 bor8 stext-102 cl2 p-lr-20" id="pw2" type="password" oninput="this.value = this.value.replaceAll(/[^a-z|A-Z|0-9]/g, '')">
									</div>
								</div>
								<div class="row p-b-10">
									<div class="col-12 p-b-5">
										<button type="button" class="flex-c-m stext-101 cl0 size-112 bg7 bor11 p-lr-15 trans-04 m-b-10" id="addBtn">
											회원가입
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
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
		       
		                document.getElementById("address").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("address").focus();
		            }
		        }).open();
		    }
		</script>
	</body>
</html>