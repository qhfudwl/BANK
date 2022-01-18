<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/incl/stylesheet_link.jsp"%>
<title>Login success</title>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
	<main>
		성공 ${user.userName}님
		<form action="logout.do" method="post">
			<input type="submit" value="로그아웃" />
		</form>
	</main>
	<%@include file="/incl/footer.jsp"%>
</body>
</html>