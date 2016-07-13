package mms.util;

public class ResponseStatus {

	private int status_code;
	
	private String status;
	
	public ResponseStatus() {
		this(ResponseStatusEnum.COMM_SUCCESS);
	}
	public ResponseStatus(ResponseStatusEnum e) {
		this(e.getCode());
	}
	
	public ResponseStatus(int code) {
		this.status_code = code;
		this.status = ResponseStatusEnum.getByCode(code).getMessage();
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
