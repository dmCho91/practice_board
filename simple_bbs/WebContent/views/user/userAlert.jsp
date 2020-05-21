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
<!-- 
	public static final int JOIN_DATABASE_ERROR = -1;
	public static final int LOGIN_PASSWORD_NOT_MATCH = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_NO_EXIST_ID = -1;
	public static final int LOGIN_DATABASE_ERROR = -2;
	public static final int MODIFY_DATABASE_ERROR = -1;
 -->

	<c:set var="msg" value="${requestScope.msg }"/>
	<c:choose>
		<c:when test="${msg eq 'ALREADY_LOGINED' }">
			<script>
				alert('이미 로그인되었습니다.');
				location.href="main.do";
			</script>
		</c:when>
		<c:when test="${msg eq 'JOIN_EXIST_ID' }">
			<script>
				alert('아이디가 존재합니다.');
				history.back();
			</script>
		</c:when>
		<c:when test="${msg eq 'NO_EXIST_ID' }">
			<script>
				alert('아이디가 존재하지 않습니다.');
				history.back();
			</script>
		</c:when>
		<c:when test="${msg eq 'NOT_MATCH_PASSWORD' }">
			<script>
				alert('비밀번호가 틀립니다.');
				history.back();
			</script>
		</c:when>
		<c:when test="${msg eq 'LOGIN_DATABASE_ERROR' }">
			<script>
				alert('데이터베이스 오류가 발생하였습니다.');
				history.back();
			</script>
		</c:when>
		<c:when test="${msg eq 'ACCESS_DENIED' }">
			<script>
				alert('잘못된 접근입니다.');
				location.href='main.do';
			</script>
		</c:when>
		
	</c:choose>

</body>
</html>