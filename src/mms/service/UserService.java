package mms.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mms.bean.AuthenBean;
import mms.bean.LessonBean;
import mms.bean.LessonReportBean;
import mms.bean.SelectOptions;
import mms.bean.StudentReportBean;
import mms.bean.UserInfoBean;
import mms.dto.req.AddLessonDtoReq;
import mms.dto.req.AddOptionDtoReq;
import mms.dto.req.DelLessonsDtoReq;
import mms.dto.req.ModifyLessonDtoReq;
import mms.dto.req.ModifyUserDtoReq;
import mms.dto.req.RegisterDtoReq;
import mms.dto.req.ReportInfoDtoReq;
import mms.dto.rsp.GeneralDtoRsp;
import mms.dto.rsp.GetOptionsDtoRsp;
import mms.dto.rsp.LessonListDtoRsp;
import mms.dto.rsp.ReportInfoDtoRsp;
import mms.dto.rsp.ReportListDtoRsp;
import mms.dto.rsp.UserInfoDtoRsp;
import mms.util.EncryptUtils;
import mms.util.RandomUtil;
import mms.util.ResponseStatus;
import mms.util.ResponseStatusEnum;

/**
 * 用户业务逻辑层
 * @author wangjialong
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserService {
	
	@Resource
	private mms.dao.IUserMapper mapper;
	
	public static final Logger log = Logger.getLogger(UserService.class);
	
	public String logIn(AuthenBean bean) {
		System.out.println("service log in ");
		
		UserInfoBean userInfo =  mapper.findUserByUsername(bean.getUsername());
		//MD5加密
		String password = EncryptUtils.encrypt(bean.getPassword(), userInfo.getPasswd_salt());
		
		if(password.length() > 20) {
			password = password.substring(0, 20);
		}
		
		if(!password.equals(userInfo.getPasswd()))
			return null;
		return userInfo.getUser_id();
	}
	
	public GeneralDtoRsp register(RegisterDtoReq req) {
		
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		
		
		UserInfoBean userInfo = mapper.findUserByUsername(req.getUsername());
		//该用户已存在
		if(userInfo != null) {
			//not success
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.USER_EXIST));
			return rspDto;
		}
		
		String passwd_salt = RandomUtil.generateString(10);
		// 加密后的passwd 
		String passwd = EncryptUtils.encrypt(req.getPasswd(), passwd_salt);
		
		if(passwd.length() > 20) 
			passwd = passwd.substring(0, 20);

		Integer result = mapper.insertUserInfo(req.getUsername(), passwd, passwd_salt);
		if(result == null || result < 0) {
			//not success
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		}
		return rspDto;
	}
	
	public UserInfoDtoRsp getUserInfo(String user_id) {
		UserInfoDtoRsp rspDto = new UserInfoDtoRsp();
		
		UserInfoBean user_info = mapper.findUserByUserid(user_id);
		System.out.println("get user info " + user_info);
		//没有对应用户信息
		if(user_info == null) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.USER_NOT_EXIST));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
			rspDto.setUser_info(user_info);
		}
		return rspDto;
	}
	
	public GeneralDtoRsp modify(ModifyUserDtoReq user_info) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		
		UserInfoBean old_one = mapper.findUserByUserid(user_info.getUser_id());
		
		if(old_one == null) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.USER_NOT_EXIST));
			return rspDto;
		}
		
		//需要修改密码
		if(user_info.getOldPwd() != null && !"".equals(user_info.getOldPwd())){

			String oldpwd = EncryptUtils.encrypt(user_info.getOldPwd(), old_one.getPasswd_salt());
			if(oldpwd.length() > 20) 
				oldpwd = oldpwd.substring(0, 20);
			
			//原始密码不一致
			if(!old_one.getPasswd().equals(oldpwd)){
				
				System.out.println(user_info.getOldPwd() + " " + old_one.getPasswd()+ " " + 
						EncryptUtils.encrypt(user_info.getOldPwd(), old_one.getPasswd_salt()));
				rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_VALIDATE_EXCEPTION));
				return rspDto;
			}
			//设置新密码加密后的字段
			String pwd = EncryptUtils.encrypt(user_info.getPasswd(), old_one.getPasswd_salt());
			if(pwd.length() > 20)
				pwd = pwd.substring(0, 20);
			user_info.setPasswd(pwd);
		}
		
		mapper.updateUser(user_info);
		rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		return rspDto;
	}
	
	public LessonListDtoRsp getLessonList(String userid) {
		LessonListDtoRsp rspDto = new LessonListDtoRsp();
		List<LessonBean> lessons = mapper.getLessonListByUserId(userid);
//		if(lessons == null || lessons.size() < 1 ) {
//			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
//			return rspDto;
//		}
		rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		rspDto.setLessonList(lessons);
		return rspDto;
	}
	
	public LessonListDtoRsp getDeletedLessonList(String userid) {
		LessonListDtoRsp rspDto = new LessonListDtoRsp();
		List<LessonBean> lessons = mapper.getDelLessonListByUserId(userid);
//		if(lessons == null || lessons.size() < 1 ) {
//			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
//			return rspDto;
//		}
		rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		rspDto.setLessonList(lessons);
		return rspDto;
	}
	
	public GeneralDtoRsp addLesson(AddLessonDtoReq reqDto) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		Integer result = mapper.insertLesson(reqDto);
		if(result == null || result < 1) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		}
		return rspDto;
	}
	
	public GeneralDtoRsp modifyLesson(ModifyLessonDtoReq reqDto) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		Integer result = mapper.updateLesson(reqDto);
		if(result == null || result < 1) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		}
		return rspDto;
	}
	
	public GeneralDtoRsp delLessonList(DelLessonsDtoReq reqDto) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		Integer result = mapper.delLessonListByIds(reqDto.getUserid(), reqDto.getDel_lesson_list());
		if(result == null || result < 1) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		}
		return rspDto;
	}
	
	public GeneralDtoRsp recoverLessonList(DelLessonsDtoReq reqDto) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		Integer result = mapper.recoverLessonListByIds(reqDto.getUserid(), reqDto.getDel_lesson_list());
		if(result == null || result < 1) {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		} else {
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		}
		return rspDto;
	}
	
	public GetOptionsDtoRsp getOptions(String userid) {
		GetOptionsDtoRsp rspDto = new GetOptionsDtoRsp();
		
		List<SelectOptions> subjectOptions=  mapper.getSubjectOptions(userid);
		if(subjectOptions != null)
			rspDto.setSubjectOptions(subjectOptions);
		
		List<SelectOptions> fileNameOptions = mapper.getFileNameOptions(userid);
		if(fileNameOptions != null) 
			rspDto.setFileNameOptions(fileNameOptions);
		
		List<SelectOptions> fileFixOptions = mapper.getFileFixOptions(userid);
		if(fileFixOptions != null)
			rspDto.setFileFixOptions(fileFixOptions);
		
		return rspDto;
	}
	
	public GeneralDtoRsp addOption(AddOptionDtoReq reqDto) {
		GeneralDtoRsp rspDto = new GeneralDtoRsp();
		
		Integer result = mapper.insertOption(reqDto);
		if(result != null && result > 0) 
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		else 
			rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_FAIL));
		
		return rspDto;
	}
	
	public ReportListDtoRsp reportList(String userid) {
		ReportListDtoRsp rspDto = new ReportListDtoRsp();
		
		rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		
		List<LessonReportBean> reportList = mapper.reportList(userid);
		rspDto.setReport_list(reportList);
		
		return rspDto;
		
	}
	
	public ReportInfoDtoRsp reportInfo(ReportInfoDtoReq reqDto) {
		ReportInfoDtoRsp rspDto = new ReportInfoDtoRsp();
		
		rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_SUCCESS));
		
		List<StudentReportBean> reportList = mapper.reportInfo(reqDto.getLesson_id());
		rspDto.setReport_list(reportList);
		
		return rspDto;
	}
	
}
