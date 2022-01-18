<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입완료</title>
<link rel="stylesheet" href="../css/add_customer_output.css"/>
<%@ include file="/incl/stylesheet_link.jsp"%>
<script type="text/javascript">
        
        // 취소 버튼 클릭시 로그인 화면으로 이동
        function goLoginForm() {
            location.href="../login/login.jsp";
        }
</script>
</head>
<body>
	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
	<main>
		<section id="addCustomerOutput">
			<h2>가입 완료</h2>
			<div id="addCustomerOutputWrap">
				<h3>${customer.userName}  님! 회원가입이 완료됐어요!</h3>
				<table id="addCustomerOutputTable">
					<tr>
						<th>고객 ID</th>
						<td><em>${customer.userId}</em></td>
					</tr>
					<tr>
						<th>고객 이름</th>
						<td><em>${customer.userName}</em></td>
					</tr>
					<tr>
						<th>고객 EMail</th>
						<td><em>${customer.email}</em></td>
					</tr>
					<tr>
						<th> 고객 전화번호</th>
						<td><em>${customer.phone}</em></td>
					</tr>
				</table>
			</div>
		</section>
	</main>

	<%@include file="/incl/footer.jsp"%>

</body>
</html>