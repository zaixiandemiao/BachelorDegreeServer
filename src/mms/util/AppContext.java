package mms.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;




/**
 * 运行时环境类，提供getBean方法
 * @author wangjialong
 *
 */
public class AppContext implements ApplicationContextAware{

	private static Logger logger = Logger.getLogger(AppContext.class);
	
	//运行时环境
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		AppContext.applicationContext = arg0;
	}

	public void init() {
		logger.info("系统启动，初始化工作----");
//		MailValidetor mailValidetor = getBean("mainThread");
//		ProjectExecutor mainExecutor = getBean("mainExecutor");
//		mainExecutor.watch(mailValidetor);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		return (T) applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}
}
