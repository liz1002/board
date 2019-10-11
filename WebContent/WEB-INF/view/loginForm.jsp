<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/common.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	$(function() {		
		$("form").submit(function() {
			var result = checkInputEmpty($("input[name]"));
			if(result == false){
				return false;
			}
		})
	})
</script>	
</head>
<body>
	<form action="login.do" method="post">
		<fieldset>
			<legend>로그인</legend>
			<p>
				<label>아이디</label>
				<input type="text" name="memberId" value="${param.memberId}">
				<span class="error">아이디를 입력하세요.</span>
			</p>
			<p>
				<label>비밀번호</label>
				<input type="password" name="password" value="${param.password}">
				<span class="error">비밀번호를 입력하세요.</span>
			</p>
			<p>
				<input type="submit" value="로그인">
				<c:if test="${notMatch == true}">
					<span class="notMatch">존재하지 않는 아이디이거나, 올바른 비밀번호가 아닙니다.</span>
				</c:if>
			</p>
		</fieldset>
	</form>
</body>
</html>