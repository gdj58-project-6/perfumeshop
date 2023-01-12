<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>QuestionList</title>
		<style>
			.menu a{cursor:pointer;}
    		.menu .hide{display:none;}
		</style>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script>
		$(document).ready(function(){
			// menu 클래스 바로 하위에 있는 a 태그를 클릭했을때
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
		<h1>나의 문의내역</h1>
		<!-- goodsQuestion 나의 상품문의 리스트 customerId로 받아오기 -->
		<h2>goodsQuestion</h2>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>분류</th>
				<th style="width:1000px;">내용</th>
				<th>작성일자</th>
			</tr>
			<c:forEach var="m" items="${list}">
				<tr>
					<td>${m.goodsQuestionCode}</td>
					<td>${m.customerId}</td>
					<td>${m.category}</td>
					<td>
						<div>
						    <ul>
						        <li class="menu">
						            <a>
						            	${m.goodsQuestionMemo}
						            </a>
						            <ul class="hide">
						            <c:if test="${m.goodsCommentMemo != null}">
					            		<li>A. ${m.goodsCommentMemo}</li>
					            	</c:if>
					            	<c:if test="${m.goodsCommentMemo == null}">
					            		<li>답변대기중</li>
					            		<li><a href="${pageContext.request.contextPath}/member/modifyGoodsQuestion?goodsQuestionCode=${m.goodsQuestionCode}">수정</a></li>
					            		<li><a href="${pageContext.request.contextPath}/member/deleteGoodsQuestion?goodsQuestionCode=${m.goodsQuestionCode}">삭제</a></li>
					            	</c:if>
						            </ul>
						        </li>
						    </ul>
						</div>
					</td>
					<td>${m.gqCreatedate}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
       		<c:if test="${currentPage != 1}">
       			<a href="${pageContext.request.contextPath}/member/myQuestionList?currentPage=1">처음</a>
       		</c:if>
       		<c:if test="${currentPage > 1}">
       			<a href="${pageContext.request.contextPath}/member/myQuestionList?currentPage=${currentPage-1}">이전</a>
       		</c:if>
       		${currentPage}
       		<c:if test="${currentPage < lastPage}">
       			<a href="${pageContext.request.contextPath}/member/myQuestionList?currentPage=${currentPage+1}">다음</a>
       		</c:if>
       		<c:if test="${currentPage != lastPage}">
       			<a href="${pageContext.request.contextPath}/member/myQuestionList?currentPage=${lastPage}">마지막</a>
       		</c:if>
       	</div>
		<!-- question 나의 주문문의 리스트 -->
		<h2>question</h2>
	</body>
</html>