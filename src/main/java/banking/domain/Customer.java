package banking.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	
	private String userId;
	private String passwd;
	private String userName;
	private String ssn;
	private String phone;
	private String email;
	private String addr;
	
	private long id;
	private Date regDate;
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public Customer() {
	}
	
	public Customer(long customerId) {
		this.id = customerId;
	}
	
	public Customer(String userId,String passwd,String userName,String ssn,String phone,String email,String addr) {
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.ssn = ssn;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
	}
	
	public Customer(long id, String userId,String passwd,String userName,String ssn,String phone,String email,String addr,Date regDate) {
		this.id = id;
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.ssn = ssn;
		this.phone = phone;
		this.email = email;
		this.addr = addr;
		this.regDate = regDate;
	}
	
	
	/**
	 * °í°´ °èÁÂ ¸®½ºÆ®¿¡ Ãß°¡
	 * @param acct
	 */
	public void addAcount(Account acct) {
		this.accounts.add(acct);
	}
	
	/**
	 * ÇØ´ç °í°´ n¹ø °èÁÂ
	 * @return 
	 */
	public Account getAccount(int accountIndex) {
		return accounts.get(accountIndex);
	}
	
	/**
	 * ÇöÀç±îÁö ÃÑ °èÁÂ °³¼ö
	 * @return
	 */
	public int getNumOfAccounts() {
		return accounts.size();
	}
	
	/**
	 * °í°´ Á¤º¸ 
	 * @return 
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id +
				", userId=" + userId +
				", passwd=" + passwd + 
				", userName="+ userName +
				", ssn=" + ssn + 
				", phone=" + phone + 
				", email=" + email + 
				", address=" + addr + 
				", regDate=" + regDate+ 
				"]";
	}

	/**
	 * °í°´ Á¤º¸ È¹µæ / ¼³Á¤ 
	 * @return @param
	 */
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
