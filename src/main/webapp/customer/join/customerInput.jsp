<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/customer/css/customerInput.css" />
<%@ include file="/incl/stylesheet_link.jsp"%>
<script src="${pageContext.request.contextPath}/incl/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/customer/js/customerInput.js" defer></script>
<title>회원가입</title>
</head>
<body>
	<header>
		<jsp:include page="/incl/header.jsp"></jsp:include>
		<%@include file="/incl/user_nav.jsp"%>
	</header>

	<main>
	
		<section id="addCustomerwrap">
			<div id="h2Wrap">
				<h2>회원 가입</h2>
				<span id="capSub">이미 가입하셨나요? <input type="button" value="로그인" id="gologin" onclick="goLoginForm()"></span>
			</div>
		<div id="addCustomerFormWrap">
		<form action="customer_input.do" method="post" name="CustomerInfo" onsubmit="return checkValue()">
			<table id="addCustomerTable">
				<tr>
					<th id="userIdWrap" class="title">아이디</th>
					<td>
						<input type = "text" name = "userId" id="userId" maxlength="30" placeholder="아이디를 입력 하세요">
						 <button type="button" class="idcheckBtn" onclick="idCheckFunc()">중복확인</button>
						<input type="hidden" name="idcheck2" id="idcheck2" value="idUncheck" >
					</td>
				</tr>
				<tr id="pass2dWrap1">
					<th class="title">패스워드</th>
					<td>
						<input type = "password" name = "passwd" id="passwd" maxlength="20" placeholder="비밀번호는 영문,숫자 조합이필요합니다."/>
						<div id="passwdShow">SHOW</div>
					</td>
				</tr>
				<tr id="passwdWrap2">
					<th class="title">패스워드 확인</th>
					<td>
						<input type = "password" name = "passwdcheck" id="passwdcheck" maxlength="20" placeholder="비밀번호를 한번 더 입력 하세요"/>
						<div id="passwdShow2">SHOW</div>
					</td>
				</tr>
				<tr>
					<th class="title">이름</th>
					<td>
						<input type = "text" name = "userName" id="userName"maxlength="10" placeholder="이름을 입력 하세요"/>
					</td>
				</tr>
				<tr>
					<th class="title">주민번호</th>
					<td id="ssn">
						<input type = "text" name = "ssn1" id="ssn1" minlength="6" maxlength="6" placeholder="주민번호 앞 6자리"/> - 
						<input type = "text" name = "ssn2" id="ssn2" minlength="7" maxlength="7" placeholder="주민번호 뒤 7자리"/> 
					</td>
				</tr>
				<tr>
					<th class="title">휴대전화</th>
					<td id="phone">
						<input type = "text" name = "phone1" id="phone1" minlength="3" maxlength="3" placeholder="앞 3 자리"/> -
						<input type = "text" name = "phone2" id="phone2" minlength="3" maxlength="4" placeholder="중간 3,4 자리"/> -
						<input type = "text" name = "phone3" id="phone3" minlength="4" maxlength="4" placeholder="끝 4자리"/>
					</td>
				</tr>
				<tr>
					<th class="title">이메일</th>
					<td id="email">
						<input type = "text" name = "email1" id="email1" maxlength="30" placeholder="이메일을 입력 하세요"/>@
						<select name="email2" >
							<option value = "null" selected>Email선택</option>
							<option value = "naver.com">naver.com</option>
							<option value = "daum.net">daum.net</option>
							<option value = "gmail.com">gmail.com</option>
							<option value = "nate.com">nate.com</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="title">주소</th>
					<td>
						<input type = "text" name = "addr" id="addr" maxlength="100" placeholder="주소를 입력 하세요"/>
					</td>
				</tr>
			</table>
			<div id="joinWrap">
			<input type="submit" value="가입" id="join"/>
			<input type="button" value="취소" id="cansle" onclick="goLoginForm()">
			</div>
		</form>
		</div>
	</section>
	</main>
	<footer>
		<%@include file="/incl/footer.jsp"%>
	</footer>


</body>
</html>