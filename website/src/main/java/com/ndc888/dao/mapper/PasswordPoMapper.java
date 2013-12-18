package com.ndc888.dao.mapper;

import com.ndc888.dao.po.PasswordPo;
import com.ndc888.dao.po.PasswordPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PasswordPoMapper {
    int countByExample(PasswordPoExample example);

    int deleteByExample(PasswordPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PasswordPo record);

    int insertSelective(PasswordPo record);

    List<PasswordPo> selectByExample(PasswordPoExample example);

    PasswordPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PasswordPo record, @Param("example") PasswordPoExample example);

    int updateByExample(@Param("record") PasswordPo record, @Param("example") PasswordPoExample example);

    int updateByPrimaryKeySelective(PasswordPo record);

    int updateByPrimaryKey(PasswordPo record);
}