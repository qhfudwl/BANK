<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통장 개설</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/add_account.css"/>
<script src="${pageContext.request.contextPath}/incl/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/account/js/add_account.js" defer></script>
<%@ include file="/incl/stylesheet_link.jsp" %>
</head>
<body>
<jsp:include page="/incl/header.jsp" ></jsp:include>
<%@include file="/incl/user_nav.jsp" %>
<main>
<section id="addAccContent">
<h2>통장 개설</h2>
	<div id="addAccWrap">
		<form id="addAccForm" action="${pageContext.request.contextPath}/account/add_account/add_account.do" method="post">
			<table id="addAccTable">
				<caption class="hidden">통장 개설</caption>
				<tr>
					<th>고객 ID</th>
					<td><em>${user.userId}</em></td>
				</tr>
				<tr>
					<th>종류 선택</th>
					<td class="radioArea">
						<c:if test="${not empty errMsgs.accTypeErr}">
							<p class="addAccErr">${errMsgs.accTypeErr}</p>
						</c:if>
						<input type="radio" value="SA" name="accType" id="saving" checked />
						<label for="saving">보통계좌</label>
						<input type="radio" value="CA" name="accType" id="checking"/>
						<label for="checking">당좌계좌</label>
					</td>
				</tr>
				<tr>
					<th>입금액</th>
					<td class="textArea">
						<c:if test="${not empty errMsgs.balanceErr}">
							<p class="addAccErr">${errMsgs.balanceErr}</p>
						</c:if>
						<input type="text" name="balance" id="balance" placeholder="최소 1,000원 이상 넣어주세요." /> 원
						<label class="hidden" for="balance">입금액</label>
					</td>
				</tr>
				<tr>
					<th>이자율</th>
					<td class="textArea">
						<input type="text" name="interestRate" id="interest_rate" readonly /> %
						<label class="hidden" for="interest_rate">이자율</label>
					</td>
				</tr>
				<tr>
					<th>한도액</th>
					<td class="textArea">
						<c:if test="${not empty errMsgs.overErr}">
							<p class="addAccErr">${errMsgs.overErr}</p>
						</c:if>
						<input type="text" name="overDraft" id="over_draft" placeholder="최대 ${maxOverDraft = '10,000,000'}원입니다." /> 원
						<label class="hidden" for="over_draft">한도액</label>
					</td>
				</tr>
				<tr>
					<th></th>
					<td class="submitArea">
						<input type="submit" value="개설" id="addAccBtn"/>
						<label class="hidden" for="addAccBtn">개설하기</label>
					</td>
				</tr>
			</table>
		</form>
	</div>
</section>
</main>
<%@include file="/incl/footer.jsp" %>
</body>
</html>