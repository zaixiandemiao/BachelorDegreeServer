package mms.dto.rsp;

import java.util.List;

import mms.bean.LessonReportBean;

public class ReportListDtoRsp extends GeneralDtoRsp{

	private List<LessonReportBean> report_list;

	public List<LessonReportBean> getReport_list() {
		return report_list;
	}

	public void setReport_list(List<LessonReportBean> report_list) {
		this.report_list = report_list;
	}
	
}
