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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		<!-- 상품문의 -->
		<div class="container">
			<div class="row">
				<!-- 문의목록 -->
				<div class="col-md-10 col-lg-10 p-b-190 p-t-55 mx-auto">
					<h1 class="mtext-109 cl2 p-b-10" style="margin-top:100px;">상품문의</h1>
					<table class="table text-center stext-110 cl2">
						<tr>
							<th class="text-center">문의번호</th>
							<th class="text-center">상품이름</th>
							<th class="text-center">분류</th>
							<th class="text-center">내용</th>
							<th class="text-center">작성일자</th>
						</tr>
						<c:forEach var="m" items="${goodsQuestionList}">
							<tr>
								<td class="text-center">${m.goodsQuestionCode}</td>
								<td class="text-center">
									<a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}">
										${m.goodsName}
									</a>
								</td>
								<td class="text-center">${m.category}</td>
								<td class="text-center">
									<div>
									    <ul>
									        <li class="menu">
												<c:if test="${m.goodsCommentMemo != null}">
													<a>
														${m.goodsQuestionMemo}
													</a>	
									        	</c:if>
									       		<c:if test="${m.goodsCommentMemo == null}">
									       			<a>
														${m.goodsQuestionMemo}[답변처리중]
													</a>
									       		</c:if>
									            <ul class="hide">
									            <c:if test="${m.goodsCommentMemo != null}">
								            		<li>A. ${m.goodsCommentMemo}</li>
								            	</c:if>
								            	<c:if test="${m.goodsCommentMemo == null}">
								            		<a style="color:black;" href="${pageContext.request.contextPath}/member/modifyGoodsQuestion?goodsQuestionCode=${m.goodsQuestionCode}">
								            			<img src="${pageContext.request.contextPath}/images/icons/modify.png" style="width:25px;">
														수정
								            		</a>
								            		<a style="color:black;" href="${pageContext.request.contextPath}/member/removeGoodsQuestion?goodsQuestionCode=${m.goodsQuestionCode}">
								            			<img src="${pageContext.request.contextPath}/images/icons/delete.png" style="width:25px;">
														삭제
								            		</a>
								            	</c:if>
									            </ul>
									        </li>
									    </ul>
									</div>
								</td>
								<td class="text-center">${m.gqCreatedate}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 상품문의 페이징 -->
					<div align="center">
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
		<!-- 주문문의 -->
		<div class="container">
			<div class="row">
				<!-- 문의목록 -->
				<div class="col-md-10 col-lg-10 p-b-190 p-t-55 mx-auto">
					<h1 class="mtext-109 cl2 p-b-10">주문문의</h1>
					<table class="table text-center stext-110 cl2">
						<tr>
							<th class="text-center">문의번호</th>
							<th class="text-center">주문번호</th>
							<th class="text-center">분류</th>
							<th class="text-center">내용</th>
							<th class="text-center">작성일자</th>
						</tr>
						<c:forEach var="m2" items="${questionList}">
							<tr>
								<td class="text-center">${m2.questionCode}</td>
								<td class="text-center">
									<a href="${pageContext.request.contextPath}/member/orderOne?orderCode=${m2.ordersCode}">
										${m2.ordersCode}
									</a>
								</td>
								<td class="text-center">${m2.category}</td>
								<td class="text-center">
									<div>
									    <ul>
									        <li class="menu">
									        	<c:if test="${m2.commentMemo != null}">
													<a>
														${m2.questionMemo}
													</a>	
									        	</c:if>
									       		<c:if test="${m2.commentMemo == null}">
									       			<a>
														${m2.questionMemo}[답변처리중]
													</a>
									       		</c:if>
									            <ul class="hide">
									            <c:if test="${m2.commentMemo != null}">
								            		<li>A. ${m2.commentMemo}</li>
								            	</c:if>
								            	<c:if test="${m2.commentMemo == null}">
								            		<a style="color:black;" href="${pageContext.request.contextPath}/member/modifyQuestion?questionCode=${m2.questionCode}">
								            			<img src="${pageContext.request.contextPath}/images/icons/modify.png" style="width:25px;">
														수정
								            		</a>
									            	<a style="color:black;" href="${pageContext.request.contextPath}/member/removeQuestion?questionCode=${m2.questionCode}">
									            		<img src="${pageContext.request.contextPath}/images/icons/delete.png" style="width:25px;">
														삭제
									            	</a>
								            	</c:if>
									            </ul>
									        </li>
									    </ul>
									</div>
								</td>
								<td class="text-center">${m2.qCreatedate}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 주문문의 페이징 -->
					<div align="center">
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
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
	</body>
</html>