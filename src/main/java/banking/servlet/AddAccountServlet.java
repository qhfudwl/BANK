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
		
		// ��ȿ�� �˻�
		if(v.isEmpty(accType)) { // ���¸� �����ߴ°�
			errMsgs.put("accTypeErr", "���� ������ �������� �ʾҽ��ϴ�.");
		}
		if(v.isEmpty(balance)) { // �Աݾ��� �����°�
			errMsgs.put("balanceErr", "�Աݾ��� �Է����� �ʾҽ��ϴ�.");
		}
		if(!v.isEmpty(balance)) {
			if (!v.isNumber(balance)) { // �Աݾ��� �����ΰ�
				errMsgs.put("balanceErr", "���ڸ� �����ּ���.");
			} else if (Integer.parseInt(balance) < 1000) { // �Աݾ��� 1000�� �̻��ΰ�
				errMsgs.put("balanceErr", "�Աݾ��� 1000�� �̸��Դϴ�.");
			}
		}
		if(accType.equals("SA")) { // ���� Ÿ���� SA
			if(!v.isEmpty(overDraft)) { // �ѵ����� ������
				errMsgs.put("overErr", "������´� �ѵ����� ������ �� �����ϴ�.");
			} else {
				overDraft = "0"; // �ѵ��� 0������ ����
			}
		}
		if(accType.equals("CA")) { // ���� Ÿ���� CA
			if (v.isEmpty(overDraft)) { // �ѵ����� ��������
				errMsgs.put("overErr", "�ѵ����� �Է����� �ʾҽ��ϴ�.");
			} else if (!v.isEmpty(overDraft) && !v.isNumber(overDraft)) { // �ѵ����� �����µ� ���ڰ� �ƴϸ�
				errMsgs.put("overErr", "���ڸ� �����ּ���.");
			} else {
				interestRate = "0";
			}
		}
		// ���� �޼����� ������
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
