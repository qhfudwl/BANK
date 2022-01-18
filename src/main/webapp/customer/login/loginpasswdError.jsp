<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/incl/stylesheet_link.jsp"%>
<title>Login error</title>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
	<main>
		<script>
		
		alert('로그인 실패\n비밀번호가 일치하지 않습니다.');
		location.href="login.jsp";
		</script>

	</main>
	<%@include file="/incl/footer.jsp"%>
</body>
</html>