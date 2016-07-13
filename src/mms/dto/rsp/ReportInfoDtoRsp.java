package mms.dto.rsp;

import java.util.List;

import mms.bean.StudentReportBean;

public class ReportInfoDtoRsp extends GeneralDtoRsp{
	
	private List<StudentReportBean> report_list;

	public List<StudentReportBean> getReport_list() {
		return report_list;
	}

	public void setReport_list(List<StudentReportBean> report_list) {
		this.report_list = report_list;
	}

}
