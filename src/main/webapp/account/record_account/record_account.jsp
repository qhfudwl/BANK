<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>거래 내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/record_account.css"/>

<script src="${pageContext.request.contextPath}/incl/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/account/js/record_account.js" defer></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<style>.btn{ margin-right:10px;}</style>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
<main>
<section id="recordContent">
<h2>거래 내역</h2>
	<div id="recordAcc">
		<dl class="accInfo">
			<dt>
				<c:choose>
					<c:when test="${acc.accType == 'S'.charAt(0)}">
						PJC 보통계좌
					</c:when>
					<c:otherwise>
						PJC 당좌계좌
					</c:otherwise>
				</c:choose>
			</dt>
			<dd class="accNum">${acc.accNumber}</dd>
			<dd class="balance allBalance"><fmt:formatNumber value="${acc.balance}" pattern=",###" type="currency" currencySymbol="" /> <span>원</span></dd>
		</dl>
		<div class="formWrap">
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<button type="submit" name="accNumber" value="${acc.accNumber}">입금</button>
				<input type="text" name="menu" value="입금" class="hidden">
				
			</form>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<button type="submit" name="accNumber" value="${acc.accNumber}">출금</button>
				<input type="text" name="menu" value="출금" class="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/incl/menu.do" method="post">
				<button type="submit" name="accNumber" value="${acc.accNumber}">이체</button>
				<input type="text" name="menu" value="이체" class="hidden">
			</form>
			<form action="${pageContext.request.contextPath}/account/record_account/remove_account.do" method="post" name="accDelPopup" enctype="UTF-8">
				<input type="hidden" name="accNumber" value="${acc.accNumber}"/>
				<button type="button" name="accNumber" value="${acc.accNumber}" class="deleteAcc" onclick="acctDelfunc()" >계좌삭제</button>
			</form>
		</div>
	</div>
	<div id="recordWrap">
		<table id="recordTable">
			<caption class="hidden">거래 내역</caption>
			<c:if test="${not empty recordList}">
				<c:forEach var="record" items="${recordList}">
					<tr>
						<td class="content">
							<time datetime="${record.regDate}">${record.regDate}</time>
							<p>${record.content}</p>
						</td>
						<td class="amount">
							<c:choose>
								<c:when test="${fn:contains(record.transType, 'D')}">
									<p>입금 <em class="red">
									<fmt:formatNumber value="${record.amount}" pattern=",###" type="currency" currencySymbol="" />
									</em> 원</p>
									<p class="balance">입금 후 잔액 
									<fmt:formatNumber value="${record.balance}" pattern=",###" type="currency" currencySymbol="" />
									 원</p>
								</c:when>
								<c:otherwise>
									<p>출금 <em class="blue">
									<fmt:formatNumber value="${record.amount}" pattern=",###" type="currency" currencySymbol="" />
									</em> 원</p>
									<p class="balance">출금 후 잔액 
									<fmt:formatNumber value="${record.balance}" pattern=",###" type="currency" currencySymbol="" />
									 원</p>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</section>
</main>
<%@include file="/incl/footer.jsp" %>
</body>
</html>
