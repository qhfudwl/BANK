package banking.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.domain.Customer;
import banking.service.CustomerService;
/**
 * 회원가입 내 id 중복체크
 * @author 정원식
 *
 */

@WebServlet("/customer/join/customer_id_check.do")
public class CustomerIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		CustomerService cs = new CustomerService();
		
		if(request.getParameter("type").equals("id")) {
			String id_InputValue = request.getParameter("id_InputValue");
			if(cs.isUserbyUserId(id_InputValue)) {
				out.print("false");//중복된 아이디입니다.
			}else {
				out.print("true");
			}
		}
	}
}
