<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addGoods</title>
</head>
<body>
	<h2>상품등록</h2>
	<form action="${pageContext.request.contextPath}/admin/addGoods" method="post" enctype="multipart/form-data">
		<div>
			상품이름 : <input type="text" name="goodsName">
		</div>
		<div>
			상품가격 : <input type="number" name="goodsPrice">
		</div>	
		<div>
			상품종류 : 
			<select name="goodsCategory">
				<option value="EDT">EDT</option>
				<option value="EDP">EDP</option>
				<option value="기타향수">기타향수</option>
				<option value="헤어퍼퓸">헤어퍼퓸</option>
				<option value="바디워시/스크럽">바디워시/스크럽</option>
				<option value="바디로션/오일">바디로션/오일</option>
				<option value="핸드케어/풋 케어">핸드케어/풋 케어</option>
				<option value="캔들">캔들</option>
				<option value="디퓨저">디퓨저</option>
				<option value="방향제">방향제</option>
				<option value="PERSONAL CARE">PERSONAL CARE</option>
				<option value="기타">기타</option>
			</select>
		</div>
		<div>
			상품설명 : 
		</div>
		<div>
			<textarea rows="5" cols="50" name="goodsMemo"></textarea>
		</div>
		<div>
			품절여부 : 
			<input type="radio" name="soldout" value="Y">Y 
			<input type="radio" name="soldout" value="N">N	 
		</div>
		<!-- hit 값은 일단 0~3으로 설정-->		
		<div>
			hit : 
			<input type="radio" name="hit" value="0">0 
			<input type="radio" name="hit" value="1">1	 
			<input type="radio" name="hit" value="2">2 
			<input type="radio" name="hit" value="3">3	 
		</div>
		<!-- empId는 받와아야 함 -> 해결했음 -->
		<div>
			작성자 : <input type="text" name="empId" value="${loginMember.empId}" readonly="readonly">
		</div>
		<div>
			상품이미지 : <input type="file" name="goodsImg">
		</div>
		<div>
			<button type="submit">상품등록</button>
		</div>
	</form>
	<a href='${pageContext.request.contextPath}/home'>홈</a>
</body>
</html>