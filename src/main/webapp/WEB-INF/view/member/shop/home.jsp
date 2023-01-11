<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<style>
			.menu a {
				cursor:pointer;
			}
			.menu .hide {
				display:none;
			}
		</style>
		<meta charset="UTF-8">
		<title>home</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			// html dom 이 다 로딩된 후 실행된다.
		    $(document).ready(function(){
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
		    });
		</script>
	</head>
	<body>
		<h1>home</h1>
		<!-- 비로그인 -->
		<c:if test="${loginMember == null}">
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</c:if>
		<!-- 고객 -->
		<c:if test="${loginMember.getAuthCode() < 4}">
			<jsp:include page="/inc/customerMenu.jsp"></jsp:include>
		</c:if>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<div>
				<jsp:include page="/inc/empMenu.jsp"></jsp:include>
			    <ul>
			        <li class="menu">
			            <a>관리자페이지</a>
			            <ul class="hide">
			            	<li><jsp:include page="/inc/adminMenu.jsp"></jsp:include></li>
			            </ul>
			        </li>
			    </ul>
		    </div>
		</c:if>
		<!-- 큰 사진 -->
		<div style="text-align:center;"><img src="${pageContext.request.contextPath}/img/camera.jpg" width="1500" height="700"></div>
		<!-- 공지목록 페이징 5개씩 -->
		<h2>공지사항</h2>
		<c:if test="${loginMember.getAuthCode() >3}">
			<a href="${pageContext.request.contextPath}/admin/addNotice">공지사항 입력</a>
		</c:if>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.noticeCode}</td>
					<td>
						<a href="${pageContext.request.contextPath}/admin/noticeOne?noticeCode=${m.noticeCode}">${m.noticeTitle}</a>
					</td>
					<td>${m.empId}</td>
					<td>${m.createdate}</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 공지목록 페이징 5개씩 -->
		<div>
			<c:if test="${currentPage != 1}">
				<a href="${pageContext.request.contextPath}/home?currentPage=1">처음</a>				
			</c:if>
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${currentPage-1}">이전</a>
			</c:if>
			${currentPage}
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${currentPage != lastPage}">
				<a href="${pageContext.request.contextPath}/home?currentPage=${lastPage}">마지막</a>
			</c:if>
		</div>
	</body>
</html>