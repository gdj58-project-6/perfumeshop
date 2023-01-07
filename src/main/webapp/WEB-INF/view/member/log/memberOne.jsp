<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<table border="1">
			<tr>	
				<th>아이디</th>
				<td>${memberOne.customerId}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${memberOne.customerName}</td>
				<td>
					<!-- 회원 정보 수정 modal -->
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">정보 수정</button>
					<form action="${pageContext.request.contextPath}/member/modifyMember" method="post">
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">회원 정보 수정</h4>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>
								<div class="modal-body">
									이름 : <input type="text" name="name" value="${memberOne.customerName}"><br>
									전화번호 : <input type="text" name="phone" value="${memberOne.customerPhone}">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
									<button type="submit">수정</button>
								</div>
							</div>
						</div>
					</div>
					</form>
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${memberOne.customerPhone}</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td>${memberOne.point}</td>
			</tr>
			<tr>
				<th>등급</th>
				<td>${memberOne.authCode}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${memberOne.address}</td>
				<td>
					<!-- 회원 주소, 수정 modal -->
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">정보 수정</button>
					<form action="${pageContext.request.contextPath}/member/modifyMember" method="post">
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">회원 정보 수정</h4>
									<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								</div>
								<div class="modal-body">
									이름 : <input type="text" name="name" value="${memberOne.customerName}"><br>
									전화번호 : <input type="text" name="phone" value="${memberOne.customerPhone}">
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
									<button type="submit">수정</button>
								</div>
							</div>
						</div>
					</div>
					</form>
				</td>
				</td>
			</tr>
			<tr>
				<th>가입일자</th>
				<td>${memberOne.createdate}</td>
			</tr>
		</table>
		<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
		<a href="${pageContext.request.contextPath}/member/modifyMemberPw">비밀번호수정</a>
		<a href="${pageContext.request.contextPath}/member/modifyMember">회원정보수정</a>
	</body>
</html>