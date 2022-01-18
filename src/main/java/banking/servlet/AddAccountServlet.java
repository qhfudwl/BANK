package banking.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.domain.Account;
import banking.domain.CheckingAccount;
import banking.domain.Customer;
import banking.domain.SavingsAccount;
import banking.service.AccountService;
import banking.service.Converter;
import banking.service.Validator;

@WebServlet("/account/add_account/add_account.do")
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountService asvc;
	private Validator v = new Validator();

	public void init(ServletConfig config) throws ServletException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accType = request.getParameter("accType");
		String balance = request.getParameter("balance");
		String interestRate = request.getParameter("interestRate");
		String overDraft = request.getParameter("overDraft");
		Map<String, String> errMsgs = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("user");
		
		request.setAttribute("customer", customer);
		
		// 유효성 검사
		if(v.isEmpty(accType)) { // 계좌를 선택했는가
			errMsgs.put("accTypeErr", "계좌 종류를 선택하지 않았습니다.");
		}
		if(v.isEmpty(balance)) { // 입금액을 적었는가
			errMsgs.put("balanceErr", "입금액을 입력하지 않았습니다.");
		}
		if(!v.isEmpty(balance)) {
			if (!v.isNumber(balance)) { // 입금액이 숫자인가
				errMsgs.put("balanceErr", "숫자만 적어주세요.");
			} else if (Integer.parseInt(balance) < 1000) { // 입금액이 1000원 이상인가
				errMsgs.put("balanceErr", "입금액이 1000원 미만입니다.");
			}
		}
		if(accType.equals("SA")) { // 계좌 타입이 SA
			if(!v.isEmpty(overDraft)) { // 한도액을 적으면
				errMsgs.put("overErr", "보통계좌는 한도액을 설정할 수 없습니다.");
			} else {
				overDraft = "0"; // 한도액 0원으로 고정
			}
		}
		if(accType.equals("CA")) { // 계좌 타입이 CA
			if (v.isEmpty(overDraft)) { // 한도액을 안적으면
				errMsgs.put("overErr", "한도액을 입력하지 않았습니다.");
			} else if (!v.isEmpty(overDraft) && !v.isNumber(overDraft)) { // 한도액을 적었는데 숫자가 아니면
				errMsgs.put("overErr", "숫자만 적어주세요.");
			} else {
				interestRate = "0";
			}
		}
		// 에러 메세지가 있으면
		if(!errMsgs.isEmpty()) {
			request.setAttribute("errMsgs", errMsgs);
			request.setAttribute("errSize", errMsgs.size());
			request.getRequestDispatcher("add_account.jsp").forward(request, response);
			return;
		}
		
		// add account
				asvc = new AccountService();
				Account acc = asvc.addAccount(customer, accType.charAt(0), Double.parseDouble(balance),
											Double.parseDouble(interestRate), Double.parseDouble(overDraft));
				String accNubmer = acc.getAccNumber();
				
				Account account = asvc.getAccount(accNubmer);
				Converter cvt = new Converter();
				String regDate = cvt.matchDateToFormat(account.getRegDate());
				
				if (accType.equals("SA")) {
					request.setAttribute("account", (SavingsAccount)account);
				} else {
					request.setAttribute("account", (CheckingAccount)account);
				}
				request.setAttribute("regDate", regDate);
				request.getRequestDispatcher("add_account_comp.jsp").forward(request, response);

		
	}

}
