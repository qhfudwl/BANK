<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이체</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/trans_account.css"/>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
<jsp:include page="/incl/header.jsp" ></jsp:include>
<%@include file="/incl/user_nav.jsp" %>
<main>
<section id="transContent">
<h2>이체</h2>
	<div id="transAccWrap">
		<form id="transAccForm" action="${pageContext.request.contextPath}/account/trans_account/trans_account.do" method="post">
			<div id="transAccInfo">
				<label>
					<c:choose>
						<c:when test="${account.accType == 'S'.charAt(0)}">
							PJC 보통계좌
						</c:when>
						<c:otherwise>
							PJC 당좌계좌
						</c:otherwise>
					</c:choose>
				</label>
				<input type="text" value="${account.accNumber}" name="accNumber" readonly />
				<c:choose>
					<c:when test="${account.accType == 'S'.charAt(0)}">
						<p>출금가능금액 <fmt:formatNumber value="${account.balance}" pattern=",###" type="currency" currencySymbol="" /> 원</p>
					</c:when>
					<c:otherwise>
						<p>출금가능금액 <fmt:formatNumber value="${account.balance}" pattern=",###" type="currency" currencySymbol="" /> 원  
						/ 한도액 <fmt:formatNumber value="${account.overdraftAmount}" pattern=",###" type="currency" currencySymbol="" /> 원</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div id="contentInfoWrap">
				<table>
					<tr>
						<td>
							<p class="comment">계좌번호</p>
							<c:choose>
								<c:when test="${not empty transAccount}">
									<input type="text" name="transAccNumber" value="${transAccount.accNumber}" />
								</c:when>
								<c:otherwise>
									<input type="text" name="transAccNumber" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<input type="submit" name="check" value="확인" />
						</td>
					</tr>
					<tr>
						<td><p class="comment">받는 분</p>
						<c:if test="${fn:contains(check, '확인') || fn:contains(check, '이체하기')}">
							<c:choose>
								<c:when test="${not empty transCustomer}">
									${transCustomer.userName} 님
								</c:when>
								<c:otherwise>
									<span class="empty">해당 계좌는 없습니다.</span>
								</c:otherwise>
							</c:choose>
						</c:if>
						</td>
					</tr>
					<tr>
						<td id="transAmount">
							<p class="comment">금액</p><input type="text" name="amount" /> 원
							<c:if test="${not empty errMsg }">
								<p class="errMsg">${errMsg }</p>
							</c:if>
						</td>
					</tr>
					<tr>
						<td>
							<p class="comment">받는 분 통장 표시</p><input type="text" name="transContent" value="${customer.userName} 계좌에서 이체" readonly />
							<input type="text" class="hidden" name="transType" value="D" />
						</td>
					</tr>
					<tr>
						<td>
							<p class="comment">내 통장 표시</p><input type="text" name="myContent" value="${transCustomer.userName} 계좌로 송금" readonly />
							<input type="text" class="hidden" name="myType" value="W" />
						</td>
						<td>
							<input type="submit" name="check" value="이체하기" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</section>
</main>
<%@include file="/incl/footer.jsp" %>
</body>
</html>
