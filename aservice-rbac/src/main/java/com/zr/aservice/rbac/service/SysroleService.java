package com.zr.aservice.rbac.service;

import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.dao.auto.mapper.SysRoleMapper;
import com.zr.dao.auto.mapper.SysUserRoleMapper;
import com.zr.dao.auto.model.SysRole;
import com.zr.dao.auto.model.SysRoleExample;
import com.zr.dao.auto.model.SysUserRoleExample;
import com.zr.dao.rbac.mapper.SystemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysroleService {

  @Resource
  private SysRoleMapper sysRoleMapper;
  @Resource
  private SystemMapper systemMapper;
  @Resource
  private SysUserRoleMapper sysUserRoleMapper;

  public List<SysRole> queryRoles(String roleLik) {
      SysRoleExample sysRoleExample = new SysRoleExample();
      if(!StringUtils.isEmpty(roleLik)){
        sysRoleExample.or().andRoleCodeLike("%"+ roleLik+ "%");
        sysRoleExample.or().andRoleDescLike("%"+ roleLik+ "%");
        sysRoleExample.or().andRoleNameLike("%"+ roleLik+ "%");
      }
      return sysRoleMapper.selectByExample(sysRoleExample);
  }
  public void updateRole(SysRole sysrole){
    if(sysrole.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysRoleMapper.updateByPrimaryKeySelective(sysrole);
    }
  }
  public void addRole(SysRole sysrole){
    sysRoleMapper.insert(sysrole);
  }
  public void deleteRole(Integer roleId){
    sysRoleMapper.deleteByPrimaryKey(roleId);
  }

  public Map<String,Object> getRolesAndChecked(Integer userId){
    Map<String,Object> ret = new HashMap<>();
    if(userId == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "获取角色信息必需要：用户id参数");
    }else{
      ret.put("checkedRoleIds",systemMapper.getCheckedRoleIds(userId));
      ret.put("roleDatas",sysRoleMapper.selectByExample(null));
    }
    return ret;
  }


  @Transactional
  public void saveCheckedKeys(Integer userId,List<Integer> checkedIds){

    SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
    sysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
    sysUserRoleMapper.deleteByExample(sysUserRoleExample);

    systemMapper.insertUserRoleIds(userId,checkedIds);
  }
}
