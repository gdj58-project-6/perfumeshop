<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>goodsList</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$('#rowPerPage').change(function() {
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
		<h3>goodsList</h3>
		<div>
		<!-- 남성, 여성등등등... include? -->
			<a href="${pageContext.request.contextPath}/member/goodsList">모든상품</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=EDT&sort=${sort}&word=${word}">EDT</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=EDP&sort=${sort}&word=${word}">EDP</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=기타향수&sort=${sort}&word=${word}">기타향수</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=헤어퍼퓸&sort=${sort}&word=${word}">헤어퍼퓸</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=바디워시/스크럽&sort=${sort}&word=${word}">바디워시/스크럽</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=바디로션/오일&sort=${sort}&word=${word}">바디로션/오일</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=핸드케어/풋 케어&sort=${sort}&word=${word}">핸드케어/풋 케어</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=캔들&sort=${sort}&word=${word}">캔들</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=디퓨저&sort=${sort}&word=${word}">디퓨저</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=방향제&sort=${sort}&word=${word}">방향제</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=PERSONAL CARE&sort=${sort}&word=${word}">PERSONAL CARE</a>
			<a href="${pageContext.request.contextPath}/member/goodsList?category=기타&sort=${sort}&word=${word}">기타</a>
			

		</div>
		<div>	
			<form id ="pageForm" method="get" action="${pageContext.request.contextPath}/member/goodsList">
				<!--  필터, 검색, 페이징 -->
				<select name="rowPerPage" id="rowPerPage">
					<option value="10" <c:out value="${rowPerPage == '10' ? 'selected':'' }"/>>10</option>
					<option value="20" <c:out value="${rowPerPage == '20' ? 'selected':'' }"/>>20</option>
					<option value="30" <c:out value="${rowPerPage == '30' ? 'selected':'' }"/>>30</option>	
				</select>	
				<select name="sort" id="sort">
					<option value="createdate" <c:out value="${sort == 'createdate' ? 'selected':'' }"/>>신상품순</option>
					<option value="hit" <c:out value="${sort == 'hit' ? 'selected':'' }"/>>인기상품순</option>
					<option value="lowPrice" <c:out value="${sort == 'lowPrice' ? 'selected':'' }"/>>낮은가격순</option>
					<option value="highPrice" <c:out value="${sort == 'highPrice' ? 'selected':'' }"/>>높은가격순</option>
				</select>
				<input type="hidden" name="category" value="${category}">
			</form>
		</div>	
		<!-- 정렬기능 만들기 -->

		<!-- 사진, 제품이름, 가격 등등등.... -->
		<table border="1">
			<tr>
				<!-- 
					varStatus 변수속성
					${s.current} 현재 for문의 해당하는 번호
					${s.index} 0부터의 순서
					${s.count} 1부터의 순서
					${s.first} 첫 번째인지 여부
					${s.last} 마지막인지 여부
					${s.begin} for문의 시작 번호
					${s.end} for문의 끝 번호
					${s.step} for문의 증가값				
				-->
				<c:forEach var="m" items="${list}" varStatus="s">
					<c:if test="${s.index != 0 && s.index % 5 == 0}">
						<tr></tr>
					</c:if>	
					<td>
						<div><img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200"></div>
						<div><a href="${pageContext.request.contextPath}/member/goodsOne?goodsCode=${m.goodsCode}">${m.goodsName}</a></div>
						<div>${m.goodsPrice}원</div>
					</td>
				</c:forEach>
			</tr>
		</table>
		<!-- 검색기능 -->
		<form action="${pageContext.request.contextPath}/member/goodsList" method="get">
			<span>
				<input type="hidden" name="rowPerPage" value="${rowPerPage}">
				<input type="hidden" name="sort" value="${sort}">
				<input type="hidden" name="category" value="${category}">
				<input type="text" name="word">
				<button type ="submit">검색</button>
			</span>
		</form>
		<div>
			<!-- 아직 검색 결과물 페이징은 안됨 -->
			<!-- 현재 페이지가 1보다 클때만 이전버튼 나오게 -->
			<c:if test="${currentPage > 1 && word != ''}"> 
				<a href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}&word=${word}">이전</a>
			</c:if>
			<c:if test="${currentPage > 1 && word == ''}">
				<a href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage-1}">이전</a>
			</c:if>
			<span>${currentPage}</span>
			<!-- 현재 페이지가 라스트페이지보다 작으면 다음버튼 나오게 -->
			<c:if test="${currentPage < lastPage && word != ''}"> 
			<a href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}&word=${word}">다음</a>
			</c:if>	
			<c:if test="${currentPage < lastPage && word == ''}">
				<a href="${pageContext.request.contextPath}/member/goodsList?rowPerPage=${rowPerPage}&currentPage=${currentPage+1}">다음</a>
			</c:if>
		</div>
		<a href='${pageContext.request.contextPath}/home'>홈</a>
	</body>
</html>