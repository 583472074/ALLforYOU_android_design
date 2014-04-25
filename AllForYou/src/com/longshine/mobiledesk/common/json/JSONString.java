package com.longshine.mobiledesk.common.json;

/**
 * 
 * @author 	maxl
 * @version 1.0
 * @date 	2011-03-25
 */
public interface JSONString {
	/**
	 * The <code>toJSONString</code> method allows a class to produce its own JSON 
	 * serialization. 
	 * 
	 * @return A strictly syntactically correct JSON text.
	 */
	public String toJSONString();
}