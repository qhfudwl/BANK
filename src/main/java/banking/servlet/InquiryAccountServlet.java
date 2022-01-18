package banking.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Account;
import banking.domain.Customer;
import banking.service.AccountService;
/**
 * 
 * @author ��ȿ��
 *
 */

@WebServlet("/account/inquiry_account/inquiryaccount.do")
public class InquiryAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer=null;
		AccountService as = new AccountService();
		//�����ҽ�
		HttpSession session = request.getSession();
		customer = (Customer)session.getAttribute("user");

		customer.setAccounts(as.getAccounts(customer.getUserId()));
		double totalBalance = getTotalAccountsBalance(customer);
		
		
		/**
		 * �� �ڻ� : totalAcctBalance
		 * ���� ���� �� �ݾ� : totalGeneralAcctBalance
		 */
		
		//�Ҽ������ϰ� 0�̸� .0�� �����ش�.
		
		request.setAttribute("totalAcctBalance", totalBalance);
		request.setAttribute("totalGeneralAcctBalance",totalBalance);
		request.setAttribute("userInquiryAcct", customer);
		request.getRequestDispatcher("inquiry_account.jsp").forward(request, response);
		
	}
	
	private double getTotalAccountsBalance(Customer customer) {
		List<Account> accounts= new ArrayList<>();
		accounts = customer.getAccounts();
		double totalBalance = 0.0;
		for(Account ac:accounts) {
			totalBalance += ac.getBalance();
		}
		return totalBalance;
	}
	
	
	

}
