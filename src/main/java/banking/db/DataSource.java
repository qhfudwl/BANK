package banking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton ΩÃ±€≈Ê ∆–≈œ 
 * @author Owner
 *
 */
public class DataSource {
	private static DataSource ds = new DataSource();
	
	private String user;
	private String pass;
	private String url;
	
	private DataSource() {
		
	}
	public static DataSource getInstance() {
		return ds;
	}
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}		
	}
	public void initDataSource(String driver, String user, String pass, String url) {
		try {
			Class.forName(driver);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.url= url;
		this.user = user;
		this.pass = pass;
	}
	public void close(ResultSet rs,PreparedStatement pstmt, Connection con) throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		if(con!=null) {
			con.close();
		}
	}
	public void close(PreparedStatement pstmt, Connection con) throws SQLException{
		if(pstmt!=null) {
			pstmt.close();
		}
		if(con!=null) {
			con.close();
		}
	}
}
