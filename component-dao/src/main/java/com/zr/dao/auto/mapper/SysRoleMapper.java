package com.zr.dao.auto.mapper;

import com.zr.dao.auto.model.SysRole;
import com.zr.dao.auto.model.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
