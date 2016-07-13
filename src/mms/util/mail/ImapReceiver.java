package mms.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPMessage;

import mms.bean.MailConnectBean;
import mms.bean.PaperInfo;
import mms.dao.IUserMapper;

public class ImapReceiver {
	
	/**
	 * 通过imap 协议 接收邮件
	 * @param conn  用户链接信息
	 * @param handler  邮件处理器
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void receive(MailConnectBean conn, IMsgHandler handler, Integer lessonid, IUserMapper mapper) throws MessagingException, IOException {
		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imap");  
        props.setProperty("mail.imap.host", conn.getHost());  
        props.setProperty("mail.imap.port", "143"); 
        
        // 创建Session实例对象  
        Session session = Session.getInstance(props);  
          
        // 创建IMAP协议的Store对象  
        Store store = session.getStore("imap");  
          
        // 连接邮件服务器  
        store.connect(conn.getUsername(), conn.getPassword());
        
     // 获得收件箱  
        Folder folder = store.getFolder("INBOX");  
        // 以读写模式打开收件箱  
        folder.open(Folder.READ_WRITE);  
          
        // 获得收件箱的邮件列表  
        Message[] messages = folder.getMessages();  
          
        // 打印不同状态的邮件数量  
        System.out.println("收件箱中共" + messages.length + "封邮件!");  
        System.out.println("收件箱中共" + folder.getUnreadMessageCount() + "封未读邮件!");  
        System.out.println("收件箱中共" + folder.getNewMessageCount() + "封新邮件!");  
        System.out.println("收件箱中共" + folder.getDeletedMessageCount() + "封已删除邮件!");  
          
        System.out.println("------------------------开始解析邮件----------------------------------");  
          
        // 解析邮件  
        for (Message message : messages) {  
            IMAPMessage msg = (IMAPMessage) message;  
            
            Flags flags =  msg.getFlags();
            
            //该邮件也读
            if(flags.contains(Flags.Flag.SEEN)) {
            	
            } else {
            	
//            	String subject = MimeUtility.decodeText(msg.getSubject()); 
            	PaperInfo paperInfo = new PaperInfo();
            	
            	paperInfo.setLesson_id(lessonid);
            	paperInfo.setHandin_time(MailReceiver.getSentDate(msg, null));
            	paperInfo.setStu_email_addr(MailReceiver.getFrom(msg));
            	paperInfo.setEmail_subject(MailReceiver.getSubject(msg));
            	
            	String attachName = null;
            	//通过注入接口，对msg进行处理
            	try {
            		attachName = handler.handle(msg);
            		

					MailSender sender = new MailSender();
					sender.setHost(MailProfiler.getHost("smtp", conn.getUsername()));
					try {
						sender.sendTextEmail(conn.getUsername(), conn.getPassword(), MailReceiver.getFrom(msg), "报告已提交", "你所发送的报告已成功提交");
					} catch (Exception sende) {
						// TODO Auto-generated catch block
						sende.printStackTrace();
					}

					paperInfo.setIs_validate(Boolean.TRUE);
					
				} catch (NotValideException e) { //邮件没有通过验证
					// TODO Auto-generated catch block
					//给该同学发送邮件，要求重新发送
					
					MailSender sender = new MailSender();
					sender.setHost(MailProfiler.getHost("smtp", conn.getUsername()));
					try {
						sender.sendTextEmail(conn.getUsername(), conn.getPassword(), MailReceiver.getFrom(msg), "报告格式有误", "你所发送的邮件格式不正确");
					} catch (Exception sende) {
						// TODO Auto-generated catch block
						sende.printStackTrace();
					}
					
					System.out.println(MailProfiler.getHost("smtp", conn.getUsername())+ " "+ 
							conn.getUsername()+" "+conn.getPassword());
				
					paperInfo.setIs_validate(Boolean.FALSE);
					
					e.printStackTrace();
				}
				msg.setFlag(Flag.SEEN, true);   //设置已读标志  
            	paperInfo.setAttach_name(attachName);
            	//向数据库中插入一条收取报告记录
            	mapper.insetPaperInfo(paperInfo);
            	

            }
        }  
          
        // 关闭资源  
        folder.close(false);  
        store.close();  
        
		
	}
	
}
