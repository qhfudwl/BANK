<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/withdraw.css"/>
<%@ include file="/incl/stylesheet_link.jsp" %>

</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include> 
	<%@ include file="/incl/user_nav.jsp" %>
	<main>
		<section id="withdrawContent">
		<h2>출금</h2>
			<div id="withdrawWrap">
				<form action="${pageContext.request.contextPath}/account/withdraw/withdraw.do" id="withdrawForm" method="post">
					<table id="withdrawTable">
						<tr>
							<th>계좌</th>
							<td class="accountArea">
							<c:choose>
								<c:when test= '${account.accType == "S".charAt(0)}'>
									PJC 보통계좌  
								</c:when>
								<c:otherwise>
									PJC 당좌계좌 
								</c:otherwise>				
							</c:choose>
								<input type="text" value="${account.accNumber}" name="accNumber">
							</td>
						</tr>
						<tr>
							<th>거래전 잔액</th>
							<td><fmt:formatNumber type="currency" value="${account.balance}"/>원 </td>				
						</tr>
						<c:if test="${account.accType=='C'.charAt(0)}">
							<tr>
								<th>보유 한도액</th>
								<td><fmt:formatNumber type="currency" value="${account.overdraftAmount}"/>원</td>
							</tr>
						</c:if>
						<tr class="inputRow">					
							<th>출금액</th>
							<td class="inputArea">
								<c:if test="${not empty errMsg }">
									<p class="errMsg">${errMsg}</p>
								</c:if>
								<input type="text" name="amount" id="wAmount" placeholder="금액을 입력하세요">
							</td>
						</tr>
						<tr>
							<th></th>
							<td class="submitArea">
								<button type="submit" value="W" name="transType" id="withdrawButton">출금하기</button>
							</td>
						</tr>
					</table>
				</form>
				<!-- 
				<c:forEach var="errorMsg" items="${errorMsgs}">
					<p>${errorMsg}</p>
				</c:forEach>
				 -->
			</div>
		</section>
	</main>
	<%@ include file="/incl/footer.jsp" %>
</body>
</html>