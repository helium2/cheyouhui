package com.ndc888.mobile.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ndc888.constants.CommonConstants;
import com.ndc888.dao.MemberDao;
import com.ndc888.dao.mapper.MemberPoMapper;
import com.ndc888.dao.po.MemberPo;
import com.ndc888.form.RegFormM;
import com.ndc888.form.RegFormO;
import com.ndc888.resp.ActionResponse;
import com.ndc888.vo.NdcUser;
import com.ndc888.vo.UserInfo;

@Controller
@RequestMapping(value="/mobile")
public class MMainController {
	
	private static Logger logger = LoggerFactory.getLogger(MMainController.class);
	
	@Autowired
	private MemberPoMapper memberPoMapper;
	
	@Autowired
	private MemberDao memberDao; 
	
	@RequestMapping(value="/index")
	public String main(HttpServletRequest request) {
		
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		if(user != null) {
			request.setAttribute("user", user);
		}
		
		return "mobile/index";
	}
	
	@RequestMapping(value="/sign_check_available", method = RequestMethod.POST)
	@ResponseBody
	public Object check_available(HttpServletRequest request){
		String mobile = request.getParameter("mobile");
		
		MemberPo po = memberDao.selectByConditions(mobile);
		
		logger.info("check avaiable: {}", mobile );
		
		return (po == null);
	}
	
	@RequestMapping(value="/sign_check_exist", method = RequestMethod.POST)
	@ResponseBody
	public Object check_exist(HttpServletRequest request){
		String mobile = request.getParameter("mobile");
		
		MemberPo po = memberDao.selectByConditions(mobile);
		
		logger.info("check avaiable: {}", mobile );
		
		return (po != null);
	}
	
	@RequestMapping(value="/sign")
	public String sign(HttpServletRequest request) {
		
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		if(user != null) {
			return "mobile/index";
		}
		
		return "mobile/sign";
	}
	
	
	
	
	@RequestMapping(value="/sign_sbmt", method = RequestMethod.POST)
	@ResponseBody
	public Object sign_apply(HttpServletRequest request, @Validated RegFormM regForm) {
		ActionResponse resp = new ActionResponse();
		
		UserInfo userInfo = new UserInfo();
		try {
			PropertyUtils.copyProperties(userInfo, regForm);
		} catch (Exception e) {
			resp.setSuccess(false);
			return resp;
		}
		//set userInfo in session
		request.getSession().setAttribute(CommonConstants.SESSION_REG_USER_INFO, userInfo);
		
		resp.setSuccess(true);
		resp.setRedirect(request.getContextPath() + "/mobile/sign_more");
		
		return resp;
	}
	
	@RequestMapping(value="/sign_more_sbmt", method = RequestMethod.POST)
	@ResponseBody
	public Object sign_more_apply(HttpServletRequest request, @Validated RegFormO regForm) {
		ActionResponse resp = new ActionResponse();
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(CommonConstants.SESSION_REG_USER_INFO);
		if(userInfo == null) {
			resp.setSuccess(false);
			resp.setRedirect(request.getContextPath() + "/mobile/sign");
			resp.setMessage("Bad Request!");
			return resp;
		}
		
		try {
			PropertyUtils.copyProperties(userInfo, regForm);
		} catch (Exception e) {
			resp.setSuccess(false);
			return resp;
		}
		
		// insert it in db
		MemberPo memberPo = null;
		try {
			memberPo = memberDao.insert(userInfo);
		} catch (Exception e) {
			logger.error("",e);
			resp.setSuccess(false);
			resp.setMessage("系统繁忙，请稍候再试！");
			return resp;
		}
		
		if(memberPo == null) {
			logger.error("insert memberPo failed!" + JSON.toJSONString(userInfo));
			resp.setSuccess(false);
			resp.setMessage("系统繁忙，请稍候再试！");
			return resp;
		}
		
		NdcUser user = new NdcUser();
		try {
			PropertyUtils.copyProperties(user, memberPo);
			user.setUserId(memberPo.getId());
		} catch (Exception e) {
		}
		
		request.getSession().setAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER, user);

