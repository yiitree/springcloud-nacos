package com.zr.bservice.gateway.jwt.jpa;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sys_user")
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    @Column
    private String password;

    @Column
    private Integer orgId;

    @Column
    private Boolean enabled;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private Date createTime;
}
