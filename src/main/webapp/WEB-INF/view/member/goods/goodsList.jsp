<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>goodsList</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
   	 	<script>
	        $(document).ready(function() {
	           $('#rowPerPage').change(function() {
	              $('#pageForm').submit();
	              alert('change');
	           })
	           
	           $('#sort').change(function() {
	              $('#pageForm').submit();
	              alert('change');
	           })
	        });
     	</script>
	</head>
	<body class="animsition">
		<!-- Header -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<!-- Product -->
		<div class="bg0 m-t-23 p-b-140" style="margin-top:80px;">
			<div class="container">
				<div class="flex-w flex-sb-m p-b-52">
					<div class="flex-w flex-l-m filter-tope-group m-tb-10">
						<a href="${pageContext.request.contextPath}/member/goodsList" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 " data-filter="*">
							모든상품
						</a>
						
						<a href="${pageContext.request.contextPath}/member/goodsList?category=EDT&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".EDT">
							EDT
						</a>
						
						<a href="${pageContext.request.contextPath}/member/goodsList?category=EDP&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".EDP">
							EDP
						</a>
						
						<a href="${pageContext.request.contextPath}/member/goodsList?category=기타향수&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".기타향수">
							기타향수
						</a>	
						<a href="${pageContext.request.contextPath}/member/goodsList?category=헤어퍼퓸&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".헤어퍼퓸">
							헤어퍼퓸
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=바디워시/스크럽&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".바디워시/스크럽">
							바디워시/스크럽
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=바디로션/오일&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".바디로션/오일">
							바디로션/오일
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=핸드케어/풋 케어&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".핸드케어/풋 케어">
							핸드케어/풋 케어
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=캔들&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".캔들">
							캔들
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=디퓨저&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".디퓨저">
							디퓨저
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=방향제&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".방향제">
							방향제
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=PERSONAL CARE&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".PERSONAL CARE">
							PERSONAL CARE
						</a>
						<a href="${pageContext.request.contextPath}/member/goodsList?category=기타&sort=${sort}" class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5" data-filter=".기타">
							기타
						</a>
					</div>
	
					<div class="flex-w flex-c-m m-tb-10">
						<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
							<i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
							<i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
							 Filter
						</div>
	
						<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
							<i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
							<i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
							Search
						</div>
					</div>
					<!-- Filter -->
					
					<div class="dis-none panel-filter w-full p-t-10">
						<div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
							<div class="filter-col1 p-r-15 p-b-27">
								<div class="mtext-102 cl2 p-b-15">
									정렬
								</div>
								<form id ="pageForm" method="get" action="${pageContext.request.contextPath}/member/goodsList">
									<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
										<select class="js-select2" name="rowPerpage" id="rowPerPage">
											<option value="10" <c:out value="${rowPerPage == '10' ? 'selected':'' }"/>>10</option>
											<option value="20" <c:out value="${rowPerPage == '20' ? 'selected':'' }"/>>20</option>
											<option value="30" <c:out value="${rowPerPage == '30' ? 'selected':'' }"/>>30</option>	
										</select>
										<div class="dropDownSelect2"></div>
									</div>
									<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
										<select class="js-select2" name="sort" id="sort">
											<option value="createdate" <c:out value="${sort == 'createdate' ? 'selected':'' }"/>>신상품순</option>
											<option value="hit" <c:out value="${sort == 'hit' ? 'selected':'' }"/>>인기상품순</option>
											<option value="lowPrice" <c:out value="${sort == 'lowPrice' ? 'selected':'' }"/>>낮은가격순</option>
											<option value="highPrice" <c:out value="${sort == 'highPrice' ? 'selected':'' }"/>>높은가격순</option>
										</select>
										<div class="dropDownSelect2"></div>
									</div>
									<input type="hidden" name="category" value="${category}">
									<input type="hidden" name="word" value="${word}">
								</form>	
							</div>
						</div>
					</div>
				</div>
				<!-- Search product -->
				<form action="${pageContext.request.contextPath}/member/goodsList" method="get">	
					<div class="dis-none panel-search w-full p-t-10 p-b-15">
						<div class="bor8 dis-flex p-l-15">
							<input type="hidden" name="rowPerPage" value="${rowPerPage}">
							<input type="hidden" name="sort" value="${sort}">
							<input type="hidden" name="category" value="${category}">
							<button type="submit" class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
								<i class="zmdi zmdi-search"></i>
							</button>
							<input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" name="word" placeholder="Search">
						</div>	
					</div>
				</form>
				<div class="row isotope-grid">
					<c:forEach var="m" items="${list}" varStatus="s">
						<div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
							<!-- Block2 -->
							<div class="block2">
								<div class="block2-pic hov-img">
									<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200">
								</div>
								<div class="block2-txt flex-w flex-t p-t-14">
									<div class="block2-txt-child1 flex-col-l ">
										<a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
											${m.goodsName}
										</a>
										<span class="stext-105 cl3">
											${m.goodsPrice}원
										</span>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>	
			</div>
			<div class="text-center">
		         <!-- 현재 페이지가 1보다 클때만 이전버튼 나오게 -->
		         <c:if test="${currentPage > 1 && word != ''}"> 
		            <a class="how-pagination1 trans-04 m-all-7" href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}&sort=${sort}&category=${category}">이전</a>
		         </c:if>
		         <c:if test="${currentPage > 1 && word == ''}">
		            <a class="how-pagination1 trans-04 m-all-7" href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&sort=${sort}&category=${category}">이전</a>
		         </c:if>
		         <span>${currentPage}</span>
		         <!-- 현재 페이지가 라스트페이지보다 작으면 다음버튼 나오게 -->
		         <c:if test="${currentPage < lastPage && word != ''}"> 
		         <a class="how-pagination1 trans-04 m-all-7" href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}&sort=${sort}&category=${category}">다음</a>
		         </c:if>   
		         <c:if test="${currentPage < lastPage && word == ''}">
		            <a class="how-pagination1 trans-04 m-all-7" href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&sort=${sort}&category=${category}">다음</a>
		         </c:if>
			</div>
		</div>
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	
		<!-- Back to top -->
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top">
				<i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	
		<!-- Modal1 -->
		<div class="wrap-modal1 js-modal1 p-t-60 p-b-20">
			<div class="overlay-modal1 js-hide-modal1"></div>
	
			<div class="container">
				<div class="bg0 p-t-60 p-b-30 p-lr-15-lg how-pos3-parent">
					<button class="how-pos3 hov3 trans-04 js-hide-modal1">
						<img src="../images/icons/icon-close.png" alt="CLOSE">
					</button>
	
					<div class="row">
						<div class="col-md-6 col-lg-7 p-b-30">
							<div class="p-l-25 p-r-30 p-lr-0-lg">
								<div class="wrap-slick3 flex-sb flex-w">
									<div class="wrap-slick3-dots"></div>
									<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
	
									<div class="slick3 gallery-lb">
										<div class="item-slick3" data-thumb="images/product-detail-01.jpg">
											<div class="wrap-pic-w pos-relative">
												<img src="../images/product-detail-01.jpg" alt="IMG-PRODUCT">
	
												<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-01.jpg">
													<i class="fa fa-expand"></i>
												</a>
											</div>
										</div>
	
										<div class="item-slick3" data-thumb="images/product-detail-02.jpg">
											<div class="wrap-pic-w pos-relative">
												<img src="../images/product-detail-02.jpg" alt="IMG-PRODUCT">
	
												<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-02.jpg">
													<i class="fa fa-expand"></i>
												</a>
											</div>
										</div>
	
										<div class="item-slick3" data-thumb="images/product-detail-03.jpg">
											<div class="wrap-pic-w pos-relative">
												<img src="../images/product-detail-03.jpg" alt="IMG-PRODUCT">
	
												<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/product-detail-03.jpg">
													<i class="fa fa-expand"></i>
												</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-6 col-lg-5 p-b-30">
							<div class="p-r-50 p-t-5 p-lr-0-lg">
								<h4 class="mtext-105 cl2 js-name-detail p-b-14">
									Lightweight Jacket
								</h4>
	
								<span class="mtext-106 cl2">
									$58.79
								</span>
	
								<p class="stext-102 cl3 p-t-23">
									Nulla eget sem vitae eros pharetra viverra. Nam vitae luctus ligula. Mauris consequat ornare feugiat.
								</p>
								
								<!--  -->
								<div class="p-t-33">
									<div class="flex-w flex-r-m p-b-10">
										<div class="size-203 flex-c-m respon6">
											Size
										</div>
	
										<div class="size-204 respon6-next">
											<div class="rs1-select2 bor8 bg0">
												<select class="js-select2" name="time">
													<option>Choose an option</option>
													<option>Size S</option>
													<option>Size M</option>
													<option>Size L</option>
													<option>Size XL</option>
												</select>
												<div class="dropDownSelect2"></div>
											</div>
										</div>
									</div>
	
									<div class="flex-w flex-r-m p-b-10">
										<div class="size-203 flex-c-m respon6">
											Color
										</div>
	
										<div class="size-204 respon6-next">
											<div class="rs1-select2 bor8 bg0">
												<select class="js-select2" name="time">
													<option>Choose an option</option>
													<option>Red</option>
													<option>Blue</option>
													<option>White</option>
													<option>Grey</option>
												</select>
												<div class="dropDownSelect2"></div>
											</div>
										</div>
									</div>
	
									<div class="flex-w flex-r-m p-b-10">
										<div class="size-204 flex-w flex-m respon6-next">
											<div class="wrap-num-product flex-w m-r-20 m-tb-10">
												<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-minus"></i>
												</div>
	
												<input class="mtext-104 cl3 txt-center num-product" type="number" name="num-product" value="1">
	
												<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
													<i class="fs-16 zmdi zmdi-plus"></i>
												</div>
											</div>
	
											<button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">
												Add to cart
											</button>
										</div>
									</div>	
								</div>
	
								<!--  -->
								<div class="flex-w flex-m p-l-100 p-t-40 respon7">
									<div class="flex-m bor9 p-r-10 m-r-11">
										<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
											<i class="zmdi zmdi-favorite"></i>
										</a>
									</div>
	
									<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
										<i class="fa fa-facebook"></i>
									</a>
	
									<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
										<i class="fa fa-twitter"></i>
									</a>
	
									<a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
										<i class="fa fa-google-plus"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
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
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>