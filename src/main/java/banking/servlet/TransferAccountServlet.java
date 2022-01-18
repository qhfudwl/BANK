package banking.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.domain.Account;
import banking.domain.CheckingAccount;
import banking.domain.Customer;
import banking.domain.SavingsAccount;
import banking.service.AccRecordService;
import banking.service.AccountService;
import banking.service.CustomerService;
import banking.service.Validator;

@WebServlet("/account/trans_account/trans_account.do")
public class TransferAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService as = new AccountService();
	private CustomerService cs = new CustomerService();
	private AccRecordService ars = new AccRecordService();
	private Validator v = new Validator();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String accNumber = request.getParameter("accNumber"); // 내 계좌번호
		String transAccNumber = request.getParameter("transAccNumber"); // 상대방 계좌번호
		String check = request.getParameter("check"); // submit
		String amount = request.getParameter("amount");
		double dAmount = 0;
		String transContent = request.getParameter("transContent");
		String myContent = request.getParameter("myContent");
		String transType = request.getParameter("transType");
		String myType = request.getParameter("myType");
		String errMsg = null;


		Account acc = as.findAccount(accNumber); // 내 계좌
		Account transAcc = as.findAccount(transAccNumber); // 상대방 계좌
		
		Account account = null;
		if (acc instanceof SavingsAccount) {
			account = (SavingsAccount)acc;
		} else {
			account = (CheckingAccount)acc;
		}
		Account transAccount = null;
		if (transAcc instanceof SavingsAccount) {
			transAccount = (SavingsAccount)transAcc;
		} else {
			transAccount = (CheckingAccount)transAcc;
		}
		
		long id = account.getCustomer().getId();
		long transId = 0;
		try {
			transId = transAccount.getCustomer().getId(); // 상대방 customer id
		} catch(NullPointerException e) {
			goPreviousPage(request, response, check, account, transAccount, null, null, null);
			return;
		}

		Customer customer = cs.findCustomerById(id); // 내 customer 객체
		Customer transCustomer = cs.findCustomerById(transId); // 상대방 customer 객체
		
		if (check.equals("확인")) {
			goPreviousPage(request, response, check, account, transAccount, customer, transCustomer, null);
			return;
		} else if (check.equals("이체하기")) {
			if (!v.isEmpty(amount)) {
				try {
					dAmount = Double.parseDouble(amount);
				} catch(RuntimeException e) {
					errMsg = "숫자만 입력해주세요.";
					goPreviousPage(request, response, check, account, transAccount, customer, transCustomer, errMsg);
				}
			}
			// 내 계좌에서는 출금
			try {
				as.withdrawAccount(account.getAccNumber(), dAmount);
			} catch (Exception e) {
				errMsg = e.getMessage();
				goPreviousPage(request, response, check, account, transAccount, customer, transCustomer, errMsg);
				return;
			}
			
			// 상대방 계좌에는 입금
			as.depositAccount(transAccount.getAccNumber(), dAmount);
			
			// 거래 후 계좌
			
			Account afterAccount = as.findAccount(accNumber);
			Account afterTransAccount = as.findAccount(transAccNumber);

			ars.writeRecord(accNumber, myType, dAmount, afterAccount.getBalance(), myContent);
			ars.writeRecord(transAccNumber, transType, dAmount, afterTransAccount.getBalance(), transContent);

			request.setAttribute("check", check);
			request.setAttribute("account", afterAccount);
			request.setAttribute("transAccount", afterTransAccount);
			request.setAttribute("customer", customer);
			request.setAttribute("transCustomer", transCustomer);
			request.setAttribute("amount", amount);
			request.setAttribute("transContent", transContent);
			request.setAttribute("myContent", myContent);
			request.getRequestDispatcher("/account/trans_account/trans_check.jsp").forward(request, response);
			return;
		}
		
		
		
	}

	private void goPreviousPage(HttpServletRequest request, HttpServletResponse response, String check, Account account,
			Account transAccount, Customer customer, Customer transCustomer, String errMsg) {
		if(!v.isEmpty(check)) {
			request.setAttribute("check", check);
		}
		if(!v.isEmpty(account)) {
			request.setAttribute("account", account);
		}
		if(!v.isEmpty(transAccount)) {
			request.setAttribute("transAccount", transAccount);
		}
		if (!v.isEmpty(customer)) {
			request.setAttribute("customer", customer);
		}
		if(!v.isEmpty(transCustomer)) {
			request.setAttribute("transCustomer", transCustomer);
		}
		if(!v.isEmpty(errMsg)) {
			request.setAttribute("errMsg", errMsg);
		}
		try {
			request.getRequestDispatcher("/account/trans_account/trans_account.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}

}
