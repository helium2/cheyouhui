package com.ndc888.ctrl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ndc888.constants.CommonConstants;
import com.ndc888.utils.SecurityUtils;

@Controller
public class GeneralController {
	
	private static Logger logger = LoggerFactory.getLogger(GeneralController.class);
	
	@RequestMapping(value="/index")
	public String index_jsp(HttpServletRequest request){
		//request.setAttribute("liming", "黎明你好");
		//logger.info("index");
		return "mobile/index";
	}
	
	@RequestMapping(value="/wx")
	public String  access(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//System.out.println(model);
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		if(signature == null || timestamp == null || nonce == null ) {
			request.setAttribute("echostr", "bad request");
			return "access";
		}
		
		char [] cbuf = new char[request.getContentLength()]; 
		
		request.getReader().read(cbuf);
		String body = new String(cbuf);
		System.out.println(body);
		
		List<String> list = new ArrayList<String>();
		list.add(CommonConstants.TOKEN);
		list.add(nonce);
		list.add(timestamp);
		
		Collections.sort(list);
		String digest = SecurityUtils.sha1(StringUtils.join(list.toArray()));
		if(signature != null && signature.equals(digest)) {
			request.setAttribute("echostr", echostr);
		} else {
			request.setAttribute("echostr", "bad request");
			
		}
		
		return "access";
		
	}
}
