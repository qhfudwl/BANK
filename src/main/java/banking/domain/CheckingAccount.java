package banking.domain;

import java.util.Date;

import banking.exception.NegativeAmountException;
import banking.exception.OverdraftException;

public class CheckingAccount extends Account {

	private double overdraftAmount;
	
	public CheckingAccount() {
		
	}

	public CheckingAccount(Customer customer, String accNumber, double initBalance, double overdraftAmount) {
		super(customer, accNumber, initBalance);
		super.accType = 'C';
		this.overdraftAmount = overdraftAmount;
	}
	
	public CheckingAccount(long id, Customer customer, String accNumber, double initBalance, double overdraftAmount, Date regDate) {
		super(id, customer, accNumber, initBalance, regDate);
		super.accType = 'C';
		this.overdraftAmount = overdraftAmount;
	}
	
	public CheckingAccount(double overDraftAmount) {
		super();
		super.accType = 'C';
		this.overdraftAmount = overDraftAmount;
	}
	
	/**
	 * 당좌 계좌 인출
	 * @param amount
	 */
	@Override
	public void withdraw(double amount) throws OverdraftException, NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException("금액을 잘못입력하셨습니다.");
		}else if(balance<amount) { 
			double overdraftNeeded = amount - balance; 
			if(overdraftAmount < overdraftNeeded ) { 
				throw new OverdraftException("에러 : 대월금 초과", overdraftAmount);
			}else {
				balance=0.0; 
				overdraftAmount-=overdraftNeeded; 
				System.out.println("[Overdraft] "+overdraftNeeded+"원 출금되었습니다.");
			}
		}else { //만약에 잔고가 있으면.
			balance=balance-amount;
			System.out.println("[CheckingAcct] "+amount+"원 출금되었습니다.");
		}
	}
	
	/**
	 * 당좌 계좌 정보
	 * @return @param
	 */
	@Override
	public String toString() {
		return super.toString() + ", overdraftAmount=" + overdraftAmount + "]";
	}
	
	/**
	 * 당좌 계좌 정보 획득 / 설정 
	 * @return @param
	 */
	
	public double getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(double overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}
	
	
	
}
