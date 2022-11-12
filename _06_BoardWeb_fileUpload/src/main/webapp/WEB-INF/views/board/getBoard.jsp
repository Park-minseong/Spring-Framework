<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
</head>
<body>
 	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<div style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
		<h3>게시글 상세</h3>
		<form action="/board/updateBoard.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boardSeq" value="${board.boardSeq }"/>
			<table border="1" style="border-collapse: collapse;">
				<tr>
					<td style="background: orenge; width: 70px">
						제목
					</td>
					<td style="text-align: left">
						<input type="text" name="boardTitle" id="boardTitle" value="${board.boardTitle }" />
					</td>
				</tr>
				<tr>
					<td style="background: orenge;">
						작성자
					</td>
					<td style="text-align: left">
						<input type="text" name="boardWriter" id="boardWriter" value="${board.boardWriter }" readonly/>
					</td>
				</tr>
				<tr>
					<td style="background: orenge;">
						내용
					</td>
					<td style="text-align: left">
						<textarea name="boardContent" id="boardContent" cols="40" rows="10">${board.boardContent }</textarea>
					</td>
				</tr>
				<tr>
					<td style="background: orenge;">
						작성일
					</td>
					<td style="text-align: left">
						${board.boardRegdate}
					</td>
				</tr>
				<tr>
					<td style="background: orenge;">
						조회수
					</td>
					<td style="text-align: left">
						${board.boardCnt}
					</td>
				</tr>
				<tr>
					<td style="background: orange; width: 70px;">
						첨부파일
					</td>
					<td>
						<c:forEach items="${fileList }" var="file">
							<a href="${file.fileName }" class="downlink" id="${fil.fileSeq }">
								${file.originalFileName }
							</a>
							<button type="button" id="btnDeleteFile" onclick="deleteBoardFile('${file.fileSeq}')">삭제</button>
							<img style="max-width: 200px;" src="/upload/${file.fileName }"/>
							<br />
						</c:forEach>
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
				<tr id="btnWrap">
					<td colspan="2" align="center">
						<button type="submit" id="btnUpdate">수정</button>
					</td>
				</tr>
			</table>
		</form>
		<hr />
		<a href="/board/insertBoard.do">글 등록</a>
		<a id="btnDelete" href="/board/deleteBoard.do?boardSeq=${board.boardSeq}">글 삭제</a>
		<a href="/board/getBoardList.do?pageNum=${pageNum }">글 목록</a>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
	<script>
	 	function deleteBoardFile(fileSeq){
			$.ajax({
				url: '/board/deleteBoardFile.do',
				type: 'post',
				data: {
						"boardSeq": $("input[name='boardSeq']").val(),
						"fileSeq": fileSeq
					  },
			    success: function(){
			    	location.reload();
			    },
			    error: function(e){
			    	console.log(e);
			    }
			});
		 }
	
		$(function() {
			const loginUserId = '${loginUser.userId}';
			const boardWriter = '${board.boardWriter}';
			
			if(loginUserId !== boardWriter) {
				$("#btnWrap").hide();
				$("#btnDelete").hide();
				$("#boardTitle").attr("readonly", true);
				$("#boardContent").attr("readonly", true);
			}
			$("#btnUpdate").on("click", (e) => {
				alert("수정이 완료되었습니다.");
			});
			
			$(".downlink").on("click", function(e) {
				e.preventDefault();
				
				const fileName = $(this).attr("href");
				window.location = "/board/fileDown.do?fileName=" + fileName;
			});			
		});
		
	</script>
</body>
</html>