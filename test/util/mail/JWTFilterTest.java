package util.mail;

import org.junit.Test;

public class JWTFilterTest {
	
	@Test
	public void testExp() {
		System.out.println((1460894377 - System.currentTimeMillis() /1000L ) / 60);
	}

	@Test
	public void testMatch() {
		String s1 = "物联网1201班-王嘉龙-0909121208";
		String s2 = "^[\u4e00-\u9fa5]+[0-9]{4}班-[\u4e00-\u9fa5]+-[0-9]{10}$";
		
		if(s1.matches(s2)) {
			System.out.println("matched");
		}
	}
	
}
