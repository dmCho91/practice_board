<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/loginCheck.js"></script>
</head>
<body>

	<c:if test="${not empty sessionScope.loginUser }">
		<c:set var="loginUser" value="${sessionScope.loginUser }" />
	</c:if>
	
	<c:set var="msg" value="${requestScope.msg }"/>
	<c:choose>
		<c:when test="${msg eq 'JOIN_SUCCESS' }">
			<script>
				alert("회원가입을 축하합니다.");
			</script>
		</c:when>
		<c:when test="${msg eq 'MODIFY_SUCCESS' }">
			<script>
				alert("회원정보가 수정되었습니다.");
			</script>
		</c:when>
	</c:choose>


	<h1>MAIN PAGE</h1>
	<br>

	<c:choose>
		<c:when test="${not empty loginUser }">
			<p>${loginUser}님이 로그인하였습니다.
			<p>
				<a href="userModify_view.do">정보수정</a>&nbsp;&nbsp;<a href="logout.do">로그아웃</a>
				
		</c:when>
		<c:otherwise>
			<form action="login.do" method="post" name="frm_login">
				<table>
					<thead>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">Login</th>
					</thead>
					<tbody>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="userId" maxlength="20">
							<td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="userPw" maxlength="20"></td>
						</tr>
				</table>
				<input type="button" onclick="location.href='join_view.do'"
					value="JOIN">&nbsp;&nbsp;
				<input type="button" onclick="loginConfirm()" value="LOGIN">
			</form>
		</c:otherwise>
	</c:choose>
	<br>
	<br> &nbsp;&nbsp;
	<a href="board.do">게시판 이동</a>

</body>
</html>