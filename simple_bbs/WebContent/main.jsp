<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String loginUser = null;
		if(session.getAttribute("loginUser") != null){
			loginUser = (String)session.getAttribute("loginUser");
		}
	%>
	<h1>MAIN PAGE</h1>
	<br>
	<%
		if(loginUser != null){
	%>
		<p><%=loginUser %>님이 로그인하였습니다.<p>
		<a href="userModifyForm.jsp">정보수정</a>&nbsp;&nbsp;<a href="logout.jsp">로그아웃</a>
		<br><br>
		<a href="board.jsp">게시판 이동</a>
	<%
		}else{
	%>
		
		<form action="loginAction.jsp" method="post">
			<table>
				<thead>
				<th colspan="2" style="background-color:#eeeeee; text-align:center;">Login</th>
			</thead>
			<tbody>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userId"  maxlength="20"><td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="userPw" maxlength="20"></td>
				</tr>
			</table>
			<input type="button" onclick="location.href='joinForm.jsp'" value="JOIN">&nbsp;&nbsp;<input type="submit" value="LOGIN">
		</form>
		<br><br>
		&nbsp;&nbsp;<a href="board.jsp">게시판 이동</a>
	<%
		}
	%>
</body>
</html>