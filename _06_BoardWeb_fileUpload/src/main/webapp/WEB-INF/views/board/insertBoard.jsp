<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<div style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
		<h3>새 글 등록</h3>
		<form action="/board/insertBoard.do" method="post" enctype="multipart/form-data">
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<td style="background: orange; width: 70px;">
						제목
					</td>
					<td style="text-align: left;">
						<input type="text" name="boardTitle" required>
					</td>
				</tr>
				<tr>
					<td style="background: orange;">
						작성자
					</td>
					<td style="text-align: left;">
						<input type="text" name="boardWriter" value="${loginUser.userId }" readonly/>
					</td>
				</tr>	
				<tr>
					<td style="background: orange;" >
						내용
					</td>
					<td style="text-align: left";>
						<textarea name="boardContent" cols="40" rows="10" required></textarea>
					</td>
				</tr>
				<tr>
					<td style="background: orange; width: 70px;">
						업로드
					</td>
					<td align="left">
						<input type="file" name="uploadFiles" multiple="multiple">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"> 
					<button type="submit">새 글 등록</button>
					</td>
				</tr>
			</table>
		</form>
		<hr/>
		<a href="/board/getBoardList.do">글 목록</a>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
</body>
</html>