package banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Customer;
import banking.service.AccountService;
import banking.service.CustomerService;

/**
 * 
 * @author À±È¿½É
 *
 */
@WebServlet("/customer/mypage/remove_customer.do")
public class RemoveCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer user = (Customer)session.getAttribute("user");
		CustomerService cs = new CustomerService();
		cs.removeCustomer(user.getUserId());
		
		session.removeAttribute("user");
		
		request.getRequestDispatcher("/customer/login/login.jsp").forward(request, response);
		
	}

}
