package util.mail;

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;

import mms.bean.MailConnectBean;
import mms.util.mail.ImapReceiver;
import mms.util.mail.MsgHandler;

public class ImapReceiverTest {
	
	/**
	 * 测试 matches 函数
	 */
	@Test
	public void testMatches() {
		String subject = "物联网1201班-王嘉龙-0909121208";
		Assert.assertTrue(subject.matches("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]+-[0-9]{10}$"));
		String fileName = "物联网1201班-王嘉龙-0909121208.doc";
		Assert.assertTrue(fileName.matches("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]{2,4}-[0-9]{10}\\.doc$"));
		String fileName1 = "物联网1201班-王嘉龙-0909121208.pdf";
		Assert.assertFalse(fileName1.matches("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]{2,4}-[0-9]{10}\\.doc$"));
		String fileName2 = "物联网1201班-王嘉龙-0909121208.docx";
		Assert.assertTrue(fileName2.matches("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]{2,4}-[0-9]{10}\\.doc[x]*$"));
	}
	
	/**
	 * 测试邮件验证工具类
	 */
	@Test
	public void testReceive() {
		
		MailConnectBean conn = new MailConnectBean();
		conn.setHost("imap.csu.edu.cn");
		conn.setUsername("0909121208@csu.edu.cn");
		conn.setPassword("093004");
		
		MsgHandler handler = new MsgHandler();
		handler.setDestDir("F:\\mailtmp\\");
		handler.setSubjectPattern("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]{2,4}-[0-9]{10}$");
		handler.setFileNamePattern("^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]{2,4}-[0-9]{10}\\.doc[x]*$");
		
		
		try {
			ImapReceiver.receive(conn, handler, null, null);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
