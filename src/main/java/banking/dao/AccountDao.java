package banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import banking.db.DataSource;
import banking.domain.Account;
import banking.domain.CheckingAccount;
import banking.domain.Customer;
import banking.domain.SavingsAccount;

public class AccountDao {
	private DataSource ds;

	public AccountDao() {
		ds = DataSource.getInstance();
	}

	/**
	 * ½Å±Ô °èÁÂ µî·Ï
	 * 
	 * @param account
	 */
	public void addAccount(Account account) {
		String sql = "INSERT INTO " + "Account(customerId, accType, accNumber, balance, interestRate, overDraft) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setLong(1, account.getCustomer().getId());
			pstmt.setString(2, String.valueOf(account.getAccType()));
			pstmt.setString(3, account.getAccNumber());
			pstmt.setDouble(4, account.getBalance());

			if (account instanceof SavingsAccount) {
				SavingsAccount sa = (SavingsAccount) account;
				pstmt.setDouble(5, sa.getInterestRate());
				pstmt.setDouble(6, 0.0);

			} else {
				CheckingAccount ca = (CheckingAccount) account;
				pstmt.setDouble(5, 0.0);
				pstmt.setDouble(6, ca.getOverdraftAmount());

			}

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * °èÁÂ¹øÈ£·Î account Ã£±â
	 * 
	 * @param accNumber
	 * @return
	 */
	public Account findAccountByAccNumber(String accNumber) {
		Account account = null;
		String sql = "SELECT * FROM Account WHERE accNumber=?";
		ResultSet rs = null;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, accNumber);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				long id = rs.getLong("id");
				char accType = rs.getString("accType").charAt(0);
				double balance = rs.getDouble("balance");
				double interestRate = rs.getDouble("interestRate");
				double overDraft = rs.getDouble("overDraft");
				Date regDate = new Date(rs.getTimestamp("regDate").getTime());
				long customerId = rs.getLong("customerId");
				if (accType == 'S') {
					account = new SavingsAccount(interestRate);
				} else {
					account = new CheckingAccount(overDraft);
				}
				account.setId(id);
				account.setAccNumber(accNumber);
				account.setAccType(accType);
				account.setBalance(balance);
				account.setRegDate(regDate);
				
				Customer customer = new Customer();
				customer.setId(customerId);
				account.setCustomer(customer);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	/**
	 * @author ¼ºÁö¿ø
	 * @param account
	 */
	public void updateAccountDao(Account account) {

		String sql = "UPDATE Account SET balance = ?, overDraft = ? WHERE id = ?";
		try (Connection con = ds.getConnection();

				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setDouble(1, account.getBalance());
			if (account instanceof CheckingAccount) {
				CheckingAccount ca = (CheckingAccount) account;
				pstmt.setDouble(2, ca.getOverdraftAmount());
			} else {
				pstmt.setDouble(2, 0);
			}
			pstmt.setLong(3, account.getId());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author À±È¿½É userId·Î °í°´ °èÁÂ¸®½ºÆ® ¹ÝÈ¯ÇÏ±â
	 * @return
	 */
	public List<Account> getAccountsByUserId(String userId) {

		String sql = "SELECT * FROM Account " + "INNER JOIN Customer " + "ON Customer.userId=? "
				+ "WHERE Account.customerId = Customer.id";

		List<Account> accounts = new ArrayList<>();
		ResultSet rs = null;
		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				long id = rs.getLong("id");
				long customerId = rs.getLong("customerId");
				char accType = rs.getString("accType").charAt(0);
				String accNumber = rs.getString("accNumber");
				double balance = rs.getDouble("balance");
				Date regDate = new Date(rs.getDate("regDate").getTime());

				if (accType == 'S') {
					double interestRate = rs.getDouble("interestRate");
					accounts.add(new SavingsAccount(id, new Customer(customerId), accNumber, balance, interestRate,
							regDate));
				} else {
					double overDraft = rs.getDouble("overDraft");
					accounts.add(
							new CheckingAccount(id, new Customer(customerId), accNumber, balance, overDraft, regDate));
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}

	/**
	 * @author ±èº¸·É
	 * @param userId
	 * @return
	 */
	public List<Account> findAllAccountByUserId(String userId) {
		List<Account> accList = new ArrayList<>();
		String sql = "SELECT c.id, a.accType, a.accNumber, a.balance " + "FROM Customer c, Account a "
				+ "WHERE c.userId=? AND a.customerId=c.id";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			try {
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Account acc = null;
					char accType = rs.getString("accType").charAt(0);
					String accNumber = rs.getString("accNumber");
					double balance = rs.getDouble("balance");

					if (accType == 'S') {
						acc = new SavingsAccount();
					} else {
						acc = new CheckingAccount();
					}

					acc.setAccType(accType);
					acc.setAccNumber(accNumber);
					acc.setBalance(balance);
					accList.add(acc);
				}
			} finally {
				ds.close(rs, pstmt, con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accList;
	}
	
	/**
	 * °èÁÂ »èÁ¦
	 * @author À±È¿½É
	 * @param accNumber
	 */
	public boolean removeAccount(String accNumber) {
		String sql="DELETE FROM Account WHERE accNumber=?";
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, accNumber);
			if(pstmt.executeUpdate()!=0) {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
