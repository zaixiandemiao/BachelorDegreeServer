package mms.util.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;

public class MsgHandler implements IMsgHandler {
	
	private String destDir;
	
	private String subjectPattern;
	
	private String fileNamePattern;
	
	

	public String getDestDir() {
		return destDir;
	}



	public void setDestDir(String destDir) {
		this.destDir = destDir;
	}



	public String getSubjectPattern() {
		return subjectPattern;
	}



	public void setSubjectPattern(String subjectPattern) {
		this.subjectPattern = subjectPattern;
	}



	public String getFileNamePattern() {
		return fileNamePattern;
	}



	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}


	
	/**
	 * 处理邮件函数
	 */
	@Override
	public String handle(Message msg) throws NotValideException{
		// TODO Auto-generated method stub
		
		try {
			String subject = MailReceiver.getSubject(msg);
			System.out.println(subject+" "+subjectPattern);
			if(!subject.matches(subjectPattern))
				throw new NotValideException("subject not match");
			if(MailReceiver.isContainAttachment(msg)) {
				FileValidate validate = new FileValidate(this.fileNamePattern);
				System.out.println("this.fileNamepattern -- " + this.fileNamePattern);
				String filename = MailReceiver.saveAttachment(msg, destDir, validate);
				return filename;
			}
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			//getSubject exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	  

}
