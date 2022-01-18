<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/customer/css/login.css" />
<%@ include file="/incl/stylesheet_link.jsp"%>
<title>Login</title>
<script type="text/javascript">
	// 회원가입 화면으로 이동
	function goJoinForm() {
		location.href = "../join/customerInput.jsp";
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/incl/header.jsp"></jsp:include>
		<%@include file="/incl/user_nav.jsp"%>
	</header>
	<main>
		<section id="mainWrap">
			<h2>Login</h2>
			<div id="loginWrap">
				<form action="<%=request.getContextPath()%>/customer/login/login.do" method="post">
					<table id="loginTable">
						<tr>
							<th>아이디</th>
							<td>
								<c:choose>
									<c:when test="${not empty cookie.saveId.value}">
										<input type="text" name="userId" value="${cookie.saveId.value}">
									</c:when>
									<c:otherwise>
										<input type="text" name="userId" placeholder="아이디를 입력 하세요." />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th>비밀번호</th>
							<td><input type="password" name="passwd" id="passwd" placeholder="비밀번호를 입력 하세요." /></td>
						</tr>
						<tr>
						<td></td>
							<td id="idRemember">
								<c:if test="${not empty cookie.saveId.value}">
									<input type="checkbox" name="loginCheckbox" checked="checked">아이디 기억하기
								</c:if>
								<c:if test="${empty cookie.saveId.value}">
									<input type="checkbox" name="loginCheckbox">아이디 기억하기
								</c:if>
							</td>
						</tr>
						<tr>
							<td></td>
							<td id="loginChk">
							<input id="button" type="button" value="회원가입" onclick="goJoinForm()" />
							<input id="submit" type="submit" value="로그인" />
							</td> 
						</tr>										
					</table>
				</form>
			</div>
		</section>
	</main>
	<%@include file="/incl/footer.jsp"%>
</body>
</html>