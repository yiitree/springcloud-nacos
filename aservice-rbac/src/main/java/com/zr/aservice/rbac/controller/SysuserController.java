package com.zr.aservice.rbac.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageInfo;
import com.zr.aservice.rbac.service.SysuserService;
import com.zr.common.exception.AjaxResponse;
import com.zr.dao.auto.model.SysUser;
import com.zr.dao.rbac.model.SysUserOrg;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


@RestController
@RequestMapping("/sysuser")
public class SysuserController {
    @Resource
    private SysuserService sysuserService;

    @GetMapping(value = "/info")
    public SysUser info(@RequestParam("username") String username) {

        return sysuserService.getUserByUserName(username);
    }

    @PostMapping("/query")
    public PageInfo<SysUserOrg> query(@RequestParam("orgId") Integer orgId,
                                      @RequestParam("username") String username,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("email") String email,
                                      @RequestParam("enabled") Boolean enabled,
                                      @RequestParam("createStartTime") Date createStartTime,
                                      @RequestParam("createEndTime") Date createEndTime,
                                      @RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize) {

        return sysuserService.queryUser(orgId, username, phone, email, enabled,
                createStartTime, createEndTime,
                pageNum, pageSize);
    }

    @PostMapping(value = "/update")
    public AjaxResponse update(@RequestBody SysUser sysUser) {
        sysuserService.updateUser(sysUser);
        return AjaxResponse.success("更新用户成功！");
    }

    @PostMapping(value = "/add")
    public AjaxResponse add(@RequestBody SysUser sysUser) {
        sysuserService.addUser(sysUser);
        return AjaxResponse.success("新增用户成功！");
    }

    @PostMapping(value = "/delete")
    public AjaxResponse delete(@RequestParam Integer userId) {
        sysuserService.deleteUser(userId);
        return AjaxResponse.success("删除用户成功!");
    }

    @PostMapping(value = "/pwd/isdefault")
    public Boolean isdefault(@RequestParam String username) {

        return sysuserService.isdefault(username);
    }

    @PostMapping(value = "/pwd/change")
    public AjaxResponse pwdchange(@RequestParam String username,
                                  @RequestParam String oldPass,
                                  @RequestParam String newPass) {
        sysuserService.changePwd(username, oldPass, newPass);
        return AjaxResponse.success("修改密码成功!");
    }

    // 限流处理
    @SentinelResource(
            // 资源名称，必需项。需要注意的是：如果controller的mappingUrl是"/sysuser/pwd/reset",那么SentinelResource的value属性就不要再配置成"/sysuser/pwd/reset"。会导致异常等处理逻辑偶尔失效。
            value = "sysuserPwdReset",
            // 拦截后执行类
            blockHandlerClass = {SysuserControllerHandler.class},
            // 拦截类中执行的方法名
            blockHandler = "pwdresetBlockHandler",


            fallbackClass = {SysuserControllerHandler.class},
            //fallback = "pwdresetFallback",
            defaultFallback = "defaultFallback")
    @PostMapping(value = "/pwd/reset")
    public AjaxResponse pwdreset(@RequestParam Integer userId) {
        sysuserService.pwdreset(userId);
        return AjaxResponse.success("重置密码成功!");
    }

//    // 拦截方法
//    public static AjaxResponse pwdresetBlockHandler(@RequestParam Integer userId, BlockException blockException) {
//        return AjaxResponse.error(CustomExceptionType.SYSTEM_ERROR, "尊敬的客户您好，系统服务繁忙，请稍后再试!");
//    }
}
