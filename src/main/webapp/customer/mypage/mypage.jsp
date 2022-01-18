<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
  <head>
    <meta charset="utf-8">
    
	<%@ include file="/incl/stylesheet_link.jsp"%>
    <script src="${pageContext.request.contextPath}/incl/js/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/customer/js/mypage.js" defer></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/customer/css/mypage.css" />
    <title>Mypage</title>
  </head>
  <body>
  	<jsp:include page="/incl/header.jsp"></jsp:include>
	<%@include file="/incl/user_nav.jsp"%>
    <main>
        <section id="myPageContent">
          <h2>내 정보</h2>
          <div id="myPageWrap">
              <div class="myPageTitleWrap">
                <h3>회원 정보</h3>
              </div>
              <div class="myPageTableWrap">
                <table class="myPageTable">
                  <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>사용자 이름</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                      	<c:if test="${not empty customer.userName }"><p class="myPage_t1">${customer.userName }</p></c:if>
  						<c:if test="${empty customer.userName }"><p class="myPage_t1 nullOutput">null</p></c:if>
                        <p class="myPage_t2">이름은 수정하실 수 없습니다.</p>
                       </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>아이디</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                        <c:if test="${not empty customer.userId }"><p class="myPage_t1">${customer.userId }</p></c:if>
  						<c:if test="${empty customer.userId }"><p class="myPage_t1 nullOutput">null</p></c:if>
                        <p class="myPage_t2">Id는 수정하실 수 없습니다.</p>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>비밀번호</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                        <p class="myPage_t1 passwordOutput1">********</p>
                        <p class="myPage_t2 passwordOutput2">영문,숫자로 이루어진 8~20자의 로그인시 사용하는 비밀번호 입니다.</p>
                        <p class='myPage_t3 passwordOutput3'>비밀번호를 성공적으로 수정하였습니다.</p>
                        <div class="myPage_modifiedConWrap mypage_passwordModiWrap ">
                          <form action=""method="post" name="passwordForm">
                            현재 비밀번호 <br>
                            <div class="pastPasswordInputWrap inputWrap ">
                              <input type="password" class="modiInput password_InputValue1" placeholder="현재 비밀번호를 입력해주세요"><br>
                            </div>
                            변경할 비밀번호<br>
                            <div class="newPasswordInputWrap inputWrap">
                              <input type="password" class="modiInput moniInput_top password_InputValue2" maxlength="20" placeholder="영문,숫자로 이루어진 8~20글자를 입력해주세요"><br>
                              <input type="password" class="modiInput password_InputValue3" maxlength="20" placeholder="다시 한 번 더 입력해주세요">
                            </div>
                            <a href="#" class="passwordFixCancelBtn fixBtn">수정취소</a>
                            <a href="#" class="passwordFixSuccessBtn fixBtn fixSuccessBtn">수정완료</a>
                          </form>
                        </div>
                        <a href="#" class="passwordFixBtn fixBtn modiBtn">수정</a>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>휴대전화</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                     	<c:if test="${not empty customer.phone }"><p class="myPage_t1 phoneOutput1">${customer.phone }</p></c:if>
                     	<c:if test="${empty customer.phone }"><p class="myPage_t1 phoneOutput1 nullOutput">null</p></c:if>
                        <p class="myPage_t2 phoneOutput2">은행 출금 내역 및 정보 안내를 받으실 때 사용하는 휴대 전화입니다.</p>
                        <p class='myPage_t3 phoneOutput3'>연락처를 성공적으로 수정하였습니다.</p>
                        <div class="myPage_modifiedConWrap mypage_phoneModiWrap">
                            변경할 휴대전화<br>
                            <span class="phoneInputWrap inputWrap">
                              <span class="phone_InputDefault">010</span>
                              <input type="text" name="modifiedPhoneNumber" class="phone_InputValue" maxlength="9">
                            </span><br>
                            <a href="#" class="phoneFixCancelBtn fixBtn">수정취소</a>
                            <a href="#" class="phoneFixSuccessBtn fixBtn fixSuccessBtn">수정완료</a>
                        </div>
                        <a href="#" class="phoneFixBtn fixBtn modiBtn">수정</a>
                      </div>
                    </td>
                  </tr>
                <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>이메일</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                     	<c:if test="${not empty customer.email }"><p class="myPage_t1 emailOutput1">${customer.email }</p></c:if>
                      	<c:if test="${empty customer.email }"><p class="myPage_t1 emailOutput1 nullOutput">null</p></c:if>
                        
                        <p class="myPage_t2 emailOutput2">정보제공동의 및 이벤트 안내를 받을 때 사용하는 메일입니다.</p>
                        <p class='myPage_t3 emailOutput3'>이메일을 성공적으로 수정하였습니다.</p>
                        <div class="myPage_modifiedConWrap mypage_emailModiWrap">
                            변경할 이메일<br>
                            <div class="emailInputWrap inputWrap">
                              <input type="text" name="modifiedEmail1" class="email_InputValue1 modiInput" maxlength="30" placeholder="이메일아이디 입력">
                              <span>@</span>
                                <input type="text" name="modifiedEmail2" value="naver.com" class="email_InputValue2 modiInput" disabled>
                              <select class="emailList modiInput" name="emailSelectList">
                                <option value="naver">naver.com</option>
                                <option value="gmail">gmail.com</option>
                                <option value="daum">daum.net</option>
                                <option value="yahoo">yahoo.co.kr</option>
                                <option value="etc">직접입력</option>
                              </select>
                            </div>
                            <a href="#" class="emailFixCancelBtn fixBtn">수정취소</a>
                            <a href="#" class="emailFixSuccessBtn fixBtn fixSuccessBtn">수정완료</a>
                        </div>
                        <a href="#" class="emailFixBtn fixBtn modiBtn">수정</a>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th scope="row" class="tableLeftSide">
                      <div>주소</div>
                    </th>
                    <td class="tableRightSide">
                      <div class="myPageText">
                      	<c:if test="${not empty customer.addr }"><p class="myPage_t1 addrOutput1">${customer.addr }</p></c:if>
                      	<c:if test="${empty customer.addr }"><p class="myPage_t1 addrOutput1 nullOutput">null</p></c:if>
                        <p class="myPage_t2 addrOutput2">카드 및 안내우편을 받을 때 사용하는 주소입니다.</p>
                        <p class='myPage_t3 addrOutput3'>주소를 성공적으로 수정하였습니다.</p>
                        <div class="myPage_modifiedConWrap mypage_addrModiWrap">
                            변경할 주소<br>
                            <div class="addrInputWrap inputWrap">
                              <input type="text" name="modifiedAddr1" class="addr_InputValue1 modiInput" placeholder="주소1" maxlength="30">
                              <input type="text" name="modifiedAddr2" class="addr_InputValue2 modiInput" placeholder="주소2" maxlength="30">
                              
                            </div>
                            <a href="#" class="addrFixCancelBtn fixBtn">수정취소</a>
                            <a href="#" class="addrFixSuccessBtn fixBtn fixSuccessBtn">수정완료</a>
                        </div>
                        <a href="#" class="addrFixBtn fixBtn modiBtn">수정</a>
                      </div>
                    </td>
                  </tr>
                  
                </table>
              </div>
              <div class="rmCustBtnWrap">
	           	<form action="${pageContext.request.contextPath}/customer/mypage/remove_customer.do" method="post" name="rmCustForm">	
	          		<button type="button" class="rmCustBtn fixBtn" style="float:right; margin-top:20px" onclick="rmCustFunc()">회원탈퇴</button>
	           	</form>
	          </div>
          </div>
        </section>
    </main>
    <%@include file="/incl/footer.jsp"%>
  </body>
</html>