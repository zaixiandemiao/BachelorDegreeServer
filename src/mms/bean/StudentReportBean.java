package mms.bean;

public class StudentReportBean {
	
	private String stu_email_addr;
	private Integer report_count;
	private String email_subject;
	private String attach_name;
	private String handin_time;
	private Boolean is_validate;
	
	public String getStu_email_addr() {
		return stu_email_addr;
	}
	public void setStu_email_addr(String stu_email_addr) {
		this.stu_email_addr = stu_email_addr;
	}
	public Integer getReport_count() {
		return report_count;
	}
	public void setReport_count(Integer report_count) {
		this.report_count = report_count;
	}
	public String getEmail_subject() {
		return email_subject;
	}
	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}
	public String getAttach_name() {
		return attach_name;
	}
	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}
	public String getHandin_time() {
		return handin_time;
	}
	public void setHandin_time(String handin_time) {
		this.handin_time = handin_time;
	}
	public Boolean getIs_validate() {
		return is_validate;
	}
	public void setIs_validate(Boolean is_validate) {
		this.is_validate = is_validate;
	}
	
	

}
