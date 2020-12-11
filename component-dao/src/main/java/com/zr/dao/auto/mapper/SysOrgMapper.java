package com.zr.dao.auto.mapper;

import com.zr.dao.auto.model.SysOrg;
import com.zr.dao.auto.model.SysOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrgMapper {
    long countByExample(SysOrgExample example);

    int deleteByExample(SysOrgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    List<SysOrg> selectByExample(SysOrgExample example);

    SysOrg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}
