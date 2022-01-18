package banking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Customer;
/**
 * 
 * @author 윤효심
 *
 */
import banking.service.CustomerService;
/**
 * 
 * @author 윤효심
 *
 */
@WebServlet("/customer/mypage/mypage.do")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Customer newCustomer;
	Customer customer;
	CustomerService cs = new CustomerService();
	
	public MypageServlet() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		//ajax기본사용법
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("modifiedPhoneNumber"));
		//ajax 비동기 통신 응답이므로, 필요한 데이터만 응답한다.
		//(html 전체 페이지 X!!)
		out.println("ajax 응답");
		out.close();
		*/
		
		
		HttpSession session = request.getSession();
		customer = (Customer)session.getAttribute("user");
		customer = cs.getCustomer(customer.getUserId());
		String userId = customer.getUserId();
		
		//자바스크립트 Ajax사용법
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//폰번호 업데이트
		if(request.getParameter("type").equals("phone")) {
			String modiPhoneNumber = "010-"+request.getParameter("phone_InputValue");
			newCustomer = cs.updatePhoneNumber(cs.updatePhoneNumber(userId, modiPhoneNumber));
			customer.setPhone(newCustomer.getPhone());
			out.print(newCustomer.getPhone());
		}
		//비밀번호 업데이트
		else if(request.getParameter("type").equals("password")) {
			String nowPwd = request.getParameter("password_InputValue1");
			String modiPwd1 = request.getParameter("password_InputValue2");
			String modiPwd2 = request.getParameter("password_InputValue3");
			
			if(!customer.getPasswd().equals(nowPwd)) {
				//유저 비밀번호와 입력받은 비밀번호가 일치 하지 않으면 실패
				out.print("현재 비밀번호가 일치하지 않습니다.");
			}else if(!modiPwd1.equals(modiPwd2)) {
				//입력받은 비밀번호 2개가 일치하지 않으면 실패
				out.print("새로운 비밀번호를 다시 한번 확인해 주세요.");
			}else {
				newCustomer = cs.updatePassword(cs.updatePassword(userId,modiPwd1));
				customer.setPasswd(newCustomer.getPasswd());
				out.print("비밀번호가 성공적으로 수정되었습니다.");
			}
		}
		//이메일 업데이트
		else if(request.getParameter("type").equals("email")) {
			String email1 = request.getParameter("email_InputValue1");
			String email2 = request.getParameter("email_InputValue2");
			String email = email1 + "@" +email2;
			
			newCustomer = cs.updateEmail(cs.updateEmail(userId,email));
			customer.setEmail(newCustomer.getEmail());
			out.print(newCustomer.getEmail());
		}
		//주소 업데이트
		else if(request.getParameter("type").equals("address")) {
			String addr1 = request.getParameter("addr_InputValue1");
			String addr2 = request.getParameter("addr_InputValue2");
			String addr = addr1 + " "+ addr2;
			
			newCustomer = cs.updateAddr(cs.updateAddr(userId,addr));
			customer.setAddr(newCustomer.getAddr());
			out.print(newCustomer.getAddr());
		}
		out.close();
	}

}
