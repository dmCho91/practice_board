<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "simple_bbs.dao.BbsDAO" %>
<%@ page import = "java.io.PrintWriter" %>
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
		
		String bId = request.getParameter("bId");
		String userId = request.getParameter("userId");
		
		if(loginUser == null || bId == null || userId==null || !loginUser.equals(userId)){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('잘못된 접근입니다.')");
			script.println("location.href='board.jsp'");
			script.println("</script>");
		}else{
		
			BbsDAO dao = new BbsDAO();
			int result = dao.bbsDelete(bId);
			if(result == 1){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('글이 삭제 되었습니다.')");
				script.println("location.href='board.jsp'");
				script.println("</script>");
			}else{
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('데이터베이스 오류가 발생하였습니다.')");
				script.println("history.go()'");
				script.println("</script>");
			}
		}
	%>
</body>
</html>