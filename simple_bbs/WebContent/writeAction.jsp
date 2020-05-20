<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@page import="simple_bbs.dao.BbsDAO"%>
<% request.setCharacterEncoding("UTF-8"); %>

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
		
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		if(loginUser == null || bTitle == null || bContent == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다.')");
			script.println("location.href='board.jsp'");
			script.println("</script>");
		}
		
		//데이터베이스 연결 -> 글쓰기 로직
		
		BbsDAO dao = new BbsDAO();
		int result = dao.write(loginUser, bTitle, bContent);
		
		if(result == BbsDAO.DATABASE_ERROR){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}else{
			//session.setAttribute("loginUser", loginUser);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href='writeComplete.jsp'");
			script.println("</script>");
		}
		
	%>

</body>
</html>