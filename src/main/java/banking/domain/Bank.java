package banking.domain;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private static List<Customer> customers;

	static {
		customers = new ArrayList<Customer>();
	}
	
	public Bank() {
		
	}
	
	/**
	 * �� �߰�
	 * @param userId
	 * @param passwd
	 * @param userName
	 * @param ssn
	 * @param phone
	 * @param email
	 * @param addr
	 */
	public static void addCustomer(String userId,String passwd,String userName,String ssn,String phone,String email,String addr) {
		customers.add(new Customer(userId, passwd, userName, ssn, phone, email, addr));
	}
	
	/**
	 * ������ �������� ���� ȹ��
	 * @return
	 */
	public static int getNumOfCustomers() {
		return customers.size();
	}
	
	/**
	 * ���࿡ ��ϵ� �� 1�� ��ȯ
	 * @param customerIndex
	 * @return
	 */
	public static Customer getCustomer(int customerIndex) {
		return customers.get(customerIndex);
	}
	
	/**
	 * ���� ���� ȹ�� / ���� 
	 * @return @param
	 */
	
	public static List<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(List<Customer> customers) {
		Bank.customers = customers;
	}
	
}
