<%@page import="simple_bbs.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="simple_bbs.dto.UserDTO" %>
<%@ page import="simple_bbs.dto.UserDTO" %>
<%@ page import="java.io.PrintWriter" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="user" class="simple_bbs.dto.UserDTO"/>
<jsp:setProperty property="*" name="user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//session체크
		String loginUser = null;
		if(session.getAttribute("loginUser")!=null){
			loginUser = (String)session.getAttribute("loginUser");
		}
		if(loginUser != null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 로그인이 되었습니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
		
		//데이터베이스 연결 -> 로그인 로직 수행
		UserDAO dao = new UserDAO();
		
		int result = dao.login(user.getUserId(), user.getUserPw());
		if(result == UserDAO.LOGIN_NO_EXIST_ID){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('아이디가 존재하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == UserDAO.LOGIN_PASSWORD_NOT_MATCH){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('패스워드가 일치하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else if(result == UserDAO.LOGIN_DATABASE_ERROR){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			session.setAttribute("loginUser", user.getUserId());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
	%>

</body>
</html>