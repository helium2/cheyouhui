package com.ndc888.utils;

import java.security.MessageDigest;

public class SecurityUtils {
	
	public static String sha1(String raw) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (Exception e) {
			return null;
		}
		
		byte[] mdbytes = md.digest(raw.getBytes());
		
		//convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		
		return sb.toString();
	}

}
