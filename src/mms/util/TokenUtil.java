package mms.util;



import java.security.Principal;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;





public class TokenUtil {
	
	public static String getToken(HttpServletRequest httpRequest) throws ServletException {
    	String token = null;
    	
    	Enumeration<String> en =  httpRequest.getHeaderNames();
    	while(en.hasMoreElements()) {
    		String name = en.nextElement();
    		System.out.println(name + ":" + httpRequest.getHeader(name));
    	}
    	
        final String authorizationHeader = (String) httpRequest.getHeader("Authorization");
        System.out.println("Authorization"+authorizationHeader);
        if (authorizationHeader == null) {
            throw new ServletException("Unauthorized: No Authorization header was found");
        }

        String[] parts = authorizationHeader.split(" ");
        if (parts.length != 2) {
            throw new ServletException("Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(scheme).matches()) {
            token = credentials;
        }
        return token;
    }

	
	public static String getUserId(SecurityContext sc) {
		Principal principal = sc.getUserPrincipal();
		return principal.getName();
	}

}
