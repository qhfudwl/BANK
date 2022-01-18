<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/incl/stylesheet_link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/afterDeposit.css"/>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include> 
	<%@ include file="/incl/user_nav.jsp" %>
	<main>
		<section id="afterDepositContent">
			<h2>입금 완료</h2>
			<div id="afterDepositWrap">
				<table id="afterDepositTable">
					<tr>
						<th>거래 계좌</th>
						<td> 		
							<c:choose>
								<c:when test= '${account.accType == "S".charAt(0)}'>
									PJC 보통계좌  
								</c:when>
								<c:otherwise>
									PJC 당좌계좌 						
								</c:otherwise>				
							</c:choose>
							${account.accNumber}
						</td>
					</tr>
					<tr>
						<th>입금액</th>
						<td>
							<fmt:formatNumber type="currency" value="${amount}"/>원 
						</td>
					</tr>
					<tr>
						<th>거래 후 잔액</th>
						<td>
							<fmt:formatNumber type="currency" value="${account.balance}"/>원
						</td>				
					</tr>
					
					<tr class="okArea">
						<th></th>
						<td>
						<form action="${pageContext.request.contextPath}/account/inquiry_account/inquiryaccount.do" method="post" name="goDetail" class="goDetail">
							<button type="submit" name="accNum"  id="okButton" >확인</button>
						</form>	
						<!-- <a href="${pageContext.request.contextPath}/account/inquiry_account/inquiryaccount.do" id="okButton">확인</a> -->				
						</td>
					</tr>			
				</table>
			</div>
		</section>
	</main>
	
	
	<%@ include file="/incl/footer.jsp" %>
</body>
</html>