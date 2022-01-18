package banking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import banking.dao.AccRecordDao;
import banking.dao.AccountDao;

public class AccRecordService {
	
	private AccRecordDao recordDao;
	
	public AccRecordService() {
		recordDao = new AccRecordDao();
	}
	
	/**
	 * 거래 내역 받아오기
	 * @param accNumber
	 * @return
	 */
	public List<Map<String, String>> getAccRecordList(String accNumber){
		return recordDao.findAllRecordByAccNumber(accNumber);
	}
	
	/**
	 * 이체 후 거래 내용 record 테이블에 쓰기
	 * @param accNumber
	 * @param transType
	 * @param amount
	 * @param balance
	 * @param content
	 */
	public void writeRecord(String accNumber, String transType, double amount, double balance, String content) {
		AccountDao accDao = new AccountDao();
		long accId = accDao.findAccountByAccNumber(accNumber).getId();
		
		
		Map<String, String> record = new HashMap<String, String>();
		
		if (transType != null) {
			record.put("transType", transType);
		}
		if (amount >= 0) {
			record.put("amount", Double.toString(amount));
		}else {
			System.out.println("-Amount Error");
		}
		
		if (balance >= 0) {
			record.put("balance", Double.toString(balance));
		}else {
			System.out.println("-Balance Error");
		}
		if (content != null) {
			record.put("content", content);
		} else {
			if (transType.equals("D")) {
				record.put("content", "내 통장 입금");
			} else if (transType.equals("W")) {
				record.put("content", "내 통장 출금");
			}
		}
		
		recordDao.writeRecordOfAccId(accId, record);
	}
	
	/**
	 * 입금 / 출금 후 거래 내용 record 테이블에 쓰기
	 * @param accNumber
	 * @param transType
	 * @param amount
	 * @param balance
	 */
	public void writeRecord(String accNumber, String transType, double amount, double balance) {
		writeRecord(accNumber, transType, amount, balance, null);
	}
	
}
