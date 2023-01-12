<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goodsList</title>
</head>
<body>
	<h3>goodsList</h3>
	<div>
	<!-- 남성, 여성등등등... include? -->
		<a href="">All</a>
		<a href="">Women</a>
		<a href="">Men</a>
	</div>
	<div>
		<!--  필터, 검색, 페이징 -->
		<a href="${pageContext.request.contextPath}/member/goodsList?col=hit&sort=${paramSort}">인기순</a>
	</div>
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
	<!-- 검색기능 -->
	<form action="${pageContext.request.contextPath}/member/goodsList" method="get">
		<span>
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