package com.zr.aservice.rbac.controller;


import com.zr.aservice.rbac.model.RoleCheckedIds;
import com.zr.aservice.rbac.model.SysApiNode;
import com.zr.aservice.rbac.service.SysapiService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysApi;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysapi")
public class SysapiController {

  @Resource
  private SysapiService sysapiService;

  @PostMapping(value = "/tree")
  public List<SysApiNode> tree(@RequestParam("apiNameLike") String apiNameLike,
                               @RequestParam("apiStatus") Boolean apiStatus) {

    return sysapiService.getApiTreeById(apiNameLike, apiStatus);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysApi sysApi) {
    sysapiService.updateApi(sysApi);
    return AjaxResponse.success("更新接口配置成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysApi sysApi) {
    sysapiService.addApi(sysApi);
    return AjaxResponse.success("新增接口配置成功！");
  }


  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestBody SysApi sysApi) {
    sysapiService.deleteApi(sysApi);
    return AjaxResponse.success("删除接口配置成功!");
  }

  @PostMapping(value = "/checkedtree")
  public Map<String,Object> checkedtree(@RequestParam Integer roleId) {
    Map<String,Object> ret = new HashMap<>();
    ret.put("tree",sysapiService.getApiTreeById("",null));
    ret.put("expandedKeys",sysapiService.getExpandedKeys());
    ret.put("checkedKeys",sysapiService.getCheckedKeys(roleId));
    return ret;
  }

  @PostMapping(value = "/savekeys")
  public AjaxResponse savekeys(@RequestBody RoleCheckedIds roleCheckedIds) {
    sysapiService.saveCheckedKeys(roleCheckedIds.getRoleId(),roleCheckedIds.getCheckedIds());
    return AjaxResponse.success("保存接口权限成功!");
  }
}
