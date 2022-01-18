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
	 * 추가
	 * @author 김보령
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
	 * 신규 고객 등록
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
	 * 고객 휴대전화 업데이트
	 * 
	 * @author 윤효심
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
	 * 고객 비밀번호 업데이트
	 * 
	 * @author 윤효심
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
	 * 고객 이메일 업데이트
	 * 
	 * @author 윤효심
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
	 * 고객 주소 업데이트
	 * 
	 * @author 윤효심
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
	 * 고객 판별
	 * @author 윤효심
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
	 * 고객 정보얻기
	 * @author 윤효심
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
	 * 고객 정보얻기
	 * @author 정원식
	 * @param userId
	 * @return
	 */
	public Customer findUserbyUserId(String userId) {
		Customer customer = null;
		String sql = "SELECT * FROM Customer WHERE userId = ?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, userId); //
			ResultSet rs = pstmt.executeQuery(); //이 쿼리문 실행 
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
	 * 아이디에 해당 하는 유저 존재하는지 체크
	 * @author 정원식
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
	 * 고객 삭제
	 * @author 윤효심
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
