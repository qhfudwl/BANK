package banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import banking.db.DataSource;
import banking.domain.Customer;

public class CustomerDao {
	private DataSource ds;
	
	public CustomerDao() {
		ds = DataSource.getInstance();
	}
	/**
	 * 
	 * �߰�
	 * @author �躸��
	 * @param id
	 * @return
	 */
	public Customer findCustomerById(long id) {
		String sql = "SELECT * FROM Customer WHERE id=?";
		Customer cust=null;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String userId = rs.getString("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String addr = rs.getString("addr");
				Date regDate = new Date(rs.getDate("regDate").getTime());
				
				cust = new Customer(id,userId,passwd,userName,ssn,phone,email,addr,regDate);
			}
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cust;
	}
	
	/**
	 * �ű� �� ���
	 * @param customer
	 */
	public void addCustomer(Customer customer) {
		String sql="INSERT INTO Customer("
				+ "userId,passwd,userName,ssn,phone,email,addr)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pstmt.setString(1, customer.getUserId());
			pstmt.setString(2, customer.getPasswd());
			pstmt.setString(3, customer.getUserName());
			pstmt.setString(4, customer.getSsn());
			pstmt.setString(5, customer.getPhone());
			pstmt.setString(6, customer.getEmail());
			pstmt.setString(7, customer.getAddr());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �� �޴���ȭ ������Ʈ
	 * 
	 * @author ��ȿ��
	 */
	public Customer updatePhoneNumber(Customer customer) {
		
		String sql="UPDATE Customer SET phone=? WHERE userId=?";
		Customer cust = customer;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, cust.getPhone());
			pstmt.setString(2, cust.getUserId());
			if(pstmt.executeUpdate() == 0 ) {
				cust = null;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cust;
		
		
	}
	/**
	 * �� ��й�ȣ ������Ʈ
	 * 
	 * @author ��ȿ��
	 */
	public Customer updatePassword(Customer customer) {
		
		String sql="UPDATE Customer SET passwd=? WHERE userId=?";
		Customer cust = customer;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, cust.getPasswd());
			pstmt.setString(2, cust.getUserId());
			if(pstmt.executeUpdate() == 0 ) {
				cust = null;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cust;
		
		
	}
	/**
	 * �� �̸��� ������Ʈ
	 * 
	 * @author ��ȿ��
	 */
	public Customer updateEmail(Customer customer) {
		
		String sql="UPDATE Customer SET email=? WHERE userId=?";
		Customer cust = customer;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, cust.getEmail());
			pstmt.setString(2, cust.getUserId());
			if(pstmt.executeUpdate() == 0 ) {
				cust = null;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cust;
		
		
	}
	/**
	 * �� �ּ� ������Ʈ
	 * 
	 * @author ��ȿ��
	 */
	public Customer updateAddr(Customer customer) {
		
		String sql="UPDATE Customer SET addr=? WHERE userId=?";
		Customer cust = customer;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, cust.getAddr());
			pstmt.setString(2, cust.getUserId());
			if(pstmt.executeUpdate() == 0 ) {
				cust = null;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cust;
		
		
	}
	/**
	 * �� �Ǻ�
	 * @author ��ȿ��
	 */
	public boolean isUser(String userId) {
		String sql = "SELECT id FROM Customer WHERE userId = ?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				rs.close();
				return true;
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * �� �������
	 * @author ��ȿ��
	 */
	public Customer getCustomer(String userId) {
		String sql = "SELECT * FROM Customer WHERE userId = ?";
		Customer cust=null;
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				long id = rs.getLong("id");
				String userId2 = rs.getString("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String addr = rs.getString("addr");
				Date regDate = new Date(rs.getDate("regDate").getTime());
				
				cust = new Customer(id,userId2,passwd,userName,ssn,phone,email,addr,regDate);

			}
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cust;
	}
	

	/**
	 * �� �������
	 * @author ������
	 * @param userId
	 * @return
	 */
	public Customer findUserbyUserId(String userId) {
		Customer customer = null;
		String sql = "SELECT * FROM Customer WHERE userId = ?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId); //
			ResultSet rs = pstmt.executeQuery(); //�� ������ ���� 
			while(rs.next()) {
				long id = rs.getLong("id");
				String userId2 = rs.getString("userId");
				String passwd = rs.getString("passwd");
				String userName = rs.getString("userName");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String addr = rs.getString("addr");
				Date regDate = new Date(rs.getDate("regDate").getTime());
				
				customer = new Customer(id, userId2, passwd, userName, ssn, phone, email, addr, regDate);
				}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	/**
	 * ���̵� �ش� �ϴ� ���� �����ϴ��� üũ
	 * @author ������
	 * @param userId
	 * @return
	 */
	public boolean isUserbyUserId(String userId) {
		String sql ="SELECT * FROM Customer WHERE userId =?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		} return false;
	}
	
	/**
	 * �� ����
	 * @author ��ȿ��
	 */
	public boolean removeCustomer(String userId) {
		String sql="DELETE FROM Customer WHERE userId=?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId);
			if(pstmt.executeUpdate()!=0) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
