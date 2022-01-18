package banking.domain;

import java.util.Date;

/**
 * 일반 저축계좌
 * @author Owner
 *
 */
public class SavingsAccount extends Account {
	private double interestRate;
		
	public SavingsAccount() {
		
	}
	
	public SavingsAccount(Customer customer, String accNumber, double initBalance, double interestRate) {
		super(customer, accNumber, initBalance);
		super.accType = 'S';
		this.interestRate = interestRate;
	}
	
	public SavingsAccount(long id, Customer customer, String accNumber, double initBalance, double interestRate, Date regDate) {
		super(id, customer, accNumber, initBalance, regDate);
		super.accType = 'S';
		this.interestRate = interestRate;
	}
	
	public SavingsAccount(double interestRate) {
		super();
		super.accType = 'S';
		this.interestRate = interestRate;
	}
	
	/**
	 * 이자율계산
	 */
	public void accumulateInterest() {
		balance = balance+ (balance * interestRate);
	}
	
	/**
	 * 저축 계좌 정보 
	 * @return 
	 */
	@Override
	public String toString() { 
		return super.toString() + ", interestRate=" + interestRate + "]";
	}
	
	/**
	 * 저축 계좌 정보 획득 / 설정 
	 * @return @param
	 */
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


}
