<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyGoods</title>
</head>
<body>
	<h3>상품 수정</h3>
	<form action="${pageContext.request.contextPath}/admin/modifyGoods" method="post">		
		<c:forEach var="b" items="${list}">
			<!-- 수정폼에서 이미지 변경시 변경된 이미지로 교체하는법? 알아내야할듯 아니면 이미지 수정은 없는걸로?-->
			<div> 
				상품이미지
		 	</div>
		 	<div> 	
		 		<img src="${pageContext.request.contextPath}/upload/${b.fileName}" width="200" height="200">
		 	 	<input type="file" name="fileName">
			</div>
			<div>
				상품이름 : <input type="text" name="goodsName" value="${b.goodsName}">
			</div>
			<div>
				상품가격 : <input type="number" name="goodsPrice" value="${b.goodsPrice}">
			</div>	
			<div>
				상품설명 : 
			</div>
			<div>
				<textarea rows="5" cols="50" name="goodsMemo">${b.goodsMemo}</textarea>
			</div>
			<div>
			<!-- 나중에 if문 활용해서 수정전 체크돼있는 것에 자동으로 체크표시 나오게 -->
				품절여부 : 
				<input type="radio" name="soldout" value="Y">Y 
				<input type="radio" name="soldout" value="N">N	 
			</div>
			<!-- hit 값은 일단 0~3으로 설정-->
			<!-- 나중에 if문 활용해서 수정전 체크돼있는 것에 자동으로 체크표시 나오게 -->		
			<div>
				hit : 
				<input type="radio" name="hit" value="0">0 
				<input type="radio" name="hit" value="1">1	 
				<input type="radio" name="hit" value="2">2 
				<input type="radio" name="hit" value="3">3	 
			</div>		
			<!-- goodsCode 히든으로 보내기 다른 좋은방법 있으면 그걸로 변경-->
			<div>
				<input type="hidden" name="goodsCode" value="${b.goodsCode}">
			</div>
			<div>
				<button type="submit">상품수정</button>
			</div>
		</c:forEach>		
	</form>
</body>
</html>