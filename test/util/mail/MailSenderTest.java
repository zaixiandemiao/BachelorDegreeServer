package util.mail;

import org.junit.Test;

import mms.util.mail.MailSender;

public class MailSenderTest {
	
	@Test
	public void testSendSimpleMessage() {
		
		MailSender sender = new MailSender();
//		sender.setFrom("0909121208@csu.edu.cn");
//		sender.setSenderByProp("smtp.csu.edu.cn", "0909121208@csu.edu.cn", "093004");
		
		sender.setHost("smtp.csu.edu.cn");
		try {
			sender.sendTextEmail("0909121208@csu.edu.cn", "093004", "wangjialong093004@126.com", "测试邮件", "https://www.baidu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		sender.sendHtmlMessage("wangjialong093004@126.com", "测试邮件", "https://www.baidu.com");
	}
	
}
