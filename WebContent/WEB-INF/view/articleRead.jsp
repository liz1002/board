<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	a:hover {
		text-shadow: 1px 1px 1px #ccc;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<th>번호</th>
			<td>${article.article_no}</td>
		</tr>
		<tr>
			<th>제목</th>			
			<td>${article.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${article.writer_id}</td>
		</tr>
		<tr>
			<th>내용</th>			
			<td>${article.content}</td>
		</tr>
		<tr>
			<th colspan="2">
				<a href="list.do">목록</a> | 
				<a href="update.do?no=${article.article_no}">게시글 수정</a> | 
				<a href="delete.do?no=${article.article_no}">게시글 삭제</a>
			</th>
		</tr>
	</table>
</body>
</html>