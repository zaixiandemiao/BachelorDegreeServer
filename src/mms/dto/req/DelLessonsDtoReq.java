package mms.dto.req;

import java.util.List;

public class DelLessonsDtoReq {

	private String userid;
	private List<Integer> del_lesson_list;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Integer> getDel_lesson_list() {
		return del_lesson_list;
	}
	public void setDel_lesson_list(List<Integer> del_lesson_list) {
		this.del_lesson_list = del_lesson_list;
	}
}
