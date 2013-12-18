package com.ndc888.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * URL工具类
 * 
 * @author tianhuaichuan
 * 
 */
public abstract class UrlUtils {

	public static String getActionUrl(String url, String encodeUrl) {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		String action_url = url + (url.indexOf("?") == -1 ? "?" : "&")
				+ (StringUtils.isEmpty(encodeUrl) ? "" : "redirect_url=" + encodeUrl);
		return action_url;
	}

	public static String getServerUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() == 80 ? "" : ":" + String.valueOf(request.getServerPort()));
	}
}
