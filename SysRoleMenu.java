package com.zhhub.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ZhongYongqi
 * @since 2022-12-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menuId;


}
