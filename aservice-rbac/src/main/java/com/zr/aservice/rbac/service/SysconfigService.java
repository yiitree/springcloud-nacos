package com.zr.aservice.rbac.service;


import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.dao.auto.mapper.SysConfigMapper;
import com.zr.dao.auto.model.SysConfig;
import com.zr.dao.auto.model.SysConfigExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysconfigService {

  @Resource
  private SysConfigMapper sysConfigMapper;

  public List<SysConfig> queryConfigs(String configLik) {
      SysConfigExample sysConfigExample = new SysConfigExample();
      if(!StringUtils.isEmpty(configLik)){
        sysConfigExample.or().andParamNameLike("%"+ configLik+ "%");
        sysConfigExample.or().andParamKeyLike("%"+ configLik+ "%");
      }
      return sysConfigMapper.selectByExample(sysConfigExample);
  }

  public void updateConfig(SysConfig sysconfig){
    if(sysconfig.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysConfigMapper.updateByPrimaryKeySelective(sysconfig);
    }
  }

  public void addConfig(SysConfig sysconfig){
    sysconfig.setCreateTime(new Date());
    sysConfigMapper.insert(sysconfig);
  }

  public void deleteConfig(Integer configId){
    sysConfigMapper.deleteByPrimaryKey(configId);
  }

}
