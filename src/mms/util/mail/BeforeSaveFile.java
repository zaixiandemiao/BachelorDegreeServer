package mms.util.mail;

public interface BeforeSaveFile {
	
	public void doBefore(String fileName) throws NotValideException;

}
