<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입금</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/deposit.css"/>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include> 
	<%@ include file="/incl/user_nav.jsp" %>
	<main>
		<section id="depositContent">
			<h2>입금</h2>
			<div id="depositWrap">
				<form action="${pageContext.request.contextPath}/account/deposit/deposit.do" id="depositForm" method="post">
					<table id="depositTable">
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
						<tr class="inputRow">
							<th>입금액</th>
							<td class="inputArea">
								<c:if test="${not empty errMsg}">
									<p class="errMsg">${errMsg}</p>
								</c:if>
								<input type="text" name="amount" id="dAmount" placeholder="금액을 입력하세요">
							</td>
						</tr>
						<tr>
							<th></th>
							<td class="submitArea">
								<button type="submit" value="D" name="transType" id="depositButton">입금하기</button> 
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