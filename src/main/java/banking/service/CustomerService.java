package banking.service;


import banking.dao.CustomerDao;
import banking.domain.Customer;

public class CustomerService {
	
	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * �� �߰�
	 * @param userId
	 * @param passwd
	 * @param userName
	 * @param ssn
	 * @param phone
	 * @param email
	 * @param addr
	 * @return
	 */
	public Customer addCustomer(String userId, String passwd, String userName, String ssn, String phone, String email, String addr) {
		Customer customer = new Customer(userId,passwd,userName,ssn,phone,email,addr);
		return customer;
	}
	
	/**
	 * DB�� �� �߰�
	 * @param customer
	 * @return
	 */
	public Customer addCustomer(Customer customer) {
		if(customer == null) {
			throw new RuntimeException("Customer is null");
		}
		customerDao.addCustomer(customer);
		
		return customer;
	}
	
	/**
	 * �̹� �����ϴ� ���̵����� üũ
	 * @author ������
	 */
	public boolean isUserbyUserId(String userId) {
		return customerDao.isUserbyUserId(userId);
	}
	
	
	/**
	 * DB�� �޴���ȭ ������Ʈ
	 * @author ��ȿ��
	 */
	public Customer updatePhoneNumber(String userId, String phoneNumber) {
		Customer customer = new Customer();
		customer.setUserId(userId);
		customer.setPhone(phoneNumber);
		return customer;	

	}
	public Customer updatePhoneNumber(Customer customer) {
		Customer cust = customerDao.updatePhoneNumber(customer);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
		
	}
	/**
	 * DB�� ��й�ȣ ������Ʈ
	 * @author ��ȿ��
	 */
	public Customer updatePassword(String userId, String password) {
		Customer customer = new Customer();
		customer.setUserId(userId);
		customer.setPasswd(password);
		return customer;	
	}
	public Customer updatePassword(Customer customer) {
		Customer cust = customerDao.updatePassword(customer);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
		
	}

	/**
	 * DB�� �̸��� ������Ʈ
	 * @author ��ȿ��
	 */
	public Customer updateEmail(String userId, String email) {
		Customer customer = new Customer();
		customer.setUserId(userId);
		customer.setEmail(email);
		return customer;	
	}
	public Customer updateEmail(Customer customer) {
		Customer cust = customerDao.updateEmail(customer);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
		
	}

	/**
	 * DB�� �ּ� ������Ʈ
	 * @author ��ȿ��
	 */
	public Customer updateAddr(String userId, String addr) {
		Customer customer = new Customer();
		customer.setUserId(userId);
		customer.setAddr(addr);
		return customer;	
	}
	public Customer updateAddr(Customer customer) {
		Customer cust = customerDao.updateAddr(customer);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
		
	}
	
	/**
	 * DB�� �α����� id�� ������ ã��
	 * @author ��ȿ��
	 */
	public Customer getCustomer(String userId) {
		Customer cust = customerDao.getCustomer(userId);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
	}
	
	/**
	 * �α��� ���̵�� �������� ã��
	 * @author ������
	 */
	public Customer findUserbyUserId(String userId) {
		return customerDao.findUserbyUserId(userId);
	}
	
	/**
	 * ������
	 * @author ��ȿ��
	 */
	
	public void removeCustomer(String userId) {
		if(userId!=null && customerDao.removeCustomer(userId)) {
			
		}
		else {
			throw new RuntimeException("Customer is null");
		}
	}
	/**
	 * �߰�
	 * @author �躸��
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(long id) {
		return customerDao.findCustomerById(id);
	}
	
	
}
