<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(function() {
		$("#btn1").click(function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/article/readJson.do",
				type: "get",
				data: {"no" : $("#no").val()},
				dataType: "json",
				success: function(res) {
					console.log(res);
					$("#result").text(res.article_no);
					$("#result").append("<br>" + res.title + "<br>");
					$("#result").append(res.content + "<br>");
					var date = new Date(res.regdate);
					$("#result").append(date.getFullYear() + "-" +
										(date.getMonth()+1) + "-" + 
										date.getDate() + "<br>");
				}
			})
		}) // #btn1.click
		
		$("#btn2").click(function() {
			$.ajax({
				url:"${pageContext.request.contextPath}/article/listJson.do",
				type: "get",
				dataType: "json",
				success: function(res) {
					console.log(res);
					$(res).each(function(i, obj) {
						var date = new Date(obj.regdate);
						var dateRes = date.getFullYear() + "-" +
									(date.getMonth()+1) + "-" +
									date.getDate();
						var $li = $("<li>");
						$li.append(obj.article_no + " | ");
						$li.append(obj.title + " | ");
						$li.append(dateRes);
						$("#list").append($li);
					})
				}
			})
		}) // #btn2.click
		
		$("#btn3").click(function() {			
			$.ajax({
				url: "${pageContext.request.contextPath}/memberJson.do",
				type: "get",
				data: {"id" : $("#id").val()},
				dataType: "json",
				success: function(res) {
					console.log(res);
					if(res.result == "fail"){
						$("#result3").text("존재하지 않는 아이디입니다.");
						return false;
					}else if(res.result == "success"){
						$("#result3").text(res.member.memberId);
						$("#result3").append("<br>" + res.member.name + "<br>");
						$("#result3").append(res.member.password + "<br>");
						var date = new Date(res.member.regdate);
						$("#result3").append(date.getFullYear() + "-" +
											(date.getMonth()+1) + "-" + 
											date.getDate() + "<br>");
					}s
				}
			})
		})
	})
</script>
</head>
<body>
	<h1>게시글 상세보기 데이터 가져오기</h1>
	<input type="text" id="no">
	<button id="btn1">가져오기</button>
	<div id="result">결과 데이터</div>
	
	<hr>
	
	<button id="btn2">리스트 가져오기</button>
	<ul id="list"></ul>
	
	<hr>
	
	<input type="text" id="id" placeholder="id 입력">
	<button id="btn3">map data가져오기</button>
	<div id="result3"></div>
</body>
</html>