<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			
			//새비밀번호 형식 확인
			var pwd = $("input[name='newPassword']").val();
			var pwdReg = /^[a-z0-9]{8,20}$/i;
			if(pwdReg.test(pwd) == false){
				$("input[name='newPassword']").next().css("display", "inline");
				return false;
			}
		})
	})
</script>	
</head>
<body>
	<form action="changePwd.do" method="post">
		<fieldset>
			<legend>비밀번호 변경</legend>
			<p>
				<label>현재 비밀번호 : </label>
				<input type="password" name="password" value="${param.password}">
				<span class="error">현재 비밀번호를 입력하세요.</span>
				<c:if test="${notMatch == true}">
					<span class="notMatch">현재 암호가 일치하지 않습니다.</span>
				</c:if>
			</p>
			<p>
				<label>새 비밀번호 : </label>
				<input type="password" name="newPassword" value="${param.newPassword}">
				<span class="error">영어, 숫자 8~20자리 입력</span>
			</p>
			<p>
				<input type="submit" value="변경">
			</p>
		</fieldset>
	</form>
</body>
</html>