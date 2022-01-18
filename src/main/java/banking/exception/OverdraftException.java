package banking.exception;

@SuppressWarnings("serial")
public class OverdraftException extends Exception{
	private double overdraftAvail;
	public OverdraftException(String msg, double overdraftAvail) {
		super(msg);
		this.overdraftAvail = overdraftAvail;
	}
	@Override
	public String getMessage() {
		return super.getMessage()+", ��� ������ ������� "+overdraftAvail+"�� �Դϴ�.";
	}
}
