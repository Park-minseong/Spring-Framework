<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- tomcat이 서버실행 시 자동으로 index를 찾게 돼있음
WEB-INF는 숨겨주는 기능이있어서 찾지못함 따라서 WEB-INF상위인 webapp에 index생성 -->
	<jsp:include page="./header.jsp"></jsp:include>
	<div style="text-align: center;">
		<h1>게시판 홈 화면입니다.</h1>
		<p>회원가입, 호그인, 그리고 간단한 게시판 기능을</p>
		<p>구현하도록 하겠습니다.</p>	
		
	</div>
	<jsp:include page="./footer.jsp"></jsp:include>

</body>
</html>