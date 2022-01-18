package banking.service;

import java.util.Random;

import banking.dao.AccountDao;
import banking.domain.Account;

public class AccountNumGenerator {
	private static Checker chk = new Checker();
	/**
	 * 4�ڸ� - 4�ڸ� ���� ���¹�ȣ ����
	 * @return
	 */
	public static String generateAccountNum() {
		String accNum = randomAccountNum();
		
		while (!chk.checkDuplicationOfAccNumber(accNum)) { // �����̸� �ߺ� �ִ�.
			accNum = randomAccountNum();
		}
		return accNum;
	}
	
	private static String randomAccountNum() {
		Random random = new Random();
		int num = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 2; i++) {
			num = random.nextInt(10000);
			while(num < 1000) {
				num = random.nextInt(10000);
			}
			sb.append(num);
			if(i < 1) {
				sb.append("-");
			}
		}
		return sb.toString();
	}
}
