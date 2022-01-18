package banking.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.domain.Customer;
import banking.service.CustomerService;
/**
 * 
 * @author 정원식
 *
 */

@WebServlet("/customer/join/customer_input.do")
public class CustomerInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		String userName = request.getParameter("userName");
		String ssn1 = request.getParameter("ssn1");
		String ssn2 = request.getParameter("ssn2");
		String ssn = ssn1 + "-" + ssn2;
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" +phone3;
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + "@" + email2;
		String addr = request.getParameter("addr");
		
		
		CustomerService customerService = new CustomerService();
		Customer customer = customerService.addCustomer(customerService.addCustomer(userId, passwd, userName, ssn, phone, email, addr));
		

		request.setAttribute("customer", customer); // EL 사용하려고 이렇게 객체 자체를 보낸 것
		request.getRequestDispatcher("customerOutput.jsp").forward(request, response);
		
		
	}
		


}
