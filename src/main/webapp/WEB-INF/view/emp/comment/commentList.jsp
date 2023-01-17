<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>commentList</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#rowPerPage').change(function() {
					$('#pageForm').submit();
					alert('change');
				})
				
				$('#category').change(function() {
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
	<body>
		<form id ="pageForm" method="get" action="${pageContext.request.contextPath}/admin/comment">
			<!-- 검색 -->
			<span>
				<input type="text" name="word" placeholder="주문번호를 입력해주세요." style="width:200px;">
				<button type ="submit">검색</button>
			</span>
			<!-- 목록수 -->
			<select name="rowPerPage" id="rowPerPage">
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
			<h1>주문문의 답변</h1>
			<table class="table table-bordered">
				<tr>
					<th>문의번호</th>
					<th>주문번호</th>
					<th>주문상태</th>
					<th>상품명</th>
					<th>
						<!-- 분류 할인, 포인트, 구매, 결제 -->
						<select name="category" id="category">
							<c:if test="${empty category}">
								<option value="" selected="selected">분류</option>
								<option value="할인">할인</option>
								<option value="포인트">포인트</option>
								<option value="구매">구매</option>
								<option value="결제">결제</option>
							</c:if>
							<c:if test="${category eq '할인'}">
								<option value="">분류</option>
								<option value="할인" selected="selected">할인</option>
								<option value="포인트">포인트</option>
								<option value="구매">구매</option>
								<option value="결제">결제</option>
							</c:if>
							<c:if test="${category eq '포인트'}">
								<option value="">분류</option>
								<option value="할인">할인</option>
								<option value="포인트" selected="selected">포인트</option>
								<option value="구매">구매</option>
								<option value="결제">결제</option>
							</c:if>
							<c:if test="${category eq '구매'}">
								<option value="">분류</option>
								<option value="할인">할인</option>
								<option value="포인트">포인트</option>
								<option value="구매" selected="selected">구매</option>
								<option value="결제">결제</option>
							</c:if>
							<c:if test="${category eq '결제'}">
								<option value="">분류</option>
								<option value="할인">할인</option>
								<option value="포인트">포인트</option>
								<option value="구매">구매</option>
								<option value="결제" selected="selected">결제</option>
							</c:if>
						</select>
					</th>
					<th>문의내용</th>
					<th>문의일자</th>
					<th>
						<!-- 정렬 답변전은 문의일자로(qCreatedate로 desc 정렬) 답변후는 답변일자로(qcCreatedate desc 정렬) -->
						<select name="sort" id="sort">
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
					</th>
					<th>답변일자</th>
				</tr>
				<c:forEach var="m" items="${list}">
					<tr>
						<td>${m.questionCode}</td>
						<td>${m.orderCode}</td>
						<td>${m.orderState}</td>
						<td>${m.goodsName}</td>
						<td>${m.category}</td>
						<td>${m.questionMemo}</td>
						<td>${m.qCreatedate}</td>
						<td>
							<c:if test="${m.commentMemo == null}">
								<a href="${pageContext.request.contextPath}/admin/addComment?questionCode=${m.questionCode}&questionMemo=${m.questionMemo}">
									답글작성
								</a>
							</c:if>
							<c:if test="${m.commentMemo != null}">
				         		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#memberOne">답변</button>
								<div class="modal" id="memberOne">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title">답변내용</h4>
												<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
											</div>
											<div class="modal-body">
												문의내용 : ${m.questionMemo} <br>
												답변내용 : ${m.commentMemo}
											</div>
										</div>
									</div>
								</div>
								<a href="${pageContext.request.contextPath}/admin/removeComment?commentCode=${m.commentCode}">삭제</a>
							</c:if>
						</td>
						<td>${m.qcCreatedate}</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 페이징 -->
			<div>
				<!-- 검색어x -->
				<c:if test="${currentPage != 1 && word == null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=1">처음</a>
				</c:if>
				<!-- 검색어o -->
				<c:if test="${currentPage != 1 && word != null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=1&word=${word}">처음</a>
				</c:if>
				<!-- 검색어x -->
				<c:if test="${currentPage > 1 && word == null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
				</c:if>
				<!-- 검색어o -->
				<c:if test="${currentPage > 1 && word != null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}">이전</a>
				</c:if>
				${currentPage}
				<!-- 검색어x -->
				<c:if test="${currentPage < lastPage && word == null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
				</c:if>
				<!-- 검색어o -->
				<c:if test="${currentPage < lastPage && word != null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}">다음</a>
				</c:if>
				<!-- 검색어x -->
				<c:if test="${currentPage != lastPage && word == null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${lastPage}">마지막</a>				
				</c:if>
				<!-- 검색어o -->
				<c:if test="${currentPage != lastPage && word != null}">
					<a href="${pageContext.request.contextPath}/admin/comment?rowPerPage=${rowPerPage}&currentPage=${lastPage}&word=${word}">마지막</a>				
				</c:if>
			</div>
		</form>
	</body>
</html>