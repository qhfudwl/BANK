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
import banking.exception.InsufficientBalanceException;
import banking.exception.NegativeAmountException;
import banking.exception.OverdraftException;
import banking.service.AccRecordService;
import banking.service.AccountService;
import banking.service.Validator;
/**
 * 
 * @author ������
 *
 */
@WebServlet("/account/withdraw/withdraw.do")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService as = new AccountService();
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transType = request.getParameter("transType"); // W ����		

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
		
		
		//�� �κ� ���� �Ǿ����ϴ�.
		String errMsg = "";
		Validator v = new Validator();
	
		if(v.isEmpty(amount)) {
			errMsg = "��ݾ��� �Է����� �����̽��ϴ�";
		}
		else if(!v.isNumber(amount)) {
			errMsg = "���ڰ� �ƴ� �ٸ� ���ڸ� �Է��ϼ̽��ϴ�";
		}
		if(!v.isEmpty(errMsg)) {
			System.out.println("test-error");
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("account", account);
			request.getRequestDispatcher("withdraw.jsp").forward(request, response); 
			
			return; 			
		}
		

		double wAmount = Double.parseDouble(amount);
		
		
		try {
			account = as.withdrawAccount(accNumber, wAmount);
		} catch (OverdraftException e) {
			errMsg = e.getMessage();			
		} catch (InsufficientBalanceException e) {
			errMsg = e.getMessage();			
		}  catch (NegativeAmountException e) {
			errMsg = e.getMessage();			
		}  finally {
			if(!v.isEmpty(errMsg)) {
				System.out.println("test-error");
				request.setAttribute("errMsg", errMsg);
				request.setAttribute("account", account);
				request.getRequestDispatcher("withdraw.jsp").forward(request, response); 
				return; 			
			}
		}
		
		AccRecordService ars = new AccRecordService();
		ars.writeRecord(accNumber, transType, wAmount, account.getBalance());
		
		//���� ���� �Ѱ��ٰų�. ���� / �ܾ� / �Աݾ� / 
		// ���ǹ� �ʿ�.
		request.setAttribute("account", account); // for ����, �ܾ�
		request.setAttribute("amount", amount); // for �Աݾ� amount: string, damount: double(������.)
		
		request.getRequestDispatcher("afterWithdraw.jsp").forward(request, response); // �Ա� �� ȭ������.
		
		return;
	}
}
