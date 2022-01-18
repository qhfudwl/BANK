package banking.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Account;
import banking.domain.CheckingAccount;
import banking.domain.SavingsAccount;
import banking.service.AccRecordService;
import banking.service.AccountService;
import banking.service.Validator;
/**
 * 
 * @author ������
 *
 */
@WebServlet("/account/deposit/deposit.do")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService as = new AccountService();
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transType = request.getParameter("transType"); // D ����
		
		String accNumber = request.getParameter("accNumber");
		String amount = request.getParameter("amount");
		
		Account brAcc = as.findAccount(accNumber);
		Account account = null;
				
		if(brAcc instanceof SavingsAccount) {
			account = (SavingsAccount)brAcc;
		}
		else {
			account = (CheckingAccount)brAcc;
		}
		
		// ���� errorMsgs ����Ʈ ��� �κ��� String errMsg �� ��ü
		String errMsg = "";
		Validator v = new Validator();
		
		if(v.isEmpty(amount)) {
			errMsg = "�Աݾ��� �Է����� �����̽��ϴ�.";
		} else if(!v.isNumber(amount)) {
			errMsg = "���ڰ� �ƴ� �ٸ� ���ڸ� �Է��ϼ̽��ϴ�.";
		}	
			
		if(!errMsg.isEmpty()) {
			System.out.println("test-error");
			request.setAttribute("errMsg", errMsg); // test
			request.setAttribute("account", account);
			request.getRequestDispatcher("deposit.jsp").forward(request, response);
			
			return;
		}
		
		double dAmount = Double.parseDouble(amount);
		account = as.depositAccount(accNumber, dAmount);

		AccRecordService ars = new AccRecordService();
		ars.writeRecord(accNumber, transType, dAmount, account.getBalance());
		
				
		//���� ���� �Ѱ��ٰų�. ���� / �ܾ� / �Աݾ� / 
		// ���ǹ� �ʿ�.
		request.setAttribute("account", account); // for ����, �ܾ�
		request.setAttribute("amount", amount); // for �Աݾ� amount: string, damount: double(������.)
		
		
		request.getRequestDispatcher("afterDeposit.jsp").forward(request, response); // �Ա� �� ȭ������.
		
		return;
	}
}
