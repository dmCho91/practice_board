<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/writeCheck.js"></script>
</head>
<body>
	<c:set var="loginUser" value="${sessionScope.loginUser }" />
	<c:choose>
		<c:when test="${empty loginUser}">
			<script>
				alert('잘못된 접근입니다.');
				location.href = 'board.do';
			</script>
		</c:when>
		<c:when test="${loginUser ne list.userId }">
			<script>
				alert('권한이 없습니다.');
				location.href = 'board.do';
			</script>
		</c:when>
	</c:choose>


	<form action="modify.do" name="frm_write" method="post">
		<input type="hidden" name="bId" value=${list.bId }>
		<table style="width: 350px; border: 1px solid #dddddd">
			<thead>
				<th colspan="2"
					style="background-color: #eeeeee; text-align: center;">글 수정</th>
			</thead>
			<tbody>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bTitle" maxlength="100"
						value=${list.bTitle }></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="bContent" maxlength="500"
							style="height: 250px;">${list.bContent }</textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<th colspan="2" style="text-align: right"><input type="button"
					onclick="writeConfirm()" value="수정"></th>
			</tfoot>
		</table>
		<input type="button" onclick="location.href='board.do'" value="목록으로">
	</form>
</body>
</html>