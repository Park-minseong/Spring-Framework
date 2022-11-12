<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="undateNameForm" action="updateName.do" method="post">
		<input type="hidden" name="originalFirstName" value="${name.firstName }">
		<input type="hidden" name="originalLastName" value="${name.lastName }">
		<input type="text" name="firstName" value="${name.firstName }">
		<input type="text" name="lastName" value="${name.lastName }">
		<button type="submit">수정</button>
	</form>
	<a href="deleteName.do?firstName=${name.firstName }&lastName=${name.lastName }">삭제</a>	
</body>
</html>