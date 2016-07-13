package mms.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import mms.bean.MailConnectBean;

public class PopReceiver extends MailReceiver{
	
	  
    /** 
     * 接收邮件 
     * @throws NoSuchProviderException 
     */  
    public static void receive(MailConnectBean conn, IMsgHandler handler) throws NoSuchProviderException, MessagingException {  
        // 准备连接服务器的会话信息  
        Properties props = new Properties();  
        props.setProperty("mail.store.protocol", "pop3");       // 协议  
        props.setProperty("mail.pop3.port", "110");             // 端口  
        props.setProperty("mail.pop3.host", conn.getHost());    // pop3服务器  
          
        // 创建Session实例对象  
        Session session = Session.getInstance(props);  
        Store store = session.getStore("pop3");  
        store.connect(conn.getUsername(), conn.getPassword());  
          
        // 获得收件箱  
        Folder folder = store.getFolder("INBOX");  
        /* Folder.READ_ONLY：只读权限 
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态） 
         */  
        folder.open(Folder.READ_WRITE); //打开收件箱  
          
          
        // 得到收件箱中的所有邮件,并解析  
        Message[] messages = folder.getMessages();  
        
        for(Message msg : messages) {
        	try {
				handler.handle(msg);
			} catch (NotValideException e) {
				// TODO Auto-generated catch block
				//向学生发送邮件，提醒重新发送
				e.printStackTrace();
			}
        }
        //释放资源  
        folder.close(true);  
        store.close();  
    }  
      
    /** 
     * 解析邮件 
     * @param messages 要解析的邮件列表 
     */  
    public static void parseMessage(Message ...messages) throws MessagingException, IOException {  
        if (messages == null || messages.length < 1)   
            throw new MessagingException("未找到要解析的邮件!");  
          
        // 解析所有邮件  
        for (int i = 0, count = messages.length; i < count; i++) {  
        	
            MimeMessage msg = (MimeMessage) messages[i];  
            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");  
            System.out.println("主题: " + getSubject(msg));  
            System.out.println("发件人: " + getFrom(msg));  
            System.out.println("收件人：" + getReceiveAddress(msg, null));  
            System.out.println("发送时间：" + getSentDate(msg, null));  
            System.out.println("是否已读：" + isSeen(msg));  
            System.out.println("邮件优先级：" + getPriority(msg));  
            System.out.println("是否需要回执：" + isReplySign(msg));  
            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");  
            boolean isContainerAttachment = isContainAttachment(msg);  
            System.out.println("是否包含附件：" + isContainerAttachment);  
            if (isContainerAttachment) {  
//                MailReceiver.saveAttachment(msg, "F:\\mailtmp\\"+msg.getSubject() + "_"); //保存附件  
            }   
            StringBuffer content = new StringBuffer(30);  
            getMailTextContent(msg, content);  
            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));  
            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");  
            System.out.println();  
        }  
    }  
  
}
