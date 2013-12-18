package com.ndc888.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ndc888.form.RegFormO;

@Controller
@RequestMapping(value="/user")
public class UserController {
//	private static Logger logger = LoggerFactory.getLogger(GeneralController.class);
//	
//	@RequestMapping(value="/reg")
//	public String register(HttpServletRequest request) {
//		
//		
//		return "reg_view_page1";
//	}
//	
//	
//	
//	@RequestMapping(value="/regone")
//	public String register(HttpServletRequest request) {
//		
//		
//		return "reg_view_page1";
//	}
//	
//	@RequestMapping(value="/regtwo")
//	public String register(HttpServletRequest request) {
//		
//		
//		return "reg_view_page2";
//	}
//	
//	@RequestMapping(value="/regthree")
//	public String register(HttpServletRequest request) {
//		
//		
//		return "reg_view_page3";
//	}
//	
//	@RequestMapping(value="/reg_apply", method = RequestMethod.POST)
//	public String register_apply_1(HttpServletRequest request, @ModelAttribute("regForm") RegForm regForm) {
//		
//		
//		return "reg_view_page2";
//	}
//	
//	
//	@RequestMapping(value="/reg_apply", method = RequestMethod.POST)
//	@ResponseBody
//	public String register_apply_2(HttpServletRequest request,  @ModelAttribute("regForm") RegForm regForm) {
//		
//		
//		return "reg_view_page3";
//	}
//	
//	@RequestMapping(value="/reg_apply", method = RequestMethod.POST)
//	public String register_apply_3(HttpServletRequest request,  @ModelAttribute("regForm") RegForm regForm) {
//		
//		
//		return "reg_view_page3";
//	}

}
