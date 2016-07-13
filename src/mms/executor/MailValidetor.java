package mms.executor;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import mms.bean.LessonBean;
import mms.bean.MailConnectBean;
import mms.util.mail.ImapReceiver;
import mms.util.mail.MailProfiler;
import mms.util.mail.MsgHandler;

public class MailValidetor implements Runnable{
	
	private static Logger logger  = Logger.getLogger(MailValidetor.class);
	
	@Resource
	private mms.dao.IUserMapper mapper;

	//每隔一段时间，读取那些未过期的课程邮件
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<LessonBean> lessons = mapper.getAllLessonWithinDeadline();
		
		Iterator<LessonBean> lesson_iter =  lessons.iterator();
		
		while(lesson_iter.hasNext()) {
			LessonBean lesson = lesson_iter.next();
			
			MailConnectBean conn = new MailConnectBean();
			conn.setHost(MailProfiler.getHost("imap", lesson.getEmail_account()));
			conn.setUsername(lesson.getEmail_account());
			conn.setPassword(lesson.getEmail_pwd());
			
			MsgHandler handler = new MsgHandler();
			handler.setDestDir("F:\\mailtmp\\" + lesson.getTeacher_id() + "\\" + lesson.getLesson_name());
			handler.setSubjectPattern(lesson.getSubject_pattern());
			handler.setFileNamePattern(lesson.getAttach_pattern());
			
			try {
				System.out.println("imap receiving");
				ImapReceiver.receive(conn, handler, lesson.getLesson_id(), mapper);
			} catch (MessagingException | IOException e) {
				// TODO Auto-generated catch block
				logger.info(e);
			}
			
		}
		
	}

}
