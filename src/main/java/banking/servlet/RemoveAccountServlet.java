package banking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.service.AccountService;
/**
 * 
 * @author À±È¿½É
 *
 */
@WebServlet("/account/record_account/remove_account.do")
public class RemoveAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accNumber = request.getParameter("accNumber");
		
		AccountService as = new AccountService();
		try {
			as.removeAccount(accNumber);
		} catch (RuntimeException e) {
			e.getMessage();
		}
		
		request.getRequestDispatcher("/account/inquiry_account/inquiryaccount.do").forward(request, response);
	}

}
