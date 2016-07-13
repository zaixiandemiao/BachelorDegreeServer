package mms.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


public class MailSender {
	private static final Logger logger = Logger
			.getLogger(MailSender.class);
	private JavaMailSenderImpl sender;
	private SimpleMailMessage message;
	
	 // 邮件发送协议 
    private final static String PROTOCOL = "smtp"; 
      
    // SMTP邮件服务器默认端口 
    private final static String PORT = "25"; 
     
    // 是否要求身份认证 
    private final static String IS_AUTH = "true"; 
     
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） 
    private final static String IS_ENABLED_DEBUG_MOD = "true"; 
    
    // 初始化连接邮件服务器的会话信息 
    private Properties props = null; 

    public void setHost(String host) {
    	props = new Properties(); 
        props.setProperty("mail.transport.protocol", PROTOCOL); 
        props.setProperty("mail.smtp.host", host); 
        props.setProperty("mail.smtp.port", PORT); 
        props.setProperty("mail.smtp.auth", IS_AUTH); 
        props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD); 
    }
 
	
	
	
	public JavaMailSenderImpl getSender() {
		return sender;
	}


	public void setSender(JavaMailSenderImpl sender) {
		this.sender = sender;
	}


	public SimpleMailMessage getMessage() {
		return message;
	}


	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	/**
	 * 设置邮件发送方
	 * @param from
	 */
	public void setFrom(String from) {
		if(this.message == null)
			this.message = new SimpleMailMessage();
		this.message.setFrom(from);
	}
	
	public void setSenderByProp(String host, String username, String password) {
		if(this.sender == null)
			this.sender = new JavaMailSenderImpl();
		
		this.sender.setHost(host);
		SmtpAuthenticator authen = new SmtpAuthenticator(username, password);
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.port", "465");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		
		this.sender.setSession(javax.mail.Session.getInstance(prop, authen));
	}
	
	
	/**
	 * 主送发送纯文本邮件
	 * 
	 * @author lisheng
	 * @param toMailAddress
	 * @param mailContent
	 * @param subject
	 * 
	 */
    public void sendSimpleMessage(String toMailAddress, String subject,
			String mailContent) {
		logger.info("使用sendSimpleMessage开始发送邮件到{" + toMailAddress + "},主题是{" + subject + "},内容是{"
				+ mailContent + "}");
		// 主送
		this.message.setTo(toMailAddress);
		this.message.setFrom(this.message.getFrom());
		this.message.setText(mailContent);
		this.message
				.setSubject((subject != null) && (!"".equals(subject)) ? subject
						: this.message.getSubject());
		this.sender.send(this.message);
		logger.info("邮件已经发送到" + toMailAddress);
	}
    /**
	 * 主送发送网页文件
	 * 
	 * @author lisheng
	 * @param toMailAddress
	 * @param subject
	 * @param htmlMailContent
	 *            void
	 * 
	 */
	public void sendHtmlMessage(String toMailAddress, String subject,
			String htmlMailContent) {
		try {
			logger.info("使用sendHtmlMessage开始发送邮件到{" + toMailAddress + "},主题是{"
					+ subject + "},内容是{" + htmlMailContent + "}");
			MimeMessage msg = this.sender.createMimeMessage();
			msg.setSentDate(new Date());
			Multipart mp = new MimeMultipart("related");
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(htmlMailContent, "text/html;charset=UTF-8");
			mp.addBodyPart(mbp);
			msg.setContent(mp);
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			// 主送
			helper.setTo(toMailAddress);
			
		    helper.setFrom(this.message.getFrom());
				
			helper.setSubject(subject);
			helper.setText(htmlMailContent, true);
			this.sender.send(msg);
			logger.info("邮件已经发送到" + toMailAddress);
		} catch (MessagingException e) {
			logger.error("异常:邮件无法正确发送,请检查你的邮件端口设置及防火墙设置等", e);
		}
	}
	
	/**
     * 发送简单的文本邮件
     */ 
    public void sendTextEmail(String from, String password, String to, String subject, String text) throws Exception { 
        // 创建Session实例对象 
        Session session = Session.getDefaultInstance(props); 
         
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
        // 设置发件人 
        message.setFrom(new InternetAddress(from)); 
        // 设置邮件主题 
        message.setSubject(subject); 
        // 设置收件人 
        message.setRecipient(RecipientType.TO, new InternetAddress(to)); 
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置纯文本内容为邮件正文 
        message.setText(text); 
        // 保存并生成最终的邮件内容 
        message.saveChanges(); 
         
        // 获得Transport实例对象 
        Transport transport = session.getTransport(); 
        // 打开连接 
        transport.connect(from.substring(0, from.indexOf("@")), password); 
        // 将message对象传递给transport对象，将邮件发送出去 
        transport.sendMessage(message, message.getAllRecipients()); 
        // 关闭连接 
        transport.close(); 
    } 

}
