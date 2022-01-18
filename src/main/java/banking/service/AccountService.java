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
	 * @author �躸��
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
	 * @author ������
	 * @param accNum
	 * @return
	 */
	public Account findAccount(String accNum) {
		return accountDao.findAccountByAccNumber(accNum); 
	}
	
	/**
	 * @author ������
	 * @param accNum
	 * @param amount
	 * @return
	 */
	public Account depositAccount(String accNum, double amount) {

		Account acc = findAccount(accNum); // ��ü.			
		
		acc.deposite(amount);
		if(acc instanceof SavingsAccount) {
			SavingsAccount sa = (SavingsAccount)acc;
			sa.accumulateInterest();
			
			acc = sa;	
		}
		accountDao.updateAccountDao(acc);  //dao: db������.
		
		return acc;
	}
	
	/**
	 * 
	 * @author ������
	 * @param accNum
	 * @param amount
	 * @return
	 * @throws OverdraftException
	 * @throws InsufficientBalanceException
	 * @throws NegativeAmountException 
	 */
	public Account withdrawAccount(String accNum, double amount) throws OverdraftException, InsufficientBalanceException, NegativeAmountException {
		Account acc = findAccount(accNum); // ��ü.		
		
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
	 * �α���id�� �ش� ���� ������ �ִ� ���� ����Ʈ�� ������ �´�.
	 * @return
	 * @author ��ȿ��
	 */
	public List<Account> getAccounts(String userId){
		List<Account> accounts = accountDao.getAccountsByUserId(userId);
		if(!accounts.isEmpty()) {
			return accounts;
		}
		else {
			//���°� ������ �� ���� �������༭ 0���� �߰� �Ѵ�.
			accounts.add(new Account());
			return accounts;
		}
	}
	
	public Account getAccount(String accNumber) {
		return accountDao.findAccountByAccNumber(accNumber);
	}
	
	/**
	 * ���� ����
	 * @author ��ȿ��
	 */
	public void removeAccount(String accNumber) {
		if(accNumber!=null && accountDao.removeAccount(accNumber)) {
			
		}else {
			throw new RuntimeException("Account is null");
		}
		
	}
}
