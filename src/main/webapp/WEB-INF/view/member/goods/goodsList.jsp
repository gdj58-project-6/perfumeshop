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
			});
		</script>
	</head>
	<body>
		<h3>goodsList</h3>
		<div>
		<!-- 남성, 여성등등등... include? -->
			<a href="">모든상품</a>
			<a href="">향수</a>
			<a href="">헤어</a>
		</div>
			
		<form id ="pageForm" method="get" action="${pageContext.request.contextPath}/member/goodsList">
			<!--  필터, 검색, 페이징 -->
			<select name="rowPerPage" id="rowPerPage">
				<c:if test="${rowPerPage == 10}">
					<option value="10" selected="selected">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 20}">
					<option value="10">10</option>
					<option value="20" selected="selected">20</option>
					<option value="30">30</option>
				</c:if>
				<c:if test="${rowPerPage == 30}">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30" selected="selected">30</option>
				</c:if>		
			</select>		
			<select>
				<option>신상품순</option>
				<option>인기상품순</option>
				<option>낮은가격순</option>
				<option>높은가격순</option>
			</select>	
			<span>
				<input type="text" name="word">
				<button type ="submit">검색</button>
			</span>
		</form>
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
						<div>${m.goodsPrice}</div>
					</td>
				</c:forEach>
			</tr>
		</table>
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