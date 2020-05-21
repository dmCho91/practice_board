<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:set var="msg" value="${requestScope.msg }"/>
	<c:choose>
		<c:when test="${msg eq 'DATABASE_ERROR'}">
			<script>
				alert('데이터베이스 오류가 발생하였씁니다.');
				location.href("history.go()");
			</script>
		</c:when>
		<c:when test="${msg eq 'ACCESS_DENIED' }">
			<script>
				alert('잘못된 접근입니다.');
				location.href("board.do");
			</script>
		</c:when>
		<c:when test="${msg eq 'NO_DATA' }">
			<script>
				alert('해당 게시물이 없습니다.');
				location.href("board.do");
			</script>
		</c:when>
	</c:choose>

</body>
</html>