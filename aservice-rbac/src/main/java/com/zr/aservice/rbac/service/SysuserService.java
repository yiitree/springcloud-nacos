package com.zr.aservice.rbac.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zr.aservice.rbac.config.DbLoadSysConfig;
import com.zr.aservice.rbac.feign.SmsService;
import com.zr.common.exception.CustomException;
import com.zr.common.exception.CustomExceptionType;
import com.zr.dao.auto.mapper.SysUserMapper;
import com.zr.dao.auto.model.SysUser;
import com.zr.dao.auto.model.SysUserExample;
import com.zr.dao.rbac.mapper.SystemMapper;
import com.zr.dao.rbac.model.SysUserOrg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

// 配置热刷新
@RefreshScope
@Service
public class SysuserService {

    @Value("${user.init.password}")
    private String defaultPwd;

    /**
     * 发送短信
     */
    @Resource
    private SmsService smsService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SystemMapper systemMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private DbLoadSysConfig dbLoadSysConfig;

    @SentinelResource("getUserByUserName")
    public SysUser getUserByUserName(String userName){
        if(!StringUtils.isEmpty(userName)){
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUsernameEqualTo(userName);

            List<SysUser> querys =
              sysUserMapper.selectByExample(sysUserExample);

            if(querys.size() > 0){
                //因为数据需要返回给前端，所以置空密码
                querys.get(0).setPassword("");
            }
            return querys.size() > 0 ? querys.get(0):null;
        }else{
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
                    "查询参数用户名不存在");
        }
    }

    public PageInfo<SysUserOrg> queryUser(Integer orgId ,
                                          String username ,
                                          String phone,
                                          String email,
                                          Boolean enabled,
                                          Date createStartTime,
                                          Date createEndTime,
                                          Integer pageNum,
                                          Integer pageSize){
      PageHelper.startPage(pageNum, pageSize);
      List<SysUserOrg> sysUserOrgs =  systemMapper.selectUser(orgId,username,phone,email,enabled,
                                    createStartTime,
                                    createEndTime);
      return PageInfo.of(sysUserOrgs);

    }


  public void updateUser(SysUser sysuser){
    if(sysuser.getId() == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "修改操作必须带主键");
    }else{
      sysUserMapper.updateByPrimaryKeySelective(sysuser);
    }
  }

  public void addUser(SysUser sysuser){
    sysuser.setPassword(passwordEncoder.encode(
      dbLoadSysConfig.getConfigItem("user.init.password")
    ));
    sysuser.setCreateTime(new Date());  //创建时间
    sysuser.setEnabled(true); //新增用户激活
    sysUserMapper.insert(sysuser);
  }

  public void deleteUser(Integer userId){
    sysUserMapper.deleteByPrimaryKey(userId);
  }

  public void pwdreset(Integer userId){
    if(userId == null){
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "重置密码必须带主键");
    }else{

//        SysUser sysUser = new SysUser();
//      sysUser.setId(userId);

//      sysUser.setPassword(passwordEncoder.encode(
//        dbLoadSysConfig.getConfigItem("user.init.password")
//      ));
//
//      sysUserMapper.updateByPrimaryKeySelective(sysUser);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
//        String defaultPwd = dbLoadSysConfig.getConfigItem("user.init.password");
        sysUser.setPassword(passwordEncoder.encode(defaultPwd));

        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        // 修改成发送短信
        smsService.send(sysUser.getPhone(),"您好，管理员已经将您的密码重置为" + defaultPwd);
    }
  }

  public Boolean isdefault(String username){
    SysUserExample sysUserExample = new SysUserExample();
    sysUserExample.createCriteria().andUsernameEqualTo(username);
    List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
    //判断数据库密码是否是默认密码
    return passwordEncoder.matches(
      dbLoadSysConfig.getConfigItem("user.init.password"),
      sysUsers.get(0).getPassword());
  }

  public void changePwd(String username,String oldPass,String newPass){
    SysUserExample sysUserExample = new SysUserExample();
    sysUserExample.createCriteria().andUsernameEqualTo(username);
    List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
    //判断旧密码是否正确
    boolean isMatch = passwordEncoder.matches(oldPass,sysUsers.get(0).getPassword());

    if(isMatch){
      SysUser sysUser = new SysUser();
      sysUser.setId(sysUsers.get(0).getId());
      sysUser.setPassword(passwordEncoder.encode(newPass));
      sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }else{
      throw new CustomException(CustomExceptionType.USER_INPUT_ERROR,
        "原密码输入错误，请确认后重新输入！");
    }

  }

}
