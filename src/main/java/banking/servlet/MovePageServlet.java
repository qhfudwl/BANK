package banking.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import banking.service.AccRecordService;
import banking.service.AccountService;
import banking.service.CustomerService;
import banking.service.Validator;


@WebServlet("/incl/menu.do")
public class MovePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService as = new AccountService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String pageName = request.getParameter("menu");
		
		String ctxPath = request.getContextPath();
		String accNumber = request.getParameter("accNumber");
		
		Account brAcc = as.findAccount(accNumber);
		Account account = null;
		// �ѱ�� �� ����ȯ
		if(brAcc instanceof CheckingAccount) {
			account = (CheckingAccount)brAcc;
		} else {
			account = (SavingsAccount)brAcc;
		}
		
		// �α��� ���� Ȯ���� ���� cutomer
		Customer customerChk = (Customer)session.getAttribute("user");
		String targetPath = null;
		
		// ��ȿ�� �˻�
		Validator v = new Validator();
		// �α��� �������� Ȯ��
		if(v.isEmpty(customerChk)) {
			// targetPath �־���� (�α��� �Ϸ� �� ����� �´� - �� �� ���)
			if (pageName.equals("���尳��"))  {
				targetPath = "���尳��";
			}else if(pageName.equals("��ü������ȸ")) {
				targetPath = "��ü������ȸ";
			}else if (pageName.equals("����������")) {
				targetPath = "����������";
			} 
			// targetPath ���� ������ ���� ���� ������ �׳� send �� ����
			response.sendRedirect(ctxPath+"/customer/login/login.jsp");
			// targetPath �� ���� �� ����
			session.setAttribute("targetPath", targetPath);
			return;
		}
		
		// targetPath �� ������ ����
		// ����Ǿ� �ִ� �����͸� pageName �� �־��ش�.
		// �α��� �������� ���ٰ� ���� pageName�� �ƹ��͵� ���� ������
		if(!v.isEmpty(session.getAttribute("targetPath"))) {
			targetPath = (String)session.getAttribute("targetPath");
			pageName = targetPath;
			// targetPath ���ֱ�(session��)
			session.removeAttribute("targetPath");
		}
		
		if (pageName.equals("���尳��"))  {
			response.sendRedirect(ctxPath+ "/account/add_account/add_account.jsp");
		}
		else if (pageName.equals("��ü������ȸ")) {
			// ��ü������ȸ ������
			request.getRequestDispatcher("/account/inquiry_account/inquiryaccount.do").forward(request, response);

		} else if (pageName.equals("�Ա�")) {
			// �Ա� ������
			
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/deposit/deposit.jsp").forward(request, response);

			return;

		} else if (pageName.equals("���")) {
			// ��� ������
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/withdraw/withdraw.jsp").forward(request, response);
			
			return;	
		} else if (pageName.equals("��ü")) {
			// ��� ������
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/trans_account/trans_account.jsp").forward(request, response);
			
			return;	
		} else if (pageName.equals("�ŷ�����")) {
			// �ŷ����� ������
	         Account acc = as.getAccount(accNumber);
	         AccRecordService arsvc = new AccRecordService();
	         List<Map<String, String>> recordList = arsvc.getAccRecordList(accNumber); // �ŷ�����
	         request.setAttribute("acc", acc);
	         request.setAttribute("recordList", recordList);
	         request.getRequestDispatcher("/account/record_account/record_account.jsp").forward(request, response);
	         return;
		}else if (pageName.equals("����������")) {
		
			// ����������
//			Customer sessionCust = (Customer)session.getAttribute("user");
			Customer sessionCust = (Customer)session.getAttribute("user");
			String userId = sessionCust.getUserId();
			CustomerService cs = new CustomerService();
			Customer customer = cs.getCustomer(userId);
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("/customer/mypage/mypage.jsp").forward(request, response);

		} else if (pageName.equals("�α���")) {
			response.sendRedirect(ctxPath+"/customer/login/login.jsp");
		}	

	}

}
