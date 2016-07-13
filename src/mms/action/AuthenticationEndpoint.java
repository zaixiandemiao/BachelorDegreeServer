package mms.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTSigner.Options;

import mms.bean.AuthenBean;
import mms.dto.req.RegisterDtoReq;
import mms.dto.rsp.GeneralDtoRsp;
import mms.service.UserService;
import mms.util.JsonConverter;
import mms.util.ResponseBuilder;
import mms.util.SysConstants;


@Component
@Path("/authentication")
public class AuthenticationEndpoint {
	
	@Resource
	private UserService userService;
	
	private JWTSigner signer = new JWTSigner(SysConstants.AUTH0_CLIENT_SECRET);
	
	private static Logger logger  = Logger.getLogger(AuthenticationEndpoint.class);

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(String request) {
    	
    	AuthenBean authBean = JsonConverter.jsonStr2Obj(request, AuthenBean.class);
    	System.out.println("login ");
        try {
        	
        	logger.info("get username / password" + authBean.getUsername() + " / " + authBean.getPassword());

            // Authenticate the user using the credentials provided
            String userid = authenticate(authBean);

            // Issue a token for the user
            String token = issueToken(userid);
            
            logger.info("token " + token);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }      
    }
    
    @POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(String request) {
		
		RegisterDtoReq registerReq = JsonConverter.jsonStr2Obj(request.toString(), RegisterDtoReq.class);

		GeneralDtoRsp rspDto =  userService.register(registerReq);
		
		return Response.ok(ResponseBuilder.build(rspDto)).build();
	}
    

    private String authenticate(AuthenBean bean) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    	String userid = userService.logIn(bean);
    	
    	if(userid == null || "".equals(userid))
    		throw new Exception("username or password wrong");
    	
    	return userid;
    	
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	
    	Map<String, Object> clamis = new HashMap<String, Object>();
    	clamis.put("userid", username);
    	
    	
    	
    	
//    	clamis.put("exp", calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
    	
    	Options option = new Options();
    	
    	
    	option.setExpirySeconds(30 * 60);
    	
    	
    	System.out.println(signer.sign(clamis, option));
    	
    	return JsonConverter.obj2Str(signer.sign(clamis, option));
    	
    }
}
