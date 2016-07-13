package mms.dto.rsp;

import mms.bean.UserInfoBean;

public class UserInfoDtoRsp extends GeneralDtoRsp{

	private UserInfoBean user_info;

	public UserInfoBean getUser_info() {
		return user_info;
	}

	public void setUser_info(UserInfoBean user_info) {
		this.user_info = user_info;
	}
	
}
