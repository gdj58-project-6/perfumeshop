<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
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
	
	<title>addGoods</title>
	</head>
	<body>
		<span>&nbsp;</span>
		<h2 class="m-b-20">상품등록</h2>
		<form action="${pageContext.request.contextPath}/admin/addGoods" method="post" enctype="multipart/form-data">
			<div class="bor19 size-218 m-b-20">
				상품이름  <input class="stext-111 cl2 plh3 size-100"  type="text" name="goodsName">
			</div>
			<div class="bor19 size-218 m-b-20">
				상품가격  <input class="stext-111 cl2 plh3 size-100" type="number" name="goodsPrice">
			</div>	
			<div class="m-b-20">
				상품종류 
				<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
					<select name="goodsCategory" class="js-select2">
						<option value="EDT">EDT</option>
						<option value="EDP">EDP</option>
						<option value="기타향수">기타향수</option>
						<option value="헤어퍼퓸">헤어퍼퓸</option>
						<option value="바디워시/스크럽">바디워시/스크럽</option>
						<option value="바디로션/오일">바디로션/오일</option>
						<option value="핸드케어/풋 케어">핸드케어/풋 케어</option>
						<option value="캔들">캔들</option>
						<option value="디퓨저">디퓨저</option>
						<option value="방향제">방향제</option>
						<option value="PERSONAL CARE">PERSONAL CARE</option>
						<option value="기타">기타</option>
					</select>
					<div class="dropDownSelect2"></div>
				</div>	
			</div>
			<div>
				상품설명 
			</div>
			<div class="bor19 m-b-20">
				<textarea class="stext-111 cl2 plh3 size-124 p-lr-18 p-tb-15" name="goodsMemo"></textarea>
			</div>
			<div class="m-b-20">
				품절여부 
				<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
					<select name="soldout" class="js-select2">	
						<option value="Y">Y</option> 
						<option value="N">N</option>
					</select>	
					<div class="dropDownSelect2"></div>
				</div>		  
			</div>
			<!-- hit 값은 기본 0-->		
			<div class="bor19 size-218 m-b-20">
				hit  <input type="number" name="hit" value="0">
			</div>
			<!-- empId는 받와아야 함 -> 해결했음 -->
			<div class="m-b-20">
				작성자  <input type="text" name="empId" value="${loginMember.empId}" readonly="readonly">
			</div>
			<div class="m-b-20">
				상품이미지  <input type="file" name="goodsImg">
			</div>
			<div class="m-b-20">
				<button class="flex-c-m stext-101 cl0 size-50 bg1 bor1 hov-btn2 p-lr-15 trans-04" type="submit">상품등록</button>
			</div>
		</form>
		<a class="how-pagination1 trans-04 m-all-10" href='${pageContext.request.contextPath}/home'>홈</a>

		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
		<script src="../vendor/animsition/js/animsition.min.js"></script>
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../vendor/select2/select2.min.js"></script>
		<script>
			$(".js-select2").each(function(){
				$(this).select2({
					minimumResultsForSearch: 20,
					dropdownParent: $(this).next('.dropDownSelect2')
				});
			})
		</script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>