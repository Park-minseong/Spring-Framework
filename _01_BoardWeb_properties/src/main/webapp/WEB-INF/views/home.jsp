<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	${hi}
</h1>

<a href="test.do?firstName=minseong&lastName=park&age=27&job=student">click</a>
<form action="test.do" method="post">
	<input type="text" placeholder="firstName" name="firstName">
	<input type="text" placeholder="lastName" name="lastName">
	<input type="text" placeholder="age" name="age">
	<input type="text" placeholder="job" name="job">
	<button type="submit">submit</button>
</form>
<a href="getNameList.do">이름 목록 페이지로</a>
</body>
</html>
