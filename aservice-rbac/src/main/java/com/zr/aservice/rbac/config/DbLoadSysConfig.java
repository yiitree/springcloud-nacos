package com.zr.aservice.rbac.config;

import com.zr.dao.auto.mapper.SysConfigMapper;
import com.zr.dao.auto.model.SysConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class DbLoadSysConfig implements CommandLineRunner {

  @Resource
  private SysConfigMapper sysConfigMapper;

  private  List<SysConfig> sysConfigList;

  //根据参数key，获取参数值
  public  String getConfigItem(String paramKey){
    Optional<SysConfig> temp =  sysConfigList.stream()
      .filter(str -> str.getParamKey().equals(paramKey))
      .findFirst();

    return temp.get().getParamValue();
  }

  //应用启动加载参数配置
  @Override
  public void run(String... args) throws Exception {
    sysConfigList = sysConfigMapper.selectByExample(null);
  }

  //获取所有参数配置项
  public  List<SysConfig> getSysConfigList() {
    return sysConfigList;
  }

  //刷新参数配置项
  public void refresh(){
    sysConfigList = sysConfigMapper.selectByExample(null);
  }
}
