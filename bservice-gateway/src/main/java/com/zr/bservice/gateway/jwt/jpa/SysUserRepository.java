package com.zr.bservice.gateway.jwt.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {

  //注意这个方法的名称，jPA会根据方法名自动生成SQL执行
  SysUser findByUsername(String username);

}
