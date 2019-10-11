<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	a{
		text-decoration: none;
		color: black;
	}
	p>a{
		font-size: 17px;
		background: #EF6F6F;
		color: #fff;
		border: 1px solid #EF6F6F;
		border-radius: 15px;
		padding: 5px 7px;
	}
	a:hover {
		text-shadow: 1px 1px 1px #ccc;
	}
</style>
</head>
<body>
	<p>
		<a href="add.do">등록하기</a>
	</p>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회 수</th>
		</tr>
		<c:forEach var="article" items="${list}">
			<tr>
				<td>${article.article_no}</td>
				<td><a href="read.do?no=${article.article_no}">${article.title}</a></td>
				<td>${article.writer_id}</td>
				<td>${article.read_cnt}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>