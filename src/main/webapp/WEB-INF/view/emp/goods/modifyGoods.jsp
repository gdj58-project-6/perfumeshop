<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>modifyGoods</title>
	</head>
	<body>
		<span>&nbsp;</span>
		<h3 class="m-b-20">상품 수정</h3>
		<form action="${pageContext.request.contextPath}/admin/modifyGoods" method="post" enctype="multipart/form-data">		
			<c:forEach var="b" items="${list}">
		 		<div class="m-b-20"> 	
			 		<img src="${pageContext.request.contextPath}/upload/${b.fileName}" width="200" height="200">
			 	 	<input type="file" name="goodsImg">
				</div>
				<div class="bor19 size-218 m-b-20">
					상품이름  <input class="stext-111 cl2 plh3 size-100" type="text" name="goodsName" value="${b.goodsName}">
				</div>
				<div class="bor19 size-218 m-b-20">
					상품가격  <input class="stext-111 cl2 plh3 size-100" type="number" name="goodsPrice" value="${b.goodsPrice}">
				</div>	
				<div class="m-b-20">
					<span>상품종류</span> 
					<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
						<select name="goodsCategory" class="js-select2">
							<option value="EDT" <c:out value="${b.goodsCategory == 'EDT' ? 'selected':'' }"/>>EDT</option>
							<option value="EDP" <c:out value="${b.goodsCategory == 'EDP' ? 'selected':'' }"/>>EDP</option>
							<option value="기타향수" <c:out value="${b.goodsCategory == '기타향수' ? 'selected':'' }"/>>기타향수</option>
							<option value="헤어퍼퓸" <c:out value="${b.goodsCategory == '헤어퍼퓸' ? 'selected':'' }"/>>헤어퍼퓸</option>
							<option value="바디워시/스크럽" <c:out value="${b.goodsCategory == '바디워시/스크럽' ? 'selected':'' }"/>>바디워시/스크럽</option>
							<option value="바디로션/오일" <c:out value="${b.goodsCategory == '바디로션/오일' ? 'selected':'' }"/>>바디로션/오일</option>
							<option value="핸드케어/풋 케어" <c:out value="${b.goodsCategory == '핸드케어/풋 케어' ? 'selected':'' }"/>>핸드케어/풋 케어</option>
							<option value="캔들" <c:out value="${b.goodsCategory == '캔들' ? 'selected':'' }"/>>캔들</option>
							<option value="디퓨저" <c:out value="${b.goodsCategory == '디퓨저' ? 'selected':'' }"/>>디퓨저</option>
							<option value="방향제" <c:out value="${b.goodsCategory == '방향제' ? 'selected':'' }"/>>방향제</option>
							<option value="PERSONAL CARE" <c:out value="${b.goodsCategory == 'PERSONAL CARE' ? 'selected':'' }"/>>PERSONAL CARE</option>
							<option value="기타" <c:out value="${b.goodsCategory == '기타' ? 'selected':'' }"/>>기타</option>
						</select>
						<div class="dropDownSelect2"></div>
					</div>	
				</div>
				<div>
					상품설명 
				</div>
				<div class="bor19 m-b-20">
					<textarea class="stext-111 cl2 plh3 size-124 p-lr-18 p-tb-15" rows="5" cols="50" name="goodsMemo">${b.goodsMemo}</textarea>
				</div>
				<div class="m-b-20">
					품절여부 
					<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
						<select name="soldout" class="js-select2">	
							<option value="Y" <c:out value="${b.soldout == 'Y' ? 'selected':'' }"/>>Y</option> 
							<option value="N" <c:out value="${b.soldout == 'N' ? 'selected':'' }"/>>N</option>
						</select>	
						<div class="dropDownSelect2"></div>
					</div>		  
				</div>
				<!-- hit 값은 기본 0으로 설정 상품이 하나 팔릴때마다 1씩 증가 -->		
				<div class="bor19 size-218 m-b-20">
					hit : <input type="number" name="hit" value="${b.hit}">
				</div>		
				<div>
					<input type="hidden" name="goodsCode" value="${b.goodsCode}">
				</div>
				<div class="m-b-20">
					<button class="flex-c-m stext-101 cl0 size-50 bg1 bor1 hov-btn2 p-lr-15 trans-04" type="submit">상품수정</button>
				</div>
			</c:forEach>		
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