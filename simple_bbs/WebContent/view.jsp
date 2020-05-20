<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="simple_bbs.dao.BbsDAO" %>
<%@ page import="simple_bbs.dto.BbsDTO" %>
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
		
		String bId = request.getParameter("bId");

		BbsDAO dao = new BbsDAO();
		BbsDTO dto = null;
		
		String writeComplete = request.getParameter("writeComplete");
		
		if(writeComplete!=null && writeComplete.equals("true")){
			dto = dao.writeComplete(loginUser);	
		}else {
			dto = dao.view(bId, 1);
		}
	
	%>

		<table style="width: 400px;  border:1px solid #dddddd">
			<thead>
				<th colspan="3" style="background-color: #eeeeee; text-align:left;"> <%=dto.getbTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">",	"&gt;").replaceAll("\n", "<br>") %></th>
			</thead>
			<tbody>
				<tr>
					<td>작성자 <%=dto.getUserId() %></td>
					<td>조회수 <%=dto.getbHit() %></td>
					<td>작성일 <%=dto.getbDate() %></td>
				</tr>
				<tr >
					<td>내용</td>
					<td colspan="2" style="min-height:200px; text-align:left;"><%=dto.getbContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">",	"&gt;").replaceAll("\n", "<br>") %></td>
				</tr>
			</tbody>
			<tfoot style="text-align:right;">
				<%
					if(loginUser != null && loginUser.equals(dto.getUserId())){
				%>
					<th><a href="bbsModify.jsp?bId=<%=dto.getbId() %>">수정</a>
					<th><a onclick="return confirm('정말 삭제하시겠습니까?')" href="bbsDelete.jsp?bId=<%=dto.getbId() %>&userId=<%=dto.getUserId() %>">삭제</a>
				<%
					} 
				%>
			</tfoot>
		</table>
		<input type="button" onclick="location.href='board.jsp'" value="목록으로">
</body>
</html>