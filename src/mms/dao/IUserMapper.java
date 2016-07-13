package mms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mms.bean.LessonBean;
import mms.bean.LessonReportBean;
import mms.bean.PaperInfo;
import mms.bean.SelectOptions;
import mms.bean.StudentReportBean;
import mms.bean.UserInfoBean;
import mms.dto.req.AddLessonDtoReq;
import mms.dto.req.AddOptionDtoReq;
import mms.dto.req.ModifyLessonDtoReq;
import mms.dto.req.ModifyUserDtoReq;

public  interface  IUserMapper {
	
	/**
	 * 登陆验证
	 * @param username 用户名
	 * @param password 密码
	 * @return user_id
	 */
	public String logIn(@Param("username")String username, 
			@Param("password")String password);
	
	/**
	 * 通过username找到对应userinfo
	 * @param username
	 * @return
	 */
	public UserInfoBean findUserByUsername(@Param("username")String username);
	
	/**
	 * 通过userid找到对应userinfo
	 * @param username
	 * @return
	 */
	public UserInfoBean findUserByUserid(@Param("userid")String userid);
	
	/**
	 * 插入userinfo
	 * @param username
	 * @param passwd
	 * @param passwd_salt
	 */
	public Integer insertUserInfo(@Param("username")String username, 
			@Param("passwd")String passwd, @Param("passwd_salt")String passwd_salt);
	
	
	public void updateUser(ModifyUserDtoReq user_info);
	/**
	 * 根据教师id查找课程信息
	 * @param userid
	 * @return
	 */
	public List<LessonBean> getLessonListByUserId(@Param("userid")String userid);
	
	/**
	 * 根据教师id查找课程信息
	 * @param userid
	 * @return
	 */
	public List<LessonBean> getDelLessonListByUserId(@Param("userid")String userid);
	/**
	 * 新增课程信息
	 * @param addBean
	 * @return
	 */
	public Integer insertLesson(AddLessonDtoReq addBean);
	/**
	 * 根据userid和lessonids删除课程
	 * @param userid
	 * @param ids
	 */
	public Integer delLessonListByIds(@Param("userid")String userid, @Param("list")List<Integer> ids);
	/**
	 * 根据userid和lessonids删除课程
	 * @param userid
	 * @param ids
	 */
	public Integer recoverLessonListByIds(@Param("userid")String userid, @Param("list")List<Integer> ids);
	
	
	/**
	 * 获得所有在期限内的课程
	 * @return
	 */
	public List<LessonBean> getAllLessonWithinDeadline();

	/**
	 * 修改课程
	 * @param reqDto
	 * @return
	 */
	public Integer updateLesson(ModifyLessonDtoReq reqDto);
	
	/**
	 * 获取主题对应的options
	 * @param userid
	 * @return
	 */
	public List<SelectOptions> getSubjectOptions(@Param("userid")String userid);
	
	public List<SelectOptions> getFileNameOptions(@Param("userid")String userid);
	
	public List<SelectOptions> getFileFixOptions(@Param("userid")String userid);
	
	public Integer insertOption(AddOptionDtoReq dto);

	public Integer insetPaperInfo(PaperInfo paperInfo);

	public List<LessonReportBean> reportList(@Param("userid")String userid);

	public List<StudentReportBean> reportInfo(@Param("lesson_id")Integer lesson_id);
	
	
}
