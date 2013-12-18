package com.ndc888.mobile.ctrl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ndc888.dao.mapper.NdcIdPoMapper;
import com.ndc888.dao.po.NdcIdPo;
import com.ndc888.dao.po.NdcIdPoExample;
import com.ndc888.dao.po.NdcIdPoExample.Criteria;
import com.ndc888.resp.ActionResponse;



@Controller
@RequestMapping(value="/mobile")
public class NdcIdController {
	
	@Autowired
	private NdcIdPoMapper ndcIdPoMapper;
	
	@RequestMapping(value="/sign_getndclist")
	@ResponseBody
	public Object main(HttpServletRequest request) {
		ActionResponse resp = new ActionResponse();
		
		String start = request.getParameter("start");
		if(StringUtils.isEmpty(start)) {
			resp.setSuccess(false);
			return resp;
		}
		
		NdcIdPoExample example = new NdcIdPoExample();
		Criteria criteria = example.createCriteria();
		criteria.andNdcidLike(start + "%");
		List<NdcIdPo> list = ndcIdPoMapper.selectByExample(example);
		Set<String> notAvaiable = new HashSet<String>();
		for(NdcIdPo po: list) {
			notAvaiable.add(po.getNdcid());
		}
		List<String> all = new ArrayList<String>();
		for(int i=0;i<100;i++) {
			String id = String.format(start+"%02d", i);
			if(!notAvaiable.contains(id)) {
				all.add(id);
			}
			
		}
		resp.setMessage(all);
		resp.setSuccess(true);
		return resp;
	}
}
