<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이체</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/trans_check.css"/>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
<jsp:include page="/incl/header.jsp" ></jsp:include>
<%@include file="/incl/user_nav.jsp" %>
<main>
<section id="transCheck">
<h2>이체</h2>
	<div id="transCheckWrap">
		<p>계좌번호 ${transAccount.accNumber}</p>
		<p>
			<em>${transCustomer.userName} 님</em>께<br>
			<em>${amount} 원</em> 이체했습니다.
		</p>
		<div id="transCheckTableWrap">
			<table>
				<tr>
					<th>출금계좌</th>
					<td>
						<c:choose>
							<c:when test="${account.accType == 'S'.charAt(0)}">
								PJC 보통계좌
							</c:when>
							<c:otherwise>
								PJC 당좌계좌
							</c:otherwise>
						</c:choose>
						 ${account.accNumber }
					 </td>
				</tr>
				<tr>
					<th>받는 분 통장 표시</th>
					<td>${transContent}</td>
				</tr>
				<tr>
					<th>내 통장 표시</th>
					<td>${myContent}</td>
				</tr>
			</table>
		</div>
		<form action="${pageContext.request.contextPath}/account/record_account/record_account.do" method="post" name="goDetail" class="goDetail">
			<button type="submit" class="goDetailBtn" value="${account.accNumber}" name="accNum" >확인</button>
		</form>
		<%--<a href="${pageContext.request.contextPath}/account/record_account/record_account.jsp">확인</a> --%>
	</div>
</section>
</main>
<%@include file="/incl/footer.jsp" %>
</body>
</html>