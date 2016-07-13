package mms.util.mail;

/**
 * 判断文件名是否符合要求
 * @author wangjialong
 *
 */
public class FileValidate implements BeforeSaveFile {
	
	private String fileNamePattern;
	
	public FileValidate() {
		// TODO Auto-generated constructor stub
	}
	
	public FileValidate(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}

	@Override
	public void doBefore(String fileName) throws NotValideException{
		// TODO Auto-generated method stub
		System.out.println("attach file name" + fileName);
		if(!fileName.matches(this.fileNamePattern))
			throw new NotValideException("file name not match");
	}

}
