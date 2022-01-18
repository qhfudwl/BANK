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
	 * �ŷ� ���� �޾ƿ���
	 * @param accNumber
	 * @return
	 */
	public List<Map<String, String>> getAccRecordList(String accNumber){
		return recordDao.findAllRecordByAccNumber(accNumber);
	}
	
	/**
	 * ��ü �� �ŷ� ���� record ���̺� ����
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
				record.put("content", "�� ���� �Ա�");
			} else if (transType.equals("W")) {
				record.put("content", "�� ���� ���");
			}
		}
		
		recordDao.writeRecordOfAccId(accId, record);
	}
	
	/**
	 * �Ա� / ��� �� �ŷ� ���� record ���̺� ����
	 * @param accNumber
	 * @param transType
	 * @param amount
	 * @param balance
	 */
	public void writeRecord(String accNumber, String transType, double amount, double balance) {
		writeRecord(accNumber, transType, amount, balance, null);
	}
	
}
