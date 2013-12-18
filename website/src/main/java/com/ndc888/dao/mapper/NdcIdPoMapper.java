package com.ndc888.dao.mapper;

import com.ndc888.dao.po.NdcIdPo;
import com.ndc888.dao.po.NdcIdPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NdcIdPoMapper {
    int countByExample(NdcIdPoExample example);

    int deleteByExample(NdcIdPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NdcIdPo record);

    int insertSelective(NdcIdPo record);

    List<NdcIdPo> selectByExample(NdcIdPoExample example);

    NdcIdPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NdcIdPo record, @Param("example") NdcIdPoExample example);

    int updateByExample(@Param("record") NdcIdPo record, @Param("example") NdcIdPoExample example);

    int updateByPrimaryKeySelective(NdcIdPo record);

    int updateByPrimaryKey(NdcIdPo record);
}