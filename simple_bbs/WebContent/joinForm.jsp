<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/joinCheck.js"></script>
</head>
<body>
	<%-- session 체크 --%>
	<%
		if(session.getAttribute("loginUser") != null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인 되었습니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
	%>


	<h1>JoinForm</h1>
	<form action="joinAction.jsp" method="post" name="frm_join">
		<table>
			<thead>
				<th colspan="2" style="background-color:#eeeeee; text-align:center;">회원가입</th>
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
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="userPw_check" maxlength="20"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="userName" maxlength="20"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="radio" name="userGender" value="여성" checked>여성<input type="radio" name="userGender" value="남">남</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="userEmail" maxlength="50"></td>
				</tr> 
			</tbody>
			<tfoot>
				<th colspan="2" style="text-align:right"><input type="button" onclick="joinConfirm()" value="회원가입"></th>
			</tfoot>
		</table>
			<input type="button" onclick="location.href='main.jsp'" value="이전">
	</form>
</body>
</html>