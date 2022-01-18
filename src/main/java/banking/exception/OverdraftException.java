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
		return super.getMessage()+", 사용 가능한 대월액은 "+overdraftAvail+"원 입니다.";
	}
}
