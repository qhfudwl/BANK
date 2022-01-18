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
	 * ∞Ì∞¥ √ﬂ∞°
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
	 * ¿∫«‡¿Ã ∫∏¿Ø¡ﬂ¿Œ ∞Ì∞¥ºˆ »πµÊ
	 * @return
	 */
	public static int getNumOfCustomers() {
		return customers.size();
	}
	
	/**
	 * ¿∫«‡ø° µÓ∑œµ» ∞Ì∞¥ 1∏Ì π›»Ø
	 * @param customerIndex
	 * @return
	 */
	public static Customer getCustomer(int customerIndex) {
		return customers.get(customerIndex);
	}
	
	/**
	 * ¿∫«‡ ¡§∫∏ »πµÊ / º≥¡§ 
	 * @return @param
	 */
	
	public static List<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(List<Customer> customers) {
		Bank.customers = customers;
	}
	
}
