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
		// 넘기기 전 형변환
		if(brAcc instanceof CheckingAccount) {
			account = (CheckingAccount)brAcc;
		} else {
			account = (SavingsAccount)brAcc;
		}
		
		// 로그인 상태 확인을 위한 cutomer
		Customer customerChk = (Customer)session.getAttribute("user");
		String targetPath = null;
		
		// 유효성 검사
		Validator v = new Validator();
		// 로그인 상태인지 확인
		if(v.isEmpty(customerChk)) {
			// targetPath 넣어놓기 (로그인 완료 후 여기로 온다 - 그 때 사용)
			if (pageName.equals("통장개설"))  {
				targetPath = "통장개설";
			}else if(pageName.equals("전체계좌조회")) {
				targetPath = "전체계좌조회";
			}else if (pageName.equals("마이페이지")) {
				targetPath = "마이페이지";
			} 
			// targetPath 외의 가져갈 것이 없기 때문에 그냥 send 로 간다
			response.sendRedirect(ctxPath+"/customer/login/login.jsp");
			// targetPath 로 지정 후 리턴
			session.setAttribute("targetPath", targetPath);
			return;
		}
		
		// targetPath 가 있으면 실행
		// 저장되어 있는 데이터를 pageName 에 넣어준다.
		// 로그인 서블릿으로 갔다가 오면 pageName에 아무것도 없기 때문에
		if(!v.isEmpty(session.getAttribute("targetPath"))) {
			targetPath = (String)session.getAttribute("targetPath");
			pageName = targetPath;
			// targetPath 없애기(session에)
			session.removeAttribute("targetPath");
		}
		
		if (pageName.equals("통장개설"))  {
			response.sendRedirect(ctxPath+ "/account/add_account/add_account.jsp");
		}
		else if (pageName.equals("전체계좌조회")) {
			// 전체계좌조회 페이지
			request.getRequestDispatcher("/account/inquiry_account/inquiryaccount.do").forward(request, response);

		} else if (pageName.equals("입금")) {
			// 입금 페이지
			
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/deposit/deposit.jsp").forward(request, response);

			return;

		} else if (pageName.equals("출금")) {
			// 출금 페이지
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/withdraw/withdraw.jsp").forward(request, response);
			
			return;	
		} else if (pageName.equals("이체")) {
			// 출금 페이지
			request.setAttribute("account", account);
			request.getRequestDispatcher("/account/trans_account/trans_account.jsp").forward(request, response);
			
			return;	
		} else if (pageName.equals("거래내역")) {
			// 거래내역 페이지
	         Account acc = as.getAccount(accNumber);
	         AccRecordService arsvc = new AccRecordService();
	         List<Map<String, String>> recordList = arsvc.getAccRecordList(accNumber); // 거래내역
	         request.setAttribute("acc", acc);
	         request.setAttribute("recordList", recordList);
	         request.getRequestDispatcher("/account/record_account/record_account.jsp").forward(request, response);
	         return;
		}else if (pageName.equals("마이페이지")) {
		
			// 마이페이지
//			Customer sessionCust = (Customer)session.getAttribute("user");
			Customer sessionCust = (Customer)session.getAttribute("user");
			String userId = sessionCust.getUserId();
			CustomerService cs = new CustomerService();
			Customer customer = cs.getCustomer(userId);
			request.setAttribute("customer", customer);
			request.getRequestDispatcher("/customer/mypage/mypage.jsp").forward(request, response);

		} else if (pageName.equals("로그인")) {
			response.sendRedirect(ctxPath+"/customer/login/login.jsp");
		}	

	}

}
