package mms.util;



public class EncryptUtils {

	 /** 
     * 基本加密处理 
     * @param msg 
     * @param salt 
     * @return 
     */  
    public static String encrypt(String msg, String salt) {  
    	 PasswordEncoder encoderSha = new PasswordEncoder(salt, "MD5");
		 return  encoderSha.encode(msg);
    }  
    
    public static void main(String[] args){
    	System.out.println(encrypt("123456","11111aaaaa1"));
    }
}
