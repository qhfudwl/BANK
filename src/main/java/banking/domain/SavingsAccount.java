package banking.domain;

import java.util.Date;

/**
 * �Ϲ� �������
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
	 * ���������
	 */
	public void accumulateInterest() {
		balance = balance+ (balance * interestRate);
	}
	
	/**
	 * ���� ���� ���� 
	 * @return 
	 */
	@Override
	public String toString() { 
		return super.toString() + ", interestRate=" + interestRate + "]";
	}
	
	/**
	 * ���� ���� ���� ȹ�� / ���� 
	 * @return @param
	 */
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


}
