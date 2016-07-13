package mms.bean;


public class UserInfoBean {
	
	
	private String user_id;
	private String login_username;
	private String real_name;
	private String passwd;
	private String passwd_salt;
	private Boolean is_deleted;
	private Boolean is_locked;
	private Integer lock_id;
	private Integer user_type_id;
	
	
	public String getLogin_username() {
		return login_username;
	}
	public void setLogin_username(String login_username) {
		this.login_username = login_username;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public Boolean getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public Boolean getIs_locked() {
		return is_locked;
	}
	public void setIs_locked(Boolean is_locked) {
		this.is_locked = is_locked;
	}
	public Integer getLock_id() {
		return lock_id;
	}
	public void setLock_id(Integer lock_id) {
		this.lock_id = lock_id;
	}
	public Integer getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(Integer user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getPasswd_salt() {
		return passwd_salt;
	}
	public void setPasswd_salt(String passwd_salt) {
		this.passwd_salt = passwd_salt;
	}

}
