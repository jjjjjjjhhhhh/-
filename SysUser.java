package com.zhhub.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;

    private Timestamp created = new Timestamp(new Date().getTime());

    private String phone;

    private Integer statu;

    private String role;


}
