<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/ress.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style_header_footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style_support.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.bundle.min.js"></script>

<style>
	header{
	    height: 100px;
	}
</style>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<main>
	<section>
	    <a href="/index.jsp"><img class="login-logo" src="${pageContext.request.contextPath }/images/main_logo.png" alt="로고"></a>
	    <form action="/user/join2.do" class="register-form" name="register-form" id="join-form" method="POST">
	        <fieldset>
	            <legend>회원가입</legend>
	            <ul class="register-info">
	                <li>
	                    <label for="reg-input-id">아이디</label>
	                    <input type="text" id="reg-input-id" name="userId" required >
	                    <input type="button" value="중복확인"  id="reg-input-id-btn">
	                </li>	    
	                <li>
	                    <label for="reg-input-pw">비밀번호</label>
	                    <input type="password" id="reg-input-pw" name="userPw" required >
	                </li>
	                <li>
	                    <label for="reg-input-pwchk">비밀번호 확인</label>
	                    <input type="password" id="reg-input-pwchk" name="userPwCheck" required >
	                    <span id="pwchk-result" style="font-size: 12px; margin-left: 5px;">비밀번호 일치</span>
	                </li>
	                <li>
	                    <label for="reg-input-name">이름</label>
	                    <input type="text" id="reg-input-name" name="userNm" required >
	                </li>
	                <li>
	                    <label for="reg-input-tel">전화번호</label>
	                    <input type="tel" id="reg-input-tel" name="userTel" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required >
	                </li>
	                <li>
	                    <label for="reg-input-telchk">전화번호 인증</label>
	                    <input type="number" id="reg-input-telchk" required >
	                    <input type="button" value="인증번호 전송"  id="reg-input-telchk-btn">
	                </li>
	                <li>
	                    <label for="reg-input-bank">계좌번호</label>
	                    <input type="number" id="reg-input-bank" name="userBnk" required>
	                </li>
	                <li>
	                    <label for="reg-input-add">주소</label>
	                    <input type="text" id="reg-input-add" name="userAdd" required >
	                    <input type="button" value="주소검색"  id="reg-input-add-btn">
	                </li>
	                <li>
	                    <label for="reg-input-addDetail">상세주소</label>
	                    <input type="text" id="reg-input-addDetail" name="userAddDetail">
	                </li>
	                <li>
	                    <label for="reg-input-mail">이메일</label>
	                    <input type="email" id="reg-input-mail" name="userMail">
	                </li>
	                <li style="justify-content: center;">
	                    <input type="button" value="뒤로가기"  id="go-back-btn">
	                    <input type="submit" value="회원가입"  id="regist-sybmit-btn">
	                </li>
	            </ul>
	        </fieldset>
	    </form>
	</section>  
	</main>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
	<script>
	$(function(){
		let idCheck = false;
		let pwValidation = false;
		let pwCheck = false;
		
		$("#pwchk-result").hide();
			
		//아이디 중복 검사	
		$("#reg-input-id-btn").on("click", () =>{
			if($("#reg-input-id").val() == null || $("#reg-input-id").val() == ''){
				console.log($("#reg-input-id").val());
				alert("아이디를 입력하세요");
				return;
			}
			$.ajax({
				url: '/user/idCheck2.do',
				type: 'post',
				data: $("#join-form").serialize(),
				success: function(obj){
				
					const data = JSON.parse(obj);
					
					if(data.resultIdCheck < 1){
						if(confirm("사용가능한 아이디입니다." + $("#reg-input-id").val() + "를(을) 사용하시겠습니까?")){
							idCheck = true;
							$("#reg-input-id-btn").attr("disabled", true);
						}
					}else{
						idCheck = false;
						alert("이미 존재하는 아이디입니다.");
						$("#reg-input-id").focus();
						return;
					}
				},
				error: function(e){
					console.log(e);
				}
			});
		});
		
		$("#reg-input-id").on("keyup", () => {
			idCheck = false;
			$("#reg-input-id-btn").attr("disabled", false);
		});
		
		//비밀번호 유효성 검사
		function validationPassword(character){
			return /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{9,}$/.test(character);
		}
		
		//비밀번호 확인
		$("#reg-input-pw").on("keyup", ()=>{
			if(!validationPassword($("#reg-input-pw").val())){
				pwValidation = false;
				$("#pwValidation").show();
				$("#reg-input-pw").focus();
			}else{
				pwValidation = true;
				$("#pwValidation").hide();
			}
			
			if($("#userPw").val() == $("#reg-input-pwchk").val()){
				pwCheck = true;
				$("#pwchk-result").css("color", "green");
				$("#pwchk-result").text("비밀번호 일치");
			}else{
				pwCheck = false;
				$("#pwchk-result").css("color", "red");
				$("#pwchk-result").text("비밀번호가 불일치");
			}
		});
		
		//비밀번호 확인
		$("#reg-input-pwchk").on("change", function(){
			$("#pwchk-result").show();
			if($("#reg-input-pw").val() == $("#reg-input-pwchk").val()){
				pwCheck = true;
				$("#pwchk-result").css("color", "green");
				$("#pwchk-result").text("비밀번호가 일치");
			}else{
				pwCheck = false;
				$("#pwchk-result").css("color", "red");
				$("#pwchk-result").text("비밀번호가 불일치");
			}
		});
		//회원가입할(회원가입 폼 서브밋)때 마지막 유효성 검사
		$("#join-form").on("submit", function(e){
			if(!idCheck){
				alert("아이디 중복체크를 진행해주세요.");
				$("#reg-input-id").focus();
				e.preventDefault();
				return;
			}
			if(!pwValidation){
				alert("비밀번호는 영문자, 숫자, 특수문자 조합의 9자리 이상으로 설정해주세요.");
				$("#reg-input-pw").focus();
				e.preventDefault();
				return;
			}
			if(!pwCheck){
				alert("비밀번호가 일지하지 않습니다.");
				$("#reg-input-pwchk").focus();
				e.preventDefault();
				return;
			}
		});
		
	});
	</script>
</body>
</html>