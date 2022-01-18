
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/incl/stylesheet_link.jsp"%>

</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
	<main>
	<div id="errorWrap">
		<h1>Error</h1>
		<p>
			<c:if test="${not empty errMsgs }">
				<c:forEach var="msgs" items="${errMsgs}">
					<b>${msgs}
					<c:if test="${(times = times - 1) > 0 }"> / </c:if></b>
				</c:forEach>
			</c:if>
		</p>
		<p>${addMsg }</p>
	</div>
	</main>
	<%@include file="/incl/footer.jsp"%>
</body>
</html>