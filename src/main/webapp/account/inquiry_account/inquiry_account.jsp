<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/account/css/inquiry_account.css" />
<%@ include file="/incl/stylesheet_link.jsp"%>
<script src="${pageContext.request.contextPath}/incl/js/jquery-3.6.0.min.js"></script>
<script src="../js/inquiry_account.js" defer></script>
<title>계좌 조회</title>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
	<main>
		<section id="inquiryAccContent">
			<h2>계좌 조회</h2>
			<div id="inquiryAccWrap">
				
					<!-- 모든계좌 총자산 -->
					<div class="totalAccBalanceWrap">
						<p class="totalABTitle">총 자산</p>
						<c:choose>
							<c:when test="${ not empty totalAcctBalance}">
								<p class="totalABText">
									<fmt:formatNumber value="${totalAcctBalance }"
										pattern=",###" type="currency" />
									<span class="wonFsize">원</span>
								</p>
							</c:when>
							<c:otherwise>
								<p class="totalABText">
									0<span class="wonFsize">원</span>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
					<!-- 예금/적금-->
					<div class="generalAcc">
						<!-- 예금/적금 총자산 -->
						<div class="generalAccTotalBalanceWrap">
							<p class="generalABTitle">예금/적금</p>
							<c:choose>
								<c:when test="${ not empty totalGeneralAcctBalance}">
									<p class="generalABText">
										<fmt:formatNumber value="${totalGeneralAcctBalance }"
											pattern=",###" type="currency"/>
										<span class="wonFsize">원</span>
									</p>
								</c:when>
								<c:otherwise>
									<p class="generalABText">
										0<span class="wonFsize">원</span>
									</p>
								</c:otherwise>
							</c:choose>

						</div>
						<!-- 계좌리스트 -->
						
						<div class="generalAccList">
							<ul>
								<!-- 누르면 계좌내역조회 페이지(후에 추가)-->
								<c:forEach var="account" items="${userInquiryAcct.accounts }">
								<form action="${pageContext.request.contextPath}/account/record_account/record_account.do" method="post" name="goDetail" class="goDetail">
									<li>
									
											<div class="generalAccNameWrap">
												<!--  계좌종류  -->
												<c:if test="${not empty account.accType }">
													<c:choose>						
														<c:when test="${account.accType == 'S'.charAt(0)}">
															<p class="generalAccName">PJC보통계좌</p>
														</c:when>
														<c:when test="${account.accType == 'C'.charAt(0)}">
															<p class="generalAccName">PJC당좌계좌</p>
														</c:when>
														<c:otherwise>
															<p class="generalAccName">PJC계좌</p>
														</c:otherwise>
													</c:choose>
												</c:if>
												<c:if test="${empty account.accType }">
													<p class="generalAccName">PJC계좌</p>
												</c:if>
												<!--  계좌번호  -->
												<c:if test="${not empty account.accNumber }">
													<p class="generalAccNumber">${account.accNumber}</p>
													<input type="hidden" name="accNum" value="${account.accNumber}">
												</c:if>
												<c:if test="${empty account.accNumber }">
													<p class="generalAccNumber">0</p>
												</c:if>

											</div>
											<div class="generalAccBalanceWrap">
												<!-- 계좌없으면 상세조회 못누름 -->
												<c:if test="${empty account.accNumber }">
												  	<button type="submit" class="goDetailBtn" disabled >상세조회</button>
												</c:if>	
												<c:if test="${not empty account.accNumber }">
													<button type="submit" class="goDetailBtn" >상세조회</button>
												</c:if>
												<c:choose>
													<c:when test="${ not empty account.balance}">
														<p class="generalAccBalance">
															<fmt:formatNumber value="${account.balance }"
																pattern=",###" type="currency" />
															<span class="wonFsize">원</span>
														</p>
													</c:when>
													<c:otherwise>
														<p class="generalAccBalance">
															0<span class="wonFsize">원</span>
														</p>
													</c:otherwise>
												</c:choose>
											
											</div>
										
									</li>
								</form>
								</c:forEach>
							</ul>
						</div>
						

					</div>
					<!-- 보험/공제(후에 추가)-->

			
			</div>

		</section>
	</main>

	<%@include file="/incl/footer.jsp"%>

</body>
</html>