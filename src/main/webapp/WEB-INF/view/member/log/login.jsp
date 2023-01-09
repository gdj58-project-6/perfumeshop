<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<style type="text/css">
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
		           width: 15%;
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
		           width: 15%;
		           background: #fff;
		       }
		       .tab_content {
		           padding: 20px;
		           font-size: 1.2em;
		       }
		</style>
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
		
		    });
		</script>
	</head>
	<body>
		<!-- 비로그인 -->
		<c:if test="${loginMember == null}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<div id="wrapper">    
		    <!--탭 메뉴 영역 -->
		    <ul class="tabs">
		        <li><a href="#tab1">고객로그인</a></li>
		        <li><a href="#tab2">직원로그인</a></li>
		    </ul>
	
		    <!--탭 콘텐츠 영역 -->
		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		        	<!-- 탭1 고객 -->
		            <h1>고객 로그인</h1>
					<form action="${pageContext.request.contextPath}/member/login" method="post">
						<table border="1">
							<tr>
								<td>아이디</td>
								<td>
									<input type="text" name="customerId">
								</td>
							</tr>
							<tr>
								<td>비번</td>
								<td>
									<input type="password" name="customerPw">
								</td>
							</tr>
						</table>
						<div>
							<button type="submit">로그인</button>
						</div>
					</form>
					<a href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
		        </div>
		        <div id="tab2" class="tab_content">
		        	<!-- 탭2 직원 -->
		         	<h1>직원 로그인</h1>
					<form action="${pageContext.request.contextPath}/member/login" method="post">
						<table border="1">
							<tr>
								<td>아이디</td>
								<td>
									<input type="text" name="empId">
								</td>
							</tr>
							<tr>
								<td>비번</td>
								<td>
									<input type="password" name="empPw">
								</td>
							</tr>
						</table>
						<div>
							<button type="submit">로그인</button>
						</div>
					</form>
					<a href="${pageContext.request.contextPath}/admin/addAdmin">회원가입</a>
		        </div>
	    	</div>
		</div>
	</body>
</html>