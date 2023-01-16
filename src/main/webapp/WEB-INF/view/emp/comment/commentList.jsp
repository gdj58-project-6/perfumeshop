<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>commentList</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<h1>주문문의 답변</h1>
		<table class="table table-bordered">
			<tr>
				<th>문의번호</th>
				<th>주문번호</th>
				<th>주문상태</th>
				<th>상품명</th>
				<th>분류</th>
				<th>문의내용</th>
				<th>문의일자</th>
				<th>답변</th>
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
		<div>
			<c:if test="${currentPage != 1}">
				<a href="${pageContext.request.contextPath}/admin/comment?currentPage=1">처음</a>
			</c:if>
			<c:if test="${currentPage > 1}">
				<a href="${pageContext.request.contextPath}/admin/comment?currentPage=${currentPage-1}">이전</a>
			</c:if>
			${currentPage}
			<c:if test="${currentPage < lastPage}">
				<a href="${pageContext.request.contextPath}/admin/comment?currentPage=${currentPage+1}">다음</a>
			</c:if>
			<c:if test="${currentPage != lastPage}">
				<a href="${pageContext.request.contextPath}/admin/comment?currentPage=${lastPage}">마지막</a>				
			</c:if>
		</div>
	</body>
</html>