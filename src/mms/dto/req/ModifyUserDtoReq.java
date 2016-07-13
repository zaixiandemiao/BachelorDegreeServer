package mms.dto.req;

import mms.bean.UserInfoBean;

public class ModifyUserDtoReq extends UserInfoBean{
	
	private String oldPwd;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	

}
