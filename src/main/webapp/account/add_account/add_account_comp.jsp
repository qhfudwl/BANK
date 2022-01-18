<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통장 개설 완료</title>
<link rel="stylesheet" href="../css/add_account_comp.css"/>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
<jsp:include page="/incl/header.jsp" ></jsp:include>
<%@include file="/incl/user_nav.jsp" %>
<main>
<section id="addAccCompContent">
<h2>통장 개설 완료</h2>
	<div id="addAccCompWrap">
		<table id="addAccCompTable">
			<caption class="hidden">통장 정보</caption>
			<tr>
				<th>고객 이름</th>
				<td><em>${user.userName}</em></td>
			</tr>
			<tr>
				<th>통장 종류</th>
				<td><em>
					<c:choose>
						<c:when test="${account.accType == 'S'.charAt(0)}">
							PJC 보통계좌
						</c:when>
						<c:otherwise>
							PJC 당좌계좌
						</c:otherwise>
					</c:choose>
				</em></td>
			</tr>
			<tr>
				<th>입금액</th>
				<td><em><fmt:formatNumber value="${account.balance}" pattern=",###" type="currency" currencySymbol="" /></em> 원</td>
			</tr>
			<tr>
				<th>계좌 번호</th>
				<td><em>${account.accNumber}</em></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${account.accType == 'S'.charAt(0)}">
						<th>이자율</th>
						<td><em>${account.interestRate}</em> %</td>
					</c:when>
					<c:otherwise>
						<th>한도액</th>
						<td><em><fmt:formatNumber value="${account.overdraftAmount}" pattern=",###" type="currency" currencySymbol="" /></em> 원</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>등록 날짜/시간</th>
				<td><em>${regDate}</em></td>
			</tr>
		</table>
	</div>
</section>
</main>
<%@include file="/incl/footer.jsp" %>
</body>
</html>