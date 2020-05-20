<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter"%>
<%@ page import = "simple_bbs.dto.UserDTO" %>
<%@ page import = "simple_bbs.dao.UserDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/modCheck.js"></script>
</head>
<body>
	<%-- session 체크 --%>
	<%
		String loginUser = null;
		if(session.getAttribute("loginUser") != null){
			loginUser = (String)session.getAttribute("loginUser");
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
			UserDAO dao = new UserDAO();
			UserDTO user = dao.selectUser(loginUser);
	%>


	<h1>ModifyForm</h1>
	<form action="modifyAction.jsp" method="post" name="frm_mod">
		<table>
			<thead>
				<th colspan="2" style="background-color:#eeeeee; text-align:center;">정보수정</th>
			</thead>
			<tbody>
				<tr>
					<td>아이디</td>
					<td><input type="hidden" name="userId" value=<%= loginUser %>><%= loginUser %><td>
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
					<td><input type="text" name="userName" maxlength="20" value=<%= user.getUserName() %> ></td>
				</tr>
				<tr>
					<td>성별</td>
					<%
						if(user.getUserGender().equals("여성")){
					%>
					<td><input type="radio" name="userGender" value="여성" checked>여성<input type="radio" name="userGender" value="남">남</td>
					<%
						}else{
					%>
					<td><input type="radio" name="userGender" value="여성">여성<input type="radio" name="userGender" value="남" checked>남</td>
					<%
						}
					%>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="userEmail" maxlength="50" value=<%=user.getUserEmail() %>></td>
				</tr> 
			</tbody>
			<tfoot>
				<th colspan="2" style="text-align:right"><input type="button" onclick="modConfirm()" value="정보수정"></th>
			</tfoot>
		</table>
			<input type="button" onclick="location.href='main.jsp'" value="이전">
	</form>
</body>
</html>