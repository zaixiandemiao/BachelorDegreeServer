package mms.util;
public enum ResponseStatusEnum {

	@ResponseStatusRetention(msg="失败")
	COMM_FAIL(0),
	@ResponseStatusRetention(msg="成功")
	COMM_SUCCESS(1),
	@ResponseStatusRetention(msg = "服务器异常")
	COMM_SERVER_EXCEPTION(2),
	@ResponseStatusRetention(msg = "参数错误")
	COMM_PARA_WRONG(3),
	@ResponseStatusRetention(msg = "用户已存在")
	USER_EXIST(4),
	@ResponseStatusRetention(msg = "用户不存在")
	USER_NOT_EXIST(5),
	@ResponseStatusRetention(msg = "用户名或密码错误")
	COMM_VALIDATE_EXCEPTION(6),
	@ResponseStatusRetention(msg = "没有经过授权，需要登陆")
	COMM_NOT_AUTHEN(7),
	@ResponseStatusRetention(msg = "该用户已经登录")
	USER_IS_LOGGED(8)
	;
	
	private int code;	
	
	private ResponseStatusEnum(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	public String getName() {
		return this.name();
	}
	public String getMessage() {
		ResponseStatusRetention err = null;
		try {
			err = this.getClass().getField(this.getName())
				.getAnnotation(ResponseStatusRetention.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return err.msg();
	}
	
	public static ResponseStatusEnum getByCode(int code) {
		for(ResponseStatusEnum en : ResponseStatusEnum.values()) {
			if(en.getCode() == code)
				return en;
		}
		return null;
	}
}
