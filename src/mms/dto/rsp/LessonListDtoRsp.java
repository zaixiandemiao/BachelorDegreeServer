package mms.dto.rsp;

import java.util.List;

import mms.bean.LessonBean;

public class LessonListDtoRsp extends GeneralDtoRsp{
	
	private List<LessonBean> lessonList;

	public List<LessonBean> getLessonList() {
		return lessonList;
	}

	public void setLessonList(List<LessonBean> lessonList) {
		this.lessonList = lessonList;
	}
	
	

}
