<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
		padding: 10px;
		text-align: center;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>비밀번호</th>
			<th>가입시간</th>
		</tr>
		<c:forEach var="m" items="${list}">
			<tr>
				<td>${m.memberId}</td>
				<td>${m.name}</td>
				<td>${m.password}</td>
				<td>${m.regdate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>