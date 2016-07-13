package mms.bean;

import java.util.Date;

public class LessonReportBean {
	
	private Integer lesson_id;
	private String lesson_name;
	private Integer stu_count;
	private Integer report_stu_count;
	private Date    deadline;
	
	public Integer getLesson_id() {
		return lesson_id;
	}
	public void setLesson_id(Integer lesson_id) {
		this.lesson_id = lesson_id;
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
	public Integer getReport_stu_count() {
		return report_stu_count;
	}
	public void setReport_stu_count(Integer report_stu_count) {
		this.report_stu_count = report_stu_count;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
}
