<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="simple_bbs.dto.BbsDTO" %>
<%@ page import="simple_bbs.dao.BbsDAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a:link {text-decoration:none; color:#000000}
	a:visited {color:#000000}
	a:hover{text-decoration:underline; color:#6E6E6E}
</style>
</head>
<body>
	<%
		BbsDAO dao = new BbsDAO();

		//페이징처리...
		int pageSize= 10; // 화면에 출력할 게시물 수
		int pageBlock = 5; // 5개 -> 1 2 3 4 5 이런 식으로 출력
		int cPage = request.getParameter("page")!=null ? Integer.parseInt(request.getParameter("page")): 1;
		int total = dao.getTotal(); // 게시판의 모든 게시물 수
		
		
		int endNo = pageSize * cPage; // 출력되는 페이지의 마지막 게시물 번호
		int startNo = endNo - pageSize; //출력되는 페이지의 시작 게시물 번호
		int totalPage; // 출력해야하는 총 페이지 수
		totalPage = ((total-1)/pageSize)+1;// ((총게시물 -1)/출력게시물수)+1
		
		int prevBlock = (int)Math.floor((cPage-1)/pageBlock) * pageBlock;
		int nextBlock = prevBlock + pageBlock + 1;
	
		ArrayList<BbsDTO> dtos = dao.bbsLists(startNo, endNo);
	%>
	<h1>SIMPLE BBS</h1>
	<table style="text-align: center; border:1px solid #dddddd">
		<thead>
			<tr>
				<th style="background-color:#eeeeee; text-align:center;">번호</th>
				<th style="background-color:#eeeeee; text-align:center;">제목</th>
				<th style="background-color:#eeeeee; text-align:center;">작성자</th>
				<th style="background-color:#eeeeee; text-align:center;">작성일</th>
				<th style="background-color:#eeeeee; text-align:center;">조회수</th>
			</tr>
		</thead>
		<tbody>
			<%
				for(int i=0; i<dtos.size(); i++){
			%>
			<tr>
				<td><%= dtos.get(i).getbId() %></td>
				<td><a href="view.jsp?bId=<%=dtos.get(i).getbId() %>"><%= dtos.get(i).getbTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">",	"&gt;").replaceAll("\n", "<br>") %></a></td>
				<td><%= dtos.get(i).getUserId() %></td>
				<td><%= dtos.get(i).getbDate() %></td>
				<td><%= dtos.get(i).getbHit() %></td>
			</tr>
			<%
				}
			%>
	</table>
	<br>
	<table>
		<%
			int prevPage = (int)Math.floor((cPage-1)/pageBlock)*pageBlock;
			int nextPage = prevPage + pageBlock + 1;
		%>
		<tr>
			<td>
				<%
					if(prevPage>0){
				%>
				<a href="board.jsp?page=<%=prevPage %>">이전</a>
				<%
					}
				%>
				<%
					for(int i= prevPage+1; i<nextPage && i<=totalPage; i++){
						if(i == cPage){
				%>
					<b>[<%=i %>]</b>
				<%
						}else{
				%>
					[<a href="board.jsp?page=<%=i %>"><%=i %></a>]
				<%
						}
					}
					if(totalPage>=nextPage){
				%>
					<a href="board.jsp?page=<%=nextPage %>">다음</a>
				<%
					}
				%>
			</td>
		</tr>
	</table>
	<a href="main.jsp">메인</a>&nbsp;&nbsp;
	<input type="button" onclick="location.href='write.jsp'" value="글쓰기">
</body>
</html>