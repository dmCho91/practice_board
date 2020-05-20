<%@page import="simple_bbs.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="simple_bbs.dao.UserDAO" %>
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
		
		//데이터베이스 연결 -> 회원가입 로직 수행
		UserDAO dao = new UserDAO();
		
		int result = dao.join(user);
		if(result == UserDAO.JOIN_DATABASE_ERROR){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			session.setAttribute("loginUser", user.getUserId());
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('회원가입을 축하합니다.')");
			script.println("location.href='main.jsp'");
			script.println("</script>");
		}
	%>

</body>
</html>