package mms.util;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ResponseBuilder {
	
	
	public static String build(Object o) {
		return JsonConverter.obj2Str(o);
	}
	
	/**
	 * 将用户自定义的类型转换为 JSONObject
	 * 每个属性必须有对应的get方法
	 * 此方法用于生产对客户端的响应
	 * @param o
	 * @return
	 */
	public static JSONObject buildResponse(Object o) {
		String str = JsonConverter.obj2Str(o);
		return buildResponse(str);
	}
	
	public static JSONObject buildResponse(String str) {
		JSONObject json = null;
		try {
			json =  new JSONObject(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
}
