package banking.service;

import banking.dao.AccountDao;
import banking.domain.Account;

public class Checker {
private Validator v;
	
	public Checker() {
		v = new Validator();
	}
	
	/**
	 * 계좌 번호 중복 검사
	 * @param accNumber
	 * @return
	 */
	public boolean checkDuplicationOfAccNumber(String accNumber) {
		AccountDao ad = new AccountDao();
		Account acc = ad.findAccountByAccNumber(accNumber);
		
		if (v.isEmpty(acc)) {
			return true;
		}
		
		return false;
	}
}
