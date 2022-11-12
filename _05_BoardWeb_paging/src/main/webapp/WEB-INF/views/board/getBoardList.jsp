<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	.pagenation {
		list-style: none;
		width: 100%;
		display: inline-block;	
		padding:0;
	}
	
	.pagenation_button{
		float: left;
		margin: auto 10px;
	}
</style>
</head>
<body>
	<jsp:include page="${pageContext.request.contextPath }/header.jsp"></jsp:include>
	<div style="display: flex; flex-direction:  column; justify-content: center; align-items: center;">
		<h3>게시글 목록</h3>
		<form id="searchForm" action="/board/getBoardList.do" method="post">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }" />
			<input type="hidden" name="amount" value="${pageMaker.cri.amount }" />
			<table border="1" style="width: 700px; border-collapse: collapse;">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="all" <c:if test="${searchCondition eq 'all'}">selected</c:if> >전체</option>
							
							<option value="title" <c:if test="${searchCondition eq 'title'}">selected</c:if> >제목</option>
							
							<option value="content"	<c:if test="${searchCondition eq 'content'}">selected</c:if> >내용</option>
							
							<option value="writer" <c:if test="${searchCondition eq 'writer'}">selected</c:if> >작성자</option>
						</select>
						<input type="text" name="searchKeyword" value="${searchKeyword }"/>
						<button type="button" id="btnSearch">검색</button>
					</td>
				</tr>
			</table>
		</form>
		<table border="1" style="width:700px; border-collapse:collapse;">
			<tr>
				<th style="background: orange; width: 100px;">번호</th>
				<th style="background: orange; width: 200px;">제목</th>
				<th style="background: orange; width: 150px;">작성자</th>
				<th style="background: orange; width: 150px;">등록일</th>
				<th style="background: orange; width: 100px;">조회수</th>
			</tr>
			<!-- <tr>
				<td>1</td>
				<td>
					<a href="/board/getBoard.do?boardSeq=1">제목1</a>
				</td>
				<td>작성자1</td>
				<td>2022.07.13</td>
				<td>1</td>
			</tr> -->
			<c:forEach var="board" items="${boardList }">
				<tr style="text-align:center;">
					<td>${board.boardSeq }</td>
					<td style="text-align:left;">
						<a href="/board/getBoard.do?boardSeq=${board.boardSeq}"> ${board.boardTitle}</a>
					</td>
					<td>${board.boardWriter }</td>
					<td>${board.boardRegdate }</td>
					<td>${board.boardCnt }</td>
				</tr>
			</c:forEach>			
		</table>
		<br />
		<div style="text-align: center;">
			<ul class="pagenation">
				<c:if test="${pageMaker.prev }">
					<li class="pagenation_button">
						<a href="${pageMaker.startPage - 1 }">Previous</a>
					</li>
				</c:if>
				
				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
					<li class="pagenation_button">
						<a href="${num}">${num }</a>
					</li>				
				</c:forEach>
				
				<c:if test="${pageMaker.next }">
					<li class="pagenation_button">
						<a href="${pageMaker.startPage + 1 }">Next</a>
					</li>
				</c:if>
			</ul>
		</div>
		<br />
		<a href="/board/insertBoard.do">새 글 등록</a>
	</div>
	<jsp:include page="${pageContext.request.contextPath }/footer.jsp"></jsp:include>
	
	<script>
		$(function(){
			const searchForm = $("#searchForm");
			
			$(".pagenation a").on("click", function(e) {
				e.preventDefault();
				
				$("input[name='pageNum']").val($(this).attr("href"));
			 	searchForm.submit();
			});
			$("#btnSearch").click(function(){
				$("input[name='pageNum']").val("1");
				searchForm.submit();
			})
		});
	</script>
</body>
</html>