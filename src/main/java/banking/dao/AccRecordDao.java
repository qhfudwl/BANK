package banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banking.db.DataSource;
import banking.service.Converter;

public class AccRecordDao {
	private DataSource ds;
	
	public AccRecordDao() {
		ds = DataSource.getInstance();
	}
	
	/**
     * 특정 계좌의 모든 거래내역 가져오기
     * @param accNumber
     * @return
     */
    public List<Map<String, String>> findAllRecordByAccNumber(String accNumber){
        List<Map<String, String>> recordList = new ArrayList<>(); // 내역 리스트

        String sql="SELECT a.id, r.transType, r.amount, r.balance, r.content, r.regDate "
                + "FROM Account a, AccRecord r "
                + "WHERE a.accNumber=? AND r.accId=a.id ORDER BY r.regDate DESC";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            try {
                pstmt.setString(1, accNumber);
                rs = pstmt.executeQuery();
                while(rs.next()) {
                    String transType = rs.getString("transType");
                    String amount = Double.toString(rs.getDouble("amount"));
                    String balance = Double.toString(rs.getDouble("balance"));
                    String content = rs.getString("content");
                    Date timeStamp = new Date(rs.getTimestamp("regDate").getTime());
                    Converter cvt = new Converter();
                    String regDate = cvt.matchDateToFormat(timeStamp);
                    Map<String, String> record = new HashMap<String, String>(); // 각 내역
                    record.put("transType", transType);
                    record.put("amount", amount);
                    record.put("balance", balance);
                    record.put("content", content);
                    record.put("regDate", regDate);

                    recordList.add(record);
                }
            } finally {
                ds.close(rs, pstmt, con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recordList;
    }
	/**
	 * DB에 거래 내역 기록
	 * @param accId
	 * @param record
	 */
	public void writeRecordOfAccId(long accId, Map<String, String> record) {
		String sql = "INSERT INTO "
				+ "AccRecord(accId, transType, amount, balance, content) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		
		try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){

			double amount = Double.parseDouble((String)record.get("amount"));
			double balance = Double.parseDouble((String)record.get("balance"));
			
			pstmt.setLong(1, accId);
			pstmt.setString(2, (String)record.get("transType"));
			pstmt.setDouble(3, amount);
			pstmt.setDouble(4, balance);
			pstmt.setString(5, (String)record.get("content"));
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}





































