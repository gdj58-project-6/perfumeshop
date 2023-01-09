<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			.menu a {
				cursor:pointer;
			}
			.menu .hide {
				display:none;
			}
	        body {
	            font-family:"Malgun Gothic";
	            font-size: 0.8em;
	
	        }
	        /*TAB CSS*/
	
	        ul.tabs {
	            margin: 0;
	            padding: 0;
	            float: left;
	            list-style: none;
	            height: 32px; /*--Set height of tabs--*/
	            border-bottom: 1px solid #999;
	            border-left: 1px solid #999;
	            width: 100%;
	        }
	        ul.tabs li {
	            float: left;
	            margin: 0;
	            padding: 0;
	            height: 31px; /*--Subtract 1px from the height of the unordered list--*/
	            line-height: 31px; /*--Vertically aligns the text within the tab--*/
	            border: 1px solid #999;
	            border-left: none;
	            margin-bottom: -1px; /*--Pull the list item down 1px--*/
	            overflow: hidden;
	            position: relative;
	            background: #e0e0e0;
	        }
	        ul.tabs li a {
	            text-decoration: none;
	            color: #000;
	            display: block;
	            font-size: 1.2em;
	            padding: 0 20px;
	            /*--Gives the bevel look with a 1px white border inside the list item--*/
	            border: 1px solid #fff; 
	            outline: none;
	        }
	        ul.tabs li a:hover {
	            background: #ccc;
	        }
	        html ul.tabs li.active, html ul.tabs li.active a:hover  {
	             /*--Makes sure that the active tab does not listen to the hover properties--*/
	            background: #fff;
	            /*--Makes the active tab look like it's connected with its content--*/
	            border-bottom: 1px solid #fff; 
	        }
	
	        /*Tab Content CSS*/
	        .tab_container {
	            border: 1px solid #999;
	            border-top: none;
	            overflow: hidden;
	            clear: both;
	            float: left; 
	            width: 100%;
	            background: #fff;
	        }
	        .tab_content {
	            padding: 20px;
	            font-size: 1.2em;
	        }
		</style>
		<meta charset="UTF-8">
		<title>goodsOne</title>
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
				    // 현재 클릭한 태그가 a 이기 때문에
				    // a 옆의 태그중 ul 태그에 hide 클래스 태그를 넣던지 빼던지 한다.
				    $(this).next("ul").toggleClass("hide");
				});
		
		    });
			// 장바구니 담기 버튼 누르면 성공했다는 창 하나 나오게
			let cartAddBtn = document.querySelector('#cartAddBtn');
			
			cartAddBtn.addEventListener('click', function(){
				// console.log('장바구니 추가 클릭!');
				alert('장바구니 담기 성공!');
			});
		</script>
	</head>
	<body>
		<a href='${pageContext.request.contextPath}/member/goodsList'>뒤로</a>
		<!-- 맨위에 include  -->
		<h3>goodsOne</h3>
		<!-- 가격, 수량조절, 장바구니로 갈지 결제창으로 갈지, 제품소개 등등.... -->
		<c:forEach var="m" items="${list}">
			<form action="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}" method="post" id="goodsOne">
				<table border="1">
					<tr>
						<td colspan="2">
							<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200">
						</td>
					</tr>
					<tr>
						<td>상품 이름</td>
						<td>${m.goodsName}</td>
					</tr>
					<tr>
						<td>상품 가격</td>
						<td>${m.goodsPrice}</td>
					</tr>
					<tr>
					<!--  수량이 변경되면 상품가격이 변경되야 함 -->
						<td>수량</td>
						<td><input type="number" value="1" name="cartQuantity"></td>
					</tr>
					<!-- 제품설명? 개별적인 설명이 나오게 하려면 어떻게?? -->
					<tr>
						<td>제품 설명</td>
						<td>${m.goodsMemo}</td>
					</tr>
					<!-- 또 추가할게 있으면 추가 -->
				</table>
				<!-- customerId값 히든으로 넘김 -->
				<input type="hidden" name="customerId" value="${loginMember.customerId}">
				<!-- 나중에 장바구니 버튼 누르면 장바구니에 담겼다는 창 한개 띄우기 -->
				<button type="submit" id="cartAddBtn">장바구니 담기</button>
			</form>	
			<!-- 장바구니, 결제창 이동-->
			<a href='${pageContext.request.contextPath}/member/cart'>장바구니로 이동</a>
			<a href='${pageContext.request.contextPath}/member/goodsPayMent'>바로 구매</a>
		</c:forEach>
		<div id="wrapper">    
		    <!--탭 메뉴 영역 -->
		    <ul class="tabs">
		        <li><a href="#tab1">상품정보</a></li>
		        <li><a href="#tab2">상품문의</a></li>
		        <li><a href="#tab3">상품리뷰</a></li>
		    </ul>
	
		    <!--탭 콘텐츠 영역 -->
		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		        	<!-- 탭1 상품정보 -->
		            <h1>상품정보</h1>
					상품정보 테이블 추가?
		        </div>
		        <div id="tab2" class="tab_content">
		        	<!-- 탭2 상품문의 -->
		         	<h1>상품문의</h1>
		         	<table border="1">
		         		<tr>
		         			<th>questionCode</th>
		         			<th>ordersCode</th>
		         			<th>category</th>
		         			<th>questionMemo</th>
		         			<th>createdate</th>
		         			<th>수정</th>
		         			<th>삭제</th>
		         		</tr>
		         		<c:forEach var="m2" items="${questionList}">
		         			<tr>
		         				<td>${m2.questionCode}</td>
		         				<td>${m2.ordersCode}</td>
		         				<td>${m2.category}</td>
		         				<td>
		         					<div>
									    <ul>
									        <li class="menu">
									            <a>${m2.questionMemo}</a>
									            <ul class="hide">
									            	<li>답글예정입니다.</li>
									            </ul>
									        </li>
									    </ul>
								    </div>
		         				</td>
		         				<td>${m2.createdate}</td>
		         				<td><!-- 수정은 모달로 memberOne에 코드 있음 --></td>
		         				<td><!-- 삭제는 그냥 가능하게 --></td>
		         			</tr>
		         		</c:forEach>
		         	</table>
		        </div>
		        <div id="tab3" class="tab_content">
		        	<!-- 탭3 리뷰 -->
		         	<h1>상품리뷰</h1>
		        </div>
	    	</div>
		</div>
	</body>
</html>