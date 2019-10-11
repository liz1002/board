<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{
		width: 450px;
		padding: 10px;
		border: 1px solid black;
	}
	a{
		text-decoration: none;
		font-size: 20px;
	}
</style>
</head>
<body>
	<div>
		<c:if test="${result <= 0}">
			<h3>비밀번호 변경을 실패했습니다...</h3>
		</c:if>
		<c:if test="${result > 0}">
			<h3>비밀번호 변경을 성공했습니다!!!</h3>
		</c:if>
		<a href="${pageContext.request.contextPath}">홈</a>
	</div>
</body>
</html>