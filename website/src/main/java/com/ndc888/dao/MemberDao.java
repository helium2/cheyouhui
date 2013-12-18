package com.ndc888.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ndc888.dao.mapper.MemberPoMapper;
import com.ndc888.dao.mapper.NdcIdPoMapper;
import com.ndc888.dao.mapper.PasswordPoMapper;
import com.ndc888.dao.po.MemberPo;
import com.ndc888.dao.po.MemberPoExample;
import com.ndc888.dao.po.NdcIdPo;
import com.ndc888.dao.po.PasswordPo;
import com.ndc888.dao.po.PasswordPoExample;
import com.ndc888.eo.NdcidStatus;
import com.ndc888.eo.UserStatus;
import com.ndc888.utils.SecurityUtils;
import com.ndc888.vo.UserInfo;


public class MemberDao {
	
	@Autowired
	private NdcIdPoMapper ndcIdPoMapper;
	@Autowired
	private PasswordPoMapper passwordPoMapper;
	@Autowired
	private MemberPoMapper memberPoMapper;

	
	
	@Transactional(value="ndc")
	public MemberPo insert(UserInfo userInfo) {
		MemberPo memberPo = new MemberPo();
		PasswordPo passwordPo = new PasswordPo();
		NdcIdPo ndcIdPo = new NdcIdPo();
		
		try {
			PropertyUtils.copyProperties(memberPo, userInfo);
			PropertyUtils.copyProperties(passwordPo, userInfo);
		} catch (Exception e) {
			return null;
		}
		
		ndcIdPo.setNdcid(userInfo.getNdcid());
		ndcIdPo.setStatus(NdcidStatus.Temp.getCode().byteValue());
		
		
		Date now = new Date();
		ndcIdPoMapper.insert(ndcIdPo);
		
		memberPo.setCreatedTime(now);
		memberPo.setUpdatedTime(now);
		memberPo.setStatus(UserStatus.New.name());
		memberPoMapper.insert(memberPo);
		
		passwordPo.setMemberid(memberPo.getId());
		passwordPo.setCreatedTime(now);
		passwordPo.setUpdatedTime(now);
		passwordPo.setPasswd(SecurityUtils.sha1(passwordPo.getPasswd()));
		passwordPoMapper.insert(passwordPo);
		
		return memberPo;
	}
	
	
	public MemberPo selectByConditions(String mobile) {
		MemberPoExample example = new MemberPoExample();
		
		example.createCriteria().andMobileEqualTo(mobile.trim());
		List<MemberPo> list = getMemberPoMapper().selectByExample(example);
		
		if(CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public  MemberPo checkPasswd(String mobile, String passwd ) {
		MemberPo memberPo = selectByConditions(mobile);
		if(memberPo != null) {
			PasswordPoExample example = new PasswordPoExample();
			example.createCriteria().andMemberidEqualTo(memberPo.getId());
			List<PasswordPo> list = getPasswordPoMapper().selectByExample(example);
			if(!CollectionUtils.isEmpty(list)) {
				PasswordPo passwdPo = list.get(0);
				if(SecurityUtils.sha1(passwd).equals(passwdPo.getPasswd())) {
					return memberPo;
				}
			}
		}
		
		return null;
		
	}
	
	
	
	
	
	public NdcIdPoMapper getNdcIdPoMapper() {
		return ndcIdPoMapper;
	}
	public PasswordPoMapper getPasswordPoMapper() {
		return passwordPoMapper;
	}
	public MemberPoMapper getMemberPoMapper() {
		return memberPoMapper;
	}
	

}
