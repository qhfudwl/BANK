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
 * @author ��ȿ��
 *
 */
import banking.service.CustomerService;
/**
 * 
 * @author ��ȿ��
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
		//ajax�⺻����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("modifiedPhoneNumber"));
		//ajax �񵿱� ��� �����̹Ƿ�, �ʿ��� �����͸� �����Ѵ�.
		//(html ��ü ������ X!!)
		out.println("ajax ����");
		out.close();
		*/
		
		
		HttpSession session = request.getSession();
		customer = (Customer)session.getAttribute("user");
		customer = cs.getCustomer(customer.getUserId());
		String userId = customer.getUserId();
		
		//�ڹٽ�ũ��Ʈ Ajax����
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//����ȣ ������Ʈ
		if(request.getParameter("type").equals("phone")) {
			String modiPhoneNumber = "010-"+request.getParameter("phone_InputValue");
			newCustomer = cs.updatePhoneNumber(cs.updatePhoneNumber(userId, modiPhoneNumber));
			customer.setPhone(newCustomer.getPhone());
			out.print(newCustomer.getPhone());
		}
		//��й�ȣ ������Ʈ
		else if(request.getParameter("type").equals("password")) {
			String nowPwd = request.getParameter("password_InputValue1");
			String modiPwd1 = request.getParameter("password_InputValue2");
			String modiPwd2 = request.getParameter("password_InputValue3");
			
			if(!customer.getPasswd().equals(nowPwd)) {
				//���� ��й�ȣ�� �Է¹��� ��й�ȣ�� ��ġ ���� ������ ����
				out.print("���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}else if(!modiPwd1.equals(modiPwd2)) {
				//�Է¹��� ��й�ȣ 2���� ��ġ���� ������ ����
				out.print("���ο� ��й�ȣ�� �ٽ� �ѹ� Ȯ���� �ּ���.");
			}else {
				newCustomer = cs.updatePassword(cs.updatePassword(userId,modiPwd1));
				customer.setPasswd(newCustomer.getPasswd());
				out.print("��й�ȣ�� ���������� �����Ǿ����ϴ�.");
			}
		}
		//�̸��� ������Ʈ
		else if(request.getParameter("type").equals("email")) {
			String email1 = request.getParameter("email_InputValue1");
			String email2 = request.getParameter("email_InputValue2");
			String email = email1 + "@" +email2;
			
			newCustomer = cs.updateEmail(cs.updateEmail(userId,email));
			customer.setEmail(newCustomer.getEmail());
			out.print(newCustomer.getEmail());
		}
		//�ּ� ������Ʈ
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
