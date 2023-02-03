<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<style>
			table, th, td {
				text-align: center;
			}
			
			a, a:hover, a:visited {
				color: #234200;
			}
		</style>
		<meta charset="UTF-8">
		<title>goodsCommentList</title>
		<link rel="icon" type="image/png" href="../images/icons/favicon.png"/>
		<link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/iconic/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" type="text/css" href="../fonts/linearicons-v1.0.0/icon-font.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
		<link rel="stylesheet" type="text/css" href="../vendor/perfect-scrollbar/perfect-scrollbar.css">
		<link rel="stylesheet" type="text/css" href="../css/util.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#rowPerPage').change(function() {
					$('#goodsForm').submit();
				});
				
				$('#category').change(function() {
					$('#goodsForm').submit();
				});
				
				$('#sort').change(function() {
					$('#goodsForm').submit();
				});
			});
		</script>
	</head>
	<body>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		<div style="margin-top:100px;">
			<h1>상품문의 답변</h1>
			<form action="${pageContext.request.contextPath}/admin/goodsComment" method="get" id="goodsForm">
				<div class="container">
					<div class="flex-w flex-sb-m">
						<div class="flex-w flex-l-m filter-tope-group m-tb-10">
						</div>
						<div class="flex-w flex-c-m m-tb-10">
							<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
								<!-- 목록수 변경 -->
								<select name="rowPerPage" id="rowPerPage" class="js-select2">
									<c:if test="${rowPerPage == 5}">
										<option value="5" selected="selected">5</option>
										<option value="10">10</option>
										<option value="15">15</option>
									</c:if>
									<c:if test="${rowPerPage == 10}">
										<option value="5">5</option>
										<option value="10" selected="selected">10</option>
										<option value="15">15</option>
									</c:if>
									<c:if test="${rowPerPage == 15}">
										<option value="5">5</option>
										<option value="10">10</option>
										<option value="15" selected="selected">15</option>
									</c:if>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
							<!-- 검색어 분류 -->
							<select name="search" class="js-select2">
								<c:if test="${empty search}">
									<option value="" selected="selected">검색분류</option>
									<option value="상품명">상품명</option>
									<option value="고객ID">고객ID</option>
								</c:if>
								<c:if test="${search eq '상품명'}">
									<option value="">검색분류</option>
									<option value="상품명" selected="selected">상품명</option>
									<option value="고객ID">고객ID</option>
								</c:if>
								<c:if test="${search eq '고객ID'}">
									<option value="">검색분류</option>
									<option value="상품명">상품명</option>
									<option value="고객ID" selected="selected">고객ID</option>
								</c:if>
							</select>
							<div class="dropDownSelect2"></div>
							</div>
							<div class="bor17 of-hidden pos-relative">
								<input class="stext-103 cl2 plh4 size-116 p-l-28 p-r-55" type="text" name="word" value="${word}" id="searchWord" placeholder="검색어를 입력해주세요.">
								<button class="flex-c-m size-122 ab-t-r fs-18 cl4 hov-cl1 trans-04" type="submit">
									<i class="zmdi zmdi-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<table class="tabale table-bordered">
					<tr>
						<th>문의번호</th>
						<th>문의고객</th>
						<th>상품명</th>
						<th>
							<select name="category" id="category">
								<c:if test="${empty category}">
									<option value="" selected="selected">분류</option>
									<option value="재입고">재입고</option>
									<option value="배송">배송</option>
									<option value="취소">취소</option>
									<option value="기타">기타</option>
								</c:if>
								<c:if test="${category eq '재입고'}">
									<option value="">분류</option>
									<option value="재입고" selected="selected">재입고</option>
									<option value="배송">배송</option>
									<option value="취소">취소</option>
									<option value="기타">기타</option>
								</c:if>
								<c:if test="${category eq '배송'}">
									<option value="">분류</option>
									<option value="재입고">재입고</option>
									<option value="배송" selected="selected">배송</option>
									<option value="취소">취소</option>
									<option value="기타">기타</option>
								</c:if>
								<c:if test="${category eq '취소'}">
									<option value="">분류</option>
									<option value="재입고">재입고</option>
									<option value="배송">배송</option>
									<option value="취소" selected="selected">취소</option>
									<option value="기타">기타</option>
								</c:if>
								<c:if test="${category eq '기타'}">
									<option value="">분류</option>
									<option value="재입고">재입고</option>
									<option value="배송">배송</option>
									<option value="취소">취소</option>
									<option value="기타" selected="selected">기타</option>
								</c:if>
							</select>
						</th>
						<th style="width:500px;">내용</th>
						<th>문의일자</th>
						<th>
							<!-- 답변 전후 정렬 -->
							<select name="sort" id="sort">
								<c:if test="${empty sort}">
									<option value="" selected="selected">정렬</option>
									<option value="asc">답변전</option>
									<option value="desc">답변완료</option>
								</c:if>
								<c:if test="${sort eq 'asc'}">
									<option value="">정렬</option>
									<option value="asc" selected="selected">답변전</option>
									<option value="desc">답변완료</option>
								</c:if>
								<c:if test="${sort eq 'desc'}">
									<option value="">정렬</option>
									<option value="asc">답변전</option>
									<option value="desc" selected="selected">답변완료</option>
								</c:if>
							</select>
						</th>
						<th>작성자일</th>
					</tr>
					<c:forEach var="m" items="${goodsList}">
						<tr>
							<td>${m.goodsQuestionCode}</td>
							<td>${m.customerId}</td>
							<td>${m.goodsName}</td>
							<td>${m.category}</td>
							<td>${m.goodsQuestionMemo}</td>
							<td>${m.gqCreatedate}</td>
							<td>
								<c:if test="${m.goodsCommentMemo == null}">
									<a href="${pageContext.request.contextPath}/admin/addGoodsComment?goodsQuestionCode=${m.goodsQuestionCode}&goodsQuestionMemo=${m.goodsQuestionMemo}">
										답글작성
									</a>
								</c:if>
								<c:if test="${m.goodsCommentMemo != null}">
					         		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#memberOne">답변</button>
									<div class="modal" id="memberOne">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h4 class="modal-title">답변내용</h4>
													<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
												</div>
												<div class="modal-body">
													문의내용 : ${m.goodsQuestionMemo} <br>
													답변내용 : ${m.goodsCommentMemo}
												</div>
											</div>
										</div>
									</div>
									<a href="${pageContext.request.contextPath}/admin/removeGoodsComment?goodsCommentCode=${m.goodsCommentCode}">삭제</a>
								</c:if>
							</td>
							<td>${m.gqcCreatedate}</td>
						</tr>				
					</c:forEach>
				</table>
			</form>
		</div>
		<div>
			<!-- 검색어x -->
			<c:if test="${currentPage != 1 && word == null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=1&sort=${sort}&category=${category}">처음</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage != 1 && word != null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=1&search=${search}&word=${word}&sort=${sort}&category=${category}">처음</a>
			</c:if>
			<!-- 검색어x -->
			<c:if test="${currentPage > 1 && word == null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&sort=${sort}&category=${category}">이전</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage > 1 && word != null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&search=${search}&word=${word}&sort=${sort}&category=${category}">이전</a>
			</c:if>
			${currentPage}
			<!-- 검색어x -->
			<c:if test="${currentPage < lastPage && word == null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&sort=${sort}&category=${category}">다음</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage < lastPage && word != null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&search=${search}&word=${word}&sort=${sort}&category=${category}">다음</a>
			</c:if>
			<!-- 검색어x -->
			<c:if test="${currentPage != lastPage && word == null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${lastPage}&sort=${sort}&category=${category}">마지막</a>				
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage != lastPage && word != null}">
				<a href="${pageContext.request.contextPath}/admin/goodsComment?rowPerPage=${rowPerPage}&currentPage=${lastPage}&search=${search}&word=${word}&sort=${sort}&category=${category}">마지막</a>
			</c:if>
		</div>
		<!-- Footer -->
		<jsp:include page="/inc/footer.jsp"></jsp:include>
		<div class="btn-back-to-top" id="myBtn">
			<span class="symbol-btn-back-to-top">
				<i class="zmdi zmdi-chevron-up"></i>
			</span>
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
		<script src="../vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
		<script src="../vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
		<script src="../js/main.js"></script>
	</body>
</html>