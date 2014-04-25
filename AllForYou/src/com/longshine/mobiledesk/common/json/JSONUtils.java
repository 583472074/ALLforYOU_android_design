package com.longshine.mobiledesk.common.json;

/**
 * 
 * @author 	maxl
 * @version 1.0
 * @date 	2011-03-25
 */
public class JSONUtils {

    public static Object deserialize(String json) throws JSONException {
		JSONReader reader = new JSONReader();
		return reader.read(json);
	}
}
