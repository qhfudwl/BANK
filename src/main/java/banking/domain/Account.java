package banking.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


import banking.exception.InsufficientBalanceException;
import banking.exception.NegativeAmountException;
import banking.exception.OverdraftException;

public class Account {
	protected Customer customer;
	
	protected double balance;
	protected String accNumber;
	protected char accType;
	
	protected long id;
	protected Date regDate;

	public Account() {
		
	}
	
	public Account(Customer customer, String accNumber, double initBalance) {
		this.customer = customer;
		this.accNumber = accNumber;
		this.balance = initBalance;
	}
	
	public Account(long id, Customer customer, String accNumber, double initBalance, Date regDate) {
		this.id = id;
		this.customer = customer;
		this.accNumber = accNumber;
		this.balance = initBalance;
		this.regDate = regDate;
	}
	
	/**
	 * 입금하기
	 * @param amount
	 */
	public void deposite(double amount) {
		this.balance += amount;
		System.out.println("[Acct] "+amount+"원 입금되었습니다.");
	}
	
	/**
	 * 출금하기
	 * @param amount
	 */
	public void withdraw (double amount) throws InsufficientBalanceException, OverdraftException, NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException("금액을 잘못입력하셨습니다.");
		}else if (this.balance-amount < 0) {
			throw new InsufficientBalanceException("잔액 부족");
		} else {
			this.balance -= amount;
			System.out.println("[Acct] "+amount+"원 출금되었습니다.");
		}
	}
	
	
	/**
	 * 계좌 정보 획득 / 설정 
	 * @return @param
	 */
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumer) {
		this.accNumber = accNumer;
	}

	public char getAccType() {
		return accType;
	}

	public void setAccType(char accType) {
		this.accType = accType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accNumber=" + accNumber + ", accType=" + accType + ", regDate="
				+ regDate + "]";
	}
	
}
