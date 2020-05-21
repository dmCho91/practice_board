<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/writeCheck.js"></script>
</head>
<body>
	<c:if test="${not empty sessionScope.loginUser }">
		<c:set var="loginUser" value="${sessionScope.loginUser }"/>
	</c:if>
	<c:if test="${empty loginUser }">
		<script>
			alert("회원만 작성가능합니다.");
			location.href="main.do";
		</script>
	</c:if>

	<form action="write.do" name="frm_write" method="post">
		<table style="width: 350px;  border:1px solid #dddddd">
			<thead>
				<th colspan="2" style="background-color: #eeeeee; text-align: center;">글쓰기</th>
			</thead>
			<tbody>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bTitle" maxlength="100"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="bContent" maxlength="500" style="height: 250px;"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<th colspan="2" style="text-align:right"><input type="button" onclick="writeConfirm()" value="작성"></th>
			</tfoot>
		</table>
		<input type="button" onclick="location.href='board.do'" value="목록으로">
	</form>
</body>
</html>