		resp.setSuccess(true);
		resp.setRedirect(request.getContextPath() + "/mobile/index");
		
		return resp;
	}
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		if(user != null) {
			return "mobile/index";
		}
		
		
		return "mobile/login";
	}
	
	@RequestMapping(value="/login_sbmt", method = RequestMethod.POST)
	@ResponseBody
	public Object login_apply(HttpServletRequest request) {
		ActionResponse resp = new ActionResponse();
		
		String url = (String) request.getAttribute("redirect_url");
        if (StringUtils.isEmpty(url)) {
            url = request.getContextPath();
            if(StringUtils.isBlank(url)) {
            	url = "/";
            }
        }
		
		String mobile = request.getParameter("mobile");
		String passwd = request.getParameter("passwd");
		if(mobile == null && passwd == null) {
			resp.setMessage("手机号或者密码不能为空");
			resp.setSuccess(false);
			return resp;
		}
		
		MemberPo memberPo = memberDao.checkPasswd(mobile, passwd);
		if(memberPo == null) {
			resp.setMessage("手机号或者密码有误");
			resp.setSuccess(false);
			return resp;
		}
		
		NdcUser user = new NdcUser();
		try {
			PropertyUtils.copyProperties(user, memberPo);
			user.setUserId(memberPo.getId());
		} catch (Exception e) {
		}
		
		request.getSession().setAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER, user);

		resp.setSuccess(true);
		resp.setRedirect(url);
		return resp;
	}
	
	@RequestMapping(value="/sign_more")
	public String sign_more(HttpServletRequest request) {
		
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(CommonConstants.SESSION_REG_USER_INFO);
		if(userInfo == null) {
			return "mobile/sign";
		}
		return "mobile/sign_more";
	}
	
	@RequestMapping(value="/info")
	public String info(HttpServletRequest request) {
		
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		
		MemberPo memberPo = memberDao.getMemberPoMapper().selectByPrimaryKey(user.getUserId());
		UserInfo  userInfo = new UserInfo();
		try {
			PropertyUtils.copyProperties(userInfo, memberPo);
		} catch (Exception e) {
		}
		request.setAttribute("user", userInfo);
		return "mobile/info";
	}
	
	
	@RequestMapping(value="/modify")
	public String modify(HttpServletRequest request) {
		
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		
		MemberPo memberPo = memberDao.getMemberPoMapper().selectByPrimaryKey(user.getUserId());
		UserInfo  userInfo = new UserInfo();
		try {
			PropertyUtils.copyProperties(userInfo, memberPo);
		} catch (Exception e) {
		}
		request.setAttribute("user", userInfo);
		
		
		return "mobile/modify";
	}
	
	@RequestMapping(value="/modify_sbmt")
	@ResponseBody
	public Object modify_apply(HttpServletRequest request, @Validated RegFormO regForm) {
		ActionResponse resp = new ActionResponse();
		NdcUser user = (NdcUser) request.getSession().getAttribute(CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
		
		MemberPo memberPo = new MemberPo();
		memberPo.setId(user.getUserId());
		try {
			PropertyUtils.copyProperties(memberPo, regForm);
		} catch (Exception e) {
			resp.setSuccess(false);
			return resp;
		}
		
		// insert it in db
		int count = 0;
		try {
			count = memberDao.getMemberPoMapper().updateByPrimaryKeySelective(memberPo);
		} catch (Exception e) {
			logger.error("",e);
			resp.setSuccess(false);
			resp.setMessage("系统繁忙，请稍候再试！");
			return resp;
		}
		
		if(count != 0) {
			resp.setSuccess(true);
			resp.setRedirect(request.getContextPath() + "/mobile/info");
		} else {
			resp.setSuccess(false);
		}
		
		return resp;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:/mobile/index";
	}
	
	
	
	
}
