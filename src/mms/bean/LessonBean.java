package mms.bean;

import java.util.Date;

public class LessonBean {
	
	private Integer lesson_id;
	private Integer teacher_id;
	private String lesson_name;
	private Integer stu_count;
	private Date deadline;
	private String subject_pattern;
	private String attach_pattern;
	private String fix_pattern;
	private String email_account;
	private String email_pwd;
	
	private String create_time;
	
	
	public String getEmail_account() {
		return email_account;
	}
	public void setEmail_account(String email_account) {
		this.email_account = email_account;
	}
	public String getEmail_pwd() {
		return email_pwd;
	}
	public void setEmail_pwd(String email_pwd) {
		this.email_pwd = email_pwd;
	}
	public Integer getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(Integer lesson_id) {
		this.lesson_id = lesson_id;
	}
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getLesson_name() {
		return lesson_name;
	}
	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}
	public Integer getStu_count() {
		return stu_count;
	}
	public void setStu_count(Integer stu_count) {
		this.stu_count = stu_count;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getSubject_pattern() {
		return subject_pattern;
	}
	public void setSubject_pattern(String subject_pattern) {
		this.subject_pattern = subject_pattern;
	}
	public String getAttach_pattern() {
		return attach_pattern;
	}
	public void setAttach_pattern(String attach_pattern) {
		this.attach_pattern = attach_pattern;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getFix_pattern() {
		return fix_pattern;
	}
	public void setFix_pattern(String fix_pattern) {
		this.fix_pattern = fix_pattern;
	}
}
