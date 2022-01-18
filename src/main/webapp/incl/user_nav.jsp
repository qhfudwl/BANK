<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav id="userSnb">
<%
	
%>

	<ul>
		<li><form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="통장개설" name="menu" />
			</form></li>
		<li>
			<%--<form action="${pageContext.request.contextPath}/account/inquiry_account/inquiryaccount.do" method="post"> --%>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="전체계좌조회" name="menu" />
			</form>
		</li>
		<%--
		<li>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="입금" name="menu" />
			</form>
		</li>
		<li>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="출금" name="menu" />
			</form>
		</li>
		<li>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="거래내역" name="menu" />
			</form>
		</li> --%>
		<li>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<input type="submit" value="마이페이지" name="menu" />
			</form>
		</li>
	</ul>
</nav>