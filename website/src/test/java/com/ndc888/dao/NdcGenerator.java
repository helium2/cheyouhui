package com.ndc888.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.ndc888.dao.mapper.MemberPoMapper;
import com.ndc888.dao.mapper.NdcIdPoMapper;
import com.ndc888.dao.po.MemberPo;
import com.ndc888.dao.po.NdcIdPo;

@ContextConfiguration({"/context-env.xml", "/context-dao.xml" })
public class NdcGenerator extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private NdcIdPoMapper ndcIdMapper;

	@Test
	public void testInsert() {
		NdcIdPo po = new NdcIdPo();
		
		for(int i=0;i<=20;i++) {
			po.setNdcid(String.format("0%02d",i ));
			po.setStatus(Integer.valueOf(2).byteValue());
			ndcIdMapper.insert(po);
			System.out.println(po.getNdcid());
		}
		
		for(int i=1;i<10;i++) {
			po.setNdcid(String.format("%d%d%d",i,i,i ));
			po.setStatus(Integer.valueOf(2).byteValue());
			ndcIdMapper.insert(po);
			System.out.println(po.getNdcid());
		}
		
		for(int i=21;i<1000;i++) {
			int gewei,shiwei,baiwei, l;
			gewei = i%10;
			l = i / 10;
			shiwei = l%10;
			l = l /10;
			baiwei = l;
			
			if(gewei == 4 || shiwei == 4 || baiwei == 4) {
				if(i == 444) continue;
				po.setNdcid(String.format("%d%d%d",baiwei,shiwei,gewei ));
				po.setStatus(Integer.valueOf(3).byteValue());
				ndcIdMapper.insert(po);
				System.out.println(po.getNdcid());
			}
			
		}
	}
}
