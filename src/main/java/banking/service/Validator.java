package banking.service;

public class Validator {
	

	/**
	 * ���� ��ü�� ����ִ°�?
	 * @param obj
	 * @return
	 */
	public boolean isEmpty(Object obj) {
		if (obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * �ش� ���ڿ��� �����ΰ�?
	 * @param str
	 * @return
	 */
	public boolean isNumber(String str) {
		String[] strArr = str.split("");
		for(String s : strArr) {
			int num = s.charAt(0);
			if (num < 48 || num > 57) { // ASCII �ڵ� 0 - 9
				return false; // �ϳ��� ���ڰ� �ƴϸ� ����
			}
		}
		return true; // ��� ���ڸ� ��
	}
}
