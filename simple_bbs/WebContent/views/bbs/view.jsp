<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="loginUser" value="${sessionScope.loginUser }"/>
	<c:set var="modify_bId" value="${requestScope.modify_bId }"/>
	<c:if test="${not empty modify_bId }">
		<script>
			alert('글이 수정되었습니다.');
			location.href="view.do?bId=${modify_bId}&complete=modify";
		</script>
	</c:if>


	<table style="width: 400px; border: 1px solid #dddddd">
		<thead>
			<th colspan="3" style="background-color: #eeeeee; text-align: left;">${list.bTitle }</th>
		</thead>
		<tbody>
			<tr>
				<td>작성자 ${list.userId} </td>
				<td>조회수 ${list.bHit }</td>
				<td>작성일 ${list.bDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="2" style="min-height: 200px; text-align: left;">${list.bContent }</td>
			</tr>
		</tbody>
		<tfoot style="text-align: right;">
			<c:if test="${not empty loginUser && loginUser eq list.userId }">
				<th><a href="modify_view.do?bId=${list.bId }">수정</a>
				<th><a onclick="return confirm('정말 삭제하시겠습니까?')"
					href="delete.do?bId=${list.bId }&userId=${list.userId}">삭제</a>
			</c:if>
		</tfoot>
	</table>
	<input type="button" onclick="location.href='board.do'" value="목록으로">
</body>
</html>