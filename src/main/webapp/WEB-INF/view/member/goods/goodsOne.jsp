<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>goods One</title>
		<meta charset="UTF-8">
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
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
		    $(document).ready(function() {
		
				//When page loads...
		        $(".tab_content").hide(); //Hide all content
		        $("ul.tabs li:first").addClass("active").show(); //Activate first tab
		        $(".tab_content:first").show(); //Show first tab content
		
		        //On Click Event
		        $("ul.tabs li").click(function() {
		
		            $("ul.tabs li").removeClass("active"); //Remove any "active" class
		            $(this).addClass("active"); //Add "active" class to selected tab
		            $(".tab_content").hide(); //Hide all tab content
		
		            var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		            $(activeTab).fadeIn(); //Fade in the active ID content
		            return false;
		        });
		        
				// memu 클래스 바로 하위에 있는 a 태그를 클릭했을때
		        $(".menu>a").click(function(){
		            var submenu = $(this).next("ul");
		 
		            // submenu 가 화면상에 보일때는 위로 보드랍게 접고 아니면 아래로 보드랍게 펼치기
		            if( submenu.is(":visible") ){
		                submenu.slideUp();
		            }else{
		                submenu.slideDown();
		            }
		        });
				
				/*
				장바구니 담기 버튼 누르면 성공했다는 창 하나 나오게
				let cartAddBtn = document.querySelector('#cartAddBtn');
				
				cartAddBtn.addEventListener('click', function(){
					// console.log('장바구니 추가 클릭!');
					alert('장바구니 담기 성공!');
				});
				*/
				
				// 바로구매시 넘길 수량
				$('#order').click(function() {
					var cartQuantity = $('#cartQuantity').val();
					$('#goodsQuantity').val(cartQuantity);
					console.log(cartQuantity);
					$('#goodsPayMent').submit();
				})
		    });
		</script>
		<style>
			 .menu a {
	            cursor:pointer;
	         }
	         .menu .hide {
	            display:none;
	         }
			
			a {
				text-decoration : none;
			}
		</style>
	</head>
	<body class="animsition">
		
		<!-- Header -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		<!-- breadcrumb -->
		<div class="container" style="margin-top:70px;">
			<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
				<a href="${pageContext.request.contextPath}/home" class="stext-109 cl8 hov-cl1 trans-04">
					Home
					<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
				</a>
	
				<a href="${pageContext.request.contextPath}/member/goodsList" class="stext-109 cl8 hov-cl1 trans-04">
					${goodsOne.goodsCategory}
					<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
				</a>
	
				<span class="stext-109 cl4 m-l-9 m-r-10">
					${goodsOne.goodsName}
				</span>
			</div>
		</div>
		
		<!-- Product Detail -->
		<section class="sec-product-detail bg0 p-t-65 p-b-60">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-7 p-b-30">
						<div class="p-l-25 p-r-30 p-lr-0-lg">
							<div class="wrap-slick3 flex-sb flex-w">
								<div class="wrap-slick3-dots"></div>
								<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
								<div class="slick3 gallery-lb">
									<div class="item-slick3" data-thumb="../images/product-detail-01.jpg">
										<c:if test="${goodsOne.soldout == 'N'}">
											<div>
												<img src="${pageContext.request.contextPath}/upload/${goodsOne.fileName}">
											</div>
										</c:if>	
									</div>
									<div class="item-slick3" data-thumb="../images/product-detail-01.jpg">
										<c:if test="${goodsOne.soldout == 'Y'}">
											<div>
												<img src="${pageContext.request.contextPath}/upload/soldout.jpg">
											</div>
										</c:if>	
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-lg-5 p-b-30">
						<form action="${pageContext.request.contextPath}/member/goodsOne" method="post" id="goodsOne">
							<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
							<input type="hidden" name="filename" value="${goodsOne.fileName}">
							<input type="hidden" value="${goodsOne.goodsName}" name="goodsName" readonly="readonly">
							<input type="hidden" value="${goodsOne.goodsPrice}" name="goodsPrice" readonly="readonly">
							<c:if test="${loginMember.getAuthCode() < 4}">
								<input type="hidden" name="customerId" value="${loginMember.customerId}">
							</c:if>						
							<div class="p-r-50 p-t-5 p-lr-0-lg">
								<h4 class="mtext-105 cl2 js-name-detail p-b-14">
									${goodsOne.goodsName}
								</h4>
		
								<span class="mtext-106 cl2">
									${goodsOne.goodsPrice}원
								</span>
		
								<p class="stext-102 cl3 p-t-23">
									
								</p>
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-204 flex-w flex-m respon6-next">
										<div class="wrap-num-product flex-w m-r-20 m-tb-10">
											<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
												<i class="fs-16 zmdi zmdi-minus"></i>
											</div>
											<input class="mtext-104 cl3 txt-center num-product" type="number" value="1" name="cartQuantity"  id="cartQuantity">
											<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m m-b-15">
												<i class="fs-16 zmdi zmdi-plus"></i>
											</div>
										</div>
									</div>	
								</div>	
								<c:if test="${loginMember.getAuthCode() < 5 || loginMember == null}">
									<c:if test="${goodsOne.soldout == 'N'}">
										<div class="m-b-15">
											<button type="submit" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" id="cartAddBtn">
												장바구니 담기
											</button>
										</div>
									</c:if>
								</c:if>	
								<c:if test="${loginMember.getAuthCode() < 5 || loginMember == null}">
									<c:if test="${goodsOne.soldout == 'N'}">
										<div class="size-204 flex-w flex-m respon6-next m-b-15">
											<a href='${pageContext.request.contextPath}/member/cart' class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">장바구니로 이동</a>
										</div>
									</c:if>
								</c:if>
								<c:if test="${loginMember.getAuthCode() > 5} ">
									<div class="size-204 flex-w flex-m respon6-next m-b-15">
										<a href="${pageContext.request.contextPath}/admin/modifyGoods?goodsCode=${goodsCode}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail">상품수정</a>
									</div>
								</c:if>
							</div>
						</form>
						<c:if test="${loginMember != null && goodsOne.soldout == 'N'}">
							<div class="p-r-50 p-t-5 p-lr-0-lg">
								<div class="size-204 flex-w flex-m respon6-next">
									<form action="${pageContext.request.contextPath}/member/goodsPayMent" method="get" id="goodsPayMent">
										<input type="hidden" name="goodsCode" value="${goodsOne.goodsCode}">
										<input type="hidden" name="goodsQuantity" id="goodsQuantity">
										<button type="button" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" id="order">바로 구매</button>
									</form>
								</div>
							</div>	
						</c:if>	
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
		
			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							<a class="nav-link active" data-toggle="tab" href="#description" role="tab">상품정보</a>
						</li>
	
						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#information" role="tab">상품문의</a>
						</li>
	
						<li class="nav-item p-b-10">
							<a class="nav-link" data-toggle="tab" href="#reviews" role="tab">상품리뷰</a>
						</li>
					</ul>
	
					<!-- Tab panes -->
					<div class="tab-content p-t-43">
						<!-- tab1 상품정보 -->
						<div class="tab-pane fade show active" id="description" role="tabpanel">
							<div class="how-pos2 p-lr-15-md">
								<p class="stext-102 cl6">
									${goodsOne.goodsMemo}
								</p>
							</div>
						</div>
	
						<!-- tab2 상품문의 -->
						<div class="tab-pane fade" id="information" role="tabpanel">
							<div class="bg0 p-t-2 p-b-2">
								<div class="container">
									<div class="row">
										<div class="col-lg-12 m-lr-auto m-b-50">
											<div class="wrap-table-shopping-cart">
												<c:if test="${loginMember == null}">
													<a class="btn btn-secondary" href="${pageContext.request.contextPath}/member/login">상품문의</a>
												</c:if>
												<c:if test="${loginMember != null}">
													<a class="btn btn-secondary" href="${pageContext.request.contextPath}/member/addGoodsQuestion?goodsCode=${goodsCode}">상품문의</a>
												</c:if>
												<table class="table-shopping-cart">
													<tr align="center">
													   <th style="width: 150px;">문의번호</th>
													   <th style="width: 150px;">작성자</th>
													   <th style="width: 150px;">문의분류</th>
													   <th style="width: 650px;">문의내용</th>
													   <th style="width: 200px;">문의일자</th>
													</tr>
													<c:forEach var="m2" items="${questionList}">
													   <tr>
													      <td align="center" style="vertical-align:middle;">${m2.goodsQuestionCode}</td>
													      <td align="center" style="vertical-align:middle;">${m2.customerId}</td>
													      <td align="center" style="vertical-align:middle;">${m2.category}</td>
													      <td>
													         <div>
													          <ul>
													              <li class="menu">
													                  <a>${m2.goodsQuestionMemo}</a>
													                  <ul class="hide">
													                     <c:if test="${m2.goodsCommentMemo != null}">
													                        <li><span style="color:blue;">A.</span> ${m2.goodsCommentMemo}</li>
													                        <li style="color:blue;">답변 등록일 : ${m2.gqcCreatedate}</li>
													                     </c:if>
													                     <c:if test="${m2.goodsCommentMemo == null}">
													                        <li>답변대기중</li>
													                     </c:if>
													                  </ul>
													              </li>
													          </ul>
													       </div>
													      </td>
													      <td align="center" style="vertical-align:middle;">${m2.gqCreatedate}</td>
													   </tr>
													</c:forEach>
												</table>
											</div>
											<br>
											<!-- 문의글 페이징 -->
											<div align="center">
											   <c:if test="${currentPage != 1}">
											      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage=1&goodsCode=${goodsCode}">처음</a>
											   </c:if>
											   <c:if test="${currentPage > 1}">
											      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage=${currentPage-1}&goodsCode=${goodsCode}">이전</a>
											   </c:if>
											   ${currentPage}
											   <c:if test="${currentPage < lastPage}">
											      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage=${currentPage+1}&goodsCode=${goodsCode}">다음</a>
											   </c:if>
											   <c:if test="${currentPage != lastPage}">
											      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage=${lastPage}&goodsCode=${goodsCode}">마지막</a>
											   </c:if>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- tab3 상품리뷰 -->
						<div class="tab-pane fade" id="reviews" role="tabpanel">
							<div class="row">
								<div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
									<div class="p-b-30 m-lr-15-sm">
										<!-- Review -->
										<div class="flex-w flex-t p-b-68">
											<c:forEach var="m3" items="${reviewList}">
												<div class="size-207">
													<div class="flex-w flex-sb-m p-b-17">
														<span class="stext-102 cl6">
															고객님 : ${m3.customerName}
														</span>
	
														<span class="fs-18 cl11">
															<i>${m3.createdate}</i>
														</span>
													</div>
	
													<p class="mtext-107 cl2 p-r-20">
														${m3.reviewMemo}
													</p>
													<span class="stext-102 cl6">------------------------------------------------------------------------------------------------------</span>
												</div>
											</c:forEach>
										</div>
										<!-- 리뷰 페이징 -->
										<div align="center">
										   <c:if test="${currentPage2 != 1}">
										      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage2=1&goodsCode=${goodsCode}">처음</a>
										   </c:if>
										   <c:if test="${currentPage2 > 1}">
										      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage2=${currentPage2-1}&goodsCode=${goodsCode}">이전</a>
										   </c:if>
										   ${currentPage2}
										   <c:if test="${currentPage2 < lastPage2}">
										      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage2=${currentPage2+1}&goodsCode=${goodsCode}">다음</a>
										   </c:if>
										   <c:if test="${currentPage2 != lastPage2}">
										      <a href="${pageContext.request.contextPath}/member/goodsOne?currentPage2=${lastPage2}&goodsCode=${goodsCode}">마지막</a>
										   </c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</section>
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	
		<!-- Back to top -->
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top">
				<i class="zmdi zmdi-chevron-up"></i>
			</span>
		</div>
	
	<!--===============================================================================================-->	
		<script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/bootstrap/js/popper.js"></script>
		<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/select2/select2.min.js"></script>
		<script>
			$(".js-select2").each(function(){
				$(this).select2({
					minimumResultsForSearch: 20,
					dropdownParent: $(this).next('.dropDownSelect2')
				});
			})
		</script>
	<!--===============================================================================================-->
		<script src="../vendor/daterangepicker/moment.min.js"></script>
		<script src="../vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/slick/slick.min.js"></script>
		<script src="js/slick-custom.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/parallax100/parallax100.js"></script>
		<script>
	        $('.parallax100').parallax100();
		</script>
	<!--===============================================================================================-->
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script>
			$('.gallery-lb').each(function() { // the containers for all your galleries
				$(this).magnificPopup({
			        delegate: 'a', // the selector for gallery item
			        type: 'image',
			        gallery: {
			        	enabled:true
			        },
			        mainClass: 'mfp-fade'
			    });
			});
		</script>
	<!--===============================================================================================-->
		<script src="../vendor/isotope/isotope.pkgd.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/sweetalert/sweetalert.min.js"></script>
	<!--===============================================================================================-->
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script>
			$('.js-pscroll').each(function(){
				$(this).css('position','relative');
				$(this).css('overflow','hidden');
				var ps = new PerfectScrollbar(this, {
					wheelSpeed: 1,
					scrollingThreshold: 1000,
					wheelPropagation: false,
				});
	
				$(window).on('resize', function(){
					ps.update();
				})
			});
		</script>
	<!--===============================================================================================-->
		<script src="../js/main.js"></script>
	</body>
</html>