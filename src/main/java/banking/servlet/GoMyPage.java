package banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.domain.Customer;
import banking.service.CustomerService;


@WebServlet("/customer/mypage/gomypage.do")
public class GoMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = "thisid";
		CustomerService cs = new CustomerService();
		Customer customer = cs.getCustomer(userId);
		request.setAttribute("customer", customer);
		request.getRequestDispatcher("mypage.jsp").forward(request, response);
		
	}

}
