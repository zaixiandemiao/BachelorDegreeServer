package mms.filter;



import java.io.IOException;
import java.security.Principal;
import java.util.Map;


import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.JWTVerifier;


import mms.util.SysConstants;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	private static JWTVerifier jwtVerifier= new JWTVerifier(SysConstants.AUTH0_CLIENT_SECRET);

    @Override
    public void filter(ContainerRequestContext  requestContext) throws IOException {

    	System.out.println("authentication filter ");
    	
        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        System.out.println(HttpHeaders.AUTHORIZATION+ " : " + authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).replace('"', ' ').trim();

        try {

        	Map<String, Object> decoded = jwtVerifier.verify(token);
        	
        	final String username = (String) decoded.get("userid");
        	
        	
        	 requestContext.setSecurityContext(new SecurityContext() {

        	        @Override
        	        public Principal getUserPrincipal() {

        	            return new Principal() {

        	                @Override
        	                public String getName() {
        	                    return username;
        	                }
        	            };
        	        }

        	        @Override
        	        public boolean isUserInRole(String role) {
        	            return true;
        	        }

        	        @Override
        	        public boolean isSecure() {
        	            return false;
        	        }

        	        @Override
        	        public String getAuthenticationScheme() {
        	            return null;
        	        }
        	    });
            
        	
            
            System.out.println(decoded.get("userid")+" "+decoded + " current" + System.currentTimeMillis() / 1000);
            

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

	
}
