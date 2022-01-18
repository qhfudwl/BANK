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
import banking.service.AccRecordService;
import banking.service.AccountService;


@WebServlet("/account/record_account/record_account.do")
public class RecordAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");

		
		AccountService asvc = new AccountService();
        String accNumber = request.getParameter("accNum");
        Account acc = asvc.getAccount(accNumber);
        AccRecordService arsvc = new AccRecordService();
        List<Map<String, String>> recordList = arsvc.getAccRecordList(accNumber); // 거래내역
        request.setAttribute("acc", acc);
        request.setAttribute("recordList", recordList);
        request.getRequestDispatcher("/account/record_account/record_account.jsp").forward(request, response);
        
	}

}
