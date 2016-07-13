package mms.bean;

import java.util.Date;

public class PaperInfo {
	
	private Integer lesson_id;
	private String  handin_time;
	private String stu_email_addr;
	private String email_subject;
	private String attach_name;
	private Boolean is_validate;
	private Date create_time;
	
	
	public Integer getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(Integer lesson_id) {
		this.lesson_id = lesson_id;
	}
	
	public String getHandin_time() {
		return handin_time;
	}
	public void setHandin_time(String handin_time) {
		this.handin_time = handin_time;
	}
	public String getStu_email_addr() {
		return stu_email_addr;
	}
	public void setStu_email_addr(String stu_email_addr) {
		this.stu_email_addr = stu_email_addr;
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
	public Boolean getIs_validate() {
		return is_validate;
	}
	public void setIs_validate(Boolean is_validate) {
		this.is_validate = is_validate;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
