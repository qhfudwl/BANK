package banking.service;

import java.util.List;

import banking.dao.AccountDao;
import banking.domain.Account;
import banking.domain.CheckingAccount;
import banking.domain.Customer;
import banking.domain.SavingsAccount;
import banking.exception.InsufficientBalanceException;
import banking.exception.NegativeAmountException;
import banking.exception.OverdraftException;

public class AccountService {

	private AccountDao accountDao;
	
	
	public AccountService() {
		accountDao = new AccountDao();
	}
	/**
	 * @author 김보령
	 * @param customer
	 * @param accType
	 * @param balance
	 * @param interestRate
	 * @param overdraftAmount
	 * @return
	 */
	public Account addAccount(Customer customer, char accType, double balance, double interestRate, double overdraftAmount) {
		String accNumber = AccountNumGenerator.generateAccountNum();
		Account acc = null;
		
		if (accType == 'S') {
			acc = new SavingsAccount(customer, accNumber, balance, interestRate);
			accountDao.addAccount(acc);
		} else {
			acc = new CheckingAccount(customer, accNumber, balance, overdraftAmount);
			accountDao.addAccount(acc);
		}
		
		return acc;
	}
	
	/**
	 * @author 성지원
	 * @param accNum
	 * @return
	 */
	public Account findAccount(String accNum) {
		return accountDao.findAccountByAccNumber(accNum); 
	}
	
	/**
	 * @author 성지원
	 * @param accNum
	 * @param amount
	 * @return
	 */
	public Account depositAccount(String accNum, double amount) {

		Account acc = findAccount(accNum); // 객체.			
		
		acc.deposite(amount);
		if(acc instanceof SavingsAccount) {
			SavingsAccount sa = (SavingsAccount)acc;
			sa.accumulateInterest();
			
			acc = sa;	
		}
		accountDao.updateAccountDao(acc);  //dao: db에접속.
		
		return acc;
	}
	
	/**
	 * 
	 * @author 성지원
	 * @param accNum
	 * @param amount
	 * @return
	 * @throws OverdraftException
	 * @throws InsufficientBalanceException
	 * @throws NegativeAmountException 
	 */
	public Account withdrawAccount(String accNum, double amount) throws OverdraftException, InsufficientBalanceException, NegativeAmountException {
		Account acc = findAccount(accNum); // 객체.		
		
		if(acc instanceof CheckingAccount) {
			CheckingAccount ca = (CheckingAccount)acc;
			ca.withdraw(amount);
			
			acc = ca;
		}
		else {
			acc.withdraw(amount);
		}
		
		accountDao.updateAccountDao(acc);
		
		return acc;
	}
	
	/**
	 * 로그인id로 해당 고객이 가지고 있는 계좌 리스트를 가지고 온다.
	 * @return
	 * @author 윤효심
	 */
	public List<Account> getAccounts(String userId){
		List<Account> accounts = accountDao.getAccountsByUserId(userId);
		if(!accounts.isEmpty()) {
			return accounts;
		}
		else {
			//계좌가 없으면 빈 계좌 생성해줘서 0으로 뜨게 한다.
			accounts.add(new Account());
			return accounts;
		}
	}
	
	public Account getAccount(String accNumber) {
		return accountDao.findAccountByAccNumber(accNumber);
	}
	
	/**
	 * 계좌 삭제
	 * @author 윤효심
	 */
	public void removeAccount(String accNumber) {
		if(accNumber!=null && accountDao.removeAccount(accNumber)) {
			
		}else {
			throw new RuntimeException("Account is null");
		}
		
	}
}
