package com.ndc888.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.ndc888.dao.mapper.MemberPoMapper;
import com.ndc888.dao.po.MemberPo;

@ContextConfiguration({"/context-env.xml", "/context-dao.xml" })
public class MemberDaoTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private MemberPoMapper memberPoMapper;

	@Test
	public void testInsert() {
		MemberPo po = new MemberPo();
		po.setCreatedTime(new Date());
		po.setUpdatedTime(new Date());
		po.setNdcid("111");
		po.setNickname("wangzi");
		po.setRealname("wanghai");
		memberPoMapper.insert(po);
	}
}
