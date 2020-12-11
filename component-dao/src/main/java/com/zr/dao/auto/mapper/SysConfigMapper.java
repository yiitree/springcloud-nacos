package com.zr.dao.auto.mapper;

import com.zr.dao.auto.model.SysConfig;
import com.zr.dao.auto.model.SysConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysConfigMapper {
    long countByExample(SysConfigExample example);

    int deleteByExample(SysConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    List<SysConfig> selectByExample(SysConfigExample example);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    int updateByExample(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}
