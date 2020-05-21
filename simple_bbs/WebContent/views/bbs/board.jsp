<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a:link {text-decoration:none; color:#000000}
	a:visited {color:#000000}
	a:hover{text-decoration:underline; color:#6E6E6E}
</style>
</head>
<body>
	<c:if test="${requestScope.msg eq 'DELETE_SUCCESS'}">
		<script>
			alert('글이 삭제되었습니다.');
		</script>
	</c:if>
	
	<h1>SIMPLE BBS</h1>
	<table style="text-align: center; border:1px solid #dddddd">
		<thead>
			<tr>
				<th style="background-color:#eeeeee; text-align:center;">번호</th>
				<th style="background-color:#eeeeee; text-align:center;">제목</th>
				<th style="background-color:#eeeeee; text-align:center;">작성자</th>
				<th style="background-color:#eeeeee; text-align:center;">작성일</th>
				<th style="background-color:#eeeeee; text-align:center;">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="dto">
				<tr>
					<td>${dto.bId }</td>
					<td><a href="view.do?bId=${dto.bId }">${dto.bTitle } </a></td>
					<td>${dto.userId }</td>
					<td>${dto.bDate }</td>
					<td>${dto.bHit }</td>
				</tr>
			</c:forEach>
	</table>
	<br>
	<table>
		<tr>
			<td>
				<c:if test="${pagination.prevPage > 0 }">
					<a href="board.do?page=${pagination.prevPage }">이전</a>
				</c:if>
				
				<c:forEach var="page" begin="${pagination.prevPage + 1}" end="${pagination.nextPage-1 }" step="1">
					<c:choose>
						<c:when test="${page eq pagination.curPage }">
							<b>[${page}]</b>
						</c:when>
						<c:otherwise>
							[<a href="board.do?page=${page }">${page }</a>]
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${pagination.totalPage >= pagination.nextPage }">
					<a href="board.do?page=${pagination.nextPage }">다음</a>
				</c:if>
				
			</td>
		</tr>
	</table>
	<a href="main.do">메인</a>&nbsp;&nbsp;
	<input type="button" onclick="location.href='write_view.do'" value="글쓰기">
</body>
</html>