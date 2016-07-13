package mms.util.mail;

public class NotValideException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValideException() {
		super("邮件没有通过验证");
	}
	
	public NotValideException(String msg) {
		super(msg);
	}

}
