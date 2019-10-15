<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/common.css?a" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/common.js"></script>
<script>
	$(function() {
		var idCheck = 0;
		
		$("form").submit(function() {
			var result = checkInputEmpty($("input[name]"));
			var count = 0;
			
			//형식 검사
			
			var id = $("input[name='memberId']").val();
			var idReg = /^[a-z][a-z0-9]{5,14}$/i;
			if(idReg.test(id) == false){
				$("input[name='memberId']").siblings(".error").eq(0).css("display", "inline");
				count++;
			}
			
			var name = $("input[name='name']").val();
			var nameReg = /^[가-힣]{2,5}$/;
			if(nameReg.test(name) == false){
				$("input[name='name']").next().css("display", "inline");
				count++;
			}
			
			var pwd = $("input[name='password']").val();
			var pwdReg = /^[a-z0-9]{8,20}$/i;
			if(pwdReg.test(pwd) == false){
				$("input[name='password']").next().css("display", "inline");
				count++;
			}
			
			var cfPwd = $("input[name='confirmPassword']").val();
			var cfPwdReg = /^[a-z0-9]{8,20}$/i;
			if(cfPwdReg.test(cfPwd) == false){
				$("input[name='confirmPassword']").next().css("display", "inline");
				count++;
			} else if (pwd != cfPwd){
				$("input[name='confirmPassword']").next().next().css("display", "inline");
				count++;
			}
			
			if(result == false || count > 0){
				return false;
			}else if (idCheck == 0){
				alert("아이디 중복 확인을 하지 않았습니다.");
				return false;
			}else if (idCheck == -1){
				alert("아이디를 다시 확인해주세요.");
				return false;
			}
		})//submit
		
		$("#idCheck").click(function() { //아이디 중복 검사
			$.ajax({
				url: "${pageContext.request.contextPath}/idCheck.do",
				type: "get",
				data: {"id" : $("#id").val()},
				dataType: "json",
				success: function(res) {
					console.log(res);
					if(res.result == "fail"){
						$("input[name='memberId']").siblings(".error").eq(1).css("display", "inline");
						idCheck = -1;
						return false;
					}else{
						$("input[name='memberId']").siblings(".ok").css("display", "inline");
						idCheck = 1;
					}
				}   
			})
		})//idCheck
	})
</script>
</head>
<body>
	<form action="join.do" method="post">
		<fieldset>
			<legend>회원가입</legend>
			<p>
				<label>아이디</label>
				<input type="text" name="memberId" id="id">
				<button type="button" id="idCheck">중복 확인</button><br>
				<span class="error idError">영어, 숫자 6~15자리 입력</span>
				<span class="error idError">이미 존재하는 아이디입니다.</span>
				<span class="ok">사용 가능한 아이디입니다.</span>
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