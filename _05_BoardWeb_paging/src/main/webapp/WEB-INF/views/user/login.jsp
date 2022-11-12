<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<style>
	.form-wrapper {
		display: flex;
		flex-direction: column;
		justify-content:  center;
		align-items: center;
	}
	
	#loginForm{
		width:250px;
		text-align: center;
	}
	
	#loginForm .label-wrapper {
		margin-top: 20px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	#loginForm input{
		box-sizing:border-box;
		width: 100%;
	}
	
	#loginForm div{
		display: flex;
		align-items: center;
	}
</style>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<div class="form-wrapper">
		<form id="loginForm"method="post">
			<h3>로그인</h3>
			<div class="label-wrapper">
				<label for="userId">아이디</label>
			</div>
			<input type="text" id="userId" name="userId" required>
			
			<div class="label-wrapper">
				<label for="userPw">비밀번호</label>
			</div>
			<input type="password" id="userPw" name="userPw" required>
			
			<div style="display: block; margin: 20px auto;">
				<button type="button" id="btnLogin">로그인</button>
			</div>
		</form>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	<<script>
	//로그인 시 아이디나 비밀번호가 틀렸을 경우 폼을 서브밋하지 않고
	//후처리를 위해 ajax로 처리
		$(function(){
			
			$("#loginForm").on("keydown", function(e){
				if(e.keyCode==13){
					$("#btnLogin").click();
				}
			})
			
			$("#btnLogin").on("click", () =>{
				$.ajax({
					url: '/user/login.do',
					type: 'post',
					data: $("#loginForm").serialize(),
					success: function (obj) {
						const data = JSON.parse(obj);
						//1. 아이디 체크
						if(data.message == 'idFail'){
							alert("존재하지 않는 아이디 입니다.");
							return false;
						}else if(data.message == 'pwFail'){
							//2. 비밀번호 체크
							alert("비밀번호가 틀렸습니다.");
							return false;
						}
						//3. 로그인 처리
						location.href = "/index.jsp"
					},
					error: function (e){
						console.log(e);
					}
				});
			});
		});
	</script>
</body>
</html>