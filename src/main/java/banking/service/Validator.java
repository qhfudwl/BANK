package banking.service;

public class Validator {
	

	/**
	 * 들어온 객체가 비어있는가?
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
	 * 해당 문자열이 숫자인가?
	 * @param str
	 * @return
	 */
	public boolean isNumber(String str) {
		String[] strArr = str.split("");
		for(String s : strArr) {
			int num = s.charAt(0);
			if (num < 48 || num > 57) { // ASCII 코드 0 - 9
				return false; // 하나라도 숫자가 아니면 거짓
			}
		}
		return true; // 모두 숫자면 참
	}
}
