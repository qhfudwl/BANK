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
	 * ���� ���� ����
	 * @param amount
	 */
	@Override
	public void withdraw(double amount) throws OverdraftException, NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException("�ݾ��� �߸��Է��ϼ̽��ϴ�.");
		}else if(balance<amount) { 
			double overdraftNeeded = amount - balance; 
			if(overdraftAmount < overdraftNeeded ) { 
				throw new OverdraftException("���� : ����� �ʰ�", overdraftAmount);
			}else {
				balance=0.0; 
				overdraftAmount-=overdraftNeeded; 
				System.out.println("[Overdraft] "+overdraftNeeded+"�� ��ݵǾ����ϴ�.");
			}
		}else { //���࿡ �ܰ� ������.
			balance=balance-amount;
			System.out.println("[CheckingAcct] "+amount+"�� ��ݵǾ����ϴ�.");
		}
	}
	
	/**
	 * ���� ���� ����
	 * @return @param
	 */
	@Override
	public String toString() {
		return super.toString() + ", overdraftAmount=" + overdraftAmount + "]";
	}
	
	/**
	 * ���� ���� ���� ȹ�� / ���� 
	 * @return @param
	 */
	
	public double getOverdraftAmount() {
		return overdraftAmount;
	}

	public void setOverdraftAmount(double overdraftAmount) {
		this.overdraftAmount = overdraftAmount;
	}
	
	
	
}
