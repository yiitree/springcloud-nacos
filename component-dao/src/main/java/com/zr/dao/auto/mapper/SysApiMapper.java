package com.zr.dao.auto.mapper;

import com.zr.dao.auto.model.SysApi;
import com.zr.dao.auto.model.SysApiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysApiMapper {
    long countByExample(SysApiExample example);

    int deleteByExample(SysApiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysApi record);

    int insertSelective(SysApi record);

    List<SysApi> selectByExample(SysApiExample example);

    SysApi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysApi record, @Param("example") SysApiExample example);

    int updateByExample(@Param("record") SysApi record, @Param("example") SysApiExample example);

    int updateByPrimaryKeySelective(SysApi record);

    int updateByPrimaryKey(SysApi record);
}
