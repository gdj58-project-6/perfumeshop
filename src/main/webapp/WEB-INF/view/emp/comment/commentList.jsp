<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>commentList</title>
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
					$('#pageForm').submit();
				})
				
				$('#category').change(function() {
					$('#pageForm').submit();
				})
				
				$('#sort').change(function() {
					$('#pageForm').submit();
				})
			});
		</script>
		<style>
			table, th, td {
				text-align: center;
			}
			
			a, a:hover, a:visited {
				color: #234200;
				text-decoration : none;
			}
			
			.po {
				position : relative;
				top : 65px;
				left : 150px;
			}
			
			.pagePo {
				position : relative;
				bottom : 40px;
				color : black;
			}
		</style>
	</head>
	<body>
		<!-- 직원 -->
		<c:if test="${loginMember.getAuthCode() > 3}">
			<jsp:include page="/inc/empMenu.jsp"></jsp:include>
		</c:if>
		<div style="margin-top:50px;">
			<h4 class="po">주문문의 답변</h4>
			<form id ="pageForm" method="get" action="${pageContext.request.contextPath}/admin/comment">
				<div class="container">
					<div class="flex-w flex-sb-m">
						<div class="flex-w flex-l-m filter-tope-group m-tb-10">
						</div>
						<div class="flex-w flex-c-m m-tb-10">
							<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
								<!-- 목록수 -->
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
							<!-- 정렬 답변전은 문의일자로(qCreatedate로 desc 정렬) 답변후는 답변일자로(qcCreatedate desc 정렬) -->
							<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
								<select name="sort" id="sort" class="js-select2">
									<c:if test="${empty sort}">
										<option value="" selected>답변</option>
										<option value="asc">답변전</option>
										<option value="desc">답변완료</option>
									</c:if>
									<c:if test="${sort eq 'asc'}">
										<option value="">답변</option>
										<option value="asc" selected>답변전</option>
										<option value="desc">답변완료</option>
									</c:if>
									<c:if test="${sort eq 'desc'}">
										<option value="">답변</option>
										<option value="asc">답변전</option>
										<option value="desc" selected>답변완료</option>
									</c:if>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<div class="rs1-select2 rs2-select2 bor8" style="width: 130px; margin: 10px;">
								<!-- 분류 할인, 포인트, 구매, 결제 -->
								<select name="category" id="category" class="js-select2">
									<c:if test="${empty category}">
										<option value="" selected="selected">문의분류</option>
										<option value="할인">할인</option>
										<option value="포인트">포인트</option>
										<option value="구매">구매</option>
										<option value="결제">결제</option>
									</c:if>
									<c:if test="${category eq '할인'}">
										<option value="">문의분류</option>
										<option value="할인" selected="selected">할인</option>
										<option value="포인트">포인트</option>
										<option value="구매">구매</option>
										<option value="결제">결제</option>
									</c:if>
									<c:if test="${category eq '포인트'}">
										<option value="">문의분류</option>
										<option value="할인">할인</option>
										<option value="포인트" selected="selected">포인트</option>
										<option value="구매">구매</option>
										<option value="결제">결제</option>
									</c:if>
									<c:if test="${category eq '구매'}">
										<option value="">문의분류</option>
										<option value="할인">할인</option>
										<option value="포인트">포인트</option>
										<option value="구매" selected="selected">구매</option>
										<option value="결제">결제</option>
									</c:if>
									<c:if test="${category eq '결제'}">
										<option value="">문의분류</option>
										<option value="할인">할인</option>
										<option value="포인트">포인트</option>
										<option value="구매">구매</option>
										<option value="결제" selected="selected">결제</option>
									</c:if>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<!-- 검색 -->
							<div class="bor17 of-hidden pos-relative">
								<input class="stext-103 cl2 plh4 size-116 p-l-28 p-r-55" type="text" name="word" value="${word}" id="searchWord" placeholder="주문번호를 입력해주세요.">
								<button class="flex-c-m size-122 ab-t-r fs-18 cl4 hov-cl1 trans-04" type ="submit">
									<i class="zmdi zmdi-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="bg0 p-t-2 p-b-2">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 m-lr-auto m-b-50">
							<div class="wrap-table-shopping-cart">
								<table class="table-shopping-cart">
									<tr class="table_head">
										<th style="width: 150px;">문의번호</th>
										<th style="width: 150px;">주문번호</th>
										<th style="width: 150px;">주문상태</th>
										<th style="width: 550px;">상품명</th>
										<th style="width: 150px;">문의분류	</th>
										<th style="width: 550px;">문의내용</th>
										<th style="width: 300px;">문의일</th>
										<th style="width: 200px;">답변</th>
									</tr>
									<c:forEach var="m" items="${list}">
										<tr>
											<td>${m.questionCode}</td>
											<td>${m.orderCode}</td>
											<td>${m.orderState}</td>
											<td>
												<a style="color:blue;" href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}">
													${m.goodsName}
												</a>
											</td>
											<td>${m.category}</td>
											<td>
												<c:if test="${m.commentMemo == null}">
													[처리중]${m.questionMemo}
												</c:if>
												<c:if test="${m.commentMemo != null}">
									         		<button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#memberOne" style="color:blue;">
									         			[완료]${m.questionMemo}
									         		</button>
													<div class="modal" id="memberOne">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<h4 class="modal-title">답변내용</h4>
																	<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
																</div>
																<div class="modal-body">
																	문의내용 : ${m.questionMemo}<br>
																	답변내용 : ${m.commentMemo}<br>
																	답변일 : ${m.qcCreatedate}
																</div>
															</div>
														</div>
													</div>
												</c:if>
												
											</td>
											<td>${m.qCreatedate}</td>
											<td>
												<c:if test="${m.commentMemo == null}">
													<a href="${pageContext.request.contextPath}/admin/addComment?questionCode=${m.questionCode}&questionMemo=${m.questionMemo}">
														<img src="${pageContext.request.contextPath}/images/icons/write.png" style="width:30px;">
													</a>
												</c:if>
												<c:if test="${m.commentMemo != null}">
													<a href="${pageContext.request.contextPath}/admin/removeComment?commentCode=${m.commentCode}">
														<img src="${pageContext.request.contextPath}/images/icons/delete.png" style="width:30px;">
													</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 페이징 -->
		<div class="pagePo" align="center">
			<!-- 검색어x -->
			<c:if test="${currentPage != 1 && word == null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=1&sort=${sort}&category=${category}">처음</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage != 1 && word != null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=1&word=${word}&sort=${sort}&category=${category}">처음</a>
			</c:if>
			<!-- 검색어x -->
			<c:if test="${currentPage > 1 && word == null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&sort=${sort}&category=${category}">이전</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage > 1 && word != null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}&sort=${sort}&category=${category}">이전</a>
			</c:if>
			${currentPage}
			<!-- 검색어x -->
			<c:if test="${currentPage < lastPage && word == null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&sort=${sort}&category=${category}">다음</a>
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage < lastPage && word != null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}&sort=${sort}&category=${category}">다음</a>
			</c:if>
			<!-- 검색어x -->
			<c:if test="${currentPage != lastPage && word == null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${lastPage}&sort=${sort}&category=${category}">마지막</a>				
			</c:if>
			<!-- 검색어o -->
			<c:if test="${currentPage != lastPage && word != null}">
				<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${lastPage}&word=${word}&sort=${sort}&category=${category}">마지막</a>				
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