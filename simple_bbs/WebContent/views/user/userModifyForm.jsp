<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/modCheck.js"></script>
</head>
<body>
	<%-- session 체크 --%>
	<c:choose>
	<c:when test="${not empty sessionScope.loginUser }">
		<c:set var="loginUser" value="${sessionScope.loginUser }"/>
	</c:when>
	<c:otherwise>
		<script>
			alert("잘못된 접근입니다.");
			location.href='main.do';
		</script>
	</c:otherwise>
	</c:choose>
	

	<h1>ModifyForm</h1>
	<form action="userModify.do" method="post" name="frm_mod">
		<table>
			<thead>
				<th colspan="2" style="background-color:#eeeeee; text-align:center;">정보수정</th>
			</thead>
			<tbody>
				<tr>
					<td>아이디</td>
					<td><input type="hidden" name="userId" value=${loginUser }>${loginUser }<td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="userPw" maxlength="20"></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="userPw_check" maxlength="20"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="userName" maxlength="20" value="${user.userName }" ></td>
				</tr>
				<tr>
					<td>성별</td>
					<c:choose>
						<c:when test="${user.userGender eq '여성' }">
							<td><input type="radio" name="userGender" value="여성" checked>여성<input type="radio" name="userGender" value="남">남</td>
						</c:when>
						<c:otherwise>
							<td><input type="radio" name="userGender" value="여성">여성<input type="radio" name="userGender" value="남" checked>남</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="userEmail" maxlength="50" value="${user.userEmail }"></td>
				</tr> 
			</tbody>
			<tfoot>
				<th colspan="2" style="text-align:right"><input type="button" onclick="modConfirm()" value="정보수정"></th>
			</tfoot>
		</table>
			<input type="button" onclick="location.href='main.do'" value="이전">
	</form>
</body>
</html>