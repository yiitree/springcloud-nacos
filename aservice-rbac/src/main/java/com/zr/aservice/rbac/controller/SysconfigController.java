package com.zr.aservice.rbac.controller;


import com.zr.aservice.rbac.config.DbLoadSysConfig;
import com.zr.aservice.rbac.service.SysconfigService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysConfig;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sysconfig")
public class SysconfigController {

  @Resource
  private DbLoadSysConfig dbLoadSysConfig;

  @PostMapping(value = "/all")
  public List<SysConfig> all() {
    return dbLoadSysConfig.getSysConfigList();
  }

  @PostMapping(value = "/refresh")
  public List<SysConfig> refresh() {
    dbLoadSysConfig.refresh();
    return dbLoadSysConfig.getSysConfigList();
  }

  @Resource
  private SysconfigService sysconfigService;

  @PostMapping(value = "/query")
  public List<SysConfig> query(@RequestParam("configLike") String configLike) {
    return sysconfigService.queryConfigs(configLike);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysConfig sysConfig) {
    sysconfigService.updateConfig(sysConfig);
    return AjaxResponse.success("更新配置成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysConfig sysConfig) {
    sysconfigService.addConfig(sysConfig);
    return AjaxResponse.success("新增配置成功！");
  }

  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestParam Integer configId) {
    sysconfigService.deleteConfig(configId);
    return AjaxResponse.success("删除配置成功!");
  }

}
