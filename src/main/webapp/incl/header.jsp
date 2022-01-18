<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header">
	<h1 id="logo"><a href="${pageContext.request.contextPath }/index/index.jsp">PJC</a></h1>
	<nav id="logSnb">
		<ul>
			<c:choose>
				<c:when test="${empty user.userId}">
					<li>
					<!--  
						<form action="${pageContext.request.contextPath }/incl/menu.do"method="post">
							<input type="submit" name="menu" value="로그인"/>
						</form>-->
						<a href="${pageContext.request.contextPath }/customer/login/login.jsp" id="loginBtn">로그인</a> 
					
						
					</li>
				</c:when>
				<c:otherwise>
					<li id="userId"><b>${user.userId }</b></li>
					<li>
						<form action="${pageContext.request.contextPath }/customer/login/logout.do" method="post" id="logoutForm">
							<input type="submit" value="로그아웃" name="logout" id="logoutBtn"/>
						</form>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</header>