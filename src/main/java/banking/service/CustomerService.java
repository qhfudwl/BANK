package banking.service;


import banking.dao.CustomerDao;
import banking.domain.Customer;

public class CustomerService {
	
	private CustomerDao customerDao = new CustomerDao();
	
	/**
	 * 고객 추가
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
	 * DB에 고객 추가
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
	 * 이미 존재하는 아이디인지 체크
	 * @author 정원식
	 */
	public boolean isUserbyUserId(String userId) {
		return customerDao.isUserbyUserId(userId);
	}
	
	
	/**
	 * DB에 휴대전화 업데이트
	 * @author 윤효심
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
	 * DB에 비밀번호 업데이트
	 * @author 윤효심
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
	 * DB에 이메일 업데이트
	 * @author 윤효심
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
	 * DB에 주소 업데이트
	 * @author 윤효심
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
	 * DB에 로그인한 id로 고객정보 찾기
	 * @author 윤효심
	 */
	public Customer getCustomer(String userId) {
		Customer cust = customerDao.getCustomer(userId);
		if(cust == null) {
			throw new RuntimeException("Customer is null");
		}
		return cust;
	}
	
	/**
	 * 로그인 아이디로 유저정보 찾기
	 * @author 정원식
	 */
	public Customer findUserbyUserId(String userId) {
		return customerDao.findUserbyUserId(userId);
	}
	
	/**
	 * 고객삭제
	 * @author 윤효심
	 */
	
	public void removeCustomer(String userId) {
		if(userId!=null && customerDao.removeCustomer(userId)) {
			
		}
		else {
			throw new RuntimeException("Customer is null");
		}
	}
	/**
	 * 추가
	 * @author 김보령
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(long id) {
		return customerDao.findCustomerById(id);
	}
	
	
}
