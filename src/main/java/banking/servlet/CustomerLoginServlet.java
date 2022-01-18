package banking.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Customer;
import banking.service.CustomerService;
import banking.service.Validator;
/**
 * �α��� ���� ������
 * @author ������
 *
 */
@WebServlet("/customer/login/login.do")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");

		CustomerService cs = new CustomerService();
		Customer customer = cs.findUserbyUserId(userId);
		if(customer == null) {
			//���̵� ����
			//���̵� ����
			request.getRequestDispatcher("loginidError.jsp").forward(request, response);
		}else if(!customer.getPasswd().equals(passwd)) {
			//��й�ȣ ���н�
			request.getRequestDispatcher("loginpasswdError.jsp").forward(request, response);
			
		}else {
			//����
			HttpSession session = request.getSession();
			Customer user = new Customer();
			user.setId(customer.getId());
			user.setUserId(customer.getUserId());
			user.setUserName(customer.getUserName());
			Cookie cookie;
			
			String SaveId = request.getParameter("loginCheckbox");
			if(SaveId != null && SaveId.equalsIgnoreCase("on")) {
				cookie = new Cookie("saveId",customer.getUserId());
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}else {
					Cookie[] cookies = request.getCookies();
					for(Cookie c : cookies) {
						if(c.getName().equals("saveId")) {
							c.setMaxAge(0);
							response.addCookie(c);
						}
					}
			}
			
			session.setAttribute("user", user);
			String targetPath = (String)session.getAttribute("targetPath");
			Validator v = new Validator();
			// targetPath ������ movepageServlet ���� �̵�
			if (!v.isEmpty(targetPath)) {
				// dispatcher�� �� ������ �̰ɷ� ���ϰ� send�� �ϸ� get���� ���� post �Լ��� ������ �ȵȴ�.
				request.getRequestDispatcher("/incl/menu.do").forward(request, response);
				return;
			}
			// ������ �׳� �α��� �Ϸ� �������� �̵�
			request.getRequestDispatcher("/account/add_account/add_account.jsp").forward(request, response);	
			
		}
	}
}
