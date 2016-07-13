package mms.util.mail;

import javax.mail.Message;

/**
 * 邮件处理器接口
 * 对邮件进行规范化判断、附件保存等
 * @author wangjialong
 *
 */
public interface IMsgHandler {
	/**
	 * 邮件处理函数
	 * @param msg
	 * @return attach_file_name
	 */
	public String handle(Message msg) throws NotValideException;

}
