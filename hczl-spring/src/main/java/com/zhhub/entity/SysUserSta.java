package com.zhhub.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhongYongqi
 * @since 2023-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserSta extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String role;


}
