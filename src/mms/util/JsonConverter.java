package mms.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	public static <T> T jsonStr2Obj(String str, Class<T> cls) {
		if(str == null || "".equals(str)) {
			System.out.println("json str is null");
			str = "{ }";
		}
		
		return gson.fromJson(str, cls);
	}
	
	public static String obj2Str(Object obj) {
		return gson.toJson(obj);
	}
	
}
