package com.zr.aservice.rbac.controller;


import com.zr.aservice.rbac.service.SysdictService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysDict;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sysdict")
public class SysdictController {

  @Resource
  private SysdictService sysdictService;

  @PostMapping(value = "/all")
  public List<SysDict> all() {
    return sysdictService.all();
  }

  @PostMapping(value = "/query")
  public List<SysDict> query(@RequestParam("dictLike") String dictLike) {
    return sysdictService.queryDicts(dictLike);
  }

  @PostMapping(value = "/update")
  public AjaxResponse update(@RequestBody SysDict sysDict) {
    sysdictService.updateDict(sysDict);
    return AjaxResponse.success("更新数据字典项成功！");
  }

  @PostMapping(value = "/add")
  public AjaxResponse add(@RequestBody SysDict sysDict) {
    sysdictService.addDict(sysDict);
    return AjaxResponse.success("新增数据字典项成功！");
  }

  @PostMapping(value = "/delete")
  public AjaxResponse delete(@RequestParam Integer dictId) {
    sysdictService.deleteDict(dictId);
    return AjaxResponse.success("删除数据字典项成功!");
  }

}
