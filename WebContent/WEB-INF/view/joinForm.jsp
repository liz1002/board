<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
			//형식 검사
			
			var id = $("input[name='memberId']").val();
			var idReg = /^[a-z][a-z0-9]{5,14}$/i;
			if(idReg.test(id) == false){
				$("input[name='memberId']").next().css("display", "inline");
				return false;
			}
			
			var name = $("input[name='name']").val();
			var nameReg = /^[가-힣]{2,5}$/;
			if(nameReg.test(name) == false){
				$("input[name='name']").next().css("display", "inline");
				return false;
			}
			
			var pwd = $("input[name='password']").val();
			var pwdReg = /^[a-z0-9]{8,20}$/i;
			if(pwdReg.test(pwd) == false){
				$("input[name='password']").next().css("display", "inline");
				return false;
			}
			
			var cfPwd = $("input[name='confirmPassword']").val();
			var cfPwdReg = /^[a-z0-9]{8,20}$/i;
			if(cfPwdReg.test(cfPwd) == false){
				$("input[name='confirmPassword']").next().css("display", "inline");
				return false;
			} else if (pwd != cfPwd){
				$("input[name='confirmPassword']").next().next().css("display", "inline");
				return false;
			}
		})
	})
</script>
</head>
<body>
	<form action="join.do" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<p>
				<label>아이디</label>
				<input type="text" name="memberId">
				<span class="error">영어, 숫자 6~15자리 입력</span>
			</p>
			<p>
				<label>이름</label>
				<input type="text" name="name">
				<span class="error">한글 2~5자리 입력</span>
			</p>
			<p>
				<label>비밀번호</label>
				<input type="password" name="password">
				<span class="error">영어, 숫자 8~20자리 입력</span>
			</p>
			<p>
				<label>비밀번호 확인</label>
				<input type="password" name="confirmPassword">
				<span class="error">영어, 숫자 8~20자리 입력</span>
				<span class="error">비밀번호 불일치</span>
			</p>
			<p>
				<input type="submit" value="가입">
			</p>
		</fieldset>
	</form>
</body>
</html>