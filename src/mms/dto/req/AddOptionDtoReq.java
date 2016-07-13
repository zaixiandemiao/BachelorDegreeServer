package mms.dto.req;

public class AddOptionDtoReq {
	
	private Integer pattern_type_id;
	private String pattern_info;
	private String pattern_comment;
	private String user_id;
	
	
	public Integer getPattern_type_id() {
		return pattern_type_id;
	}
	public void setPattern_type_id(Integer pattern_type_id) {
		this.pattern_type_id = pattern_type_id;
	}
	public String getPattern_info() {
		return pattern_info;
	}
	public void setPattern_info(String pattern_info) {
		this.pattern_info = pattern_info;
	}
	public String getPattern_comment() {
		return pattern_comment;
	}
	public void setPattern_comment(String pattern_comment) {
		this.pattern_comment = pattern_comment;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
