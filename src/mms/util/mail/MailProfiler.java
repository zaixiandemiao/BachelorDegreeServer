package mms.util.mail;

public class MailProfiler {
	
	/**
	 * 以下均为非SSL协议端口
	 */
	private static String IMAP_PORT = "143";
	private static String POP_PORT = "110";
	private static String SMTP_PORT = "465";
	
	
	private static String CSU_IMAP_SERVER = "imap.csu.edu.cn";
	private static String WANGYI_IMAP_126_SERVER = "imap.126.com";
	private static String WANGYI_IMAP_163_SERVER = "imap.163.com";
	
	private static String CSU_POP_SERVER = "pop.csu.edu.cn";
	private static String WANGYI_POP_126_SERVER = "pop.126.com";
	private static String WANGYI_POP_163_SERVER = "pop.163.com";
	
	private static String CSU_SMTP_SERVER = "smtp.csu.edu.cn";
	private static String WANGYI_SMTP_126_SERVER = "smtp.126.com";
	private static String WANGYI_SMTP_163_SERVER = "smtp.163.com";
	
	public static String getHost(String protocol, String username) {
		String host = "";
		String strAft_a = username.substring(username.indexOf("@"));
		
		switch(protocol.toUpperCase()) {
		
		case "IMAP": {
			if(strAft_a.indexOf("csu.edu.cn") > 0) 
				host = CSU_IMAP_SERVER;
			else if(strAft_a.indexOf("126.com") > 0)
				host = WANGYI_IMAP_126_SERVER;
			else if(strAft_a.indexOf("163.com") > 0) 
				host = WANGYI_IMAP_163_SERVER;
			break;
		}
		case "POP": 
		case "POP3": {
			if(strAft_a.indexOf("csu.edu.cn") > 0) 
				host = CSU_POP_SERVER;
			else if(strAft_a.indexOf("126.com") > 0)
				host = WANGYI_POP_126_SERVER;
			else if(strAft_a.indexOf("163.com") > 0) 
				host = WANGYI_POP_163_SERVER;
			break;
		}
		case "SMTP": {
			if(strAft_a.indexOf("csu.edu.cn") > 0) 
				host = CSU_SMTP_SERVER;
			else if(strAft_a.indexOf("126.com") > 0)
				host = WANGYI_SMTP_126_SERVER;
			else if(strAft_a.indexOf("163.com") > 0) 
				host = WANGYI_SMTP_163_SERVER;
			break;
		}
		
		}
		
		return host;
	}
	
	
	
	

}
