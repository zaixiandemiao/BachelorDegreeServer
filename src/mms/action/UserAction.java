package mms.action;



import javax.annotation.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.stereotype.Component;

import mms.dto.req.AddLessonDtoReq;
import mms.dto.req.AddOptionDtoReq;
import mms.dto.req.DelLessonsDtoReq;
import mms.dto.req.ModifyLessonDtoReq;
import mms.dto.req.ModifyUserDtoReq;
import mms.dto.req.ReportInfoDtoReq;
import mms.dto.rsp.GeneralDtoRsp;
import mms.dto.rsp.GetOptionsDtoRsp;
import mms.dto.rsp.LessonListDtoRsp;
import mms.dto.rsp.ReportInfoDtoRsp;
import mms.dto.rsp.ReportListDtoRsp;
import mms.dto.rsp.UserInfoDtoRsp;
import mms.filter.Secured;
import mms.service.UserService;
import mms.util.JsonConverter;
import mms.util.ResponseBuilder;
import mms.util.TokenUtil;

@Secured
@Component
@Path("/secured/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	@Context 
	private SecurityContext  securityContext;


	@POST
	@Path("/get_user_info")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String userInfo(String request) {
		
		String user_id = TokenUtil.getUserId(securityContext);
		
		System.out.println("user id " + user_id);
		
		UserInfoDtoRsp rspDto = userService.getUserInfo(user_id);

		return ResponseBuilder.build(rspDto);
	}

	@POST
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String ChangePsw(String request) {
		
		
		ModifyUserDtoReq reqDto = JsonConverter.jsonStr2Obj(request, ModifyUserDtoReq.class);
		reqDto.setUser_id(TokenUtil.getUserId(securityContext)); 
		
		GeneralDtoRsp rspDto =  userService.modify(reqDto);

		return ResponseBuilder.build(rspDto);
	}
	
	@POST
	@Path("/get_lesson_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String lessonList(String request) {
		
		LessonListDtoRsp rspDto =  userService.getLessonList(TokenUtil.getUserId(securityContext));
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/deleted_lesson_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deletedLessonList(String request) {
		
		LessonListDtoRsp rspDto =  userService.getDeletedLessonList(TokenUtil.getUserId(securityContext));
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/add_lesson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addLesson(String request) {
		
		
		AddLessonDtoReq reqDto = JsonConverter.jsonStr2Obj(request, AddLessonDtoReq.class);
		
		String userid = TokenUtil.getUserId(securityContext);
		
		reqDto.setUserid(userid); 
//		reqDto.generateAttachPattern();
		reqDto.getDeadline().setDate(reqDto.getDeadline().getDate() + 1);
		
		
		GeneralDtoRsp rspDto = userService.addLesson(reqDto);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/modify_lesson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String modifyLesson(String request) {
		
		
		ModifyLessonDtoReq lesson = JsonConverter.jsonStr2Obj(request, ModifyLessonDtoReq.class);
		
		System.out.println(request);
		
		String userid = TokenUtil.getUserId(securityContext);
		
		lesson.setTeacher_id(Integer.parseInt(userid));
		
		lesson.getDeadline().setDate(lesson.getDeadline().getDate() + 1);
		
		
		GeneralDtoRsp rspDto = userService.modifyLesson(lesson);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/del_lesson_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delLessonList(String request) {
		
		DelLessonsDtoReq reqDto = JsonConverter.jsonStr2Obj(request, DelLessonsDtoReq.class);
		reqDto.setUserid(TokenUtil.getUserId(securityContext));
		
		GeneralDtoRsp rspDto = userService.delLessonList(reqDto);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/recover_lesson_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String recoverLessonList(String request) {
		
		DelLessonsDtoReq reqDto = JsonConverter.jsonStr2Obj(request, DelLessonsDtoReq.class);
		reqDto.setUserid(TokenUtil.getUserId(securityContext));
		
		GeneralDtoRsp rspDto = userService.recoverLessonList(reqDto);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/get_options")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getOptions(String request) {
		
		String userid = TokenUtil.getUserId(securityContext);
		
		GetOptionsDtoRsp rspDto = userService.getOptions(userid);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/add_options")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addOptions(String request) {
		
		AddOptionDtoReq reqDto = JsonConverter.jsonStr2Obj(request, AddOptionDtoReq.class);
		
		String userid = TokenUtil.getUserId(securityContext);
		reqDto.setUser_id(userid);
		
		
		GeneralDtoRsp rspDto = userService.addOption(reqDto);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/get_report_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getReportList(String request) {
		
		String userid = TokenUtil.getUserId(securityContext);
		
		ReportListDtoRsp rspDto = userService.reportList(userid);
		
		return ResponseBuilder.build(rspDto);
		
	}
	
	@POST
	@Path("/get_report_info")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getReportInfo(String request) {
		
		ReportInfoDtoReq reqDto = JsonConverter.jsonStr2Obj(request, ReportInfoDtoReq.class);
		reqDto.setUser_id(TokenUtil.getUserId(securityContext));
		
		ReportInfoDtoRsp rspDto = userService.reportInfo(reqDto);
		
		return ResponseBuilder.build(rspDto);
		
	}
	

}
