package mms.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWTVerifier;

import mms.dto.rsp.GeneralDtoRsp;
import mms.util.ResponseBuilder;
import mms.util.ResponseStatus;
import mms.util.ResponseStatusEnum;
import mms.util.SysConstants;
import mms.util.TokenUtil;


//@WebFilter(filterName= "jwt-filter", urlPatterns = { "/secured/*" })
public class JWTFilter implements Filter {
	private JWTVerifier jwtVerifier;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        jwtVerifier = new JWTVerifier(
//          new Base64(true).decodeBase64(System.getProperty("AUTH0_CLIENT_SECRET")),
//          System.getProperty("AUTH0_CLIENT_ID"));
    	jwtVerifier = new JWTVerifier(SysConstants.AUTH0_CLIENT_SECRET);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
//    	request.setCharacterEncoding("UTF-8");
    	
  
    	
    	String token = TokenUtil.getToken( (HttpServletRequest) request);
        
        try {
            Map<String, Object> decoded = jwtVerifier.verify(token);
            
            //将Token中的user_id放到request中
            request.setAttribute("user_id", decoded.get("userid"));
            
            
            System.out.println(decoded.get("userid")+" "+decoded + " current" + System.currentTimeMillis() / 1000);
            // Do something with decoded information like UserId
            chain.doFilter(request, response);
           
        } catch (Exception e) {
        	GeneralDtoRsp rspDto = new GeneralDtoRsp();
        	rspDto.setResponse_status(new ResponseStatus(ResponseStatusEnum.COMM_NOT_AUTHEN));
        	
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	
        	PrintWriter out =  response.getWriter();
        	
        	out.write(ResponseBuilder.buildResponse(rspDto).toString());
        	out.flush();
        	
//            throw new ServletException("Unauthorized: Token validation failed", e);
        }
    }

    
	@Override
	public void destroy() {
		
	}

}
