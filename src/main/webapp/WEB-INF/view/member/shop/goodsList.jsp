<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<!--  필터? 검색? -->
	</div>
	<!-- 사진, 제품이름, 가격 등등등.... -->
	<table border="1">
		<tr>
			<td>
				<div><img src="${pageContext.request.contextPath}/upload/${test}" width="200" height="200"></div>
			</td>
		</tr>
	</table>
</body>
</html>