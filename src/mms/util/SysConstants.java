package mms.util;

/**
 * 存放系统常量
 * @author wangjialong
 *
 */
public abstract class SysConstants {
	
	//Json web token 
	public static String AUTH0_CLIENT_SECRET = "public.mail.server";
	
	/**
	 * 以下三个为 表pattern_info 中 pattern_type_id 的取值
	 */
	public static Integer SUBJECT_PATTERN_TYPE = 1; //邮件主题格式
	public static Integer FILENAME_PATTERN_TYPE = 2;  //附件文件名
	public static Integer FILEFIX_PATTERN_TYPE = 3;   //附件后缀
	
	
	
}
