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
			${document}.ready(function() {
				$('#goodsRowPerPage').change(function() {
					$('#goodsForm').submit();
					alert('change');
				});
			});
		</script>
	</head>
	<body>
		<h1>답변</h1>
		<form action="${pageContext.request.contextPath}/admin/comment" method="get" id="goodsForm">
			<!-- 검색 기능 -->
			<div>
				<label for="word">검색</label>
				<input type="text" name="word" value="${word}">
				<button type="submit">검색</button>
			</div>
			<!-- 목록수 변경 -->
			<select name="goodsRowPerPage" id="goodsRowPerPage">
				<c:if test="${goodsRowPerPage == 5}">
					<option value="5" selected="selected">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
				</c:if>
				<c:if test="${goodsRoePerPage == 10}">
					<option value="5">5</option>
					<option value="10" selected="selected">10</option>
					<option value="15">15</option>
				</c:if>
				<c:if test="${goodsRoePerPage == 15}">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15" selected="selected">15</option>
				</c:if>
			</select>
			<h2>상품문의</h2>
			<table class="tabale table-bordered">
				<tr>
					<th>문의번호</th>
					<th>상품명</th>
					<th>분류</th>
					<th style="width:500px;">내용</th>
					<th>문의일자</th>
					<th>답변</th>
					<th>작성일자</th>
				</tr>
				<c:forEach var="m" items="${goodsList}">
					<tr>
						<td>${m.goodsQuestionCode}</td>
						<td>${m.goodsName}</td>
						<td>${m.category}</td>
						<td>${m.goodsQuestionMemo}</td>
						<td>${m.gqCreatedate}</td>
						<td>
							<c:if test="${m.goodsCommentMemo == null}">
								<a href="${pageContext.request.contextPath}/admin/addComment?goodsQuestionCode=${m.goodsQuestionCode}&goodsQuestionMemo=${m.goodsQuestionMemo}">
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
								<a href="${pageContext }">삭제</a>
							</c:if>
						</td>
						<td>${m.gqcCreatedate}</td>
					</tr>				
				</c:forEach>
			</table>
			<div>
				<c:if test="${goodsCurrentPage != 1}">
					<a href="${pageContext.request.contextPath}/admin/comment?goodsCurrentPage=1">처음</a>
				</c:if>
				<c:if test="${goodsCurrentPage > 1}">
					<a href="${pageContext.request.contextPath}/admin/comment?goodsCurrentPage=${goodsCurrentPage-1}">이전</a>
				</c:if>
				${goodsCurrentPage}
				<c:if test="${goodsCurrentPage < goodsLastPage}">
					<a href="${pageContext.request.contextPath}/admin/comment?goodsCurrentPage=${goodsCurrentPage+1}">다음</a>
				</c:if>
				<c:if test="${goodsCurrentPage != goodsLastPage}">
					<a href="${pageContext.request.contextPath}/admin/comment?goodsCurrentPage=${goodsLastPage}">마지막</a>				
				</c:if>
			</div>
		</form>
	</body>
</html>