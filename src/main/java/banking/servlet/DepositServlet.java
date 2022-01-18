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
 * @author 성지원
 *
 */
@WebServlet("/account/deposit/deposit.do")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService as = new AccountService();
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String transType = request.getParameter("transType"); // D 받음
		
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
		
		// 기존 errorMsgs 리스트 썼던 부분을 String errMsg 로 대체
		String errMsg = "";
		Validator v = new Validator();
		
		if(v.isEmpty(amount)) {
			errMsg = "입금액을 입력하지 않으셨습니다.";
		} else if(!v.isNumber(amount)) {
			errMsg = "숫자가 아닌 다른 문자를 입력하셨습니다.";
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
		
				
		//내가 뭐를 넘겨줄거냐. 계좌 / 잔액 / 입금액 / 
		// 조건문 필요.
		request.setAttribute("account", account); // for 계좌, 잔액
		request.setAttribute("amount", amount); // for 입금액 amount: string, damount: double(뭐쓸지.)
		
		
		request.getRequestDispatcher("afterDeposit.jsp").forward(request, response); // 입금 후 화면으로.
		
		return;
	}
}
