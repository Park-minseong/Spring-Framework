<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ul, li {
		list-style: none;
	}
	
	a{
		text-decoration:none;
		color: black;
	}
	
	a:hover{
		color: blue;
	}
	
	.main-nav li a{
		font-size: 1.123rem;
	}
	
	.user-nav{
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.user-nav li {
		margin-left: 10px;
	}
	
	.user-nav li a{
		font-size: 0.9rem;	
	}
</style>
</head>
<body>
	<header style="display: flex; justify-content: space-around; align-items: center; background: orange;">
		<h1>
			<a href="/index.jsp"> 홈 </a>
		</h1>
		<nav>
			<ul class="main-nav">
				<li>
					<a href="/board/getBoardList.do">게시글 목록</a>
				</li>
			</ul>
		</nav>
		<nav>
			<ul class="user-nav">
				<!-- 로그인 정보 없을 때 -->
				<c:choose>
					<c:when test="${loginUser eq null }">
						<li>
							<a href="/user/login.do">로그인</a>
						</li>
						<li>
							<a href="/user/join.do">회원가입</a>
						</li>
						<li>
							<a href="/user/join2.do">회원가입2</a>
						</li>
					</c:when>
				
				
					<c:otherwise>
						<li>
							<a href="/user/login.do">${loginUser.userId }</a>
						</li>
						<li>
							<a href="/user/logout.do">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
				<!-- 로그인 정보 있을 때 ^-->
			</ul>
		</nav>
	</header>
</body>
</html>