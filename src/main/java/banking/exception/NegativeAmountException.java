package banking.exception;

public class NegativeAmountException extends Exception {
	public NegativeAmountException(String msg) {
		super(msg);
	}
}
