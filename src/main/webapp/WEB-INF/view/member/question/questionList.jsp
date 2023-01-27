<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>QuestionList</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
		<link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
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
		<h1>고객센터</h1>
		<!-- goodsQuestion 나의 상품문의 리스트 customerId로 받아오기 -->
		<h2>goodsQuestion</h2>
		<div class="bg0 p-t-2 p-b-2">
			<div class="row">
				<div class="col-lg-12 m-lr-auto m-b-50">
					<div class="wrap-table-shopping-cart">
						<table class="table-shopping-cart">
							<tr>
								<th>문의번호</th>
								<th>상품이름</th>
								<th>작성자</th>
								<th>분류</th>
								<th style="width:1000px;">내용</th>
								<th>작성일자</th>
							</tr>
							<c:forEach var="m" items="${goodsQuestionList}">
								<tr>
									<td>${m.goodsQuestionCode}</td>
									<td>${m.goodsName}</td>
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
									            		<li><a href="${pageContext.request.contextPath}/member/removeGoodsQuestion?goodsQuestionCode=${m.goodsQuestionCode}">삭제</a></li>
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
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage=1">처음</a>
				       		</c:if>
				       		<c:if test="${currentPage > 1}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage=${currentPage-1}">이전</a>
				       		</c:if>
				       		${currentPage}
				       		<c:if test="${currentPage < lastPage}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage=${currentPage+1}">다음</a>
				       		</c:if>
				       		<c:if test="${currentPage != lastPage}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage=${lastPage}">마지막</a>
				       		</c:if>
				       	</div>
					</div>
				</div>
			</div>
		</div>
		<!-- question 나의 주문문의 리스트 -->
		<h2>question</h2>
		<div class="bg0 p-t-2 p-b-2">
			<div class="row">
				<div class="col-lg-12 m-lr-auto m-b-50">
					<div class="wrap-table-shopping-cart">
						<table class="table-shopping-cart">
							<tr>
								<th>문의번호</th>
								<th>주문번호</th>
								<th>작성자</th>
								<th>분류</th>
								<th style="width:1000px;">내용</th>
								<th>작성일자</th>
							</tr>
							<c:forEach var="m2" items="${questionList}">
								<tr>
									<td>${m2.questionCode}</td>
									<td>${m2.ordersCode}</td>
									<td>${m2.customerId}</td>
									<td>${m2.category}</td>
									<td>
										<div>
										    <ul>
										        <li class="menu">
										            <a>
										            	${m2.questionMemo}
										            </a>
										            <ul class="hide">
										            <c:if test="${m2.commentMemo != null}">
									            		<li>A. ${m2.commentMemo}</li>
									            	</c:if>
									            	<c:if test="${m2.commentMemo == null}">
									            		<li>답변대기중</li>
									            		<li><a href="${pageContext.request.contextPath}/member/modifyQuestion?questionCode=${m2.questionCode}">수정</a></li>
									            		<li><a href="${pageContext.request.contextPath}/member/removeQuestion?questionCode=${m2.questionCode}">삭제</a></li>
									            	</c:if>
										            </ul>
										        </li>
										    </ul>
										</div>
									</td>
									<td>${m2.qCreatedate}</td>
								</tr>
							</c:forEach>
						</table>
						<div>
				       		<c:if test="${currentPage2 != 1}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage2=1">처음</a>
				       		</c:if>
				       		<c:if test="${currentPage2 > 1}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage2=${currentPage2-1}">이전</a>
				       		</c:if>
				       		${currentPage2}
				       		<c:if test="${currentPage2 < lastPage2}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage2=${currentPage2+1}">다음</a>
				       		</c:if>
				       		<c:if test="${currentPage2 != lastPage2}">
				       			<a href="${pageContext.request.contextPath}/member/question?currentPage2=${lastPage2}">마지막</a>
				       		</c:if>
				       	</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>