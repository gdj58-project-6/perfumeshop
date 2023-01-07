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
			품절여부 : 
			<input type="radio" name="soldout" value="Y">Y 
			<input type="radio" name="soldout" value="N">N	 
		</div>
		<!-- hit 값은 일단 0~5로 설정, 나중에 셀렉으로 변경 예쩡-->		
		<div>
			hit : <input type="number" name="hit">
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
</body>
</html>