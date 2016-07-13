package mms.dto.rsp;

import java.util.List;

import mms.bean.SelectOptions;

public class GetOptionsDtoRsp {
	
	private List<SelectOptions> subjectOptions;
	private List<SelectOptions> fileNameOptions;
	private List<SelectOptions> fileFixOptions;
	
	public List<SelectOptions> getSubjectOptions() {
		return subjectOptions;
	}
	public void setSubjectOptions(List<SelectOptions> subjectOptions) {
		this.subjectOptions = subjectOptions;
	}
	public List<SelectOptions> getFileNameOptions() {
		return fileNameOptions;
	}
	public void setFileNameOptions(List<SelectOptions> fileNameOptions) {
		this.fileNameOptions = fileNameOptions;
	}
	public List<SelectOptions> getFileFixOptions() {
		return fileFixOptions;
	}
	public void setFileFixOptions(List<SelectOptions> fileFixOptions) {
		this.fileFixOptions = fileFixOptions;
	}
}
